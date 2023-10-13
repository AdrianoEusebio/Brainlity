package com.example.brainlity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainlity.DAO.CheckUtilits;
import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.utils.Standard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // todo - Atributos
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText editTextEmail;
    private SharedPreferences sharedPreferences;

    private EditText editTextPassword;
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
        sharedPreferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        editTextEmail = findViewById(R.id.editText_LoginEmail);
        editTextPassword = findViewById(R.id.editText_loginSenha);
        buttonLogin = findViewById(R.id.button_LoginEntrar);
        textCadastro = findViewById(R.id.textCadastro);
        visibility = findViewById(R.id.imageView10);

        // todo - metodo para trocar a cor da barra de notificações
        standard.actionColorDefault(this);

        // todo - 1 metodo de ação para o botão Login onde irá fazer as devidas verificações e checagens para o login do usuario
        buttonLoginClick();

        // todo - 2 metodo de ação do textCadastro, ao apertar o usuario será redirecionado a tela de CadastroActivity
        textCadastroClick();

        // todo - 3 metodo para deixar a senha visivel ou mascarado
        togglePasswordVisibility();

    }

    // todo - 1 metodo
    public void buttonLoginClick() {
        buttonLogin.setOnClickListener(view -> {

            // todo - declaração das devidas variaveis
            String email = editTextEmail.getText().toString();
            String senha = editTextPassword.getText().toString();

            // todo 1 Verificação, saber se os editTexts estão nulo ou não
            if(!email.equals("") || !senha.equals("")){
                checkUser(email,senha);
            } else {
                standard.toast(LoginActivity.this,"Falta preencher alguns campos", 2);
            }

        });
    }

    public void textCadastroClick(){
        textCadastro.setOnClickListener(view ->{
            Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(intent);
            finish();
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

    public void checkUser(String email, String senha){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Task<AuthResult> user = firebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    salvarCredenciais(email,senha);
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    standard.toast(LoginActivity.this, "Login Invalido",2);
                }
            }
        });
    }

    public void salvarCredenciais(String email, String senha){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("senha", senha);
        editor.apply();
    }
}