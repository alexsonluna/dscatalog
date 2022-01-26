package br.com.dev.dscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dev.dscatalog.dto.CategoriaDTO;
import br.com.dev.dscatalog.entidades.Categoria;
import br.com.dev.dscatalog.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll() {
		List<Categoria> lstCategoria = repository.findAll();

		return lstCategoria.stream().map(lst -> new CategoriaDTO(lst)).collect(Collectors.toList());
	}

}
