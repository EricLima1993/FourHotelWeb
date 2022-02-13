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
import br.com.fourHotel.Entities.services.ClienteService;
import br.com.fourHotel.Entities.services.FuncionarioService;
import br.com.fourHotel.Entities.services.QuartoService;
import br.com.fourHotel.util.ClienteDados;
import br.com.fourHotel.util.FuncionarioDados;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

	@Autowired
	private ClienteService cs;
	@Autowired
	private QuartoService qs;
	@Autowired
	private FuncionarioService fs;
	
	@GetMapping(path = "/")
	public String telaInicial() {
		ClienteDados.setClienteLogado(null);
		return "principal";
	}
	
	@GetMapping(path = "/login")
	public String login(Model model) {
		ClienteModel cliente = new ClienteModel();
		model.addAttribute("cliente", cliente);
		return "login";
	}
	@GetMapping(path = "/loginf")
	public String loginf(Model model) {
		FuncionarioModel funcionario = new FuncionarioModel();
		model.addAttribute("funcionario", funcionario);
		return "loginf";
	}
	
	@PostMapping(path = "/entrarf")
	public String entrarf(FuncionarioModel funcionario) {
		
		FuncionarioModel obj = new FuncionarioModel();

		try {
			obj = fs.buscar(funcionario);

			FuncionarioDados.setFuncinarioLogado(obj);
			return "redirect:../funcionario/home";
		} catch (AccountNotFoundException e) {
			return "redirect:../principal/";
		}

	}
	
	@PostMapping(path = "/entrar")
	public String entrar(ClienteModel cliente) {
		
		ClienteModel obj = new ClienteModel();
		obj.setQuarto(new QuartoModel());
		List<QuartoModel> quartos = new ArrayList();
		try {
			obj = cs.buscar(cliente);
			quartos = qs.buscarTodos();
			for(QuartoModel quarto: quartos) {
				if(quarto.getCliente() != null) {
					if(quarto.getCliente().getIdUsuario() == obj.getIdUsuario()) {
						obj.setQuarto(quarto);
					}
				}
			}
			ClienteDados.setClienteLogado(obj);
			return "redirect:../cliente/home";
		} catch (AccountNotFoundException e) {
			return "redirect:../principal/";
		}

	}
	
	@GetMapping(path= "/cadastro")
	public String cadastro(Model model) {
		ClienteModel cliente = new ClienteModel();
		model.addAttribute("cliente", cliente);
		return "cadastro";
	}
	@PostMapping(path= "/cadastrar")
	public String cadastrar(ClienteModel cliente) {
		
		ClienteModel obj = new ClienteModel();
		
		obj = cs.cadastrar(cliente);
		
		ClienteDados.setClienteLogado(cliente);
		
		return "redirect:../cliente/home";
	}
}
