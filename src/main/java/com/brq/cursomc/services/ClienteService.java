package com.brq.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brq.cursomc.domain.CidadeDomain;
import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.domain.EnderecoDomain;
import com.brq.cursomc.dto.ClienteDTO;
import com.brq.cursomc.dto.NovoClienteDTO;
import com.brq.cursomc.enums.TipoClienteEnum;
import com.brq.cursomc.repositories.ClienteRepository;
import com.brq.cursomc.repositories.EnderecoRepository;
import com.brq.cursomc.services.exception.DataIntegrityException;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public ClienteDomain buscar(Integer id) throws RecursoNaoEncontrado {

		Optional<ClienteDomain> optClienteDomain = clienteRepository.findById(id);
		return optClienteDomain.orElseThrow(() -> new RecursoNaoEncontrado("Objeto não encontrado! Id: " + id));
	}
	
	@Transactional
	public ClienteDomain inserir(ClienteDomain clienteDomain) {
		
		clienteDomain.setId(null);
		clienteDomain = clienteRepository.save(clienteDomain);
		enderecoRepository.saveAll(clienteDomain.getEnderecos());
		
		return clienteDomain;
	}

	public ClienteDomain atualizar(ClienteDomain cliente) {

		ClienteDomain novoClienteDomain = buscar(cliente.getId());
		atualizaDados(novoClienteDomain, cliente);
		return clienteRepository.save(novoClienteDomain);
	}

	public void detelar(Integer id) {

		buscar(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException excecao) {
			throw new DataIntegrityException("Não é possível excluir porque o cliente contêm pedido.");
		}
	}

	public List<ClienteDomain> buscarTodas() {
		return clienteRepository.findAll();
	}

	public Page<ClienteDomain> buscaPaginacao(Integer pagina, Integer linhaPorPagina, String orderBy, String order) {

		PageRequest paginaRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(order), orderBy);
		return clienteRepository.findAll(paginaRequest);
	}

	public ClienteDomain transformDTO(ClienteDTO clienteDTO) {
		return new ClienteDomain(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}

	public ClienteDomain transformDTO(NovoClienteDTO novoClienteDTO) {

		ClienteDomain clienteDomain = new ClienteDomain(null, novoClienteDTO.getNome(), novoClienteDTO.getEmail(), 
				novoClienteDTO.getCpfCnpj(), TipoClienteEnum.buscaTipoCliente(novoClienteDTO.getTipoCliente()));
		
		CidadeDomain cidadeDomain = new CidadeDomain(novoClienteDTO.getCidadeId(), null, null);
		
		EnderecoDomain enderecoDomain = new EnderecoDomain(null, novoClienteDTO.getLogradouro(), novoClienteDTO.getNumero(), 
				novoClienteDTO.getComplemento(), novoClienteDTO.getBairro(), novoClienteDTO.getCep(), 
				clienteDomain, cidadeDomain);
		
		clienteDomain.getEnderecos().add(enderecoDomain);
		clienteDomain.getTelefones().add(novoClienteDTO.getTelefone1());
		if(novoClienteDTO.getTelefone2() != null) {
			clienteDomain.getTelefones().add(novoClienteDTO.getTelefone2());
		}
		if(novoClienteDTO.getTelefone3() != null) {
			clienteDomain.getTelefones().add(novoClienteDTO.getTelefone3());
		}
		
		return clienteDomain;
	}
	
	private void atualizaDados(ClienteDomain novoClienteDomain, ClienteDomain cliente) {
		novoClienteDomain.setNome(cliente.getNome());
		novoClienteDomain.setEmail(cliente.getEmail());
	}
	
}
