package com.example.brainlity.Entidade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro {
    private long id;
    private String humor, descricao, frase;
    private String data;

    public Registro(long id, String humor, String descricao, String frase, String date){
        this.id = id;
        this.humor = humor;
        this.descricao = descricao;
        this.frase = frase;
        this.data = date;
    }

    public Registro(){
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHumor() {
        return humor;
    }

    public void setHumor(String humor) {
        this.humor = humor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
