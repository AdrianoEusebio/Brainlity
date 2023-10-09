package com.example.brainlity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.brainlity.utils.Standard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class CadastroActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private Button cadastrar, button;
    private TextView textLogin;
    private Standard standard;
    private ImageView visibility;
    private boolean passwordVisible = false;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private CheckUtilits checkUtilits = new CheckUtilits(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        standard = new Standard();
        standard.actionColorDefault(this);

        editTextEmail = findViewById(R.id.editText_CadastroEmail);
        editTextName = findViewById(R.id.editText_CadastroNome);
        editTextPassword = findViewById(R.id.editText_CadastroSenha);
        visibility = findViewById(R.id.imageView9);
        textLogin = findViewById(R.id.textLogin);
        cadastrar = findViewById(R.id.button_cadastrar);
        button = findViewById(R.id.button);

        buttonCadastrarClick();
        textLoginClick();
        buttonClick();
        togglePasswordVisibility();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CadastroActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void buttonCadastrarClick(){
        cadastrar.setOnClickListener(view ->{
            CheckUtilits checkUtilits = new CheckUtilits(CadastroActivity.this);
            String nome = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String senha = editTextPassword.getText().toString();

            if(checkUtilits.checkFieldsVoid(nome,email,senha)){
                mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if(task.isSuccessful()){
                            SignInMethodQueryResult result = task.getResult();

                            if(result.getSignInMethods().isEmpty()){
                               if(checkUtilits.checkPasswordChar(senha)){
                                   createUser(email,senha);

                               } else {
                                   standard.toast(CadastroActivity.this,"A senha precisa ter 6 ou mais caracteres.",2);}

                            } else {

                                standard.toast(CadastroActivity.this,"Email já está sendo utilizado",2);}

                        } else {
                            Exception exception = task.getException();
                            if(exception != null){
                                standard.toast(CadastroActivity.this,"Email Invalido",2);
                            }
                        }
                    }
                });
            } else {
                standard.toast(CadastroActivity.this,"Falta preencher alguns campos de texto.",2);}
        });
    }

    public void textLoginClick(){
        textLogin.setOnClickListener(view -> {
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void buttonClick(){
        button.setOnClickListener(view ->{
            FirebaseBDLocal firebaseDatabase = new FirebaseBDLocal(CadastroActivity.this);
            firebaseDatabase.deleteAllUsuarios();
            Toast.makeText(this, "Delete users ", Toast.LENGTH_SHORT).show();
        });
    }

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

    public void createUser(String email, String senha) {
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {

                // Sucesso ao criar a conta. O usuário será automaticamente logado.
                standard.toast(CadastroActivity.this, "Conta criada com Sucesso", 1);
                FirebaseUser user = mAuth.getCurrentUser();

                // Enviar e-mail de verificação
                checkUtilits.checkEmailInvite(user,CadastroActivity.this);

            } else {
                // Falha ao criar a conta. Trate o erro aqui.
            }
        });
    }
}