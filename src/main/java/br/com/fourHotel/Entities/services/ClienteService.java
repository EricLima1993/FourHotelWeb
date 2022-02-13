package br.com.fourHotel.Entities.services;

import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourHotel.Entities.bo.ClienteBo;
import br.com.fourHotel.Entities.models.ClienteModel;
import br.com.fourHotel.Entities.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cr;
	private ClienteBo cb = new ClienteBo();
	
	public ClienteModel cadastrar(ClienteModel obj) {	
		return cr.save(obj);
	}
	
	public ClienteModel buscar(ClienteModel cli) throws AccountNotFoundException {
		//return cr.findByLogin(cli.getLogin());
		Optional<ClienteModel> obj = cr.search(cli.getLogin() ,cli.getSenha());
		return obj.orElseThrow(() -> new AccountNotFoundException("Senha ou login invalido"));
	}
	
	public ClienteModel buscarLogin(ClienteModel cli) throws AccountNotFoundException {
		Optional<ClienteModel> obj = cr.searchLogin(cli.getLogin());
		return obj.orElseThrow(() -> new AccountNotFoundException("Senha ou login invalido"));
	}
	
	public ClienteModel atualizar(ClienteModel obj) {
		return cr.save(obj);
	}
}
