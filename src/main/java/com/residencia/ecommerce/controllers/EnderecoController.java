package com.residencia.ecommerce.controllers;

import com.residencia.ecommerce.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Endereco")
public class EnderecoController {

    @Autowired
    public EnderecoService enderecoService;

}