package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.brainlity.Utils.Standard;

public class RegistroActivity extends AppCompatActivity {

    private Button voltar, proximo;
    private EditText editText;
    private Standard standard;
    private TextView[] textViews = new TextView[5];
    private static int selectedTextViewNumber = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        standard = new Standard();
        standard.actionColorDefault(this);
        voltar = findViewById(R.id.button_voltar);
        proximo = findViewById(R.id.button_proximo);
        editText = findViewById(R.id.editText_frase);
        textViews[0] = findViewById(R.id.text_humor1);
        textViews[1]= findViewById(R.id.text_humor2);
        textViews[2] = findViewById(R.id.text_humor3);
        textViews[3] = findViewById(R.id.text_humor4);
        textViews[4] = findViewById(R.id.text_humor5);
        humorSelect();

        voltar.setOnClickListener(view ->{
            onBackPressed();
        });

        proximo.setOnClickListener(view ->{
            Intent intent = new Intent(RegistroActivity.this, DescricaoActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void humorSelect(){
        for (int i = 0; i < textViews.length; i++) {
            final int textViewNumber = i;
            textViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedTextViewNumber != -1) {
                        // Se algum TextView já foi selecionado, redefine sua cor de fundo
                        textViews[selectedTextViewNumber].setBackgroundColor(getResources().getColor(R.color.background_item3));
                    }

                    // Define a cor de fundo do TextView atual
                    textViews[textViewNumber].setBackgroundColor(getResources().getColor(R.color.background_item5));
                    selectedTextViewNumber = textViewNumber;
                }
            });
        }
    }


}
