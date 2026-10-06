package br.edu.fatec.delivery.produto.exception;

/**
 * Exceção lançada quando um produto não é encontrado.
 */
public class ProdutoNotFoundException extends BusinessException {

    public ProdutoNotFoundException(String message) {
        super(message);
    }
}