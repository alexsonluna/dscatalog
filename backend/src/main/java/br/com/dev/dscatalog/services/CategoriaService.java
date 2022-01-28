package br.com.dev.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dev.dscatalog.dto.CategoriaDTO;
import br.com.dev.dscatalog.entidades.Categoria;
import br.com.dev.dscatalog.repository.CategoriaRepository;
import br.com.dev.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll() {
		List<Categoria> lstCategoria = repository.findAll();

		return lstCategoria.stream().map(lst -> new CategoriaDTO(lst)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> optionalCategoria = repository.findById(id);

		Categoria categoria = optionalCategoria.orElseThrow(() -> new EntityNotFoundException("Objeto não endontrado"));

		return new CategoriaDTO(categoria);
	}

	@Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria categoria = new Categoria();
		categoria.setNome(dto.getNome());

		categoria = repository.save(categoria);

		return new CategoriaDTO(categoria);
	}

}
