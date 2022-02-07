package br.com.fourHotel.Entities.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourHotel.Entities.models.QuartoModel;
import br.com.fourHotel.Entities.repositories.QuartoRepository;

@Service
public class QuartoService {

	@Autowired
	private QuartoRepository qr;
	
	public List<QuartoModel> buscarTodos(){
		List<QuartoModel> quartos = new ArrayList();
		quartos = qr.findAll();
		return quartos;
	}

	public QuartoModel buscarPorNumero(Integer numero) {
		QuartoModel quarto = new QuartoModel();
		quarto = qr.getById(numero);
		return quarto;
	}
	
	public QuartoModel atualizar(QuartoModel quarto) {
		return qr.save(quarto);
	}
}
