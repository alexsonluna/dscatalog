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

import br.com.dev.dscatalog.dto.ProdutoDTO;
import br.com.dev.dscatalog.entidades.Produto;
import br.com.dev.dscatalog.repository.ProdutoRepository;
import br.com.dev.dscatalog.services.exceptions.DataBaseException;
import br.com.dev.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAllPaged(PageRequest pageRequest) {
		Page<Produto> lstProduto = repository.findAll(pageRequest);

		return lstProduto.map(lst -> new ProdutoDTO(lst));
	}

	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long id) {
		Optional<Produto> optionalProduto = repository.findById(id);

		Produto produto = optionalProduto
				.orElseThrow(() -> new ResourceNotFoundException("Objeto não endontrado"));

		return new ProdutoDTO(produto,produto.getLstCategoria());
	}

	@Transactional
	public ProdutoDTO insert(ProdutoDTO dto) {
		Produto produto = new Produto();
//		produto.setNome(dto.getNome());

		produto = repository.save(produto);

		return new ProdutoDTO(produto);
	}

	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO dto) {

		try {
			Produto produto = repository.getOne(id);

//			produto.setNome(dto.getNome());

			produto = repository.save(produto);

			return new ProdutoDTO(produto);

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
