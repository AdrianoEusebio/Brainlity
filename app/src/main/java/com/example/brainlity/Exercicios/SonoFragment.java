package com.example.brainlity.Exercicios;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brainlity.R;
import com.example.brainlity.Utils.Standard;

public class SonoFragment extends Fragment {

    View view;
    TextView textView;
    private Standard standard;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sono, container, false);
        standard = new Standard();
        standard.actionColorDefault((AppCompatActivity) getContext());
        textView = view.findViewById(R.id.text_link6);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setOnClickListener(v -> {
            String url = "https://hospitalsiriolibanes.org.br/blog/alimentacaoebemestar/insonia-10-dicas-para-dormir-melhor";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
       return view;
    }
}