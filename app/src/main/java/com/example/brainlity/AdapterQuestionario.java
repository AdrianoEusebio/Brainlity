package com.example.brainlity;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class AdapterQuestionario extends RecyclerView.Adapter<AdapterQuestionario.MyViewHolder>{

    private List<Questionario> itemList;
    private Context context;

    private int count;
    public AdapterQuestionario(Context context, List<Questionario> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questionario,parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Sucesso", Toast.LENGTH_SHORT).show();
        });

        Questionario questionario = itemList.get(position);
        holder.nomeQuestionario.setText(questionario.getTitulo());
    }

    @Override
    public int getItemCount() {
        return count ;
    }

    public void setCount(int a){
        this.count = a;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nomeQuestionario;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeQuestionario = itemView.findViewById(R.id.textView_nomeQuestionario);
        }
    }
}

