package com.example.brainlity.Insight;

import android.graphics.drawable.Drawable;

import com.example.brainlity.R;

public class Insight {
    private String text;
    private String author;
    private int backgroundResId;

    public Insight() {
    }

    public Insight(String text, String author, int backgroundResId) {
        this.text = text;
        this.author = author;
        this.backgroundResId = backgroundResId;
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

    public int getBackgroundResId() {
        return backgroundResId;
    }

    public void setBackgroundResId(int backgroundResId) {
        this.backgroundResId = backgroundResId;
    }
}