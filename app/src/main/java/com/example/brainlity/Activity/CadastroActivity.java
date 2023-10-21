package com.example.brainlity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.brainlity.Entidade.Usuario;
import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    // todo - Atributos
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private Button cadastrar;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private TextView textLogin;
    private Standard standard;
    boolean passwordVisible = false;
    private ImageView visibility;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // todo - Declaração dos atributos
        standard = new Standard();
        editTextEmail = findViewById(R.id.editText_CadastroEmail);
        editTextName = findViewById(R.id.editText_CadastroNome);
        editTextPassword = findViewById(R.id.editText_CadastroSenha);
        visibility = findViewById(R.id.imageView9);
        textLogin = findViewById(R.id.textLogin);
        cadastrar = findViewById(R.id.button_cadastrar);


        standard.actionColorDefault(this);
        buttonCadastrarClick();
        textLoginClick();
        togglePasswordVisibility(visibility, editTextPassword);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void buttonCadastrarClick() {
        cadastrar.setOnClickListener(view -> {

            String nome = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String senha = editTextPassword.getText().toString();

            try {
                if (!nome.equals("") || !email.equals("") || !senha.equals("")) {
                    mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                            if (task.isSuccessful()) {
                                SignInMethodQueryResult result = task.getResult();

                                if (result.getSignInMethods().isEmpty()) {

                                    if (checkPasswordChar(senha)) {
                                        Usuario usuario = new Usuario(nome, email, senha);
                                        createUsers(CadastroActivity.this, usuario);

                                    } else {
                                        standard.toast(CadastroActivity.this, "A senha precisa ter 6 ou mais caracteres.", 2);
                                    }

                                } else {
                                    standard.toast(CadastroActivity.this, "Email já está sendo utilizado", 2);
                                }

                            } else {
                                Exception exception = task.getException();
                                if (exception != null) {
                                    standard.toast(CadastroActivity.this, "Email Invalido", 2);
                                }
                            }
                        }
                    });

                } else {
                    standard.toast(CadastroActivity.this, "Falta preencher alguns campos de texto.", 2);
                }
            } catch (IllegalArgumentException e) {
                standard.toast(CadastroActivity.this, "Falta preencher alguns campos de texto.", 2);
            }
        });
    }

    public boolean checkPasswordChar(String password) {
        if (password.length() < 6) {
            return false;
        } else {
            return true;
        }
    }

    public void textLoginClick() {
        textLogin.setOnClickListener(view -> {
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void createUsers(AppCompatActivity activity, Usuario user) {
        Dialog dialog = standard.showProgressBar(CadastroActivity.this);
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnSuccessListener(task -> {

                    dialog.create();

                    Map<String, Object> userData = new HashMap<>();
                    userData.put("nome", user.getNome());
                    userData.put("email", user.getEmail());

                    CollectionReference usersCollection = db.collection("Users");
                    DocumentReference newUserDocRef = usersCollection.document(user.getEmail());

                    newUserDocRef.set(userData).addOnSuccessListener(task1 -> {
                        Map<String, String> dados = new HashMap<>();
                        dados.put("descricao","default");
                        dados.put("humor","default");
                        dados.put("data","default");

                        DocumentReference documentReference = newUserDocRef.collection("Registros").document("default");
                        documentReference.set(dados).addOnSuccessListener(command -> {
                            standard.toast(activity, "Cadastro Concluido", 1);
                            dialog.cancel();
                        }).addOnFailureListener(e -> {
                            standard.toast(activity, "Error: " + e.getMessage() + " tente mais tarde", 2);
                            dialog.cancel();
                        });



                    }).addOnFailureListener(e -> {
                        standard.toast(activity, "Error: " + e.getMessage() + " tente mais tarde", 2);
                        dialog.cancel();

                    });

                }).

                addOnFailureListener(e -> {

                    standard.toast(activity, "Error: " + e.getMessage() + ". tente mais tarde", 2);
                    dialog.cancel();

                });
    }


    public void togglePasswordVisibility(ImageView visibility, EditText editTextPassword) {
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
}
