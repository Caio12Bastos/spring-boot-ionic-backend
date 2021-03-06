package com.brq.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.services.validation.ClienteAtualizarValidation;

@ClienteAtualizarValidation
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -4230417686324652467L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 a 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(ClienteDomain clienteDomain) {
		id = clienteDomain.getId();
		nome = clienteDomain.getNome();
		email = clienteDomain.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
