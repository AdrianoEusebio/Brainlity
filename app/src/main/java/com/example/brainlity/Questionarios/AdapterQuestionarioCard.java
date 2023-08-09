package com.example.brainlity.Questionarios;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainlity.R;

import java.util.List;


public class AdapterQuestionarioCard extends RecyclerView.Adapter<AdapterQuestionarioCard.MyViewHolder>{

    //Atributos
    private List<Questionario> itemList;
    private Context context;
    private int count;

    //Construtor
    public AdapterQuestionarioCard(Context context, List<Questionario> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    //Metodo para setar e inflar a item_view
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questionario_card,parent, false);
        return new MyViewHolder(itemLista);
    }

    //Metodo de ação da item_view
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,QuestionarioActivity.class);
            context.startActivity(intent);
        });

        //acao de trocar de tela
        Questionario questionario = itemList.get(position);
        holder.nomeQuestionario.setText(questionario.getTitulo());
        holder.perguntasTotal.setText("Perguntas:" + questionario.getPerguntas());
    }

    //metodo para retorna quantas vezes deve ser criada o item_view
    @Override
    public int getItemCount() {
        return count;
    }


    //metodo para setar quantas vezes deve ser criada o item_view
    public void setCount(int a){
        this.count = a;
    }


    //SubClasse viewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nomeQuestionario, perguntasTotal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeQuestionario = itemView.findViewById(R.id.textView_nomeQuestionario);
            perguntasTotal = itemView.findViewById(R.id.textView_quantidadePerguntas);
        }
    }
}

