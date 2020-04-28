package com.brq.cursomc.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brq.cursomc.domain.CategoriaDomain;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<CategoriaDomain> listar() {
		
		CategoriaDomain categoriaDomain1 = new CategoriaDomain(1, "Informática");
		CategoriaDomain categoriaDomain2 = new CategoriaDomain(1, "Escritório");
		
		List<CategoriaDomain> listaCategoria = new ArrayList<>();
		listaCategoria.add(categoriaDomain1);
		listaCategoria.add(categoriaDomain2);
		
		return listaCategoria;
	}
	
}
