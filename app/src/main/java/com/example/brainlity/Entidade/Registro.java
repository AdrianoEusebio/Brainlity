package com.example.brainlity.Entidade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro {
    private long id;
    private String humor, descricao;
    private String data;

    public Registro(String humor, String descricao, String date){
        this.humor = humor;
        this.descricao = descricao;
        this.data = date;
    }

    public Registro(){
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
