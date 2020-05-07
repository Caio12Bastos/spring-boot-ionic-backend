package com.brq.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.dto.CategoriaDTO;
import com.brq.cursomc.repositories.CategoriaRepository;
import com.brq.cursomc.security.UserSpringSecurity;
import com.brq.cursomc.services.exception.AuthorizationException;
import com.brq.cursomc.services.exception.DataIntegrityException;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ImagemService imagemService;

	@Autowired
	private S3Service s3Service;
	
	@Value("${img.prefix.categora}")
	private String prefixoCategoria;
	
	@Value("${img.profile.size}")
	private int tamanho;
	
	public CategoriaDomain buscar(Integer id) throws RecursoNaoEncontrado {
		
		Optional<CategoriaDomain> optCategoriaDomain = categoriaRepository.findById(id);
		return optCategoriaDomain.orElseThrow(() -> new RecursoNaoEncontrado( 
				"Objeto não encontrado! Id: " + id)); 
	}

	public CategoriaDomain inserir(CategoriaDomain categoria) {
		
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public CategoriaDomain atualizar(CategoriaDomain categoria) {
		
		CategoriaDomain novoCategoriaDomain = buscar(categoria.getId());
		atualizaDados(novoCategoriaDomain, categoria);
		return categoriaRepository.save(novoCategoriaDomain);
	}

	public void detelar(Integer id) {
		
		buscar(id);
		try {
			categoriaRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException excecao) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<CategoriaDomain> buscarTodas() {
		return categoriaRepository.findAll();
	}
	
	public Page<CategoriaDomain> buscaPaginacao(Integer pagina, Integer linhaPorPagina, String orderBy, String order) {
		
		PageRequest paginaRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(order), orderBy);
		return categoriaRepository.findAll(paginaRequest);
	}
	
	public CategoriaDomain transformDTO(CategoriaDTO categoriaDTO) {
		return new CategoriaDomain(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	private void atualizaDados(CategoriaDomain novoCategoriaDomain, CategoriaDomain categoria) {
		novoCategoriaDomain.setNome(categoria.getNome());
	}
	
	public URI uploadPerfilFoto(MultipartFile multipartFile) {
		
		UserSpringSecurity userSpringSecurity = UserService.autenticado();
		if(userSpringSecurity == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImagem = imagemService.getJpgImagemArquivo(multipartFile);
		jpgImagem = imagemService.cortarQuadrado(jpgImagem);
		jpgImagem = imagemService.redimensionar(jpgImagem, tamanho);
		
		String nomeArquivo = prefixoCategoria + userSpringSecurity.getId() + ".jpg";
		return s3Service.uploadFile(imagemService.getInputStream(jpgImagem, "jpg"), nomeArquivo, "imagem");
		
	}
	
}
