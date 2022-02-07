package br.com.fourHotel.Entities.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fourHotel.Entities.models.ClienteModel;
import br.com.fourHotel.Entities.models.ServicoModel;
import br.com.fourHotel.Entities.services.QuartoService;
import br.com.fourHotel.Entities.services.ServicoService;
import br.com.fourHotel.util.ClienteDados;

@Controller
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	private ServicoService ss;
	@Autowired
	private QuartoService qs;
	
	@GetMapping(path = "/produtos")
	public String produtos(Model model) {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		List<ServicoModel> servicos = new ArrayList();
		servicos = ss.buscarTodos();
		model.addAttribute("servicos",servicos);
		
		return "servicos";
	}
	
	@GetMapping(path = "/compra/{idServico}")
	public String comprar(@PathVariable int idServico, Model model) {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		ServicoModel servico = new ServicoModel();
		servico = ss.buscarPorNumero(idServico);
		cliente.getQuarto().getServicos().add(servico);
		servico.setQuarto(cliente.getQuarto());
		qs.atualizar(cliente.getQuarto());
		ss.atualizar(servico);
		
		ClienteDados.setClienteLogado(cliente);
		
		return "redirect:../../cliente/home";
		
	}
}
