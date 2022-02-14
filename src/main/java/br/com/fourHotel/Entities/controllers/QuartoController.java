package br.com.fourHotel.Entities.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fourHotel.Entities.bo.QuartoBo;
import br.com.fourHotel.Entities.models.ClienteModel;
import br.com.fourHotel.Entities.models.PedidoModel;
import br.com.fourHotel.Entities.models.QuartoModel;
import br.com.fourHotel.Entities.services.ClienteService;
import br.com.fourHotel.Entities.services.PedidoService;
import br.com.fourHotel.Entities.services.QuartoService;
import br.com.fourHotel.enuns.TipoQuarto;
import br.com.fourHotel.util.ClienteDados;
import br.com.fourHotel.util.QuartoDados;

@Controller
@RequestMapping("/quarto")
public class QuartoController {

	@Autowired
	private QuartoService qs;
	@Autowired
	private ClienteService cs;
	@Autowired
	private PedidoService ps;
	
	@GetMapping(path = "/lista")
	public String quartos(Model model) {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		List<QuartoModel> quartos = new ArrayList();
		List<QuartoModel> quartos5 = new ArrayList();
		List<QuartoModel> quartos4 = new ArrayList();
		List<QuartoModel> quartos3 = new ArrayList();
		quartos = qs.buscarTodos();
		
		for(QuartoModel quarto: quartos) {
			if(quarto.getTipo().equals(TipoQuarto.CINCO_ESTRELAS)) {
				quartos5.add(quarto);
			}else if(quarto.getTipo().equals(TipoQuarto.QUATRO_ESTRELAS)) {
				quartos4.add(quarto);
			}else if(quarto.getTipo().equals(TipoQuarto.TRES_ESTRELAS)){
				quartos3.add(quarto);
			}
		}
		
		model.addAttribute("quartos5",quartos5);
		model.addAttribute("quartos4",quartos4);
		model.addAttribute("quartos3",quartos3);
		return "quartos";
	}
	@GetMapping(path = "/alugar/{numeroQuarto}")
	public String alugar(@PathVariable int numeroQuarto, Model model) {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		QuartoModel quarto = new QuartoModel();
		quarto = qs.buscarPorNumero(numeroQuarto);

		cliente.setQuarto(quarto);
		quarto.setCliente(cliente);
		quarto = qs.atualizar(quarto);

		cliente.getQuarto().setPedidos(new ArrayList());
		
		QuartoModel temp = new QuartoModel();
		ClienteDados.setClienteLogado(cliente);
		model.addAttribute("temp",temp);
		return "estadia";
	}
	
	@PostMapping(path = "/estadia")
	public String estadia(QuartoModel temp) {
		ClienteModel cliente = ClienteDados.getClienteLogado();

		QuartoModel quarto = new QuartoModel();
		quarto = qs.buscarPorNumero(cliente.getQuarto().getNumeroQuarto());
		quarto.setEstadia(temp.getEstadia());
		QuartoBo.datas(quarto);
		quarto.setOcupado(true);
		quarto = qs.atualizar(quarto);
		
		return "redirect:../cliente/home";
	}
	
	@GetMapping(path = "/listaf")
	public String quartosF(Model model) {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		List<QuartoModel> quartos = new ArrayList();
		List<QuartoModel> quartos5 = new ArrayList();
		List<QuartoModel> quartos4 = new ArrayList();
		List<QuartoModel> quartos3 = new ArrayList();
		quartos = qs.buscarTodos();
		
		for(QuartoModel quarto: quartos) {
			if(quarto.getTipo().equals(TipoQuarto.CINCO_ESTRELAS)) {
				quartos5.add(quarto);
			}else if(quarto.getTipo().equals(TipoQuarto.QUATRO_ESTRELAS)) {
				quartos4.add(quarto);
			}else if(quarto.getTipo().equals(TipoQuarto.TRES_ESTRELAS)){
				quartos3.add(quarto);
			}
		}
		
		model.addAttribute("quartos5",quartos5);
		model.addAttribute("quartos4",quartos4);
		model.addAttribute("quartos3",quartos3);
		return "quartosf";
	}
	@GetMapping(path = "/alugarf/{numeroQuarto}")
	public String alugarF(@PathVariable int numeroQuarto, Model model) {
		ClienteModel cliente = ClienteDados.getClienteLogado();
		QuartoModel quarto = new QuartoModel();
		quarto = qs.buscarPorNumero(numeroQuarto);

		cliente.setQuarto(quarto);
		quarto.setCliente(cliente);
		quarto = qs.atualizar(quarto);

		cliente.getQuarto().setPedidos(new ArrayList());
		
		QuartoModel temp = new QuartoModel();
		ClienteDados.setClienteLogado(cliente);
		model.addAttribute("temp",temp);
		return "estadiaf";
	}
	
	@PostMapping(path = "/estadiaf")
	public String estadiaF(QuartoModel temp) {
		ClienteModel cliente = ClienteDados.getClienteLogado();

		QuartoModel quarto = new QuartoModel();
		quarto = qs.buscarPorNumero(cliente.getQuarto().getNumeroQuarto());
		quarto.setEstadia(temp.getEstadia());
		QuartoBo.datas(quarto);
		quarto.setOcupado(true);
		quarto = qs.atualizar(quarto);
		
		return "redirect:../funcionario/home";
	}
	
	@GetMapping(path = "/encerramento")
	public String encerramento(Model model) {

		QuartoModel quarto = QuartoDados.getQuartoSelecionado();
		quarto = qs.buscarPorNumero(quarto.getNumeroQuarto());
		
		List<PedidoModel> pedidos = new ArrayList<PedidoModel>();
		pedidos = quarto.getPedidos();
		
		double total = 0;
		for(PedidoModel pedido: pedidos) {
			total += pedido.getValor();
		}
		total += quarto.getValor();
		
		model.addAttribute("quarto",quarto);
		model.addAttribute("pedidos",pedidos);
		model.addAttribute("total",total);
		
		return "encerramento";
	}
	
	@GetMapping(path = "/encerrar")
	public String encerrar() {
		
		QuartoModel quarto = QuartoDados.getQuartoSelecionado();
		quarto = qs.buscarPorNumero(quarto.getNumeroQuarto());
		List<PedidoModel> pedidos = new ArrayList<PedidoModel>();
		//pedidos = quarto.getPedidos();
		
		for(PedidoModel pedido: quarto.getPedidos()) {
			pedidos.add(ps.buscarPorNumero(pedido.getIdPedido()));
		}
		
		quarto.setEstadia(null);
		quarto.setOcupado(false);
		quarto.setCliente(null);
		quarto.setPedidos(null);
		quarto.setCheckIn(null);
		quarto = qs.atualizar(quarto);
		
		for(PedidoModel pedido: pedidos) {
			ps.deletar(pedido.getIdPedido());
		}
		
		return "redirect:../funcionario/home";
	}
}
