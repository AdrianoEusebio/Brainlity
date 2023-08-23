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

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.background_item1)); // Substitua pela cor desejada
        }

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
                Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // Delay for 2 seconds
    }
}