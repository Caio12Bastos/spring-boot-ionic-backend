package com.brq.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.brq.cursomc.enums.EstadoPagamentoEnum;

@Entity
@Table(name="Pagamento_Boleto")
public class PagamentoBoletoDomain extends PagamentoDomain {
	
	private static final long serialVersionUID = 4009447453681549612L;
	
	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoBoletoDomain() {
		
	}

	public PagamentoBoletoDomain(Integer id, EstadoPagamentoEnum estado, 
			PedidoDomain pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}
