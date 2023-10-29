package com.example.brainlity.Exercicios;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brainlity.R;

import org.w3c.dom.Text;

public class FocoFragment extends Fragment {
    private View view;
    private TextView textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_foco, container, false);
        textView = view.findViewById(R.id.text_link3);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(v -> {
            String url = "https://www.unimedcampinas.com.br/blog/saude-emocional/o-que-causa-a-dificuldade-de-concentracao-e-o-que-ela-pode-significar";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
        return view;
    }
}