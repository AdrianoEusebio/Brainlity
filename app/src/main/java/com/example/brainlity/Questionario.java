package com.example.brainlity;

import com.google.firebase.database.FirebaseDatabase;

public class Questionario {
    FirebaseDatabase database;
    private String titulo;
    private int perguntas;

    public Questionario(){

    }

    public String getTitulo(){return titulo;}

    public int getPerguntas(){
        return this.perguntas;
    }

    public void setTitulo(String e){
        titulo = e;
    }

    public void setPerguntas(int e){
        perguntas = e;
    }
}
