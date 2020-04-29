package com.brq.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.domain.CidadeDomain;
import com.brq.cursomc.domain.ClienteDomain;
import com.brq.cursomc.domain.EnderecoDomain;
import com.brq.cursomc.domain.EstadoDomain;
import com.brq.cursomc.domain.ProdutoDomain;
import com.brq.cursomc.enums.TipoCliente;
import com.brq.cursomc.repositories.CategoriaRepository;
import com.brq.cursomc.repositories.CidadeRepository;
import com.brq.cursomc.repositories.ClienteRepository;
import com.brq.cursomc.repositories.EnderecoRepository;
import com.brq.cursomc.repositories.EstadoRepository;
import com.brq.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		CategoriaDomain categoriaDomain1 = new CategoriaDomain(null, "Informática");
		CategoriaDomain categoriaDomain2 = new CategoriaDomain(null, "Escritório");
		
		ProdutoDomain produtoDomain1 = new ProdutoDomain(null, "Computador", 2000.00);
		ProdutoDomain produtoDomain2 = new ProdutoDomain(null, "Impressora", 800.00);
		ProdutoDomain produtoDomain3 = new ProdutoDomain(null, "Mouse", 80.00);
		
		categoriaDomain1.getProdutos().addAll(Arrays.asList(produtoDomain1, produtoDomain2, produtoDomain3));
		categoriaDomain2.getProdutos().addAll(Arrays.asList(produtoDomain2));
		
		produtoDomain1.getCategorias().addAll(Arrays.asList(categoriaDomain1));
		produtoDomain2.getCategorias().addAll(Arrays.asList(categoriaDomain1, categoriaDomain2));
		produtoDomain3.getCategorias().addAll(Arrays.asList(categoriaDomain1));
		
		categoriaRepository.saveAll(Arrays.asList(categoriaDomain1, categoriaDomain2));
		produtoRepository.saveAll(Arrays.asList(produtoDomain1, produtoDomain2, produtoDomain3));

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
				null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		clienteDomain1.getTelefones().addAll(Arrays.asList("1112345678", "1198765432"));
		
		EnderecoDomain enderecoDomain1 = new EnderecoDomain(
				null, "Rua Flores", "300", "Apto 300", "Jardim", "38220834", clienteDomain1, cidadeDomain1);
		EnderecoDomain enderecoDomain2 = new EnderecoDomain(
				null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", clienteDomain1, cidadeDomain2);
		
		clienteDomain1.getEnderecos().addAll(Arrays.asList(enderecoDomain1, enderecoDomain2));
				
		clienteRepository.saveAll(Arrays.asList(clienteDomain1));
		enderecoRepository.saveAll(Arrays.asList(enderecoDomain1, enderecoDomain2));
	}

}
