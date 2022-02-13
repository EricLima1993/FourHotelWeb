package br.com.fourHotel.Entities.services;

import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourHotel.Entities.bo.FuncionarioBo;
import br.com.fourHotel.Entities.models.FuncionarioModel;
import br.com.fourHotel.Entities.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository fr;
	private FuncionarioBo fb = new FuncionarioBo();
	
	public FuncionarioModel buscar(FuncionarioModel fun) throws AccountNotFoundException {

		Optional<FuncionarioModel> obj = fr.search(fun.getLogin() ,fun.getSenha());
		return obj.orElseThrow(() -> new AccountNotFoundException("Senha ou login invalido"));
	}
}
