package com.residencia.ecommerce.controllers;

import com.residencia.ecommerce.entities.Categoria;
import com.residencia.ecommerce.services.CategoriaService;
import com.residencia.ecommerce.vo.CategoriaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Categoria")
public class CategoriaController {

    @Autowired
    public CategoriaService categoriaService;

    @GetMapping("/{nome}")
    public ResponseEntity<CategoriaVO> findByNome(@PathVariable String nome) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(categoriaService.findByNomeCat(nome), headers, HttpStatus.OK);
    }

    @GetMapping("/categoria-por-nome")
    public ResponseEntity<List<CategoriaVO>> findAllVO(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(categoriaService.findAllVO(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return categoriaService.count();
    }

    @PostMapping("/criar-categoria")
    public ResponseEntity<CategoriaVO> save(@Valid @RequestBody CategoriaVO categoriaVO) {
        HttpHeaders headers = new HttpHeaders();

        CategoriaVO novaOrderVO = categoriaService.saveVO(categoriaVO);

        if (null != novaOrderVO)
            return new ResponseEntity<>(novaOrderVO, headers, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(categoriaService.update(id, categoria), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
        try {
            categoriaService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}