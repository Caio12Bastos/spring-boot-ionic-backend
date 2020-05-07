package com.brq.cursomc.dto;

import java.io.Serializable;

import com.brq.cursomc.domain.EstadoDomain;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 2387472677395222793L;

	private Integer id;
	private String nome;
	
	public EstadoDTO() {
		
	}
	
	public EstadoDTO(EstadoDomain estadoDomain) {
		id = estadoDomain.getId();
		nome = estadoDomain.getNome();
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
