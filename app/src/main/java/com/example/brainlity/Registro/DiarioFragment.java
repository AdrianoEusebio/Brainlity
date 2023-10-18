package com.example.brainlity.Registro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
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
import com.example.brainlity.Entidade.Registro;
import com.example.brainlity.R;
import com.example.brainlity.RegistroActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DiarioFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private FloatingActionButton button;
    private RegistroAdapter registroAdapter;
    private FirebaseBDLocal firebaseBDLocal;
    private List<Registro> registros;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_diario, container, false);

        firebaseBDLocal = new FirebaseBDLocal(getContext());
        registros = firebaseBDLocal.getAllRegistros();
        button = view.findViewById(R.id.floatingActionButton);
        registroAdapter = new RegistroAdapter(registros);
        recyclerView = view.findViewById(R.id.recyclerView_registro);
        recyclerViewConfig();
        buttonCLick();


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
        recyclerView.setAdapter(registroAdapter);
    }

    public void buttonCLick(){
        button.setOnClickListener(view ->{
            Intent intent = new Intent(getActivity(), RegistroActivity.class);
            startActivity(intent);
        });
    }

}