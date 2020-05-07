package com.brq.cursomc.dto;

import java.io.Serializable;

import com.brq.cursomc.domain.CidadeDomain;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 6881004231936221410L;

	private Integer id;
	private String nome;
	
	public CidadeDTO() {
		
	}
	
	public CidadeDTO(CidadeDomain cidadeDomain) {
		id = cidadeDomain.getId();
		nome = cidadeDomain.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
