package br.com.dev.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.dscatalog.entidades.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> lstCategoria = new ArrayList<>();
		lstCategoria.add(new Categoria(1L, "livros"));
		lstCategoria.add(new Categoria(2L, "Eletronicos"));

		return ResponseEntity.ok().body(lstCategoria);
	}

}
