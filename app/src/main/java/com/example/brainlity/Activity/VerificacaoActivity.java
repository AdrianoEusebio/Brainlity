package com.example.brainlity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class VerificacaoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText editText;
    private Standard standard = new Standard();
    private String codigo = gerarCodigoVerificacao();
    private String assunto = "Código de verificação";
    private String mensagem = "Seu código de verificação é: " + codigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacao);

    }

    private String gerarCodigoVerificacao() {
        Random random = new Random();
        int codigoAleatorio = random.nextInt(10000);
        return String.format("%04d", codigoAleatorio);
    }
}