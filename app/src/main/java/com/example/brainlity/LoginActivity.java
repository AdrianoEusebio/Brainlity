package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextEmail;
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
        standard = new Standard();
        standard.actionColorDefault(this);

        editTextEmail = findViewById(R.id.editText_LoginEmail);
        editTextPassword = findViewById(R.id.editText_loginSenha);
        buttonLogin = findViewById(R.id.button_LoginEntrar);
        textCadastro = findViewById(R.id.textCadastro);
        visibility = findViewById(R.id.imageView10);
        buttonLoginClick();
        textCadastroClick();
        togglePasswordVisibility();

    }


    public void buttonLoginClick() {
        CheckUtilits checkUtilits = new CheckUtilits(LoginActivity.this);
        buttonLogin.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString();
            String senha =editTextPassword.getText().toString();

            if(!email.equals("") && !senha.equals("")){
                if(checkUtilits.checkUser(email,senha) != null){
                    standard.toast(LoginActivity.this,"Login feito com sucesso", 1);
                    Usuario usuario = checkUtilits.checkUser(email,senha);
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);
                    finish();
                } else{
                    standard.toast(LoginActivity.this,"Login Invalido", 2);
                }
            } else{
                standard.toast(LoginActivity.this,"Alguns campos não foram preenchidos", 2);
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
}