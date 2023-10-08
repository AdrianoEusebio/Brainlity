package com.example.brainlity.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brainlity.R;
import com.example.brainlity.Usuario;


public class PerfilFragment extends Fragment {

    View view;
    TextView textNome, textEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        textNome = view.findViewById(R.id.textView_userNome);
        textEmail = view.findViewById(R.id.textView_userEmail);

        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("usuario")) {
            Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");

            // Agora você pode usar o objeto Usuario
            String nome = usuario.getNome();
            String email = usuario.getEmail();
            textNome.setText(nome);
            textEmail.setText(email);
        }


        return view;
    }
}