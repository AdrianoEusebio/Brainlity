package com.example.brainlity.Exercicios.Relaxar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.brainlity.R;
import com.example.brainlity.Utils.Musica;
import com.example.brainlity.Utils.Standard;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MediaPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private FloatingActionButton playButton;
    private SeekBar seekBar;
    private int musicaId;
    private Runnable moveSeekBarThread;
    private Standard standard;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        playButton = findViewById(R.id.floatingActionButton2);
        seekBar = findViewById(R.id.seekBar);
        standard = new Standard();
        imageView = findViewById(R.id.imageView11);
        standard.actionColorDefault(this);
        SharedPreferences sharedPreferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        musicaId = sharedPreferences.getInt("musica", 0);
        mediaPlayer = MediaPlayer.create(this, musicaId);
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();

        playButton.getDrawable().setTint(ContextCompat.getColor(getApplicationContext(), R.color.black));
        playButton.setOnClickListener(v -> {
            playMusic();
        });

        imageView.setOnClickListener(v -> {
            onBackPressed();
        });
        moveSeekBarThread = new Runnable() {
            public void run() {
                if(mediaPlayer != null && mediaPlayer.isPlaying()){
                    int newPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(newPosition);
                }
                handler.postDelayed(this, 1000); // Executa este Runnable novamente após 1000ms.
            }
        };
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Se o usuário alterar a posição do SeekBar
                if(b) mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(moveSeekBarThread);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                handler.postDelayed(moveSeekBarThread, 0);
            }
        });

        // Começa a tocar a música e atualiza o SeekBar
        mediaPlayer.start();
        moveSeekBarThread.run();
    }


    public boolean playMusic(){
        if(mediaPlayer.isPlaying()){
            playButton.getDrawable().setTint(ContextCompat.getColor(getApplicationContext(), R.color.black));
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.baseline_play_arrow);
            return true;
        }else {
            playButton.getDrawable().setTint(ContextCompat.getColor(getApplicationContext(), R.color.black));
            mediaPlayer.start();
            playButton.setImageResource(R.drawable.baseline_pause);
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            handler.removeCallbacks(moveSeekBarThread); // Remove as callbacks
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        if (mediaPlayer != null) {
            handler.removeCallbacks(moveSeekBarThread); // Remove as callbacks
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onBackPressed();
    }
}