package com.example.brainlity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.brainlity.Insight.AdapterShorts;
import com.example.brainlity.Insight.Insight;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InsightFragment extends Fragment {

    private RecyclerView recyclerView;
    AdapterShorts adapterShorts;
    List<Insight> dataSet = new ArrayList<>();
    DatabaseReference databaseReference;
    ImageView back;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_insight, container, false);
        FirebaseApp.initializeApp(getActivity());
        back = view.findViewById(R.id.shorts_back);
        adapterShorts = new AdapterShorts(dataSet);
        databaseReference = FirebaseDatabase.getInstance().getReference("Frases");
        recyclerView = view.findViewById(R.id.recyclerview_shorts);
        recyclerViewConfig();

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
                        Toast.makeText(getActivity(), "Erro ao carregar algumas coisa", Toast.LENGTH_SHORT).show();
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

        return view;
    }


    public void recyclerViewConfig(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        final SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapterShorts);
    }
}