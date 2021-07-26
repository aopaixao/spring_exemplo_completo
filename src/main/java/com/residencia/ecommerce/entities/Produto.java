package com.residencia.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {
    private Integer produtoId;
    private String nomeProduto;
    private String descricaoProduto;
    private Double precoProduto;
    private Integer qtdEstoque;
    private Calendar dataCadastroProduto;
    private String imagem;
    private Categoria categoria;
    private List<ProdutoPedido> produtoPedido;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    @Column(name = "nome_produto")
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Column(name = "descricao_produto")
    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }


    @Column(name = "preco_produto")
    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    @Column(name = "qtd_estoque")
    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    @Column(name = "data_cadastro_produto")
    public Calendar getDataCadastroProduto() {
        return dataCadastroProduto;
    }

    public void setDataCadastroProduto(Calendar dataCadastroProduto) {
        this.dataCadastroProduto = dataCadastroProduto;
    }

    @Column(name = "imagem")
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "produtoByProdutoId")
    public List<ProdutoPedido> getProdutoPedidos() {
        return produtoPedido;
    }

    public void setProdutoPedidos(List<ProdutoPedido> produtoPedido) {
        this.produtoPedido = produtoPedido;
    }
}
