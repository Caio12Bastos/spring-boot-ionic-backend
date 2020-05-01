package com.brq.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.brq.cursomc.enums.EstadoPagamentoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Pagamento")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PagamentoDomain implements Serializable {

	private static final long serialVersionUID = -4317883150140282478L;
	
	@Id
	private Integer id;
	private Integer estado;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private PedidoDomain pedido;
	
	public PagamentoDomain() {
		
	}

	public PagamentoDomain(Integer id, EstadoPagamentoEnum estado, PedidoDomain pedido) {
		super();
		this.id = id;
		this.estado = (estado == null) ? null : estado.getCodigo();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamentoEnum getEstado() {
		return EstadoPagamentoEnum.buscaPagamentoEnum(estado);
	}

	public void setEstado(EstadoPagamentoEnum estado) {
		this.estado = estado.getCodigo();
	}

	public PedidoDomain getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDomain pedido) {
		this.pedido = pedido;
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
		PagamentoDomain other = (PagamentoDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
