package com.residencia.ecommerce.repositories;

import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.entities.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Integer> {
    List<ProdutoPedido> findAllByPedidoByPedidoId(Pedido pedidoByPedidoId);
}
