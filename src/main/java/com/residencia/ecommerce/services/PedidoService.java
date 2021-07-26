package com.residencia.ecommerce.services;


import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.entities.ProdutoPedido;
import com.residencia.ecommerce.exceptions.EmailException;
import com.residencia.ecommerce.repositories.ClienteRepository;
import com.residencia.ecommerce.repositories.PedidoRepository;
import com.residencia.ecommerce.repositories.ProdutoPedidoRepository;
import com.residencia.ecommerce.repositories.ProdutoRepository;
import com.residencia.ecommerce.vo.PedidoVO;
import com.residencia.ecommerce.vo.ProdutoPedidoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    public PedidoRepository pedidoRepository;

    @Autowired
    public EmailService emailService;

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoPedidoRepository produtoPedidoRepository;

//******************************************************************************************************************

    public Pedido save(PedidoVO pedidoVO) {
        return converteVOParaEntidade(pedidoVO, null);
    }

    private Pedido editarPedido(Integer id, Pedido pedido) {
        Pedido newPedido = pedidoRepository.findById(id).get();

        newPedido.setPedidoId(pedido.getPedidoId());
        newPedido.setNumeroPedido(pedido.getNumeroPedido());
        newPedido.setValorTotalPedido(pedido.getValorTotalPedido());
        newPedido.setStatus(true);
        newPedido.setDataPedido(pedido.getDataPedido());
        newPedido.setClienteByClienteId(pedido.getClienteByClienteId());
        return pedidoRepository.save(newPedido);
    }

    public PedidoVO update(PedidoVO pedidoVO, Integer id) throws EmailException {
        Pedido pedido = pedidoRepository.findById(id).get();
        if (pedido.getStatus()) {
            List<ProdutoPedido> listaProdutoPedido = produtoPedidoRepository.findAllByPedidoByPedidoId(pedido);
            List<ProdutoPedidoVO> listaProdutoPedidoVO = pedidoVO.getProdutoPedidosByPedidoId();

            Calendar dataAtualizacao = Calendar.getInstance();
            pedido.setDataPedido(dataAtualizacao);

            double valorTotal = pedido.getValorTotalPedido();
            if (pedidoVO.getProdutoPedidosByPedidoId() != null) {
                for (ProdutoPedidoVO produtoPedidoVO : listaProdutoPedidoVO) {
                    ProdutoPedido produtoPedido = new ProdutoPedido();
                    produtoPedido.setPedidoByPedidoId(pedido);
                    produtoPedido.setProdutoByProdutoId(produtoRepository.findById(produtoPedidoVO.getProdutoId()).get());
                    produtoPedido.setPrecoProdutoPedido(produtoRepository.findById(produtoPedidoVO.getProdutoId()).get().getPrecoProduto());
                    produtoPedido.setQtdProdutoPedido(produtoPedidoVO.getQuantidade());
                    produtoPedidoVO.setNome(produtoRepository.findById(produtoPedidoVO.getProdutoId()).get().getNomeProduto());
                    listaProdutoPedido.add(produtoPedido);
                    produtoPedidoRepository.save(produtoPedido);

                    double preco = produtoRepository.findById(produtoPedidoVO.getProdutoId()).get().getPrecoProduto() * produtoPedidoVO.getQuantidade();
                    valorTotal += preco;
                    pedido.setValorTotalPedido(valorTotal);
                }
            }
            if (listaProdutoPedido != null)
            pedido.setProdutoPedidosByPedidoId(listaProdutoPedido);
            pedido.setStatus(false);
            pedidoRepository.save(pedido);
            emailService.sendMail(pedido, pedidoVO);
        }
        return converteEntidadeParaVO(pedido);
    }

    public Pedido converteVOParaEntidade(PedidoVO pedidoVO, Integer id) {
        Pedido pedido = new Pedido();
        List<ProdutoPedidoVO> listPedido = pedidoVO.getProdutoPedidosByPedidoId();
        List<ProdutoPedido> listProdPedido = new ArrayList<>();

        Calendar data = Calendar.getInstance();

        pedido.setPedidoId((null == id) ? pedidoVO.getPedidoId() : id);
        pedido.setNumeroPedido(pedidoVO.getNumeroPedido());
        pedido.setStatus(true);
        pedido.setDataPedido(data);
        pedido.setClienteByClienteId(clienteRepository.findById(pedidoVO.getClienteId()).get());

        double valorTotal = 0.0;

        for (ProdutoPedidoVO produtoPedidoVO : listPedido) {
            double preco = produtoRepository.findById(produtoPedidoVO.getProdutoId()).get().getPrecoProduto() * produtoPedidoVO.getQuantidade();
            valorTotal += preco;
            pedido.setValorTotalPedido(valorTotal);
        }
        pedidoRepository.save(pedido);

        for (ProdutoPedidoVO produtoPedidoVO : listPedido) {
            ProdutoPedido produtoPedido = new ProdutoPedido();
            produtoPedido.setProdutoPedidoId(pedidoVO.getPedidoId());
            produtoPedido.setProdutoByProdutoId(produtoRepository.findById(produtoPedidoVO.getProdutoId()).get());
            produtoPedido.setQtdProdutoPedido(produtoPedidoVO.getQuantidade());
            produtoPedido.setPedidoByPedidoId(pedido);
            produtoPedido.setPrecoProdutoPedido(produtoPedidoVO.getPreco());

            produtoPedidoVO.setPreco(produtoRepository.findById(produtoPedidoVO.getProdutoId()).get().getPrecoProduto());
            produtoPedidoVO.setNome(produtoRepository.findById(produtoPedidoVO.getProdutoId()).get().getNomeProduto());

            pedido.setProdutoPedidosByPedidoId(listProdPedido);
            produtoPedido.setPrecoProdutoPedido(produtoPedidoVO.getPreco());
            produtoPedidoRepository.save(produtoPedido);
        }
        pedido.setNumeroPedido(pedido.getPedidoId());
        pedidoRepository.save(pedido);

        return pedido;
    }

    public void delete(Integer id) {
        Pedido pedido = pedidoRepository.findById(id).get();
        pedidoRepository.deleteById(pedido.getPedidoId());
    }

    private PedidoVO converteEntidadeParaVO(Pedido pedido) {
        PedidoVO pedidoVO = new PedidoVO();

        pedidoVO.setPedidoId(pedido.getPedidoId());
        pedidoVO.setNumeroPedido(pedido.getNumeroPedido());
        pedidoVO.setDataPedido(pedido.getDataPedido());
        pedidoVO.setValorTotalPedido(pedido.getValorTotalPedido());
        pedidoVO.setStatus(false);
        pedidoVO.setClienteId(pedido.getClienteByClienteId().getClienteId());

        List<ProdutoPedido> listPedido = produtoPedidoRepository.findAllByPedidoByPedidoId(pedido);
        List<ProdutoPedidoVO> listPedidoVO = new ArrayList<>();

        for (ProdutoPedido produtoPedido : listPedido) {
            ProdutoPedidoVO produtoPedidoVO = new ProdutoPedidoVO();

            produtoPedidoVO.setProdutoId(produtoPedido.getProdutoByProdutoId().getProdutoId());
            produtoPedidoVO.setPreco(produtoPedido.getPrecoProdutoPedido());
            produtoPedidoVO.setQuantidade(produtoPedido.getQtdProdutoPedido());

            listPedidoVO.add(produtoPedidoVO);
        }
        pedidoVO.setProdutoPedidosByPedidoId(listPedidoVO);
        return pedidoVO;
    }
}
