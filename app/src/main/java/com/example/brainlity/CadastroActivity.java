package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainlity.DAO.CheckUtilits;
import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.utils.Standard;

public class CadastroActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private Button cadastrar, button;
    private TextView textLogin;
    private Standard standard;
    private ImageView visibility;
    private boolean passwordVisible = false;

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

    //Tem um usuario com nome = "", email = "" e senha = ""
    public void buttonCadastrarClick(){
        cadastrar.setOnClickListener(view ->{
            CheckUtilits checkUtilits = new CheckUtilits(CadastroActivity.this);
            FirebaseBDLocal firebaseDatabase = new FirebaseBDLocal(CadastroActivity.this);
            String nome = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String senha = editTextPassword.getText().toString();

            if(!nome.equals("") && !email.equals("") && !senha.equals("")){
                if(!checkUtilits.checkEmailExist(email)){
                    Usuario usuario = new Usuario(nome, senha, email);
                    if(firebaseDatabase.inserirUsuario(usuario) != -1){
                        editTextName.setText(null);
                        editTextPassword.setText(null);
                        editTextEmail.setText(null);
                        standard.toast(CadastroActivity.this,"Cadastro Realizado com sucesso.", 1);
                    }

                } else {
                    standard.toast(CadastroActivity.this,"Esse Email já está sendo utilizado", 2);
                }

            } else{
                standard.toast(CadastroActivity.this,"Você deixou alguns campos vazios", 2);
            }
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

}