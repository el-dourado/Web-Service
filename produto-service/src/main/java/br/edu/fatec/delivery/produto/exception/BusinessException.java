package br.edu.fatec.delivery.produto.exception;

/**
 * Exceção de negócio genérica.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}