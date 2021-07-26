package com.residencia.ecommerce.services;

import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.exceptions.EmailException;
import com.residencia.ecommerce.repositories.PedidoRepository;
import com.residencia.ecommerce.vo.PedidoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    private JavaMailSender mailSender;

    //    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail(Pedido pedido, PedidoVO pedidoVO) throws EmailException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setText("Data do pedido: " + pedido.getDataPedido().getTime() + "\n" +
                pedidoVO.getProdutoPedidosByPedidoId() + "\n" +
                "Valor final: " + pedido.getValorTotalPedido());
        message.setTo(pedido.getClienteByClienteId().getEmail());
        message.setSubject("Sua compra foi finalizada, " + pedido.getClienteByClienteId().getNome());
        message.setFrom("springtestepet@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            throw new EmailException ("Houve um erro ao enviar o email." + e.getMessage());
        }
    }
}