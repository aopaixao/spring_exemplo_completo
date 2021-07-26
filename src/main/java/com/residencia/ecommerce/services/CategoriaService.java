package com.residencia.ecommerce.services;

import com.residencia.ecommerce.entities.Categoria;
import com.residencia.ecommerce.entities.Produto;
import com.residencia.ecommerce.repositories.CategoriaRepository;
import com.residencia.ecommerce.vo.CategoriaVO;
import com.residencia.ecommerce.vo.ProdutoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository categoriaRepository;

//******************************************************************************************************************

    public CategoriaVO findByNomeCat(String nome) {
        Categoria categoria = categoriaRepository.findByNomeCategoria(nome);
        return converteEntidadeParaVO(categoria);
    }

//******************************************************************************************************************

    public List<CategoriaVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<Categoria> listCategoria = null;
        List<Categoria> listCategoriaComPaginacao = null;
        List<CategoriaVO> listCategoriaVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listCategoriaComPaginacao = categoriaRepository.findAll(page).getContent();

                for (Categoria lCategoria : listCategoriaComPaginacao) {
                    listCategoriaVO.add(converteEntidadeParaVO(lCategoria));
                }

            } else {
                listCategoria = categoriaRepository.findAll();

                for (Categoria lCategoria : listCategoria) {
                    listCategoriaVO.add(converteEntidadeParaVO(lCategoria));
                }
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de categorias ::" + e.getMessage());
        }
        return listCategoriaVO;
    }

//******************************************************************************************************************

    public long count() {
        return categoriaRepository.count();
    }

//******************************************************************************************************************

    public CategoriaVO saveVO(CategoriaVO categoriaVO) {
        Categoria novaCategoria = converteVOParaEntidade(categoriaVO);
        categoriaRepository.save(novaCategoria);

        return converteEntidadeParaVO(novaCategoria);
    }

//********************************************************************************************************************

    public Categoria update(Integer id, Categoria categoria) {
        Categoria newCategoria = categoriaRepository.findById(id).get();
        updateDados(newCategoria, categoria);
        return categoriaRepository.save(newCategoria);
    }

    private void updateDados(Categoria newCategoria, Categoria categoria) {
        newCategoria.setCategoriaId(categoria.getCategoriaId());
        newCategoria.setNomeCategoria(categoria.getNomeCategoria());
        newCategoria.setDescricaoCategoria(categoria.getDescricaoCategoria());
    }
//********************************************************************************************************************

    public void delete(Integer id) {
        if (id != null) {
            categoriaRepository.deleteById(id);
        }
    }

    private CategoriaVO converteEntidadeParaVO(Categoria categoria) {
        CategoriaVO categoriaVO = new CategoriaVO();
        List<ProdutoVO> listProdutoVO = new ArrayList<>();

        categoriaVO.setCategoriaId(categoria.getCategoriaId());
        categoriaVO.setNomeCategoria(categoria.getNomeCategoria());
        categoriaVO.setDescricaoCategoria(categoria.getDescricaoCategoria());

        for (Produto produto : categoria.getProdutosByCategoriaId()) {
            ProdutoVO produtoVO = new ProdutoVO();

            produtoVO.setProdutoId(produto.getProdutoId());
            produtoVO.setNomeProduto(produto.getNomeProduto());
            produtoVO.setDescricaoProduto(produto.getDescricaoProduto());
            produtoVO.setPrecoProduto(produto.getPrecoProduto());
            produtoVO.setQtdEstoque(produto.getQtdEstoque());
            produtoVO.setImagem(produto.getImagem());

            listProdutoVO.add(produtoVO);
        }

        categoriaVO.setProdutosByCategoriaId(listProdutoVO);

        return categoriaVO;
    }

    private Categoria converteVOParaEntidade(CategoriaVO categoriaVO) {
        Categoria categoria = new Categoria();

        categoria.setCategoriaId(categoriaVO.getCategoriaId());
        categoria.setNomeCategoria(categoriaVO.getNomeCategoria());
        categoria.setDescricaoCategoria(categoriaVO.getDescricaoCategoria());

        List<Produto> listProduto = new ArrayList<>();
        for (ProdutoVO lProdutoVO : categoriaVO.getProdutosByCategoriaId()) {
            Produto produto = new Produto();

            produto.setProdutoId(lProdutoVO.getProdutoId());
            produto.setNomeProduto(lProdutoVO.getNomeProduto());
            produto.setDescricaoProduto(lProdutoVO.getDescricaoProduto());
            produto.setPrecoProduto(lProdutoVO.getPrecoProduto());
            produto.setQtdEstoque(lProdutoVO.getQtdEstoque());
            produto.setImagem(lProdutoVO.getImagem());

            listProduto.add(produto);
        }
        categoria.setProdutosByCategoriaId(listProduto);
        return categoria;
    }
}
