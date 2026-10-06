package br.edu.fatec.delivery.produto.endpoint;

import br.edu.fatec.delivery.produto.entity.Produto;
import br.edu.fatec.delivery.produto.model.ConsultarProdutoRequest;
import br.edu.fatec.delivery.produto.model.ConsultarProdutoResponse;
import br.edu.fatec.delivery.produto.service.ProdutoService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Endpoint SOAP para operações relacionadas a produtos.
 * Recebe requisições SOAP e retorna respostas no formato definido pelo XSD.
 */
@Endpoint
public class ProdutoEndpoint {

    private static final String NAMESPACE = "http://fatec.edu.br/delivery/produto";

    private final ProdutoService produtoService;

    public ProdutoEndpoint(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * Operação SOAP para consultar produto por código.
     *
     * @param request requisição contendo o código do produto
     * @return resposta com os detalhes do produto
     */
    @PayloadRoot(namespace = NAMESPACE, localPart = "consultarProdutoRequest")
    @ResponsePayload
    public ConsultarProdutoResponse consultarProduto(@RequestPayload ConsultarProdutoRequest request) {

        // Busca o produto através do service
        Produto produto = produtoService.consultarPorCodigo(request.getCodigoProduto());

        // Mapeia a entidade para o objeto de resposta SOAP
        ConsultarProdutoResponse response = new ConsultarProdutoResponse();
        response.setCodigoProduto(produto.getCodigoProduto());
        response.setNomeProduto(produto.getNomeProduto());
        response.setDescricao(produto.getDescricao());
        response.setMarca(produto.getMarca());
        response.setQuantidadeEstoque(produto.getQuantidadeEstoque());

        return response;
    }
}