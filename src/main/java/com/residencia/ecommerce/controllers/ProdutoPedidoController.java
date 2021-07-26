package com.residencia.ecommerce.controllers;

import com.residencia.ecommerce.services.ProdutoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ProdutoPedido")
public class ProdutoPedidoController {

    @Autowired
    public ProdutoPedidoService produtoPedidoService;

}