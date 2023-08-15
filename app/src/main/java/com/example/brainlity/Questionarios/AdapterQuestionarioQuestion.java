package com.example.brainlity.Questionarios;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainlity.R;
import com.google.android.play.integrity.internal.c;

import java.util.List;

public class AdapterQuestionarioQuestion extends RecyclerView.Adapter<AdapterQuestionarioQuestion.MyViewHolder>{

    private Context context;
    private int count;
    private List<Perguntas> perguntasList;
    SharedPreferences sharedPreferences;

    public AdapterQuestionarioQuestion(Context context, List<Perguntas> a){
        this.context = context;
        this.perguntasList = a;
        sharedPreferences = context.getSharedPreferences("Questionario", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questionario_question,parent, false);
        return new AdapterQuestionarioQuestion.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.enunciado.setText( "teste");

    }

    @Override
    public int getItemCount() {
        return sharedPreferences.getInt("Card",0);
    }


    public void setCount(int count) {
        this.count = count;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView enunciado;
        private RadioGroup radioGroup;
        private RadioButton alternativa1,alternativa2,alternativa3,alternativa4;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            enunciado = itemView.findViewById(R.id.enuciado);
            radioGroup = itemView.findViewById(R.id.radiogroup_question);
            alternativa1 = itemView.findViewById(R.id.alternativa_1);
            alternativa2 = itemView.findViewById(R.id.alternativa_2);
            alternativa3 = itemView.findViewById(R.id.alternativa_3);
            alternativa4 = itemView.findViewById(R.id.alternativa_4);
        }
    }
}
