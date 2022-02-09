package br.com.fourHotel.Entities.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.fourHotel.enuns.TipoQuarto;

@Entity
@Table(name="tb_quarto")
public class QuartoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_quarto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quarto_seq")
	@SequenceGenerator(name = "quarto_seq", initialValue = 1, allocationSize = 1)
	private Integer numeroQuarto;
	
	@Column(name="ocupado")
	private Boolean ocupado;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="cama_solteiro")
	private int camaSolteiro;
	
	@Column(name="cama_casal")
	private int camaCasal;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "quarto_cliente", 
      joinColumns = 
        { @JoinColumn(name = "numero_quarto", referencedColumnName = "numero_quarto") },
      inverseJoinColumns = 
        { @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario") }
    )
	private ClienteModel cliente;
	
	@OneToMany(mappedBy="quarto")
	private List<PedidoModel> pedidos;
	
	@Column(name="estadia")
	private Integer estadia;
	
	@Column(name="checkin")
	private Date checkIn;
	
	@Column(name="tipo")
	private TipoQuarto tipo;
	
	public QuartoModel() {
		super();
	}
	
	public QuartoModel(Integer numeroQuarto, Boolean ocupado, Double valor, int camaSolteiro, int camaCasal,
			ClienteModel cliente, List<PedidoModel> pedidos, int estadia, Date checkIn, TipoQuarto tipo) {
		this.numeroQuarto = numeroQuarto;
		this.ocupado = ocupado;
		this.valor = valor;
		this.camaSolteiro = camaSolteiro;
		this.camaCasal = camaCasal;
		this.cliente = cliente;
		this.pedidos = pedidos;
		this.estadia = estadia;
		this.checkIn = checkIn;
		this.tipo = tipo;
	}

	public Integer getNumeroQuarto() {
		return numeroQuarto;
	}

	public void setNumeroQuarto(Integer numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getCamaSolteiro() {
		return camaSolteiro;
	}

	public void setCamaSolteiro(int camaSolteiro) {
		this.camaSolteiro = camaSolteiro;
	}

	public int getCamaCasal() {
		return camaCasal;
	}

	public void setCamaCasal(int camaCasal) {
		this.camaCasal = camaCasal;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public List<PedidoModel> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoModel> pedidos) {
		this.pedidos = pedidos;
	}

	public Integer getEstadia() {
		return estadia;
	}

	public void setEstadia(Integer estadia) {
		this.estadia = estadia;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public TipoQuarto getTipo() {
		return tipo;
	}

	public void setTipo(TipoQuarto tipo) {
		this.tipo = tipo;
	}
	
}
