package com.example.brainlity.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.R;

public class DiarioActivity extends AppCompatActivity {

    TextView textView;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);
        textView = findViewById(R.id.textDiario);
        back = findViewById(R.id.imageView7);

        Intent intent = getIntent();
        if(intent != null){
            String descricao = intent.getStringExtra("descricao");
            textView.setText(descricao);
        }

        back.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}