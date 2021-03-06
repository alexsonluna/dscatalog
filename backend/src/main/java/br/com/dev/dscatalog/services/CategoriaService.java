package br.com.dev.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dev.dscatalog.dto.CategoriaDTO;
import br.com.dev.dscatalog.entidades.Categoria;
import br.com.dev.dscatalog.repository.CategoriaRepository;
import br.com.dev.dscatalog.services.exceptions.DataBaseException;
import br.com.dev.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public Page<CategoriaDTO> findAllPaged(PageRequest pageRequest) {
		Page<Categoria> lstCategoria = repository.findAll(pageRequest);

		return lstCategoria.map(lst -> new CategoriaDTO(lst));
	}

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> optionalCategoria = repository.findById(id);

		Categoria categoria = optionalCategoria
				.orElseThrow(() -> new ResourceNotFoundException("Objeto não endontrado"));

		return new CategoriaDTO(categoria);
	}

	@Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria categoria = new Categoria();
		categoria.setNome(dto.getNome());

		categoria = repository.save(categoria);

		return new CategoriaDTO(categoria);
	}

	@Transactional
	public CategoriaDTO update(Long id, CategoriaDTO dto) {

		try {
			Categoria categoria = repository.getOne(id);

			categoria.setNome(dto.getNome());

			categoria = repository.save(categoria);

			return new CategoriaDTO(categoria);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado" + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado" + id);

		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de Integridade");
		}
	}

}
