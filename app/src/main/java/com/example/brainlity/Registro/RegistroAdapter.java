package com.example.brainlity.Registro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.brainlity.Entidade.Insight;
import com.example.brainlity.Entidade.Registro;
import com.example.brainlity.R;

import java.util.ArrayList;
import java.util.List;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.MyViewHolder>{

    private List<Registro> dataset = new ArrayList<>();

    public RegistroAdapter(List<Registro> dataSet){
        this.dataset = dataSet;
    }

    @NonNull
    @Override
    public RegistroAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registro_card,parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Registro item = dataset.get(position);
        holder.frase.setText(item.getFrase());
        holder.humor.setText(item.getHumor());
        holder.data.setText(item.getData());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView humor, data, frase;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            humor = itemView.findViewById(R.id.textRegistro_humor);
            data = itemView.findViewById(R.id.textRegistro_data);
            frase = itemView.findViewById(R.id.textRegistro_descricao);

        }
    }
}
