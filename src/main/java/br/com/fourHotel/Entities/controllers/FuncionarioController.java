package br.com.fourHotel.Entities.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fourHotel.Entities.models.ClienteModel;
import br.com.fourHotel.Entities.models.FuncionarioModel;
import br.com.fourHotel.Entities.models.QuartoModel;
import br.com.fourHotel.Entities.models.ServicoModel;
import br.com.fourHotel.Entities.services.ClienteService;
import br.com.fourHotel.Entities.services.FuncionarioService;
import br.com.fourHotel.Entities.services.QuartoService;
import br.com.fourHotel.Entities.services.ServicoService;
import br.com.fourHotel.enuns.TipoFuncionario;
import br.com.fourHotel.enuns.TipoProduto;
import br.com.fourHotel.enuns.TipoQuarto;
import br.com.fourHotel.util.ClienteDados;
import br.com.fourHotel.util.FuncionarioDados;
import br.com.fourHotel.util.QuartoDados;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService fs;
	@Autowired
	private ClienteService cs;
	@Autowired
	private QuartoService qs;
	@Autowired
	private ServicoService ss;
	
	@GetMapping(path = "/home")
	public String home(Model model) {
		FuncionarioModel funcionario = FuncionarioDados.getFuncinarioLogado();
		
		TipoFuncionario cargo = TipoFuncionario.GERENTE;
		
		model.addAttribute("funcionario", funcionario);
		model.addAttribute("cargo", cargo);
		
		return "funcionario";
	}
	
	@GetMapping(path = "/alugar")
	public String alugar(Model model) {
		ClienteModel cliente = new ClienteModel();
		model.addAttribute("cliente", cliente);
		
		return "buscar";
	}
	
	@PostMapping(path = "/buscar")
	public String buscar(ClienteModel cliente) {
		ClienteModel obj = new ClienteModel();
		obj.setQuarto(new QuartoModel());
		List<QuartoModel> quartos = new ArrayList();
		try {
			obj = cs.buscarLogin(cliente);
			quartos = qs.buscarTodos();
			for(QuartoModel quarto: quartos) {
				if(quarto.getCliente() != null) {
					if(quarto.getCliente().getIdUsuario() == obj.getIdUsuario()) {
						obj.setQuarto(quarto);
					}
				}
			}
			ClienteDados.setClienteLogado(obj);
			return "redirect:../funcionario/quartos";
		} catch (AccountNotFoundException e) {
			return "redirect:../funcionario/home";
		}
	}
	
	@GetMapping(path = "/quartos")
	public String quartos() {
		ClienteModel cliente = ClienteDados.getClienteLogado();

		if(cliente.getQuarto() != null) {
			return "redirect:./home";
		}else {
			return "redirect:../quarto/listaf";
		}
	}
	
	@GetMapping(path = "/pedir")
	public String pedir(Model model) {
		QuartoModel quarto = new QuartoModel();
		model.addAttribute("quarto", quarto);
		
		return "buscarquarto";
	}
	
	@PostMapping(path = "/buscarquarto")
	public String buscarQuarto(QuartoModel quarto) {
		QuartoModel obj = new QuartoModel();
		
		try {
			obj = qs.buscarPorNumero(quarto.getNumeroQuarto());
			QuartoDados.setQuartoSelecionado(quarto);
			return "redirect:../servico/produtosf";
		} catch (Exception e) {
			return "redirect:../funcionario/home";
		}
	}
	
	@GetMapping(path = "/encerrar")
	public String encerrar(Model model) {
		QuartoModel quarto = new QuartoModel();
		model.addAttribute("quarto", quarto);
		
		return "buscarquartoencerramento";
	}
	
	@PostMapping(path = "/buscarquartoencerramento")
	public String buscarQuartoEncerramento(QuartoModel quarto) {
		QuartoModel obj = new QuartoModel();
		
		try {
			obj = qs.buscarPorNumero(quarto.getNumeroQuarto());
			QuartoDados.setQuartoSelecionado(quarto);
			return "redirect:../quarto/encerramento";
		} catch (Exception e) {
			return "redirect:../funcionario/home";
		}
	}
	
	@GetMapping(path= "/servico")
	public String cadastro(Model model) {
		ServicoModel servico = new ServicoModel();
		model.addAttribute("servico", servico);
		return "servicocad";
	}
	
	@PostMapping(path= "/servicocad")
	public String servicoCad(ServicoModel servico, String tipo) {
		
		ServicoModel obj = new ServicoModel();

		obj = ss.inserir(servico);
		
		return "redirect:../funcionario/home";
	}
	
	@GetMapping(path= "/cadastrof")
	public String cadastrof(Model model) {
		FuncionarioModel funcionario = new FuncionarioModel();
		model.addAttribute("funcionario", funcionario);
		return "cadastrof";
	}
	
	@PostMapping(path= "/cadastrarf")
	public String cadastrarf(FuncionarioModel funcionario) {
		
		FuncionarioModel obj = new FuncionarioModel();

		obj = fs.cadastrar(funcionario);
		
		return "redirect:../funcionario/home";
	}
	
	@GetMapping(path = "/consulta")
	public String quartos(Model model) {

		List<QuartoModel> quartosT = new ArrayList();
		List<QuartoModel> quartos = new ArrayList();

		quartosT = qs.buscarTodos();
		
		for(QuartoModel quarto: quartosT) {
			if(quarto.getCliente() != null){
				quartos.add(quarto);
			}
		}
		model.addAttribute("quartos",quartos);

		return "quartosalugados";
	}
}
