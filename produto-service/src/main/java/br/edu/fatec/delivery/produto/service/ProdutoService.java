package br.edu.fatec.delivery.produto.service;

import br.edu.fatec.delivery.produto.entity.Produto;
import br.edu.fatec.delivery.produto.exception.BusinessException;
import br.edu.fatec.delivery.produto.exception.ProdutoNotFoundException;
import br.edu.fatec.delivery.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para regra de negócio relacionada a produtos.
 */
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * Consulta um produto pelo código.
     *
     * @param codigoProduto código do produto a ser consultado
     * @return produto encontrado
     * @throws ProdutoNotFoundException quando o produto não existe
     * @throws BusinessException quando ocorre erro de negócio
     */
    public Produto consultarPorCodigo(String codigoProduto) {
        if (codigoProduto == null || codigoProduto.trim().isEmpty()) {
            throw new BusinessException("Código do produto é obrigatório");
        }

        return produtoRepository.findById(codigoProduto)
                .orElseThrow(() -> new ProdutoNotFoundException(
                        "Produto não encontrado com o código: " + codigoProduto));
    }

    /**
     * Retorna todos os produtos disponíveis.
     *
     * @return lista de produtos
     */
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    /**
     * Salva um novo produto ou atualiza um existente.
     *
     * @param produto produto a ser salvo
     * @return produto salvo
     */
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }
}