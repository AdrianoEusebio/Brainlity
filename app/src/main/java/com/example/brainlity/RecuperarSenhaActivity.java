package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.brainlity.databinding.ActivityRecuperarSenhaBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private ActivityRecuperarSenhaBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperarSenhaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.buttonRecuperarConta.setOnClickListener(view -> {
            validaDados();
        });
    }

    private void validaDados() {
        String email = binding.editTextRecuperarEmail.getText().toString().trim();
        if (!email.isEmpty()) {
            recuperaContaFirebase(email);
        } else{
            Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show();
        }
        binding.progressBar2.setVisibility(View.VISIBLE);
    }

    private void recuperaContaFirebase(String email){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task ->{
            if(task.isSuccessful()){
                startActivity(new Intent(this, SplashMenuActivity.class));
            } else{
                Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
            binding.progressBar2.setVisibility(View.GONE);
        });

    }
}