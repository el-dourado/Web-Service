# Produto SOAP Service

Serviço SOAP para consultar produtos de hardware e componentes eletrônicos.

## Tecnologias

- Java 17
- Spring Boot 3.2.0
- Spring Web Services
- Maven
- H2 Database
- SOAP 1.1
- WSDL 1.1
- JAXB

## Estrutura do projeto

O projeto usa a abordagem **Contract First**: o contrato é definido no arquivo XSD e as classes Java são geradas a partir dele.

```text
br.edu.fatec.delivery.produto
├── config/       # Configurações do serviço
├── endpoint/     # Endpoints SOAP
├── entity/       # Entidades do banco
├── exception/    # Exceções
├── model/        # Classes geradas pelo JAXB
├── repository/   # Repositórios
└── service/      # Regras de negócio
```

### Exemplo de requisição

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:prod="http://fatec.edu.br/delivery/produto">
   <soapenv:Header/>
   <soapenv:Body>
      <prod:consultarProdutoRequest>
         <prod:codigoProduto>RTX4090</prod:codigoProduto>
      </prod:consultarProdutoRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### Exemplo de resposta

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Body>
      <ns2:consultarProdutoResponse xmlns:ns2="http://fatec.edu.br/delivery/produto">
         <ns2:codigoProduto>RTX4090</ns2:codigoProduto>
         <ns2:nomeProduto>NVIDIA GeForce RTX 4090</ns2:nomeProduto>
         <ns2:descricao>Placa de vídeo com 24GB GDDR6X</ns2:descricao>
         <ns2:marca>NVIDIA</ns2:marca>
         <ns2:quantidadeEstoque>15</ns2:quantidadeEstoque>
      </ns2:consultarProdutoResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

Se o código não existir, o serviço retorna um **SOAP Fault** informando que o produto não foi encontrado.

## Produtos cadastrados

| Código | Nome | Marca |
|---|---|---|
| RTX4090 | NVIDIA GeForce RTX 4090 | NVIDIA |
| I9-14900K | Intel Core i9-14900K | Intel |
| RYZEN9-7950X | AMD Ryzen 9 7950X | AMD |
| DDR5-6000-32GB | Kingston Fury Beast DDR5 | Kingston |
| SSD-990PRO-2TB | Samsung 990 PRO 2TB | Samsung |
| B650-GAMING | ASUS ROG Strix B650-A | ASUS |

## Endpoints

- **WSDL:** `http://localhost:8080/ws/produtos.wsdl`
- **Serviço SOAP:** `http://localhost:8080/ws`
- **Console H2:** `http://localhost:8080/h2-console`

## Erros

O serviço usa o padrão **SOAP Fault 1.1** para informar erros:

- `ProdutoNotFoundException`: produto não encontrado.
- `BusinessException`: erro de validação ou regra de negócio.

Os detalhes do erro são enviados no elemento `<detail>` da resposta SOAP.
