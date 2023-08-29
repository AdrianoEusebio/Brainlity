package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

public class RespirarActivity extends AppCompatActivity {

    TextView cronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respirar);

        Standard standard = new Standard();
        standard.actionColorDefault(this);

        cronometro = findViewById(R.id.textView_cronometro);
        int selectedMinutes = getIntent().getIntExtra("selectedMinutes", 0);
        int selectedSeconds = getIntent().getIntExtra("selectedSeconds", 0);
        long totalMilliseconds = (selectedMinutes * 60 + selectedSeconds) * 1000;

        cronometro.setText(String.format("%02d:%02d",selectedMinutes,selectedSeconds));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startCountdown(totalMilliseconds);
            }
        }, 2000);
    }

    private void startCountdown(long milliseconds) {
        new CountDownTimer(milliseconds, 1000) {
            public void onTick(long millisUntilFinished) {
                long totalSeconds = millisUntilFinished / 1000;
                long minutes = totalSeconds / 60;
                long seconds = totalSeconds % 60;
                cronometro.setText(String.format("%02d:%02d", minutes, seconds));
            }

            public void onFinish() {
                cronometro.setText("00:00");
            }
        }.start();
    }
}