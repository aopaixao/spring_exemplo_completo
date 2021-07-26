package com.residencia.ecommerce.controllers;

import com.residencia.ecommerce.entities.Produto;
import com.residencia.ecommerce.services.ArquivoService;
import com.residencia.ecommerce.services.ProdutoService;
import com.residencia.ecommerce.vo.ProdutoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Produto")
public class ProdutoController {

    @Autowired
    public ProdutoService produtoService;

    @GetMapping("/{nome}")
    public ResponseEntity<ProdutoVO> findByNome(@PathVariable String nome) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(produtoService.findByNomeProd(nome), headers, HttpStatus.OK);
    }

    @GetMapping("/produto-por-nome")
    public ResponseEntity<List<ProdutoVO>> findAllVO(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(produtoService.findAllVO(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return produtoService.count();
    }

    @PostMapping
    public ResponseEntity<ProdutoVO> save(ProdutoVO produtoVO) {
        HttpHeaders headers = new HttpHeaders();

        ProdutoVO novoProdutoVO = produtoService.saveVO(produtoVO);

        if (null != novoProdutoVO)
            return new ResponseEntity<>(novoProdutoVO, headers, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Integer id, @Valid @RequestBody Produto produto) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(produtoService.update(id, produto), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable Integer id) {
        try {
            produtoService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}