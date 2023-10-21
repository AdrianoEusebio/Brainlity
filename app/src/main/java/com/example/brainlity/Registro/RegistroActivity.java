package com.example.brainlity.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.brainlity.Activity.MenuActivity;
import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;

public class RegistroActivity extends AppCompatActivity {

    private Standard standard;
    private SharedPreferences sharedPreferences;
    private TextView[] textTag1;
    private TextView[] textTag2;
    private Button buttonProximo, buttonVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        standard = new Standard();
        buttonProximo = findViewById(R.id.button_proximo);
        buttonVoltar = findViewById(R.id.button_voltar);
        sharedPreferences = getSharedPreferences("Registro", MODE_PRIVATE);
        standard.actionColorDefault(this);
        textTag1 = new TextView[5];
        textTag2 = new TextView[6];
        textTag1List();
        textTag2List();
        textTag1Click();
        textTag2Click();

        buttonProximo.setOnClickListener(view ->{
            Intent intent = new Intent(RegistroActivity.this, DescricaoActivity.class);
            startActivity(intent);
            finish();
        });

        buttonVoltar.setOnClickListener(view ->{
            onBackPressed();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegistroActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void textTag1List(){
        textTag1[0] = findViewById(R.id.tag_muitofeliz);
        textTag1[1] = findViewById(R.id.tag_alegre);
        textTag1[2] = findViewById(R.id.tag_maismenos);
        textTag1[3] = findViewById(R.id.tag_normal);
        textTag1[4] = findViewById(R.id.tag_triste);
    }

    public void textTag2List(){
        textTag2[0] = findViewById(R.id.tag_familia);
        textTag2[1] = findViewById(R.id.tag_amigos);
        textTag2[2] = findViewById(R.id.tag_estudos);
        textTag2[3] = findViewById(R.id.tag_trabalho);
        textTag2[4] = findViewById(R.id.tag_social);
        textTag2[5] = findViewById(R.id.tag_outros);
    }

    public void textTag1Click(){
       for(int i = 0; i <= textTag1.length-1; i++){
           int finalI = i;
           textTag1[i].setOnClickListener(view ->{
               clearSelectionTag1();
               textTag1[finalI].setBackgroundColor(getResources().getColor(R.color.background_item5));
               selectedTextTag1(textTag1[finalI].getText().toString());
           });
       }
    }

    public void textTag2Click(){
        for(int i = 0; i <= textTag2.length-1; i++){
            int finalI = i;
            textTag2[i].setOnClickListener(view ->{
                clearSelectionTag2();
                textTag2[finalI].setBackgroundColor(getResources().getColor(R.color.background_item5));
                selectedTextTag2(textTag2[finalI].getText().toString());
            });
        }
    }

    private void clearSelectionTag1() {
        textTag1[0].setBackgroundColor(getResources().getColor(R.color.background_item3));
        textTag1[1].setBackgroundColor(getResources().getColor(R.color.background_item3));
        textTag1[2].setBackgroundColor(getResources().getColor(R.color.background_item3));
        textTag1[3].setBackgroundColor(getResources().getColor(R.color.background_item3));
        textTag1[4].setBackgroundColor(getResources().getColor(R.color.background_item3));
    }
    private void clearSelectionTag2() {
        textTag2[0].setBackgroundColor(getResources().getColor(R.color.background_item3));
        textTag2[1].setBackgroundColor(getResources().getColor(R.color.background_item3));
        textTag2[2].setBackgroundColor(getResources().getColor(R.color.background_item3));
        textTag2[3].setBackgroundColor(getResources().getColor(R.color.background_item3));
        textTag2[4].setBackgroundColor(getResources().getColor(R.color.background_item3));
        textTag2[5].setBackgroundColor(getResources().getColor(R.color.background_item3));
    }

    private void selectedTextTag1(String selectedText) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tag1", selectedText);
        editor.apply();
    }

    private void selectedTextTag2(String selectedText) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tag2", selectedText);
        editor.apply();
    }
}
