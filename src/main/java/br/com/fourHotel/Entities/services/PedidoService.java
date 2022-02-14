package br.com.fourHotel.Entities.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourHotel.Entities.models.PedidoModel;
import br.com.fourHotel.Entities.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pr;
	
	public List<PedidoModel> buscarTodos(){
		List<PedidoModel> pedidos = new ArrayList();
		pedidos = pr.findAll();
		return pedidos;
	}
	
	public PedidoModel buscarPorNumero(Integer id) {
		PedidoModel pedido = new PedidoModel();
		pedido = pr.getById(id);
		return pedido;
	}
	
	public PedidoModel atualizar(PedidoModel servico) {
		return pr.save(servico);
	}
	
	public void deletar(Integer id) { 
		pr.deleteById(id);
	}
}
