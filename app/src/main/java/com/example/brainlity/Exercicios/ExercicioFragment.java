package com.example.brainlity.Exercicios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.brainlity.Exercicios.Respiração.ConhecimentoActivity;
import com.example.brainlity.Utils.AsyncTask;
import com.example.brainlity.Exercicios.Relaxar.RelaxarActivity;
import com.example.brainlity.Exercicios.Respiração.RespirarMainActivity;
import com.example.brainlity.R;


public class ExercicioFragment extends Fragment {


    View view;
    ImageView bottomRespirar, bottomRelaxar, bottomMeditacao, bottomSaudeMental, bottomAnsiedade, bottomSono, bottomFoco,bottomRotina;
    AsyncTask asyncTask = new AsyncTask(getContext());
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercicio, container, false);
        bottomRespirar = view.findViewById(R.id.button_respirar);
        bottomRespirar.setOnClickListener(view ->{
            Intent intent = new Intent(getActivity(), RespirarMainActivity.class);
            startActivity(intent);
        });

        bottomRelaxar = view.findViewById(R.id.button_relaxar);
        bottomRelaxar.setOnClickListener(view ->{
            Intent intent = new Intent(getActivity(), RelaxarActivity.class);
            startActivity(intent);
        });

        fragmentSelect();
        return view;
    }

    public void fragmentSelect(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent intent = new Intent(getActivity(), ConhecimentoActivity.class);
        bottomMeditacao = view.findViewById(R.id.button_meditacao);
        bottomMeditacao.setOnClickListener(v -> {
            startActivity(intent);
            editor.putString("conhecimento","meditacao");
            editor.apply();
        });

        bottomRotina = view.findViewById(R.id.button_rotina);
        bottomRotina.setOnClickListener(v -> {
            startActivity(intent);
            editor.putString("conhecimento","rotina");
            editor.apply();
        });

        bottomAnsiedade = view.findViewById(R.id.button_ansiedade);
        bottomAnsiedade.setOnClickListener(v -> {
            startActivity(intent);
            editor.putString("conhecimento","ansiedade");
            editor.apply();
        });

        bottomFoco = view.findViewById(R.id.button_foco);
        bottomFoco.setOnClickListener(v -> {
            startActivity(intent);
            editor.putString("conhecimento","foco");
            editor.apply();
        });

        bottomSaudeMental = view.findViewById(R.id.button_saudemental);
        bottomSaudeMental.setOnClickListener(v -> {
            startActivity(intent);
            editor.putString("conhecimento","saudemental");
            editor.apply();
        });

        bottomSono = view.findViewById(R.id.button_sono);
        bottomSono.setOnClickListener(v -> {
            startActivity(intent);
            editor.putString("conhecimento","sono");
            editor.apply();
        });

    }
}