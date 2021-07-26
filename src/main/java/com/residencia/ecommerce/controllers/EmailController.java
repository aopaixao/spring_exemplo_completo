package com.residencia.ecommerce.controllers;

import com.residencia.ecommerce.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    private JavaMailSender mailSender;

//    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
//    public String sendMail() {
//        emailService.sendMail()
//    }
}