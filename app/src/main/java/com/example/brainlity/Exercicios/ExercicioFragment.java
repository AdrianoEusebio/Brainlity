package com.example.brainlity.Exercicios;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.brainlity.Exercicios.Respiração.ConhecimentoActivity;
import com.example.brainlity.Utils.AsyncTask;
import com.example.brainlity.Exercicios.Relaxar.RelaxarActivity;
import com.example.brainlity.Exercicios.Respiração.RespirarMainActivity;
import com.example.brainlity.R;


public class ExercicioFragment extends Fragment {


    View view;
    private ColorStateList colorStateList;
    private ConstraintLayout bottomRespirar,bottomRelaxar;
    private LinearLayout bottomMeditacao, bottomSaudeMental, bottomAnsiedade, bottomSono, bottomFoco,bottomRotina;
    private AsyncTask asyncTask = new AsyncTask(getContext());
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.color_3));
        view = inflater.inflate(R.layout.fragment_exercicio, container, false);
        bottomRespirar = view.findViewById(R.id.button_respirar);

        bottomRespirar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundTintList(colorStateList);
                        return true;
                    case MotionEvent.ACTION_UP:
                        v.getGlobalVisibleRect(rect);
                        if(rect.contains(x + rect.left, y + rect.top)){
                            Intent intent = new Intent(getActivity(), RespirarMainActivity.class);
                            startActivity(intent);
                            v.setBackgroundTintList(null);
                        } else {
                            v.setBackgroundTintList(null);
                        }
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundTintList(null);
                        return true;
                }
                return false;
            }
        });

        bottomRelaxar = view.findViewById(R.id.button_relaxar);
        bottomRelaxar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundTintList(colorStateList);
                        return true;
                    case MotionEvent.ACTION_UP:
                        v.getGlobalVisibleRect(rect);
                        if(rect.contains(x + rect.left, y + rect.top)){
                            Intent intent = new Intent(getActivity(), RelaxarActivity.class);
                            startActivity(intent);
                            v.setBackgroundTintList(null);
                        } else {
                            v.setBackgroundTintList(null);
                        }
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundTintList(null);
                        return true;
                }
                return false;
            }
        });

        fragmentSelect();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void fragmentSelect(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent intent = new Intent(getActivity(), ConhecimentoActivity.class);
        bottomMeditacao = view.findViewById(R.id.button_meditacao);
        bottomMeditacao.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundTintList(colorStateList);
                        return true;

                    case MotionEvent.ACTION_UP:
                        v.getGlobalVisibleRect(rect);
                        if(rect.contains(x + rect.left, y + rect.top)){
                            editor.putString("conhecimento","meditacao");
                            editor.apply();
                            startActivity(intent);
                            v.setBackgroundTintList(null);
                        } else {
                            v.setBackgroundTintList(null);
                        }
                        return true;

                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundTintList(null);
                        return true;

                }
                return false;
            }
        });
        bottomRotina = view.findViewById(R.id.button_rotina);
        bottomRotina.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundTintList(colorStateList);
                        return true;

                    case MotionEvent.ACTION_UP:
                        v.getGlobalVisibleRect(rect);
                        if(rect.contains(x + rect.left, y + rect.top)){
                            editor.putString("conhecimento","rotina");
                            editor.apply();
                            startActivity(intent);
                            v.setBackgroundTintList(null);
                        } else {
                            v.setBackgroundTintList(null);
                        }
                        return true;

                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundTintList(null);
                        return true;

                }
                return false;
            }
        });

        bottomAnsiedade = view.findViewById(R.id.button_ansiedade);
        bottomAnsiedade.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundTintList(colorStateList);
                        return true;

                    case MotionEvent.ACTION_UP:
                        v.getGlobalVisibleRect(rect);
                        if(rect.contains(x + rect.left, y + rect.top)){
                            editor.putString("conhecimento","ansiedade");
                            editor.apply();
                            startActivity(intent);
                            v.setBackgroundTintList(null);
                        } else {
                            v.setBackgroundTintList(null);
                        }
                        return true;

                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundTintList(null);
                        return true;

                }
                return false;
            }
        });

        bottomFoco = view.findViewById(R.id.button_foco);
        bottomFoco.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundTintList(colorStateList);
                        return true;

                    case MotionEvent.ACTION_UP:
                        v.getGlobalVisibleRect(rect);
                        if(rect.contains(x + rect.left, y + rect.top)){
                            editor.putString("conhecimento","foco");
                            editor.apply();
                            startActivity(intent);
                            v.setBackgroundTintList(null);
                        } else {
                            v.setBackgroundTintList(null);
                        }
                        return true;

                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundTintList(null);
                        return true;

                }
                return false;
            }
        });

        bottomSaudeMental = view.findViewById(R.id.button_saudemental);
        bottomSaudeMental.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundTintList(colorStateList);
                        return true;

                    case MotionEvent.ACTION_UP:
                        v.getGlobalVisibleRect(rect);
                        if(rect.contains(x + rect.left, y + rect.top)){
                            editor.putString("conhecimento","saudemental");
                            editor.apply();
                            startActivity(intent);
                            v.setBackgroundTintList(null);
                        } else {
                            v.setBackgroundTintList(null);
                        }
                        return true;

                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundTintList(null);
                        return true;

                }
                return false;
            }
        });

        bottomSono = view.findViewById(R.id.button_sono);
        bottomSono.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundTintList(colorStateList);
                        return true;

                    case MotionEvent.ACTION_UP:
                        v.getGlobalVisibleRect(rect);
                        if(rect.contains(x + rect.left, y + rect.top)){
                            editor.putString("conhecimento","sono");
                            editor.apply();
                            startActivity(intent);
                            v.setBackgroundTintList(null);
                        } else {
                            v.setBackgroundTintList(null);
                        }
                        return true;

                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundTintList(null);
                        return true;

                }
                return false;
            }
        });
    }
}