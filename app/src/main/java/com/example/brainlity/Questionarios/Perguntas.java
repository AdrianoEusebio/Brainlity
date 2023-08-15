package com.example.brainlity.Questionarios;

import java.util.List;

public class Perguntas {
    private String enunciado;
    private List<String> alternativas;

    public Perguntas(String a, List<String> b){
        this.enunciado = a;
        this.alternativas = b;
    }
    public Perguntas(String a){
        this.enunciado = a;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }
}
