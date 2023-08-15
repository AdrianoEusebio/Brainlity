package com.example.brainlity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.brainlity.Questionarios.AdapterQuestionarioCard;
import com.example.brainlity.Questionarios.Questionario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashExemploActivity extends AppCompatActivity {

    AdapterQuestionarioCard adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_exemplo);
        checkInternetConnectivity();
    }

    private void checkInternetConnectivity() {

        NetworkUtils networkUtils = new NetworkUtils();

        if(networkUtils.isNetworkAvailable(this)){
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
                Intent intent = new Intent(SplashExemploActivity.this, MenuExemploActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // Delay for 2 seconds
    }
}