package com.example.brainlity.Questionarios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainlity.R;

import java.security.Key;
import java.util.List;


public class AdapterQuestionarioCard extends RecyclerView.Adapter<AdapterQuestionarioCard.MyViewHolder>{

    //Atributos
    private Context context;
    private int count;
    private List<Questionario> questionarioList;
    private SharedPreferences sharedPreferences;
    private long perguntas;


    //Construtor
    public AdapterQuestionarioCard(Context context, List<Questionario> a) {
        this.context = context;
        this.questionarioList = a;
        sharedPreferences = context.getSharedPreferences("Questionario", Context.MODE_PRIVATE);
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
        Questionario questionario = questionarioList.get(position);
        holder.nomeQuestionario.setText(questionario.getTitulo());
        holder.perguntasTotal.setText("Perguntas:" + questionario.getPerguntas());


        holder.itemView.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Toast.makeText(v.getContext(), "Sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,QuestionarioQuestionActivity.class);
            editor.putInt("Card",questionario.getPerguntas());
            editor.commit();
            context.startActivity(intent);
        });


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

    public long getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(long perguntas) {
        this.perguntas = perguntas;
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

