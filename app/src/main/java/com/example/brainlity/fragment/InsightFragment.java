package com.example.brainlity.fragment;

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

import com.example.brainlity.DAO.FirebaseSync;
import com.example.brainlity.Insight.AdapterShorts;
import com.example.brainlity.Insight.Insight;
import com.example.brainlity.R;
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
    DatabaseReference databaseReference;
    List<Insight> insightsList;
    ImageView back;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseApp.initializeApp(getActivity());
        FirebaseSync firebaseSync = new FirebaseSync(getContext());
        databaseReference = FirebaseDatabase.getInstance().getReference("Frases");


        insightsList = firebaseSync.getAllFrases();
        adapterShorts = new AdapterShorts(insightsList);

        view =  inflater.inflate(R.layout.fragment_insight, container, false);
        back = view.findViewById(R.id.shorts_back);

        recyclerView = view.findViewById(R.id.recyclerview_shorts);
        recyclerViewConfig();

        return view;
    }


    public void recyclerViewConfig(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setReverseLayout(true);
        lm.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        final SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapterShorts);
    }
}