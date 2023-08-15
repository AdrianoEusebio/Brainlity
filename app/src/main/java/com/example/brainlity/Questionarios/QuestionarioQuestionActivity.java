package com.example.brainlity.Questionarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.brainlity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionarioQuestionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterQuestionarioQuestion adapter;
    private SharedPreferences sharedPreferences;
    private List<Perguntas> itemList;
    List<String> alternativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario_question);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Questionarios");


        sharedPreferences = getSharedPreferences("Questionario", MODE_PRIVATE);

        itemList = new ArrayList<>();
        alternativas = new ArrayList<>();
        adapter = new AdapterQuestionarioQuestion(this,itemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerView_questions);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
}