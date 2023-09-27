package com.example.brainlity.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.brainlity.R;

public class Musica {

    Context context;
    public static MediaPlayer mediaPlayer;
    private int id;

    public Musica(Context context, int id){
        this.id = id;
        this.context = context;
        this.mediaPlayer = MediaPlayer.create(context,this.id);
    }
    public void startMusic(){
        mediaPlayer.start();
    }
    public void pauseMusic(){
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(context, this.id);
    }

    public boolean isPlaying(){
        if(mediaPlayer.isPlaying()){
            return true;
        } else {
            return false;
        }
    }
}
