package com.example.brainlity.Insight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.Entidade.Insight;
import com.example.brainlity.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class InsightFragment extends Fragment {

    private RecyclerView recyclerView;
    private InsightAdapter insightAdapter;
    private DatabaseReference databaseReference;
    private List<Insight> insightsList;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_insight, container, false);
        FirebaseApp.initializeApp(getActivity());
        FirebaseBDLocal firebaseBDLocal = new FirebaseBDLocal(getContext());
        databaseReference = FirebaseDatabase.getInstance().getReference("Frases");


        insightsList = firebaseBDLocal.getAllFrases();
        insightAdapter = new InsightAdapter(insightsList);
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
        recyclerView.setAdapter(insightAdapter);
    }
}