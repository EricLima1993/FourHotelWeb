package br.com.fourHotel.Entities.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.fourHotel.enuns.TipoProduto;

@Entity
@Table(name="tb_servico")
public class ServicoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_servico")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servico_seq")
	@SequenceGenerator(name = "servico_seq", initialValue = 1, allocationSize = 1)
	private Integer idServico;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="tipo")
	private TipoProduto tipo;

	public ServicoModel() {
		super();
	}

	public ServicoModel(Integer idServico, Double valor, String nome, TipoProduto tipo) {
		super();
		this.idServico = idServico;
		this.valor = valor;
		this.nome = nome;
		this.tipo = tipo;
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

}
