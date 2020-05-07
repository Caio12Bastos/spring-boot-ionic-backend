package com.brq.cursomc.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brq.cursomc.domain.CategoriaDomain;
import com.brq.cursomc.dto.CategoriaDTO;
import com.brq.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoriaDomain> buscarIdCategoria(@PathVariable Integer id) {
		
		CategoriaDomain categoriaDomain = categoriaService.buscar(id);
		
		return ResponseEntity.ok().body(categoriaDomain);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		CategoriaDomain categoriaDomain = categoriaService.transformDTO(categoriaDTO);
		categoriaDomain = categoriaService.inserir(categoriaDomain);
		
		URI uriCategoria = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoriaDomain.getId()).toUri();
		
		return ResponseEntity.created(uriCategoria).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id) {
		CategoriaDomain categoriaDomain = categoriaService.transformDTO(categoriaDTO);
		
		categoriaDomain.setId(id);
		categoriaDomain = categoriaService.atualizar(categoriaDomain);
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletardCategoria(@PathVariable Integer id) {
		
		categoriaService.detelar(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarTodasCategoria() {
		
		List<CategoriaDomain> listaCategoriaDomain = categoriaService.buscarTodas();
		List<CategoriaDTO> listaCategoriaDTO = 
				listaCategoriaDomain.stream()
					.map(categoriaDomain -> 
						new CategoriaDTO(categoriaDomain))
					.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaCategoriaDTO);
	}
	
	@RequestMapping(value = "/pagina", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> buscarPaginacao(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina, 
			@RequestParam(value = "linhaPorPagina", defaultValue = "24") Integer linhaPorPagina, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "order", defaultValue = "ASC") String order) {
		
		Page<CategoriaDomain> listaCategoriaDomain = categoriaService.buscaPaginacao
				(pagina, linhaPorPagina, orderBy, order);
		
		Page<CategoriaDTO> listaCategoriaDTO = 
				listaCategoriaDomain.map(categoriaDomain -> new CategoriaDTO(categoriaDomain));
		
		return ResponseEntity.ok().body(listaCategoriaDTO);
	}
	
	@RequestMapping(value = "/foto", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadFotoCategoria(@RequestParam (name="file") MultipartFile multipartFile) {
		
		URI uri = categoriaService.uploadPerfilFoto(multipartFile);
		
		return ResponseEntity.created(uri).build();
	}
	
}
