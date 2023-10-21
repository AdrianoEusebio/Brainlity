package com.example.brainlity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.Entidade.Registro;
import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    // todo - Atributos
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseBDLocal firebaseBDLocal;
    private SharedPreferences sharedPreferences;
    private EditText editTextPassword, editTextEmail;
    private Button buttonLogin;
    private TextView textCadastro;
    private ImageView visibility;
    private Boolean passwordVisible = false;
    private Standard standard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // todo - Declaraçãos dos atributos
        standard = new Standard();
        firebaseBDLocal = new FirebaseBDLocal(this);
        sharedPreferences = getSharedPreferences("Usuario", this.MODE_PRIVATE);
        editTextEmail = findViewById(R.id.editText_LoginEmail);
        editTextPassword = findViewById(R.id.editText_loginSenha);
        buttonLogin = findViewById(R.id.button_LoginEntrar);
        textCadastro = findViewById(R.id.textCadastro);
        visibility = findViewById(R.id.imageView10);

        standard.actionColorDefault(this);

        buttonLoginClick();
        textCadastroClick();
        togglePasswordVisibility();
    }

    public void buttonLoginClick() {
        buttonLogin.setOnClickListener(view -> {
            Dialog dialog = standard.showProgressBar(LoginActivity.this);
            dialog.create();

            String email = editTextEmail.getText().toString();
            String senha = editTextPassword.getText().toString();

            try {
                if (!email.equals("") || !senha.equals("")) {
                    checkUserExist(email, senha, dialog);
                }
            } catch (IllegalArgumentException e) {
                dialog.cancel();
                standard.toast(LoginActivity.this, "Falta preencher alguns campos", 2);
            }
        });
    }

    public void textCadastroClick() {
        textCadastro.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(intent);
            finish();
        });

    }

    public void togglePasswordVisibility() {
        visibility.setOnClickListener(view -> {
            passwordVisible = !passwordVisible;
            if (passwordVisible) {

                editTextPassword.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                visibility.setImageResource(R.drawable.baseline_visibility_off);
            } else {

                editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                visibility.setImageResource(R.drawable.baseline_visibility);
            }

            editTextPassword.setSelection(editTextPassword.getText().length());
        });
    }

    public void checkUserExist(String email, String senha, Dialog dialog) {
        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {

            CollectionReference usersCollection = db.collection("Users");
            Query query = usersCollection.whereEqualTo("email", email);

            if (task.isSuccessful()) {
                firestoreSelected(email);
                query.get().addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String nome = documentSnapshot.getString("nome");
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", email);
                        editor.putString("senha", senha);
                        editor.putString("nome", nome);
                        editor.apply();
                        standard.toast(LoginActivity.this, "Login realizado com sucesso", 1);
                    }

                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();

                }).addOnFailureListener(e -> {
                });
            } else {
                standard.toast(LoginActivity.this, "Login Invalido", 2);
                dialog.cancel();
            }
        });
    }

    public void firestoreSelected(String email){
        firebaseBDLocal.deleteAllRegistro();
        CollectionReference collectionReference = db.collection("Users");
        Query query = collectionReference.whereEqualTo("email", email);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                CollectionReference colecao = collectionReference.document(email).collection("Registros");
                colecao.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            if(!document.getId().equals("default")){
                                Registro registro = document.toObject(Registro.class);
                                firebaseBDLocal.inserirRegistro(registro);
                            }
                        }
                    }
                }).addOnFailureListener(e -> {

                });
            }
        });

    }
}