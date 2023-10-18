package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.Entidade.Registro;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DescricaoActivity extends AppCompatActivity {

    private Button voltar, finalizar;
    private FirebaseBDLocal firebaseBDLocal;
    private SharedPreferences sharedPreferences;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);

        firebaseBDLocal = new FirebaseBDLocal(this);
        sharedPreferences = getSharedPreferences("Registro", MODE_PRIVATE);
        editText = findViewById(R.id.editText_descricao);
        voltar = findViewById(R.id.button_voltarDesc);
        finalizar = findViewById(R.id.button_finalizar);
        finalizarClick();
    }

    public void finalizarClick(){
        finalizar.setOnClickListener(view ->{
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String data = dateFormat.format(calendar.getTime());

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("descricao", editText.getText().toString());
            editor.apply();
            String humor = sharedPreferences.getString("humor","");
            String descricao = sharedPreferences.getString("descricao","");
            String frase = sharedPreferences.getString("frase","");
            Registro registro = new Registro(humor,descricao,frase,data);
            firebaseBDLocal.inserirRegistro(registro);
            finish();
        });
    }

}