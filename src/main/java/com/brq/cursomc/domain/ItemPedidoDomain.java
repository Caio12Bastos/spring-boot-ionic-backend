package com.brq.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Item_Pedido")
public class ItemPedidoDomain implements Serializable {
	
	private static final long serialVersionUID = 936662921597419241L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPKDomain id = new ItemPedidoPKDomain();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemPedidoDomain() {
		
	}

	public ItemPedidoDomain(PedidoDomain pedido, ProdutoDomain produto, Double desconto, Integer quantidade, 
			Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public Double getSubTotal() {
		return (preco - desconto * quantidade);
	}

	@JsonIgnore
	public PedidoDomain getPedido() {
		return id.getPedido();
	}
	
	@JsonIgnore
	public ProdutoDomain getProduto() {
		return id.getProduto();
	}
	
	public ItemPedidoPKDomain getId() {
		return id;
	}

	public void setId(ItemPedidoPKDomain id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		ItemPedidoDomain other = (ItemPedidoDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
