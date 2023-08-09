package com.example.brainlity.Questionarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainlity.R;

import java.util.List;

public class AdapterQuestionarioQuestion extends RecyclerView.Adapter<AdapterQuestionarioQuestion.MyViewHolder> {

    private List<Questionario.Perguntas> listaDePerguntas;
    private Context context;

    public AdapterQuestionarioQuestion(Context context /*, List<Questionario.Perguntas> listaDePerguntas*/){
        this.context = context;
        //this.listaDePerguntas = listaDePerguntas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questionario_question,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //Questionario.Perguntas perguntas = listaDePerguntas.get(position);
        //holder.enuciado.setText(perguntas.getEnunciado());
        holder.enuciado.setText("Testeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        holder.grupoQuestion.removeAllViews();

       /* for (String alternativa : perguntas.getAlternativas()) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setText("a");
            holder.grupoQuestion.addView(radioButton);
        }*/

    }

    public List<Questionario.Perguntas> getListaDePerguntas() {
        return listaDePerguntas;
    }

    public void setListaDePerguntas(List<Questionario.Perguntas> listaDePerguntas) {
        this.listaDePerguntas = listaDePerguntas;
    }

    public int getItemCount() {
        //return listaDePerguntas.size();
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView enuciado;
        RadioGroup grupoQuestion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            enuciado = itemView.findViewById(R.id.enuciado);
            grupoQuestion = itemView.findViewById(R.id.radiogroup_question);
        }
    }
}
