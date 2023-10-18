package com.example.brainlity.DAO;

import android.content.Context;

import com.example.brainlity.Utils.Standard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CheckUtilits {

    // todo - Atributos
    private DataBaseDBHelper dbHelper;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Context context;
    private Standard standard = new Standard();

    // todo - Construtor
    public CheckUtilits(Context context){
        this.context = context;
        dbHelper = new DataBaseDBHelper(context);
    }

    /*todo metodo para checar se o */

    public boolean checkPasswordChar(String password){
        if(password.length() < 6){
            return false;
        }else {
            return true;
        }
    }
}
