package br.com.fourHotel.Entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourHotel.Entities.bo.FuncionarioBo;
import br.com.fourHotel.Entities.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository fr;
	private FuncionarioBo fb = new FuncionarioBo();
}
