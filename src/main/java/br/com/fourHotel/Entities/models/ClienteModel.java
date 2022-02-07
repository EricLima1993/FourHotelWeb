package br.com.fourHotel.Entities.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_cliente")
public class ClienteModel extends PessoaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cpf")
	private String cpf;
	
	@Column(name="idade")
	private int idade;
	
	@OneToOne(mappedBy = "cliente")
	private QuartoModel quarto;
	
	public ClienteModel() {
	}

	public ClienteModel(Integer idUsuario, String nome, String login, String senha, String cpf, int idade) {
		super(idUsuario, nome, login, senha);
		this.cpf = cpf;
		this.idade = idade;
	}

	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	@SequenceGenerator(name = "usuario_seq", initialValue = 1, allocationSize = 1)
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Transient
	public QuartoModel getQuarto() {
		return quarto;
	}

	public void setQuarto(QuartoModel quarto) {
		this.quarto = quarto;
	}
	
}
