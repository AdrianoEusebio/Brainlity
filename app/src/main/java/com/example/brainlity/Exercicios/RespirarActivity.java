package com.example.brainlity.Exercicios;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.MenuActivity;
import com.example.brainlity.R;
import com.example.brainlity.Standard;
import com.example.brainlity.utils.Musica;

public class RespirarActivity extends AppCompatActivity {

    private TextView cronometro;
    private ImageView rollback, volume;
    private Button button;
    private CountDownTimer countDownTimer;
    private static int selectedMinutes, selectedSeconds;
    private long totalMilliseconds;

    private Musica musica;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respirar);

        Standard standard = new Standard();
        standard.actionColorDefault(this);

        cronometro = findViewById(R.id.textView_cronometro);
        button = findViewById(R.id.button2);
        rollback = findViewById(R.id.image_rollback_respirar);
        volume = findViewById(R.id.imageView_volume);


        selectedMinutes = getIntent().getIntExtra("selectedMinutes", 0);
        selectedSeconds = getIntent().getIntExtra("selectedSeconds", 0);
        totalMilliseconds = (selectedMinutes * 60 + selectedSeconds) * 1000;
        cronometro.setText(String.format("%02d:%02d",selectedMinutes,selectedSeconds));



        buttonClick();
        rollbackClick();
    }

    public void buttonClick(){
        button.setOnClickListener(item ->{

            if (button.getText().equals("Começar")) {
                button.setText("Resetar");
                if (countDownTimer == null) {
                    countDownTimer = startCountdown(totalMilliseconds);
                }
                countDownTimer.start();
            } else {
                button.setText("Começar");
                cronometro.setText(String.format("%02d:%02d",selectedMinutes,selectedSeconds));
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
            }
        });
    }

    public void rollbackClick(){
        rollback.setOnClickListener(view ->{
            finish();
        });
    }

    @SuppressLint("ResourceType")
    public void volumeCLick(){
        int id = R.drawable.baseline_volume_up;
        volume.setOnClickListener(view ->{
          if(volume.getId() == id){
               volume.setImageResource(R.drawable.baseline_volume_off);
          } else {
              volume.setImageResource(R.drawable.baseline_volume_up);
          }
        });

    }

    private CountDownTimer startCountdown(long milliseconds) {
        CountDownTimer countDownTimer;
       return countDownTimer = new CountDownTimer(milliseconds, 1000) {
            public void onTick(long millisUntilFinished) {
                    long totalSeconds = millisUntilFinished / 1000;
                    long minutes = totalSeconds / 60;
                    long seconds = totalSeconds % 60;
                    cronometro.setText(String.format("%02d:%02d", minutes, seconds));
            }

            public void onFinish() {
                musica.startPause();
                cronometro.setText("00:00");
            }
        }.start();
    }
}