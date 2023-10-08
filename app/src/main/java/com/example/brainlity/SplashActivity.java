package com.example.brainlity;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.DAO.SyncManager;
import com.example.brainlity.utils.Standard;

public class SplashActivity extends AppCompatActivity {
    Standard standard = new Standard();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // Delay for 2 seconds
    }

}