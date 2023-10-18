package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private TextView text1,text2,text3,text4,text5;
    private SharedPreferences sharedPreferences;
    private static int selectedTextViewNumber = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        standard = new Standard();
        sharedPreferences = getSharedPreferences("Registro", MODE_PRIVATE);
        standard.actionColorDefault(this);
        voltar = findViewById(R.id.button_voltar);
        proximo = findViewById(R.id.button_proximo);
        editText = findViewById(R.id.editText_frase);
        text1 = findViewById(R.id.text_humor1);
        text2= findViewById(R.id.text_humor2);
        text3 = findViewById(R.id.text_humor3);
        text4 = findViewById(R.id.text_humor4);
        text5 = findViewById(R.id.text_humor5);

        proximoClick();

        voltar.setOnClickListener(view ->{
            onBackPressed();
        });



        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                text1.setBackgroundColor(getResources().getColor(R.color.background_item5));
                storeSelectedText("Muito Feliz");
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                text2.setBackgroundColor(getResources().getColor(R.color.background_item5));
                storeSelectedText("Alegre");
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                text3.setBackgroundColor(getResources().getColor(R.color.background_item5));
                storeSelectedText("Mais ou Menos");
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                text4.setBackgroundColor(getResources().getColor(R.color.background_item5));
                storeSelectedText("Normal");
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                text5.setBackgroundColor(getResources().getColor(R.color.background_item5));
                storeSelectedText("Triste");
            }
        });
    }

    public void proximoClick(){
        proximo.setOnClickListener(view ->{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("frase", editText.getText().toString());
            editor.apply();
            Intent intent = new Intent(RegistroActivity.this, DescricaoActivity.class);
            startActivity(intent);
        });
    }

    private void clearSelection() {
        int id = R.color.background_item3;
        text1.setBackgroundResource(id);
        text2.setBackgroundResource(id);
        text3.setBackgroundResource(id);
        text4.setBackgroundResource(id);
        text5.setBackgroundResource(id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void storeSelectedText(String selectedText) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("humor", selectedText);
        editor.apply();
    }
}
