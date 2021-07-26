package com.residencia.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Categoria {
    private Integer categoriaId;
    private String nomeCategoria;
    private String descricaoCategoria;
    private List<Produto> produtosByCategoriaId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    @NotBlank(message = "O campo 'nome da categoria' não pode ser nulo ou vazio.")
    @Column(name = "nome_categoria")
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @NotBlank(message = "O campo 'descrição da cateogria' não pode ser nulo ou vazio.")
    @Column(name = "descricao_categoria")
    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "categoria")
    public List<Produto> getProdutosByCategoriaId() {
        return produtosByCategoriaId;
    }

    public void setProdutosByCategoriaId(List<Produto> produtosByCategoriaId) {
        this.produtosByCategoriaId = produtosByCategoriaId;
    }
}
