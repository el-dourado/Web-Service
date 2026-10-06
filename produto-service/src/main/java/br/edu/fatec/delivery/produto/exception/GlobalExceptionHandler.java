package br.edu.fatec.delivery.produto.exception;

import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.namespace.QName;

/**
 * Handler global para tratamento de exceções no contexto SOAP.
 * Converte exceções de negócio em SOAP Fault 1.1.
 */
public class GlobalExceptionHandler extends SoapFaultMappingExceptionResolver {

    private static final QName FAULT_CODE = new QName("http://schemas.xmlsoap.org/soap/envelope/", "Client");

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        if (ex instanceof ProdutoNotFoundException || ex instanceof BusinessException) {
            fault.setFaultCode(FAULT_CODE);
            fault.setFaultStringOrReason(ex.getMessage());

            SoapFaultDetail detail = fault.addFaultDetail();
            detail.addFaultDetailElement(
                    new QName("http://fatec.edu.br/delivery/produto", "error"))
                    .addText(ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }
}