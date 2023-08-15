package com.example.brainlity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.brainlity.Questionarios.AdapterQuestionarioCard;
import com.example.brainlity.Questionarios.AdapterQuestionarioQuestion;
import com.example.brainlity.Questionarios.Perguntas;
import com.example.brainlity.Questionarios.Questionario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionarioMainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AdapterQuestionarioCard adapter;
    private List<Questionario> itemList;
    private SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Questionarios");
        preferences = getSharedPreferences("Questionario",MODE_PRIVATE);
        itemList = new ArrayList<>();
        adapter = new AdapterQuestionarioCard(this,itemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.recylerView_card);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    SharedPreferences.Editor editor = preferences.edit();

                    int total = snapshot.child("QuestionarioTotal").getValue(int.class);
                    adapter.setCount(total);

                    for (int i = 1; i <= total; i++) {
                        String titulos = snapshot.child("Questionario" + i).child("Titulo").getValue(String.class);
                        int perguntasTotal = snapshot.child("Questionario" + i).child("perguntaTotal").getValue(int.class);
                        Questionario questionario = new Questionario(titulos,perguntasTotal);
                        itemList.add(questionario);


                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }

}