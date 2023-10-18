package com.example.brainlity.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainlity.Activity.LoginActivity;
import com.example.brainlity.R;
import com.example.brainlity.Activity.SplashActivity;
import com.example.brainlity.Utils.Standard;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class PerfilFragment extends Fragment {

    private View view;
    private TextView textSenha, textEmail, textNome, exit, alterarSenha;
    private ImageView imageView;
    private Standard standard = new Standard();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SharedPreferences sharedPreferences;
    private CollectionReference usersCollection;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseApp.initializeApp(getContext());
        usersCollection = db.getInstance().collection("Users");
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        textNome = view.findViewById(R.id.textView_userNome);
        textSenha = view.findViewById(R.id.textView_userSenha);
        exit = view.findViewById(R.id.textView_exit);
        textEmail = view.findViewById(R.id.textView_userEmail);
        imageView = view.findViewById(R.id.imageView_edit);

        sharedPreferences = getActivity().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        String nome = sharedPreferences.getString("nome","");
        String email = sharedPreferences.getString("email", "");
        String senha = sharedPreferences.getString("senha","");

        textNome.setText(nome);
        textEmail.setText(email);
        textSenha.setText(senha);

        exit();
        imageView.setOnClickListener(view ->{
            openPopup();

        });
        return view;
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

                if(standard.avaliarConexao(getContext())){
                    standard.toast((AppCompatActivity)getActivity(),"Seu nome foi modificado e salvo na nuvem", 1);
                    Intent intent = new Intent(getActivity(),SplashActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    standard.toast((AppCompatActivity)getActivity(),"Seu nome não foi 100% salvo, conecte-se a internet para salvar na nuvem", 2);
                }
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