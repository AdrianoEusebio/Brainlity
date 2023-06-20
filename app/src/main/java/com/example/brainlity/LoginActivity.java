package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.brainlity.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.textViewNotCount.setOnClickListener(view ->{
            startActivity(new Intent(this, CadastroActivity.class));
        });

        binding.buttonLogin.setOnClickListener(v ->{
            validaDados();
        });
    }

    private void validaDados() {
        String email = binding.editTextLoginEmail.getText().toString().trim();
        String senha = binding.editTextLoginSenha.getText().toString().trim();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                binding.progressBarLogin.setVisibility(View.VISIBLE);
                acessarContaFirebase(email,senha);
                } else {
                    Toast.makeText(this, "Informe a sua senha", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Informe seu email", Toast.LENGTH_SHORT).show();
            }
    }
    public void acessarContaFirebase(String email, String senha){
        mAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(task ->{
            if(task.isSuccessful()){
                startActivity(new Intent(this, MainActivity.class));
            }else{
                Toast.makeText(this, "Deu merda", Toast.LENGTH_SHORT).show();
            }
        });
    }
}