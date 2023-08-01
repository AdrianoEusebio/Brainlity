package com.example.brainlity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterQuestionario adapterQuestionario;
    private List<Questionario> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Questionarios");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerView_questionario);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        itemList = new ArrayList<>();
        int count = 0;
        adapterQuestionario = new AdapterQuestionario(this, itemList);
        recyclerView.setAdapter(adapterQuestionario);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int total = snapshot.child("QuestionarioTotal").getValue(Integer.class);
                    adapterQuestionario.setCount(total);
                    for (int i = 1; i <= total; i++) {
                        String reference = "Questionario" + i;
                        String a = snapshot.child(reference).child("Titulo").getValue(String.class);
                        Questionario questionario = new Questionario(a);
                        itemList.add(questionario);
                    }
                    adapterQuestionario.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}