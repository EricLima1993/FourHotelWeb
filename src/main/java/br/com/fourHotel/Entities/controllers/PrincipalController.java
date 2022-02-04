package br.com.fourHotel.Entities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fourHotel.Entities.models.ClienteModel;
import br.com.fourHotel.Entities.services.ClienteService;
import br.com.fourHotel.util.ClienteDados;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

	@Autowired
	private ClienteService cs;
	
	@GetMapping(path = "/")
	public String telaInicial() {
		
		return "principal";
	}
	
	@GetMapping(path = "/login")
	public String login() {
		return "login";
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
