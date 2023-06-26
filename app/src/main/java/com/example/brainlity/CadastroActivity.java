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

    private ActivityCadastroBinding binding; //atributo binding
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.textViewHasCount.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        binding.buttonPasso2.setOnClickListener(v -> validaDados());
    }

    private void validaDados() {
        String email = binding.editTextCadastroEmail.getText().toString().trim();
        String senha = binding.editTextCadastroSenha.getText().toString().trim();

            if (!email.isEmpty()) {
                if (!senha.isEmpty()) {

                    binding.progressBar.setVisibility(View.VISIBLE);
                    criarContaFirebase(email,senha);

                } else {
                    Toast.makeText(this, "Informe a sua senha", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Informe seu email", Toast.LENGTH_SHORT).show();
            }
        binding.progressBar.setVisibility(View.GONE);
    }

    public void criarContaFirebase(String email, String senha){
        mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(task ->{
            if(task.isSuccessful()){
                binding.progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(this, CadastroPasso2Activity.class));
                finish();
            }else{
                Toast.makeText(this, "Ocorreu um Erro", Toast.LENGTH_SHORT).show();
            }
        });

    }
}