package com.example.brainlity.Questionarios;

import java.util.ArrayList;
import java.util.List;

public class Questionario {
    private String titulo;
    private int perguntasCount;

    public Questionario(String a, int b) {
        titulo = a;
        perguntasCount = b;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPerguntas() {
        return perguntasCount;
    }

    public void setPerguntas(int perguntas) {
        this.perguntasCount = perguntas;
    }

}
