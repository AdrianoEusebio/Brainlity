package com.example.brainlity;

public class Questionario {
    private String titulo;
    private int perguntas;

    public Questionario(String a) {
        titulo = a;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(int perguntas) {
        this.perguntas = perguntas;
    }
}
