package com.example.brainlity.Insight;

import android.graphics.drawable.Drawable;

import com.example.brainlity.R;

public class Insight {

    private long id;
    private String text;
    private String author;
    private int fundo;

    public Insight() {
    }

    public Insight(String text, String author, int backgroundResId) {
        this.text = text;
        this.author = author;
        this.fundo = backgroundResId;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getFundo() {
        return fundo;
    }

    public void setFundo(int backgroundResId) {
        this.fundo = backgroundResId;
    }
}