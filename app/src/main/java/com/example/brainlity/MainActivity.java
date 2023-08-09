package com.example.brainlity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.brainlity.Questionarios.AdapterQuestionarioCard;
import com.example.brainlity.Questionarios.Questionario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //atributos
    private RecyclerView recyclerView;
    private AdapterQuestionarioCard adapterQuestionario;
    private List<Questionario> itemList;

    //metodo onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaração dos atributos do FIREBASE
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Questionarios");

        //Declaração do atributo do RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerView_questionario);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        itemList = new ArrayList<>();
        adapterQuestionario = new AdapterQuestionarioCard(this, itemList);
        recyclerView.setAdapter(adapterQuestionario);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    int total = snapshot.child("QuestionarioTotal").getValue(int.class);
                    adapterQuestionario.setCount(total);

                    for (int i = 1; i <= total; i++) {

                        String titulo = snapshot.child("Questionario" + i).child("Titulo").getValue(String.class);
                        long perguntasCount = snapshot.child("Questionario" + i).child("perguntas").getChildrenCount();
                        Questionario questionario = new Questionario(titulo,perguntasCount);
                        itemList.add(questionario);
                    }
                    adapterQuestionario.notifyDataSetChanged();


                }else {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}