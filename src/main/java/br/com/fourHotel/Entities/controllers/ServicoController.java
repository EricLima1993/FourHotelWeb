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
import br.com.fourHotel.Entities.models.PedidoModel;
import br.com.fourHotel.Entities.models.QuartoModel;
import br.com.fourHotel.Entities.models.ServicoModel;
import br.com.fourHotel.Entities.services.PedidoService;
import br.com.fourHotel.Entities.services.QuartoService;
import br.com.fourHotel.Entities.services.ServicoService;
import br.com.fourHotel.enuns.TipoProduto;
import br.com.fourHotel.util.ClienteDados;

@Controller
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	private ServicoService ss;
	@Autowired
	private QuartoService qs;
	@Autowired
	private PedidoService ps;
	
	@GetMapping(path = "/produtos")
	public String produtos(Model model) {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		
		if(cliente.getQuarto() == null) {
			return "redirect:../cliente/home";
		}else {
		
			List<ServicoModel> servicos = new ArrayList();
			List<ServicoModel> servicosCo = new ArrayList();
			List<ServicoModel> servicosBe = new ArrayList();
			List<ServicoModel> servicosBA = new ArrayList();
			List<ServicoModel> servicosSe = new ArrayList();
			servicos = ss.buscarTodos();
		
			for(ServicoModel servico: servicos) {
				if(servico.getTipo().equals(TipoProduto.COMIDA)) {
					servicosCo.add(servico);
				}else if(servico.getTipo().equals(TipoProduto.BEBIDA)) {
					servicosBe.add(servico);
				}else if(servico.getTipo().equals(TipoProduto.BEBIDA_ALCOOLICA)) {
					servicosBA.add(servico);
				}else if(servico.getTipo().equals(TipoProduto.SERVICO)) {
					servicosSe.add(servico);
				}
			}
		
			model.addAttribute("servicosCo",servicosCo);
			model.addAttribute("servicosBe",servicosBe);
			model.addAttribute("servicosBA",servicosBA);
			model.addAttribute("servicosSe",servicosSe);
		
			return "servicos";
		}
	}
	
	@GetMapping(path = "/compra/{idServico}")
	public String comprar(@PathVariable int idServico, Model model) {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		ServicoModel servico = new ServicoModel();
		QuartoModel quarto = new QuartoModel();
		PedidoModel pedido = new PedidoModel();
		quarto = qs.buscarPorNumero(cliente.getQuarto().getNumeroQuarto());
		List<PedidoModel> pedidos = new ArrayList();

		pedidos = quarto.getPedidos();
		servico = ss.buscarPorNumero(idServico);
		
		pedido.setNome(servico.getNome());
		pedido.setTipo(servico.getTipo());
		pedido.setValor(servico.getValor());
		pedido.setLogin(cliente.getLogin());
		pedido.setQuarto(quarto);
		
		ps.atualizar(pedido);
		
		pedidos.add(pedido);

		quarto.setPedidos(pedidos);
		cliente.setQuarto(quarto);
		qs.atualizar(cliente.getQuarto());
		
		ClienteDados.setClienteLogado(cliente);
		
		return "redirect:../../cliente/home";
		
	}
}
