package com.example.brainlity;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.brainlity.DAO.FirebaseSync;
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
        FirebaseSync firebaseSync = new FirebaseSync(getApplicationContext());
        if (standard.avaliarConexao(this)) {
            firebaseSync.syncFirebaseDataToLocalDatabase();
            navigateToNextActivity();
        } else {
            navigateToNextActivity();
            showToast("Sincronização não é possível sem uma conexão de rede.");
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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}