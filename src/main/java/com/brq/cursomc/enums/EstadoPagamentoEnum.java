package com.brq.cursomc.enums;

public enum EstadoPagamentoEnum {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	private EstadoPagamentoEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamentoEnum buscaPagamentoEnum(Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for(EstadoPagamentoEnum estadoPagamento : EstadoPagamentoEnum.values()) {
			if(codigo.equals(estadoPagamento.getCodigo())) {
				return estadoPagamento;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
