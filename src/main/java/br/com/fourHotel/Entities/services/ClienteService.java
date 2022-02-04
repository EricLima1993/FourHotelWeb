package br.com.fourHotel.Entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourHotel.Entities.bo.ClienteBo;
import br.com.fourHotel.Entities.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cr;
	private ClienteBo cb = new ClienteBo();
}
