package com.brq.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brq.cursomc.enums.ClientePerfil;
import com.brq.cursomc.enums.TipoClienteEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Cliente")
public class ClienteDomain implements Serializable {

	private static final long serialVersionUID = -709664087506040263L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Column(unique = true)
	private String email;
	private String cpfCnpj;
	private Integer tipoCliente;
	
	@JsonIgnore
	private String senha;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<EnderecoDomain> enderecos = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name="telefone")
	private Set<String> telefones = new HashSet<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<PedidoDomain> pedidos = new ArrayList<>();
	
	private String imagemUrl;
	
	public ClienteDomain() {
		addPerfil(ClientePerfil.CLIENTE);
	}

	public ClienteDomain(Integer id, String nome, String email, String cpfCnpj, 
			TipoClienteEnum tipoCliente, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.tipoCliente = (tipoCliente == null) ? null : tipoCliente.getCodigo();
		this.senha = senha;
		addPerfil(ClientePerfil.CLIENTE);
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public TipoClienteEnum getTipoCliente() {
		return TipoClienteEnum.buscaTipoCliente(tipoCliente);
	}

	public void setTipoCliente(TipoClienteEnum tipoCliente) {
		this.tipoCliente = tipoCliente.getCodigo();
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<EnderecoDomain> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDomain> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public Set<ClientePerfil> getPerfis(){
		return perfis.stream()
				.map(perfil -> ClientePerfil.buscaClientePerfil(perfil))
				.collect(Collectors.toSet());
	}
	
	public void addPerfil(ClientePerfil clientePerfil) {
		perfis.add(clientePerfil.getCodigo());
	}

	public List<PedidoDomain> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoDomain> pedidos) {
		this.pedidos = pedidos;
	}
	
	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteDomain other = (ClienteDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
