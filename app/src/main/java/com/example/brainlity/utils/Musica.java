package com.example.brainlity.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.brainlity.R;

public class Musica {

    Context context;
    public static MediaPlayer mediaPlayer;

    public Musica(Context context){
        this.context = context;
        this.mediaPlayer = MediaPlayer.create(context, R.raw.relax_music);
    }
    public void startMusic(){
        mediaPlayer.start();
    }
    public void pauseMusic(){
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(context, R.raw.relax_music);
    }

    public boolean isPlaying(){
        if(mediaPlayer.isPlaying()){
            return true;
        } else {
            return false;
        }
    }
}
