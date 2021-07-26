package com.residencia.ecommerce.services;

import com.residencia.ecommerce.entities.Produto;
import com.residencia.ecommerce.repositories.CategoriaRepository;
import com.residencia.ecommerce.repositories.ProdutoRepository;
import com.residencia.ecommerce.vo.ProdutoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    public ProdutoRepository produtoRepository;

    @Autowired
    public CategoriaRepository categoriaRepository;

    @Autowired
    public ArquivoService arquivoService;

//******************************************************************************************************************

    public ProdutoVO findByNomeProd(String nome) {
        Produto produto = produtoRepository.findByNomeProduto(nome);
        return converteEntidadeParaVO(produto);
    }

//******************************************************************************************************************

    public List<ProdutoVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<Produto> listProduto = null;
        List<Produto> listProdutoComPaginacao = null;
        List<ProdutoVO> listProdutoVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listProdutoComPaginacao = produtoRepository.findAll(page).getContent();

                for (Produto lProduto : listProdutoComPaginacao) {
                    listProdutoVO.add(converteEntidadeParaVO(lProduto));
                }

            } else {
                listProduto = produtoRepository.findAll();

                for (Produto lProduto : listProduto) {
                    listProdutoVO.add(converteEntidadeParaVO(lProduto));
                }
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de produtos ::" + e.getMessage());
        }
        return listProdutoVO;
    }

//******************************************************************************************************************


    public long count() {
        return produtoRepository.count();
    }

//******************************************************************************************************************

    public ProdutoVO saveVO(ProdutoVO produtoVO) {
        Produto novoProduto = converteVOParaEntidade(produtoVO);
        Produto produto = produtoRepository.save(novoProduto);

        //Salva a imagem na pasta usando o id do produto e o nome da imagem
        String fileName = arquivoService.storeFile(produtoVO.getImagemProduto(), produto.getProdutoId());

        //Atualiza o nome da imagem do produto no banco
        produto.setImagem(fileName);
        produto = produtoRepository.save(produto);

        return converteEntidadeParaVO(produto);
    }

//********************************************************************************************************************

    public Produto update(Integer id, Produto produto) {
        Produto newProduto = produtoRepository.findById(id).get();
        updateDados(newProduto, produto);
        return produtoRepository.save(newProduto);
    }

    private void updateDados(Produto newProduto, Produto produto) {
        newProduto.setProdutoId(produtoRepository.findById(produto.getProdutoId()).get().getProdutoId());
        newProduto.setNomeProduto(produto.getNomeProduto());
        newProduto.setDescricaoProduto(produto.getDescricaoProduto());
        newProduto.setPrecoProduto(produto.getPrecoProduto());
        newProduto.setQtdEstoque(produto.getQtdEstoque());
        newProduto.setImagem(produto.getImagem());
        newProduto.setCategoria(categoriaRepository.findById(produto.getCategoria().getCategoriaId()).get());
    }

//********************************************************************************************************************

    public void delete(Integer id) {
        if (id != null)
            produtoRepository.deleteById(id);
    }

    private ProdutoVO converteEntidadeParaVO(Produto produto) {
        ProdutoVO produtoVO = new ProdutoVO();

        produtoVO.setProdutoId(produto.getProdutoId());
        produtoVO.setNomeProduto(produto.getNomeProduto());
        produtoVO.setDescricaoProduto(produto.getDescricaoProduto());
        produtoVO.setPrecoProduto(produto.getPrecoProduto());
        produtoVO.setQtdEstoque(produto.getQtdEstoque());
        produtoVO.setImagem(produto.getImagem());

        return produtoVO;
    }

    private Produto converteVOParaEntidade(ProdutoVO produtoVO) {
        Produto produto = new Produto();

        produto.setProdutoId(produtoVO.getProdutoId());
        produto.setNomeProduto(produtoVO.getNomeProduto());
        produto.setDescricaoProduto(produtoVO.getDescricaoProduto());
        produto.setPrecoProduto(produtoVO.getPrecoProduto());
        produto.setQtdEstoque(produtoVO.getQtdEstoque());
        produto.setImagem(produtoVO.getImagem());
        produto.setCategoria(categoriaRepository.getById(produtoVO.getCategoria()));

        return produto;
    }
}
