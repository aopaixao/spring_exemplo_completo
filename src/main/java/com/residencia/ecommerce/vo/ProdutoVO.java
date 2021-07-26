package com.residencia.ecommerce.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProdutoVO {
    private Integer produtoId;
    private String nomeProduto;
    private String descricaoProduto;
    private Double precoProduto;
    private Integer qtdEstoque;
    private String imagem;
    private Integer categoria;
    private MultipartFile imagemProduto;

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public MultipartFile getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(MultipartFile imagemProduto) {
        this.imagemProduto = imagemProduto;
    }
}
