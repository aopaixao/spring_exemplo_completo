package com.residencia.ecommerce.exceptions;

import java.util.List;

public class ErroResposta {
    private Integer status;
    private String titulo;
    private String dataHora;
    private List<String> erros;

    public ErroResposta(Integer status, String titulo, String dataHora, List<String> erros) {
        super();
        this.status = status;
        this.titulo = titulo;
        this.dataHora = dataHora;
        this.erros = erros;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
