package br.com.dev.dscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev.dscatalog.entidades.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
