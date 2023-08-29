package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MenuActivity extends AppCompatActivity {

    ConstraintLayout exercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Standard standard = new Standard();
        standard.actionColorDefault(this);

        exercicios = findViewById(R.id.Exercicios_button);

        exercicios.setOnClickListener(view ->{
            Intent intent = new Intent(this,RespirarMainActivity.class);
            startActivity(intent);

        });

    }
}