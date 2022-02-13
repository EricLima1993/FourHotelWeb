package br.com.fourHotel.util;

import br.com.fourHotel.Entities.models.QuartoModel;

public class QuartoDados {

	private static QuartoModel quartoSelecionado;

	public static QuartoModel getQuartoSelecionado() {
		return quartoSelecionado;
	}

	public static void setQuartoSelecionado(QuartoModel quartoSelecionado) {
		QuartoDados.quartoSelecionado = quartoSelecionado;
	}
	
}
