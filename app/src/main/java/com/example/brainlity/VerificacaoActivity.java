package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.brainlity.DAO.CheckUtilits;
import com.example.brainlity.utils.Standard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseAuthWebException;
import com.google.firebase.auth.FirebaseUser;

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