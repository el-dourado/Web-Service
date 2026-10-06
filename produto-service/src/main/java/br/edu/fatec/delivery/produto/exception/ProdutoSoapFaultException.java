package br.edu.fatec.delivery.produto.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * Exceção customizada para SOAP Fault quando produto não é encontrado.
 * Retorna um SOAP Fault 1.1 com faultcode soap:Client.
 */
@SoapFault(faultCode = FaultCode.CLIENT)
public class ProdutoSoapFaultException extends RuntimeException {

    public ProdutoSoapFaultException(String message) {
        super(message);
    }

    public ProdutoSoapFaultException(String message, Throwable cause) {
        super(message, cause);
    }
}