package com.example.brainlity;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brainlity.Exercicios.Respiração.RespirarMainActivity;


public class ExercicioFragment extends Fragment {


    View view;
    ConstraintLayout bottomRespirar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercicio, container, false);
        bottomRespirar = view.findViewById(R.id.bottom_respirar);
        bottomRespirar.setOnClickListener(view ->{
            Intent intent = new Intent(getActivity(), RespirarMainActivity.class);
            startActivity(intent);
        });
        return view;
    }
}