package com.residencia.ecommerce.services;


import com.residencia.ecommerce.entities.ProdutoPedido;
import com.residencia.ecommerce.repositories.ProdutoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoPedidoService {

    @Autowired
    public ProdutoPedidoRepository produtoPedidoRepository;
//******************************************************************************************************************

    public ProdutoPedido findById(Integer id) {
        return produtoPedidoRepository.findById(id).get();
    }
//******************************************************************************************************************

    public List<ProdutoPedido> findAll(Integer id) {
        return produtoPedidoRepository.findAll();
    }

//******************************************************************************************************************

    public long count() {
        return produtoPedidoRepository.count();
    }

//******************************************************************************************************************

    public ProdutoPedido save(ProdutoPedido produtoPedido) {
        ProdutoPedido novoProdutoPedido = produtoPedidoRepository.save(produtoPedido);
        if (novoProdutoPedido.getProdutoPedidoId() != null) {
            return novoProdutoPedido;
        } else {
            return null;
        }

    }
//********************************************************************************************************************

    public ProdutoPedido update(ProdutoPedido produtoPedido) {

        return produtoPedidoRepository.save(produtoPedido);
    }


//********************************************************************************************************************

    public boolean delete(Integer id) {
        if (id != null) {
            produtoPedidoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
