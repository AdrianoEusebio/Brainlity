package com.example.brainlity.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brainlity.R;


public class PerfilFragment extends Fragment {

    View view;
    TextView nome, email;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        nome = view.findViewById(R.id.textView_userNome);
        email = view.findViewById(R.id.textView_userEmail);
        nome.setText(getActivity().getIntent().getStringExtra("Nome"));
        email.setText(getActivity().getIntent().getStringExtra("Email"));



        return view;
    }
}