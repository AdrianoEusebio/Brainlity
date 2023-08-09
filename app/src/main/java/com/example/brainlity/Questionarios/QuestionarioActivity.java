package com.example.brainlity.Questionarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.brainlity.DAO.FirebaseHelper;
import com.example.brainlity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionarioActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseHelper firebaseHelper;
    private AdapterQuestionarioQuestion adapter;
    private List<Questionario.Perguntas> listaDePerguntas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Questionarios");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);
        adapter = new AdapterQuestionarioQuestion(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerView_questionario_questions);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
}