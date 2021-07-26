package com.residencia.ecommerce.services;

import com.residencia.ecommerce.exceptions.ArquivosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class ArquivoService {

    private Path fileStorageLocation;

    @Value("${arquivos.imagem}")
    private String dirArquivosImagem;

    private void createDirectory() {
        this.fileStorageLocation = Paths.get(dirArquivosImagem)
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new ArquivosException("Nâo foi possível criar o diretório para armazenar o arquivo.", ex);
        }
    }

    public String storeFile(MultipartFile file, Integer produtoId) {
        createDirectory();

        // Limpeza no nome do arquivo
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        //Concatena o produtoId ao nome do arquivo
        fileName = produtoId.toString() + "_" + fileName;

        try {
            if (fileName.contains("..")) {
                throw new ArquivosException("Nome de arquivo inválido! " + fileName);
            }
            // Copia/Sobrescrita do arquivo na pasta de destino
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new ArquivosException("Ocorreu um erro e não foi possível armazenar o arquivo " + fileName, ex);
        }
    }

    public boolean deleteFile(String file) {
        String fileName = StringUtils.cleanPath(file);

        try {
            if (fileName.contains("..")) {
                throw new ArquivosException("Desculpe, o nome do arquivo contém uma sequência de caminho inválida! " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            return Files.deleteIfExists(targetLocation);

        } catch (IOException ex) {
            throw new ArquivosException("O arquivo " + fileName + " não foi encontrado no servidor!", ex);
        }
    }
}