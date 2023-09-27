package com.example.brainlity.Exercicios.Relaxar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.brainlity.R;
import com.example.brainlity.utils.Standard;

public class RelaxarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Standard standard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxar);
        standard = new Standard();
        standard.actionColorDefault(this);
    }
}