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
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService fs;
	@Autowired
	private ClienteService cs;
	@Autowired
	private QuartoService qs;
	
	@GetMapping(path = "/home")
	public String home(Model model) {
		FuncionarioModel funcionario = FuncionarioDados.getFuncinarioLogado();
		model.addAttribute("funcionario", funcionario);
		
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
}
