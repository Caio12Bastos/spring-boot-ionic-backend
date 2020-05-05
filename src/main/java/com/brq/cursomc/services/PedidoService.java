package com.brq.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brq.cursomc.domain.ItemPedidoDomain;
import com.brq.cursomc.domain.PagamentoBoletoDomain;
import com.brq.cursomc.domain.PedidoDomain;
import com.brq.cursomc.enums.EstadoPagamentoEnum;
import com.brq.cursomc.repositories.ItemPedidoRepository;
import com.brq.cursomc.repositories.PagamentoRepository;
import com.brq.cursomc.repositories.PedidoRepository;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired 
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired 
	private EmailService emailService;
	
	public PedidoDomain buscar(Integer id) throws RecursoNaoEncontrado {
		
		Optional<PedidoDomain> optPedidoDomain = pedidoRepository.findById(id);
		return optPedidoDomain.orElseThrow(() -> new RecursoNaoEncontrado( 
				"Objeto não encontrado! Id: " + id)); 
	}

	@Transactional
	public PedidoDomain inserir(PedidoDomain pedidoDomain) {
		
		pedidoDomain.setId(null);
		pedidoDomain.setDataPedido(new Date());
		pedidoDomain.setCliente(clienteService.buscar(pedidoDomain.getCliente().getId()));
		pedidoDomain.getPagamento().setEstado(EstadoPagamentoEnum.PENDENTE);
		pedidoDomain.getPagamento().setPedido(pedidoDomain);
		
		if(pedidoDomain.getPagamento() instanceof PagamentoBoletoDomain) {
			PagamentoBoletoDomain pagamentoBoletoDomain = (PagamentoBoletoDomain) pedidoDomain.getPagamento();
			boletoService.preencherPagamentoBoleto(pagamentoBoletoDomain, pedidoDomain.getDataPedido());
		}
	
		pedidoDomain =  pedidoRepository.save(pedidoDomain);
		pagamentoRepository.save(pedidoDomain.getPagamento());
	
		for(ItemPedidoDomain itemPedidoDomain : pedidoDomain.getItens()) {
			itemPedidoDomain.setDesconto(0.0);
			itemPedidoDomain.setProduto(produtoService.buscar(itemPedidoDomain.getProduto().getId()));
			itemPedidoDomain.setPreco(itemPedidoDomain.getProduto().getPreco());
			itemPedidoDomain.setPedido(pedidoDomain);
		}
		
		itemPedidoRepository.saveAll(pedidoDomain.getItens());
		emailService.enviarOrdemConfirmacaoEmail(pedidoDomain);
		return pedidoDomain;
	}
	
}
