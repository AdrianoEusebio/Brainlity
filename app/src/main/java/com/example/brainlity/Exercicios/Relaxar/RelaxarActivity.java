package com.example.brainlity.Exercicios.Relaxar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;

import java.security.PrivilegedAction;

public class RelaxarActivity extends AppCompatActivity {

    private Standard standard;
    private TextView music1, music2,music3;
    private ImageView imageView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxar);
        standard = new Standard();
        standard.actionColorDefault(this);
        imageView = findViewById(R.id.imageView12);
        music1 = findViewById(R.id.titulo_music);
        music2 = findViewById(R.id.titulo_music2);
        music3 = findViewById(R.id.titulo_music3);
        sharedPreferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        musicSelected();

        imageView.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    public void musicSelected(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        music1.setOnClickListener(v -> {
            editor.putInt("musica",R.raw.ambient_guitar);
            Intent intent = new Intent(this,MediaPlayerActivity.class);
            startActivity(intent);
            editor.apply();
        });

        music2.setOnClickListener(v -> {
            editor.putInt("musica",R.raw.inspiring_ambient);
            Intent intent = new Intent(this,MediaPlayerActivity.class);
            startActivity(intent);
            editor.apply();
        });

        music3.setOnClickListener(v -> {
            editor.putInt("musica",R.raw.peaceful_meditation);
            Intent intent = new Intent(this,MediaPlayerActivity.class);
            startActivity(intent);
            editor.apply();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}