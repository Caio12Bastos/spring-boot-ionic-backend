package com.brq.cursomc.dto;

import java.io.Serializable;

import com.brq.cursomc.domain.CategoriaDomain;

public class CategoriaDTO implements Serializable{

	private static final long serialVersionUID = -726300662775894186L;

	private Integer id;
	private String nome;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(CategoriaDomain categoriaDomain) {
		id = categoriaDomain.getId();
		nome = categoriaDomain.getNome();
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
