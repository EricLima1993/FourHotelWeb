package br.com.fourHotel.Entities.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.fourHotel.enuns.TipoFuncionario;

@Entity
@Table(name="tb_funcionario")
public class FuncionarioModel extends PessoaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cargo")
	private TipoFuncionario cargo;

	public FuncionarioModel() {
		
	}

	public FuncionarioModel(Integer idUsuario, String nome, String login, String senha, TipoFuncionario cargo) {
		super(idUsuario, nome, login, senha);
		this.cargo = cargo;
	}
	
	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_seq")
	@SequenceGenerator(name = "funcionario_seq", initialValue = 1, allocationSize = 1)
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name="nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name="senha")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoFuncionario getCargo() {
		return cargo;
	}

	public void setCargo(TipoFuncionario cargo) {
		this.cargo = cargo;
	}
	
}
