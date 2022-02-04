package br.com.fourHotel.util;

import br.com.fourHotel.Entities.models.ClienteModel;

public class ClienteDados {
	
	public static ClienteModel clienteLogado;

	public static ClienteModel getClienteLogado() {
		return clienteLogado;
	}

	public static void setClienteLogado(ClienteModel clienteLogado) {
		ClienteDados.clienteLogado = clienteLogado;
	}
	
	

}
