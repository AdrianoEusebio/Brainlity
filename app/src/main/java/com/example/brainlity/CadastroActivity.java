package com.example.brainlity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.brainlity.databinding.ActivityCadastroBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.textViewHasCount.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        binding.buttonCadastrar.setOnClickListener(v -> validaDados());
    }

    private void validaDados() {
        String email = binding.editTextCadastroEmail.getText().toString().trim();
        String nome = binding.editTextCadastroNome.getText().toString().trim();
        String senha = binding.editTextCadastroSenha.getText().toString().trim();

        if (!nome.isEmpty()) {

            if (!email.isEmpty()) {

                if (!senha.isEmpty()) {
                    binding.progressBar.setVisibility(View.VISIBLE);

                    criarContaFirebase(nome, email, senha);

                    } else {
                        Toast.makeText(this, "Informe a sua senha", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Informe seu email", Toast.LENGTH_SHORT).show();
                }
            } else{
                Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show();
            }
    }

    private void criarContaFirebase(String nome , String email, String senha){
        mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(task ->{
            if(task.isSuccessful()){
                finish();
                startActivity(new Intent(this, MainActivity.class));

            } else{
                Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        });

    }
}