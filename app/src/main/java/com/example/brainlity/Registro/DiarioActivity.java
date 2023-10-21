package com.example.brainlity.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.brainlity.R;

public class DiarioActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);
        textView = findViewById(R.id.textDiario);

        Intent intent = getIntent();
        if(intent != null){
            String descricao = intent.getStringExtra("descricao");
            textView.setText(descricao);
        }
    }
}