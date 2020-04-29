package com.brq.cursomc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.brq.cursomc.enums.EstadoPagamentoEnum;

@Entity
@Table(name="Pagamento_Cartao")
public class PagamentoCartaoDomain extends PagamentoDomain {

	private static final long serialVersionUID = 3824780830981556L;
	
	private Integer numeroParcelas;
	
	public PagamentoCartaoDomain() {
		
	}

	public PagamentoCartaoDomain(Integer id, EstadoPagamentoEnum estado, 
			PedidoDomain pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
}
