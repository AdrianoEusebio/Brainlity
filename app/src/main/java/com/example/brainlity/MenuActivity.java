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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.background_item1)); // Substitua pela cor desejada
        }

        exercicios = findViewById(R.id.Exercicios_button);

        exercicios.setOnClickListener(view ->{
            Intent intent = new Intent(this,RespirarMainActivity.class);
            startActivity(intent);

        });

    }
}