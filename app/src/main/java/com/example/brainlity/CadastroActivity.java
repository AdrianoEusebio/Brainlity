package com.example.brainlity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.utils.Standard;

public class CadastroActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Standard standard = new Standard();
        standard.actionColorDefault(this);

        editTextEmail = findViewById(R.id.editText_CadastroEmail);
        editTextName = findViewById(R.id.editText_CadastroNome);
        editTextPassword = findViewById(R.id.editText_CadastroSenha);
        cadastrar = findViewById(R.id.button_cadastrar);
        buttonClick();
    }


    //Tem um usuario com nome = "", email = "" e senha = ""
    public void buttonClick(){
        cadastrar.setOnClickListener(view ->{
            FirebaseBDLocal firebaseDatabase = new FirebaseBDLocal(CadastroActivity.this);
            String nome = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String senha = editTextPassword.getText().toString();
            Usuario usuario = new Usuario(nome, senha, email);

            if(firebaseDatabase.inserirUsuario(usuario) != -1){
                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "Deu mrd", Toast.LENGTH_SHORT).show();
            }
        });
    }
}