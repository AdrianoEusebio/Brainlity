package com.example.brainlity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainlity.DAO.CheckUtilits;
import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.entidade.Usuario;
import com.example.brainlity.utils.Standard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
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
    private ImageView visibility;
    private boolean passwordVisible = false;

    private CheckUtilits checkUtilits = new CheckUtilits(this);

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

        // todo - metodo para trocar a cor da barra de notificações
        standard.actionColorDefault(this);

        // todo - 1 metodo de ação do botão Cadastrar onde irá fazer as devidas verificações e checagens para a criação do usuario
        buttonCadastrarClick();

        // todo - 2 metodo de ação do textLogin, ao apertar o usuario será redirecionado para a tela de LoginActivity
        textLoginClick();

        // todo - 3 metodo para deixar a senha visivel e mascarada
        togglePasswordVisibility();
    }

    // todo - metodo padrão back do celular
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CadastroActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    // todo - Metodo 1
    public void buttonCadastrarClick(){
        cadastrar.setOnClickListener(view ->{
            // todo - Declaração das devidas variaveis
            String nome = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String senha = editTextPassword.getText().toString();

            // todo - 1 Verificação, saber se os editText estão vazio ou não

            try {
                if (!nome.equals("") || !email.equals("") || !senha.equals("")) {
                    mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                            // todo - 2 Verificação, saber se o Email é valido
                            if (task.isSuccessful()) {
                                SignInMethodQueryResult result = task.getResult();

                                // todo - 3 Verificação, saber se o email já esta sendo utilizado
                                if (result.getSignInMethods().isEmpty()) {

                                    // todo - 4 Verificação, saber se a senha tem 6 ou mais caracteres
                                    if (checkUtilits.checkPasswordChar(senha)) {
                                        VerificacaoTask verificacaoTask = new VerificacaoTask();
                                        verificacaoTask.execute();

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
            }catch ( IllegalArgumentException e){
                standard.toast(CadastroActivity.this, "Falta preencher alguns campos de texto.", 2);
            }
        });


    }

    // todo - Metodo 2
    public void textLoginClick(){
        textLogin.setOnClickListener(view -> {
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // todo - Metodo 3
    public void togglePasswordVisibility() {
        visibility.setOnClickListener(view -> {
            passwordVisible = !passwordVisible;
            if (passwordVisible) {
                // Mostrar a senha
                editTextPassword.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                visibility.setImageResource(R.drawable.baseline_visibility_off);
            } else {
                // Ocultar a senha
                editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                visibility.setImageResource(R.drawable.baseline_visibility);
            }
            // Mover o cursor para o final do texto
            editTextPassword.setSelection(editTextPassword.getText().length());
        });
    }

    private class VerificacaoTask extends AsyncTask<Void, Void, Boolean>{
        private FirebaseFirestore db = FirebaseFirestore.getInstance();
        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e){

            }
            return true;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                String nome = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String senha = editTextPassword.getText().toString();
                Usuario usuario = new Usuario(nome,email,senha);
                createUsers(CadastroActivity.this,usuario);
            } else {
                // Trate o caso de falha nas verificações
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
                            standard.toast(activity,"Error: " + e.getMessage() + ". tente mais tarde",2);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    standard.toast(activity,"Error: " + e.getMessage() + ". tente mais tarde",2);
                }
            });
        }
    }
}