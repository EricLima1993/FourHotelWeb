package br.com.fourHotel.Entities.models;

public class PessoaModel {

	protected Integer idUsuario;
	protected String nome;
	protected String login;
	protected String senha;
	
	public PessoaModel() {

	}

	public PessoaModel(Integer idUsuario, String nome, String login, String senha) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	
}
