package com.brq.cursomc.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pesso Física"),
	PESSOAJURIDICA(2, "Pesso Jurídica");
	
	private Integer codigo;
	private String descricao;
	
	private TipoCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente buscaTipoCliente(Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for(TipoCliente tipoCliente : TipoCliente.values()) {
			if(codigo.equals(tipoCliente.getCodigo())) {
				return tipoCliente;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
