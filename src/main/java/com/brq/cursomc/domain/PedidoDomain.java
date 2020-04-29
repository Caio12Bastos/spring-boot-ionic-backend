package com.brq.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Pedido")
public class PedidoDomain implements Serializable {
	
	private static final long serialVersionUID = 1415754876277782753L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date dataPedido;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private PagamentoDomain pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private ClienteDomain cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_entrega_id")
	private EnderecoDomain enderecoEntrega;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedidoDomain> itens = new HashSet<>();
	
	public PedidoDomain() {
		
	}

	public PedidoDomain(Integer id, Date dataPedido, ClienteDomain cliente,
			EnderecoDomain enderecoEntrega) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public PagamentoDomain getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoDomain pagamento) {
		this.pagamento = pagamento;
	}

	public ClienteDomain getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDomain cliente) {
		this.cliente = cliente;
	}

	public EnderecoDomain getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoDomain enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Set<ItemPedidoDomain> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedidoDomain> itens) {
		this.itens = itens;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDomain other = (PedidoDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
