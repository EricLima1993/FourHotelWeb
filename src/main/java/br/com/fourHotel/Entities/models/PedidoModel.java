package br.com.fourHotel.Entities.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.fourHotel.enuns.TipoProduto;

@Entity
@Table(name="tb_pedido")
public class PedidoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
	@SequenceGenerator(name = "pedido_seq", initialValue = 1, allocationSize = 1)
	private Integer idPedido;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="data_compra")
	private Date dataDaCompra;
	
	@Column(name="tipo")
	private TipoProduto tipo;
	
	@Column(name="")
	private String Login;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "quarto_pedidos",
	joinColumns = @JoinColumn(name = "id_pedido"),
	inverseJoinColumns = @JoinColumn(name = "numero_quarto")
	)
	private QuartoModel quarto;

	public PedidoModel() {
		super();
	}

	public PedidoModel(Integer idPedido, Double valor, String nome, Date dataDaCompra, TipoProduto tipo, String login,
			QuartoModel quarto) {
		super();
		this.idPedido = idPedido;
		this.valor = valor;
		this.nome = nome;
		this.dataDaCompra = dataDaCompra;
		this.tipo = tipo;
		Login = login;
		this.quarto = quarto;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
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

	public Date getDataDaCompra() {
		return dataDaCompra;
	}

	public void setDataDaCompra(Date dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public void setQuarto(QuartoModel quarto) {
		this.quarto = quarto;
	}
	
	
}
