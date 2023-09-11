package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.brainlity.Exercicios.RespirarMainActivity;

public class MenuActivity extends AppCompatActivity {

    ConstraintLayout exercicios, shorts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Standard standard = new Standard();
        standard.actionColorDefault(this);

        exercicios = findViewById(R.id.Exercicios_button);
        shorts = findViewById(R.id.Exercicios_button2);

        shorts.setOnClickListener(view ->{
            Intent intent = new Intent(this, ShortsActivity.class);
            startActivity(intent);
        });

        exercicios.setOnClickListener(view ->{
            Intent intent = new Intent(this, RespirarMainActivity.class);
            startActivity(intent);
        });

    }
}