package br.edu.fatec.delivery.produto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade que representa um produto de hardware/componentes eletrônicos.
 */
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @Column(name = "codigo_produto", length = 20)
    private String codigoProduto;

    @Column(name = "nome_produto", nullable = false, length = 150)
    private String nomeProduto;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "marca", nullable = false, length = 80)
    private String marca;

    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;

    public Produto() {
    }

    public Produto(String codigoProduto, String nomeProduto, String descricao, String marca, Integer quantidadeEstoque) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.marca = marca;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}