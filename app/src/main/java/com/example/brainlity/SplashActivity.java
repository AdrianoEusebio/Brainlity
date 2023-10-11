package com.example.brainlity;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.DAO.SyncManager;
import com.example.brainlity.utils.Standard;

public class SplashActivity extends AppCompatActivity {

    // todo - Atributos
    private Standard standard;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // todo - declaração dos atributos
        standard = new Standard();
        sharedPreferences = getSharedPreferences("Usuario",MODE_PRIVATE);

        standard.actionColorDefault(this);
        if (SyncManager.getInstance().isSyncNeeded()) {
            realizarSincronizacao();
        }




    }


    private void realizarSincronizacao() {
        FirebaseBDLocal firebaseBDLocal = new FirebaseBDLocal(getApplicationContext());
        if (standard.avaliarConexao(this)) {
            firebaseBDLocal.syncFirebaseDataToLocalDatabase();
            navigateToNextActivity();
        } else {
            navigateToNextActivity();
            standard.toast(SplashActivity.this, "Você está no modo offline", 1);
        }
    }

    private void navigateToNextActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!sharedPreferences.getString("email","").equals("") && !sharedPreferences.getString("senha","").equals("")){
                    Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000); // Delay for 2 seconds
    }

}