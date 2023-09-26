package com.example.brainlity.Exercicios.Respiração;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.R;
import com.example.brainlity.utils.Standard;
import com.example.brainlity.utils.Musica;

import pl.droidsonroids.gif.GifImageView;

public class RespirarActivity extends AppCompatActivity {

    private TextView cronometro;
    private ImageView rollback,volume;
    private Button button;
    private GifImageView gifImageView;
    private CountDownTimer countDownTimer;
    private static int selectedMinutes, selectedSeconds;
    private long totalMilliseconds;
    private Standard standard = new Standard();

    private Musica musica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respirar);

        musica = new Musica(this);
        standard.actionColorDefault(this);
        cronometro = findViewById(R.id.textView_cronometro);
        button = findViewById(R.id.button2);
        rollback = findViewById(R.id.image_rollback_respirar);
        volume = findViewById(R.id.imageView);
        gifImageView = findViewById(R.id.gifImageView);

        selectedMinutes = getIntent().getIntExtra("selectedMinutes", 0);
        selectedSeconds = getIntent().getIntExtra("selectedSeconds", 0);
        totalMilliseconds = (selectedMinutes * 60 + selectedSeconds) * 1000;
        cronometro.setText(String.format("%02d:%02d",selectedMinutes,selectedSeconds));

        rollback.setOnClickListener(view ->{
            onBackPressed();
        });

        volume.setOnClickListener(view ->{
            volume();
        });

        buttonClick();
    }

    @Override
    public void onBackPressed() {
        if(musica.isPlaying()){
            musica.pauseMusic();
        }
        super.onBackPressed();
    }

    public void buttonClick(){
        button.setOnClickListener(item ->{
            if (button.getText().equals("Começar")) {
                button.setText("Resetar");

                standard.vibrator(RespirarActivity.this);
                gifImageView.setImageResource(R.drawable.gif_ansiedade_respiracao);
                volume();

                if (countDownTimer == null) {
                    countDownTimer = startCountdown(totalMilliseconds);
                }
                countDownTimer.start();
            } else {
                button.setText("Começar");
                musica.pauseMusic();
                gifImageView.setImageResource(R.drawable.gif_pause);
                cronometro.setText(String.format("%02d:%02d",selectedMinutes,selectedSeconds));
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
            }
        });
    }
    private CountDownTimer startCountdown(long milliseconds) {
       return new CountDownTimer(milliseconds, 1000) {
            public void onTick(long millisUntilFinished) {
                long totalSeconds = millisUntilFinished / 1000;
                long minutes = totalSeconds / 60;
                long seconds = totalSeconds % 60;
                cronometro.setText(String.format("%02d:%02d", minutes, seconds));
            }
            public void onFinish() {
                musica.pauseMusic();
                gifImageView.setImageResource(R.drawable.gif_pause);
                cronometro.setText("00:00");
            }
        }.start();
    }

    public boolean volume(){
            if(musica.isPlaying()){
                musica.pauseMusic();
                volume.setImageResource(R.drawable.baseline_volume_off);
                return true;
            }else {
                musica.startMusic();
                volume.setImageResource(R.drawable.baseline_volume_up);
                return false;
            }
    }
}