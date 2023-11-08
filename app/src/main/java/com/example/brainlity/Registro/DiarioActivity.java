package com.example.brainlity.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;

public class DiarioActivity extends AppCompatActivity {

    TextView textView;
    private ImageView back;
    private Standard standard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);
        textView = findViewById(R.id.textDiario);
        back = findViewById(R.id.imageView7);
        standard = new Standard();
        standard.actionColorDefault(this);

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