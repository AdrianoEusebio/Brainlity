package com.example.brainlity.Registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.brainlity.Activity.MenuActivity;
import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.Entidade.Registro;
import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DescricaoActivity extends AppCompatActivity {

    private Button voltar, finalizar;
    private FirebaseBDLocal firebaseBDLocal;
    private Standard standard;
    private SharedPreferences sharedPreferences;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);

        standard = new Standard();
        standard.actionColorDefault(this);
        firebaseBDLocal = new FirebaseBDLocal(this);
        editText = findViewById(R.id.editText_descricao);
        voltar = findViewById(R.id.button_voltarDesc);
        finalizar = findViewById(R.id.button_finalizar);
        finalizarClick();

        voltar.setOnClickListener(view ->{
            onBackPressed();
        });
    }

    public void finalizarClick(){
        finalizar.setOnClickListener(view ->{
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            sharedPreferences = getSharedPreferences("Registro", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("descricao", editText.getText().toString());
            editor.apply();

            String humor = sharedPreferences.getString("tag1","") + "/" + sharedPreferences.getString("tag2","");
            String descricao = sharedPreferences.getString("descricao","");
            String data = dateFormat.format(calendar.getTime());
            Registro registro = new Registro(humor,descricao,data);
            firebaseBDLocal.inserirRegistro(registro);
            Intent intent = new Intent(DescricaoActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DescricaoActivity.this,RegistroActivity.class);
        startActivity(intent);
        finish();
    }
}