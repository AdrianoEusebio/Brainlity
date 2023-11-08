package com.example.brainlity.Activity;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;
import com.google.firebase.FirebaseApp;

public class SplashActivity extends AppCompatActivity {

    // todo - Atributos
    private Standard standard;
    private SharedPreferences sharedPreferences;
    private FirebaseBDLocal firebaseBDLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // todo - declaração dos atributos
        FirebaseApp.initializeApp(this);
        standard = new Standard();
        firebaseBDLocal = new FirebaseBDLocal(this);
        sharedPreferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        standard.actionColorDefault(this);
        navigateToNextActivity();

    }

    private void navigateToNextActivity(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (standard.verificacaoCount(SplashActivity.this)) {
                    Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }}, 3000); // Delay for 2 seconds
        }


}