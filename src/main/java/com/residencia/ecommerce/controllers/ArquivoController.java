package com.residencia.ecommerce.controllers;

import com.residencia.ecommerce.services.ArquivoService;
import com.residencia.ecommerce.vo.ArquivosVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {
    @Autowired
    ArquivoService arquivoService;

    @PostMapping("/uploadFile")
    public ArquivosVO uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = arquivoService.storeFile(file, null);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/foto/downloadFile/")
                .path(fileName)
                .toUriString();

        return new ArquivosVO(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/deleteFile")
    public boolean deleteFile(@RequestParam("file") String file) {
        return arquivoService.deleteFile(file);
    }
}
