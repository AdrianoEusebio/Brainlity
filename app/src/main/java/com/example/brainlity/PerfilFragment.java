package com.example.brainlity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.Activity.LoginActivity;
import com.example.brainlity.Activity.MenuActivity;
import com.example.brainlity.Utils.AsyncTask;
import com.example.brainlity.Utils.Standard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class PerfilFragment extends Fragment {

    private View view;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Boolean passwordVisible = false;
    private TextView textSenha, textEmail, textNome, exit, textExit2;
    private ImageView imageView,imageView2;
    private Standard standard = new Standard();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SharedPreferences sharedPreferences;
    private CollectionReference usersCollection;
    private AsyncTask asyncTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseApp.initializeApp(getContext());
        asyncTask = new AsyncTask(getContext());
        asyncTask.execute();
        usersCollection = db.getInstance().collection("Users");
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        textExit2 = view.findViewById(R.id.textView_exit2);
        textNome = view.findViewById(R.id.textView_userNome);
        imageView2 = view.findViewById(R.id.imageView2);
        textSenha = view.findViewById(R.id.textView_userSenha);
        exit = view.findViewById(R.id.textView_exit);
        textEmail = view.findViewById(R.id.textView_userEmail);
        imageView = view.findViewById(R.id.imageView_edit);

        sharedPreferences = getActivity().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        String nome = sharedPreferences.getString("nome","");
        String email = sharedPreferences.getString("email", "");
        String senha = sharedPreferences.getString("senha","");

        togglePasswordVisibility();
        textNome.setText(nome);
        textEmail.setText(email);
        textSenha.setText(senha);

        exit();
        imageView.setOnClickListener(view ->{
            openPopup();

        });
        textSenhaClick();
        return view;

    }

    public void togglePasswordVisibility() {
        imageView2.setOnClickListener(view -> {
            passwordVisible = !passwordVisible;
            if (passwordVisible) {
                textSenha.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageView2.setImageResource(R.drawable.baseline_visibility_off);
            } else {

                textSenha.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageView2.setImageResource(R.drawable.baseline_visibility);
            }
        });
    }


    public void textSenhaClick(){
        textExit2.setOnClickListener(v -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
            dialogBuilder.setTitle("Verificação de email");
            EditText editTextName = new EditText(getContext());
            dialogBuilder.setView(editTextName);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String email = editTextName.getText().toString();
                    recuperarSenha(email);
                }
            });

            dialogBuilder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();
        });
    }
    public void recuperarSenha(String email){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    standard.toast((AppCompatActivity) getActivity(),"Email Enviado com sucesso",1);
                } else {
                    standard.toast((AppCompatActivity) getActivity(),"Falha ao enviar o email",2);
                }
            }
        });
    }
    public void exit(){
        exit.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nome","");
            editor.putString("email","");
            editor.putString("senha","");
            editor.apply();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }
    public void openPopup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Novo nome");
        EditText editTextName = new EditText(getContext());
        builder.setView(editTextName);

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editTextName.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nome", name);
                editor.apply();

                standard.toast((AppCompatActivity) getActivity(),"Nome Trocado com sucesso",1);
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
                getActivity().finish();

                textNome.setText(sharedPreferences.getString("nome",""));
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}