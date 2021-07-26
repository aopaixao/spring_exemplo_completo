package com.residencia.ecommerce.controllers;

import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.exceptions.EmailException;
import com.residencia.ecommerce.services.PedidoService;
import com.residencia.ecommerce.vo.PedidoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Pedido")
public class PedidoController {

    @Autowired
    public PedidoService pedidoService;

//    @GetMapping("/pedido-por-nome")
//    public ResponseEntity<List<PedidoVO>> findAllVO(
//            @RequestParam(required = false) Integer pagina,
//            @RequestParam(required = false) Integer qtdRegistros)
//            throws Exception {
//
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<>(pedidoService.findAllVO(pagina,
//                qtdRegistros), headers, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody PedidoVO pedidoVO) {

        HttpHeaders headers = new HttpHeaders();
        Pedido novoPedido = pedidoService.save(pedidoVO);

        if (null != novoPedido)
            return new ResponseEntity<>(novoPedido, headers, HttpStatus.OK);
        else
            return new ResponseEntity<>(novoPedido, headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public PedidoVO update(@RequestBody PedidoVO pedidoVO, @PathVariable Integer id) throws EmailException {
        return pedidoService.update(pedidoVO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable Integer id){
        pedidoService.delete(id);
    }
}