package com.brq.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.domain.ProdutoDomain;
import com.brq.cursomc.repositories.CategoriaRepository;
import com.brq.cursomc.repositories.ProdutoRepository;
import com.brq.cursomc.security.UserSpringSecurity;
import com.brq.cursomc.services.exception.AuthorizationException;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ImagemService imagemService;

	@Autowired
	private S3Service s3Service;
	
	@Value("${img.prefix.produto}")
	private String prefixoProduto;
	
	@Value("${img.profile.size}")
	private int tamanho;
	
	public ProdutoDomain buscar(Integer id) throws RecursoNaoEncontrado {
		
		Optional<ProdutoDomain> optProdutoDomain = produtoRepository.findById(id);
		return optProdutoDomain.orElseThrow(() -> new RecursoNaoEncontrado( 
				"Objeto não encontrado! Id: " + id)); 
	}
	
	public Page<ProdutoDomain> procurar(String nome, List<Integer> ids, Integer pagina, 
			Integer linhaPorPagina, String orderBy, String order) {
		
		PageRequest paginaRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(order), orderBy);
		List<CategoriaDomain> listaCategoria = categoriaRepository.findAllById(ids);
		
		return produtoRepository.procurar(nome, listaCategoria, paginaRequest);
	}
	
	public URI uploadPerfilFoto(MultipartFile multipartFile) {
		
		UserSpringSecurity userSpringSecurity = UserService.autenticado();
		if(userSpringSecurity == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImagem = imagemService.getJpgImagemArquivo(multipartFile);
		jpgImagem = imagemService.cortarQuadrado(jpgImagem);
		jpgImagem = imagemService.redimensionar(jpgImagem, tamanho);
		
		String nomeArquivo = prefixoProduto + userSpringSecurity.getId() + ".jpg";
		return s3Service.uploadFile(imagemService.getInputStream(jpgImagem, "jpg"), nomeArquivo, "imagem");
		
	}
}
