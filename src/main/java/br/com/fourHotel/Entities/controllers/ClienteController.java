package br.com.fourHotel.Entities.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fourHotel.Entities.models.ClienteModel;
import br.com.fourHotel.Entities.models.QuartoModel;
import br.com.fourHotel.Entities.services.ClienteService;
import br.com.fourHotel.Entities.services.QuartoService;
import br.com.fourHotel.util.ClienteDados;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService cs;
	@Autowired
	private QuartoService qs;
	
	@GetMapping(path = "/home")
	public String home(Model model) {
		
		ClienteModel cliente = ClienteDados.getClienteLogado();
		List<QuartoModel> quartos = new ArrayList();
		try {
			cliente = cs.buscar(cliente);
			quartos = qs.buscarTodos();
			for(QuartoModel quarto: quartos) {
				if(quarto.getCliente() != null) {
					if(quarto.getCliente().getIdUsuario() == cliente.getIdUsuario()) {
						cliente.setQuarto(quarto);
					}
				}
			}
		}catch (AccountNotFoundException e) {

		}
		model.addAttribute("cliente", cliente);
		
		return "cliente";
	}
	
	@GetMapping(path = "/quartos")
	public String quartos() {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		//QuartoModel quarto = new QuartoModel();
		//quarto = qs.buscarPorNumero(cliente.getQuarto().getNumeroQuarto());
		//cliente.setQuarto(quarto);
		//model.addAttribute("cliente", cliente);
		if(cliente.getQuarto() != null) {
			return "redirect:./home";
		}else {
			return "redirect:../quarto/lista";
		}
		
	}
	
	@GetMapping(path = "/historico")
	public String historico(Model model) {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		QuartoModel quarto = new QuartoModel();

		if(cliente.getQuarto() == null) {
			return "redirect:./home";
		}else {
			quarto = qs.buscarPorNumero(cliente.getQuarto().getNumeroQuarto());
			cliente.setQuarto(quarto);
			model.addAttribute("cliente", cliente);
			
			return "historico";
		}

	}
}
