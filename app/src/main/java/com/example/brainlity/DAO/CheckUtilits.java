package com.example.brainlity.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brainlity.CadastroActivity;
import com.example.brainlity.entidade.Usuario;
import com.example.brainlity.utils.Standard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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

    public void createUsers(AppCompatActivity activity, Usuario user) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Map<String, Object> userData = new HashMap<>();
                userData.put("nome", user.getNome());
                userData.put("email", user.getEmail());
                CollectionReference usersCollection = db.collection("Users");
                DocumentReference newUserDocRef = usersCollection.document();
                newUserDocRef.set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        standard.toast(activity,"Cadastro Concluido",1);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        standard.toast(activity,"Error: " + e.getMessage() + " tente mais tarde",2);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                standard.toast(activity,"Error: " + e.getMessage() + " tente mais tarde",2);
            }
        });
    }
}
