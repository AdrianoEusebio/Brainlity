package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.brainlity.databinding.ActivityCadastroBinding;
import com.example.brainlity.databinding.ActivitySplashMenuBinding;

public class SplashMenuActivity extends AppCompatActivity {

    private ActivitySplashMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewCadastro.setOnClickListener(view ->{
            startActivity(new Intent(this, CadastroActivity.class));
        });

        binding.viewLogin.setOnClickListener(view ->{
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}