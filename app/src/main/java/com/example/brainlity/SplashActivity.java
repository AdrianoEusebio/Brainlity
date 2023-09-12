package com.example.brainlity;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

public class SplashActivity extends AppCompatActivity {
    Standard standard = new Standard();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        standard.actionColorDefault(this);
        checkInternetConnectivity();
    }

    private void checkInternetConnectivity() {

        if(standard.avaliarConexao(this)){
            navigateToNextActivity();
            Toast.makeText(this, "Voce esta conectado a internet", Toast.LENGTH_SHORT).show();

        }else{
            navigateToNextActivity();
            Toast.makeText(this, "Voce esta no modo offline", Toast.LENGTH_SHORT).show();
        }

    }


    private void navigateToNextActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // Delay for 2 seconds
    }
}