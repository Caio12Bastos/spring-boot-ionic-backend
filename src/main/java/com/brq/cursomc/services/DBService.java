package com.brq.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.domain.CidadeDomain;
import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.domain.EnderecoDomain;
import com.brq.cursomc.domain.EstadoDomain;
import com.brq.cursomc.domain.ItemPedidoDomain;
import com.brq.cursomc.domain.PagamentoBoletoDomain;
import com.brq.cursomc.domain.PagamentoCartaoDomain;
import com.brq.cursomc.domain.PagamentoDomain;
import com.brq.cursomc.domain.PedidoDomain;
import com.brq.cursomc.domain.ProdutoDomain;
import com.brq.cursomc.enums.ClientePerfil;
import com.brq.cursomc.enums.EstadoPagamentoEnum;
import com.brq.cursomc.enums.TipoClienteEnum;
import com.brq.cursomc.repositories.CategoriaRepository;
import com.brq.cursomc.repositories.CidadeRepository;
import com.brq.cursomc.repositories.ClienteRepository;
import com.brq.cursomc.repositories.EnderecoRepository;
import com.brq.cursomc.repositories.EstadoRepository;
import com.brq.cursomc.repositories.ItemPedidoRepository;
import com.brq.cursomc.repositories.PagamentoRepository;
import com.brq.cursomc.repositories.PedidoRepository;
import com.brq.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void instantiateDataBase() throws ParseException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		CategoriaDomain categoriaDomain1 = new CategoriaDomain(null, "Informática");
		CategoriaDomain categoriaDomain2 = new CategoriaDomain(null, "Escritório");
		CategoriaDomain categoriaDomain3 = new CategoriaDomain(null, "Cama mesa e banho");
		CategoriaDomain categoriaDomain4 = new CategoriaDomain(null, "Eletrônicos");
		CategoriaDomain categoriaDomain5 = new CategoriaDomain(null, "Jardinagem");
		CategoriaDomain categoriaDomain6 = new CategoriaDomain(null, "Decoração");
		CategoriaDomain categoriaDomain7 = new CategoriaDomain(null, "Perfumaria");
		
		ProdutoDomain produtoDomain1 = new ProdutoDomain(null, "Computador", 2000.00);
		ProdutoDomain produtoDomain2 = new ProdutoDomain(null, "Impressora", 800.00);
		ProdutoDomain produtoDomain3 = new ProdutoDomain(null, "Mouse", 80.00);
		ProdutoDomain produtoDomain4 = new ProdutoDomain(null, "Mesa Escritório", 300.00);
		ProdutoDomain produtoDomain5 = new ProdutoDomain(null, "Toalha", 50.00);
		ProdutoDomain produtoDomain6 = new ProdutoDomain(null, "Colcha", 200.00);
		ProdutoDomain produtoDomain7 = new ProdutoDomain(null, "TV true color", 1200.00);
		ProdutoDomain produtoDomain8 = new ProdutoDomain(null, "Roçadeira", 800.00);
		ProdutoDomain produtoDomain9 = new ProdutoDomain(null, "Abajour", 100.00);
		ProdutoDomain produtoDomain10 = new ProdutoDomain(null, "Pente", 5.00);
		ProdutoDomain produtoDomain11 = new ProdutoDomain(null, "Shampoo", 15.00);
		
		categoriaDomain1.getProdutos().addAll(Arrays.asList(produtoDomain1, produtoDomain2, produtoDomain3));
		categoriaDomain2.getProdutos().addAll(Arrays.asList(produtoDomain2, produtoDomain4));
		categoriaDomain3.getProdutos().addAll(Arrays.asList(produtoDomain5, produtoDomain6));
		categoriaDomain4.getProdutos().addAll(Arrays.asList(produtoDomain1, produtoDomain2, produtoDomain3, produtoDomain7));
		categoriaDomain5.getProdutos().addAll(Arrays.asList(produtoDomain8));
		categoriaDomain6.getProdutos().addAll(Arrays.asList(produtoDomain9, produtoDomain10));
		categoriaDomain7.getProdutos().addAll(Arrays.asList(produtoDomain11));
		
		produtoDomain1.getCategorias().addAll(Arrays.asList(categoriaDomain1, categoriaDomain4));
		produtoDomain2.getCategorias().addAll(Arrays.asList(categoriaDomain1, categoriaDomain2, categoriaDomain4));
		produtoDomain3.getCategorias().addAll(Arrays.asList(categoriaDomain1, categoriaDomain4));
		produtoDomain4.getCategorias().addAll(Arrays.asList(categoriaDomain2));
		produtoDomain5.getCategorias().addAll(Arrays.asList(categoriaDomain3));
		produtoDomain6.getCategorias().addAll(Arrays.asList(categoriaDomain3));
		produtoDomain7.getCategorias().addAll(Arrays.asList(categoriaDomain4));
		produtoDomain8.getCategorias().addAll(Arrays.asList(categoriaDomain5));
		produtoDomain9.getCategorias().addAll(Arrays.asList(categoriaDomain6));
		produtoDomain10.getCategorias().addAll(Arrays.asList(categoriaDomain6));
		produtoDomain11.getCategorias().addAll(Arrays.asList(categoriaDomain7));
		
		categoriaRepository.saveAll(Arrays.asList(categoriaDomain1, categoriaDomain2, categoriaDomain3, categoriaDomain4,
				categoriaDomain5, categoriaDomain6, categoriaDomain7));
		produtoRepository.saveAll(Arrays.asList(produtoDomain1, produtoDomain2, produtoDomain3, produtoDomain4,
				produtoDomain5, produtoDomain6, produtoDomain7, produtoDomain8, produtoDomain9, produtoDomain10,
				produtoDomain11));

		EstadoDomain estadoDomain1 = new EstadoDomain(null, "Minas Gerais");
		EstadoDomain estadoDomain2 = new EstadoDomain(null, "São Paulo");
		
		CidadeDomain cidadeDomain1 = new CidadeDomain(null, "Uberlândia", estadoDomain1);
		CidadeDomain cidadeDomain2 = new CidadeDomain(null, "São Paulo", estadoDomain2);
		CidadeDomain cidadeDomain3 = new CidadeDomain(null, "Campinas", estadoDomain2);
		
		estadoDomain1.getCidades().addAll(Arrays.asList(cidadeDomain1));
		estadoDomain2.getCidades().addAll(Arrays.asList(cidadeDomain2, cidadeDomain3));
		
		estadoRepository.saveAll(Arrays.asList(estadoDomain1, estadoDomain2));
		cidadeRepository.saveAll(Arrays.asList(cidadeDomain1, cidadeDomain2, cidadeDomain3));
		
		ClienteDomain clienteDomain1 = new ClienteDomain(
				null, "Caio Bastos", "caio12bastos@gmail.com", "36378912377", TipoClienteEnum.PESSOAFISICA, 
				bCryptPasswordEncoder.encode("123456789"));
		clienteDomain1.getTelefones().addAll(Arrays.asList("1112345678", "1198765432"));
		
		ClienteDomain clienteDomain2= new ClienteDomain(
				null, "Juliana", "juliana.andraden07@gmail.com", "47150956882", TipoClienteEnum.PESSOAFISICA, 
				bCryptPasswordEncoder.encode("12345"));
		clienteDomain1.getTelefones().addAll(Arrays.asList("1198765432"));
		clienteDomain2.addPerfil(ClientePerfil.ADMIN);
		
		EnderecoDomain enderecoDomain1 = new EnderecoDomain(
				null, "Rua Flores", "300", "Apto 300", "Jardim", "38220834", clienteDomain1, cidadeDomain1);
		EnderecoDomain enderecoDomain2 = new EnderecoDomain(
				null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", clienteDomain1, cidadeDomain2);
		EnderecoDomain enderecoDomain3 = new EnderecoDomain(
				null, "Rua Doutor Carlos Siqueira Neto", "403", "Casa", "Taboao", "06766200", clienteDomain2, cidadeDomain2);
		
		clienteDomain1.getEnderecos().addAll(Arrays.asList(enderecoDomain1, enderecoDomain2));
		clienteDomain2.getEnderecos().addAll(Arrays.asList(enderecoDomain3));
				
		clienteRepository.saveAll(Arrays.asList(clienteDomain1, clienteDomain2));
		enderecoRepository.saveAll(Arrays.asList(enderecoDomain1, enderecoDomain2, enderecoDomain3));
		
		PedidoDomain pedidoDomain1 = new PedidoDomain(null, simpleDateFormat.parse("30/09/2017 10:32"), 
				clienteDomain1, enderecoDomain1);
		PedidoDomain pedidoDomain2 = new PedidoDomain(null, simpleDateFormat.parse("10/10/2017 19:35"), 
				clienteDomain1, enderecoDomain2);
		
		PagamentoDomain pagamentoDomain1 = new PagamentoCartaoDomain(null, EstadoPagamentoEnum.QUITADO, pedidoDomain1, 6);
		pedidoDomain1.setPagamento(pagamentoDomain1);
		
		PagamentoDomain pagamentoDomain2 = new PagamentoBoletoDomain(null, EstadoPagamentoEnum.PENDENTE, pedidoDomain2, 
				simpleDateFormat.parse("20/10/2017 00:00"), null);	
		pedidoDomain2.setPagamento(pagamentoDomain2);
		
		clienteDomain1.getPedidos().addAll(Arrays.asList(pedidoDomain1, pedidoDomain2));
		
		pedidoRepository.saveAll(Arrays.asList(pedidoDomain1, pedidoDomain2));
		pagamentoRepository.saveAll(Arrays.asList(pagamentoDomain1, pagamentoDomain2));
		
		ItemPedidoDomain itemPedidoDomain1 = new ItemPedidoDomain(pedidoDomain1, produtoDomain1, 0.00, 1, 2000.00);
		ItemPedidoDomain itemPedidoDomain2 = new ItemPedidoDomain(pedidoDomain1, produtoDomain3, 0.00, 2, 80.00);
		ItemPedidoDomain itemPedidoDomain3 = new ItemPedidoDomain(pedidoDomain2, produtoDomain2, 100.00, 1, 800.00);
		
		pedidoDomain1.getItens().addAll(Arrays.asList(itemPedidoDomain1, itemPedidoDomain2));
		pedidoDomain2.getItens().addAll(Arrays.asList(itemPedidoDomain3));
		
		produtoDomain1.getItens().addAll(Arrays.asList(itemPedidoDomain1));
		produtoDomain2.getItens().addAll(Arrays.asList(itemPedidoDomain3));
		produtoDomain3.getItens().addAll(Arrays.asList(itemPedidoDomain2));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedidoDomain1, itemPedidoDomain2, itemPedidoDomain3));

	}

}
