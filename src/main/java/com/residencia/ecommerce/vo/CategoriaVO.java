package com.residencia.ecommerce.vo;

import java.util.List;

public class CategoriaVO {
    private Integer categoriaId;
    private String nomeCategoria;
    private String descricaoCategoria;
    private List<ProdutoVO> produtosByCategoriaId;

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public List<ProdutoVO> getProdutosByCategoriaId() {
        return produtosByCategoriaId;
    }

    public void setProdutosByCategoriaId(List<ProdutoVO> produtosByCategoriaId) {
        this.produtosByCategoriaId = produtosByCategoriaId;
    }
}
