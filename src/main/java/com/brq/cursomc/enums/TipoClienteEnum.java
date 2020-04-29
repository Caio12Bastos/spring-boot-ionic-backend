package com.brq.cursomc.enums;

public enum TipoClienteEnum {

	PESSOAFISICA(1, "Pesso Física"),
	PESSOAJURIDICA(2, "Pesso Jurídica");
	
	private Integer codigo;
	private String descricao;
	
	private TipoClienteEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoClienteEnum buscaTipoCliente(Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for(TipoClienteEnum tipoCliente : TipoClienteEnum.values()) {
			if(codigo.equals(tipoCliente.getCodigo())) {
				return tipoCliente;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
