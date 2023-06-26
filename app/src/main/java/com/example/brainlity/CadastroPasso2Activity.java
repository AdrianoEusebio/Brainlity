package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.brainlity.databinding.ActivityCadastroPasso2Binding;
import com.example.brainlity.databinding.ActivityLoginBinding;

public class CadastroPasso2Activity extends AppCompatActivity {

    private ActivityCadastroPasso2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPasso2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textViewVolta.setOnClickListener(view ->{
            startActivity(new Intent(this, CadastroActivity.class));
            finish();
        });
    }
}