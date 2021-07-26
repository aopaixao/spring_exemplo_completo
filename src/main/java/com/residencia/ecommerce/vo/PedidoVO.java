package com.residencia.ecommerce.vo;

import java.util.Calendar;
import java.util.List;

public class PedidoVO {
    private Integer pedidoId;
    private Integer numeroPedido;
    private Double valorTotalPedido;
    private Calendar dataPedido;
    private Boolean status;
    private Integer clienteId;
    private List<ProdutoPedidoVO> produtoPedidosByPedidoId;

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Double getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(Double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    public Calendar getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Calendar dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public List<ProdutoPedidoVO> getProdutoPedidosByPedidoId() {
        return produtoPedidosByPedidoId;
    }

    public void setProdutoPedidosByPedidoId(List<ProdutoPedidoVO> produtoPedidosByPedidoId) {
        this.produtoPedidosByPedidoId = produtoPedidosByPedidoId;
    }
}
