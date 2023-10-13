package com.example.brainlity.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brainlity.R;


public class PerfilFragment extends Fragment {

    private View view;
    private TextView textSenha, textEmail;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        textSenha = view.findViewById(R.id.textView_userSenha);
        textEmail = view.findViewById(R.id.textView_userEmail);
        sharedPreferences = getActivity().getSharedPreferences("Usuario", getContext().MODE_PRIVATE);

        String email = sharedPreferences.getString("email", "");
        String senha = sharedPreferences.getString("senha","");

        textEmail.setText(email);
        textSenha.setText(senha);




        return view;
    }
}