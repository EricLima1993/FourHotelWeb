package br.com.fourHotel.Entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourHotel.Entities.repositories.QuartoRepository;

@Service
public class QuartoService {

	@Autowired
	private QuartoRepository qr;
}
