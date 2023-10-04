package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.utils.Standard;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Standard standard = new Standard();
        standard.actionColorDefault(this);

        editTextEmail = findViewById(R.id.editText_LoginEmail);
        editTextPassword = findViewById(R.id.editText_loginSenha);
        buttonLogin = findViewById(R.id.button_LoginEntrar);
        buttonClick();

    }


    public void buttonClick() {
        FirebaseBDLocal firebaseBDLocal = new FirebaseBDLocal(LoginActivity.this);
        String email = editTextEmail.getText().toString();
        String passaword = editTextPassword.getText().toString();
        buttonLogin.setOnClickListener(view -> {


        });
    }
}