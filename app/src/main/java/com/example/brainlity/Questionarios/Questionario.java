package com.example.brainlity.Questionarios;

import java.util.ArrayList;
import java.util.List;

public class Questionario {
    private String titulo;
    private long perguntasCount;
    private ArrayList<Perguntas> perguntas;

    public Questionario(String a, long b) {
        titulo = a;
        perguntasCount = b;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getPerguntas() {
        return perguntasCount;
    }

    public void setPerguntas(int perguntas) {
        this.perguntasCount = perguntas;
    }

    public static class Perguntas{

        private String enunciado;
        private List<String> alternativas;


        public Perguntas(String a, List<String> b){
            this.enunciado = a;
            this.alternativas = b;
        }

        public String getEnunciado() {
            return enunciado;
        }

        public void setEnnuciado(String enuciado) {
            this.enunciado = enuciado;
        }

        public List<String> getAlternativas() {
            return alternativas;
        }

        public void setAlternativas(List<String> alternativas) {
            this.alternativas = alternativas;
        }
    }
}
