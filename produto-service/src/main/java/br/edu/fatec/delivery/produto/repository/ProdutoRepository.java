package br.edu.fatec.delivery.produto.repository;

import br.edu.fatec.delivery.produto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações de persistência da entidade Produto.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {
}