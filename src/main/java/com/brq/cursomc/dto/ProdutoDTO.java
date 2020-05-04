package com.brq.cursomc.dto;

import java.io.Serializable;

import com.brq.cursomc.domain.ProdutoDomain;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = -6417511619605517821L;

	private Integer id;
	private String nome;
	private Double preco;
	
	public ProdutoDTO() {
		
	}

	public ProdutoDTO(ProdutoDomain produtoDomain) {
		id = produtoDomain.getId();
		nome = produtoDomain.getNome();
		preco = produtoDomain.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}
