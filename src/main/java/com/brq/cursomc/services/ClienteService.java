package com.brq.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.dto.ClienteDTO;
import com.brq.cursomc.repositories.ClienteRepository;
import com.brq.cursomc.services.exception.DataIntegrityException;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteDomain buscar(Integer id) throws RecursoNaoEncontrado {

		Optional<ClienteDomain> optClienteDomain = clienteRepository.findById(id);
		return optClienteDomain.orElseThrow(() -> new RecursoNaoEncontrado("Objeto não encontrado! Id: " + id));
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

	private void atualizaDados(ClienteDomain novoClienteDomain, ClienteDomain cliente) {
		novoClienteDomain.setNome(cliente.getNome());
		novoClienteDomain.setEmail(cliente.getEmail());
	}
	
}
