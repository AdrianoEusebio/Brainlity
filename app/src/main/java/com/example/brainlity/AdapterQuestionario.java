package com.example.brainlity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterQuestionario extends RecyclerView.Adapter<AdapterQuestionario.MyViewHolder>{

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nomeQuestionario, perguntas;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeQuestionario = itemView.findViewById(R.id.textView_nomeQuestionario);
            perguntas = itemView.findViewById(R.id.textView_quantidadePerguntas);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questionario,parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ArrayList<String> arrayList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Questionarios").child("Questionario1");
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Sucesso", Toast.LENGTH_SHORT).show();
        });

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String titulo = snapshot.child("Titulo").getValue(String.class);
                arrayList.add(titulo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.nomeQuestionario.setText("a");
        holder.perguntas.setText("5");

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

