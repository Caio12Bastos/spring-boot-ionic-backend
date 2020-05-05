package com.brq.cursomc.enums;

public enum ClientePerfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private Integer codigo;
	private String descricao;
	
	private ClientePerfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static ClientePerfil buscaClientePerfil(Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for(ClientePerfil clientePerfil : ClientePerfil.values()) {
			if(codigo.equals(clientePerfil.getCodigo())) {
				return clientePerfil;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
