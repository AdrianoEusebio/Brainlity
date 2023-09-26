package com.example.brainlity.Insight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.brainlity.MenuActivity;
import com.example.brainlity.R;
import com.example.brainlity.utils.Standard;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InsightActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    AdapterShorts adapterShorts;
    List<Insight> dataSet = new ArrayList<>();
    DatabaseReference databaseReference;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shorts);
        FirebaseApp.initializeApp(this);

        //Definições padrões
        Standard standard = new Standard();
        standard.actionColorDefault(this);


        //Atribuição de valores
        back = findViewById(R.id.shorts_back);
        adapterShorts = new AdapterShorts(dataSet);
        databaseReference = FirebaseDatabase.getInstance().getReference("Frases");
        recyclerView = findViewById(R.id.recyclerview_shorts);

        //========================================================Action===========================================================================

        buttonBackAction();
        recyclerViewConfig();

        //Sincronização com o firebase, Atribuição do Insight
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSet.clear();
                int i = 1;
                String text = "";
                String author = "";
                int image = 1;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    try{
                        text = snapshot.child("frase"+i).child("texto").getValue(String.class);
                        author= snapshot.child("frase"+i).child("autor").getValue(String.class);
                        image = snapshot.child("frase"+i).child("fundo").getValue(int.class);
                    }catch (DatabaseException e){
                        Toast.makeText(InsightActivity.this, "Erro ao carregar algumas coisa", Toast.LENGTH_SHORT).show();
                    }
                    int imageId;
                    switch (image){
                        case 1:
                            imageId = R.drawable.image_shorts1;
                            break;
                        case 2:
                            imageId = R.drawable.image_shorts2;
                            break;
                        case 3:
                            imageId = R.drawable.image_shorts3;
                            break;
                        case 4:
                            imageId = R.drawable.image_shorts4;
                            break;
                        default:
                            imageId = R.drawable.ic_launcher_background;
                    }

                    Insight insight = new Insight(text,author,imageId);
                    dataSet.add(insight);
                    i++;
                }
                adapterShorts.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void buttonBackAction(){
        back.setOnClickListener(view ->{
            Intent intent = new Intent(InsightActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();

        });
    }

    public void recyclerViewConfig(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        final SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapterShorts);
    }
}