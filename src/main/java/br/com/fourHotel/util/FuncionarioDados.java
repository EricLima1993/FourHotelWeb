package br.com.fourHotel.util;

import br.com.fourHotel.Entities.models.FuncionarioModel;

public class FuncionarioDados {

	private static FuncionarioModel funcinarioLogado;

	public static FuncionarioModel getFuncinarioLogado() {
		return funcinarioLogado;
	}

	public static void setFuncinarioLogado(FuncionarioModel funcinarioLogado) {
		FuncionarioDados.funcinarioLogado = funcinarioLogado;
	}
	
	
}
