package com.brq.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.brq.cursomc.enums.EstadoPagamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name="Pagamento_Boleto") 
@JsonTypeName("pagamentoComBoleto")
public class PagamentoBoletoDomain extends PagamentoDomain {
	
	private static final long serialVersionUID = 4009447453681549612L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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
