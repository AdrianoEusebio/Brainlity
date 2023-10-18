package com.example.brainlity.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brainlity.R;

public class MenuFragment extends Fragment {
    private TextView textUser;
    private View view;
    private SharedPreferences sharedPreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        sharedPreferences = getActivity().getSharedPreferences("Usuario", getContext().MODE_PRIVATE);
        String nome = sharedPreferences.getString("nome","");
        textUser = view.findViewById(R.id.textUser);
        textUser.setText("Olá, " + nome);
        return view;
    }
}