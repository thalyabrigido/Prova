package com.tech4me.prova.shared;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MusicaDTO {
    
    private String id;

    @NotBlank(message = "O nome deve possuir caracteres não brancos")
    @NotEmpty(message = "O nome deve ser preenchido")
    @Size(min = 3, message = "O nome deve ter, no mínimo, 3 caracteres")
    private String nome;

    private Integer anoLancamento;

    private String plataforma;

    private String genero;

    private Double valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    } 
}
