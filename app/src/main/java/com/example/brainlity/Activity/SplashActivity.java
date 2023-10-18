package com.example.brainlity.Activity;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class SplashActivity extends AppCompatActivity {

    // todo - Atributos
    private Standard standard;
    private SharedPreferences sharedPreferences;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // todo - declaração dos atributos
        FirebaseApp.initializeApp(this);
        standard = new Standard();
        usersCollection = db.getInstance().collection("Users");
        sharedPreferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        standard.actionColorDefault(this);

        if (standard.avaliarConexao(this)) {
            realizarSincronizacaoInsight();
            realizarSincronizacaoFirestore();
            navigateToNextActivity();
        } else {
            navigateToNextActivity();
        }

    }

    public void realizarSincronizacaoInsight() {
        FirebaseBDLocal firebaseBDLocal = new FirebaseBDLocal(getApplicationContext());
        firebaseBDLocal.syncFirebaseDataToLocalDatabaseInsight();
    }

    public void realizarSincronizacaoFirestore() {
        String emailToSearch = sharedPreferences.getString("email", "");
        String nomeUpdate = sharedPreferences.getString("nome","");
        Query query = usersCollection.whereEqualTo("email", emailToSearch);
        if(verificacaoCount()){
            query.get().addOnCompleteListener(task -> {
               if(task.isSuccessful()){
                    QuerySnapshot queryDocument = task.getResult();
                   if (queryDocument != null && !queryDocument.isEmpty()) {
                        for(DocumentSnapshot documentSnapshot: queryDocument.getDocuments()){
                           if(documentSnapshot.getReference().update("nome",nomeUpdate).isSuccessful()){

                           }
                        }
                    }
               }
            });
        }
    }

    public boolean verificacaoCount() {
        if (!sharedPreferences.getString("email", "").equals("") &&
                !sharedPreferences.getString("senha", "").equals("")) {
            return true;
        } else {
            return false;
        }
    }
    private void navigateToNextActivity(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (verificacaoCount()) {
                    Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }}, 2000); // Delay for 2 seconds
        }
    }