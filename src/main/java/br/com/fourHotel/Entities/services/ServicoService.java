package br.com.fourHotel.Entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourHotel.Entities.repositories.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository sr;
}
