package br.com.fourHotel.Entities.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourHotel.Entities.models.ServicoModel;
import br.com.fourHotel.Entities.repositories.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository sr;
	
	public List<ServicoModel> buscarTodos(){
		List<ServicoModel> servicos = new ArrayList();
		servicos = sr.findAll();
		return servicos;
	}
	
	public ServicoModel buscarPorNumero(Integer id) {
		ServicoModel servico = new ServicoModel();
		servico = sr.getById(id);
		return servico;
	}
	
	public ServicoModel atualizar(ServicoModel servico) {
		return sr.save(servico);
	}
	
	public ServicoModel inserir(ServicoModel servico) {
		return sr.save(servico);
	}
}
