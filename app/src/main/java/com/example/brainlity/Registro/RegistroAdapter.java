package com.example.brainlity.Registro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.Entidade.Registro;
import com.example.brainlity.R;

import java.util.ArrayList;
import java.util.List;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.MyViewHolder>{

    private List<Registro> dataset = new ArrayList<>();
    private DiarioFragment diarioFragment = new DiarioFragment();
    private Context context;
    private  FirebaseBDLocal firebaseBDLocal;

    public RegistroAdapter(List<Registro> dataSet, Context context){
        firebaseBDLocal = new FirebaseBDLocal(context);
        this.dataset = dataSet;
        this.context = context;
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
        holder.humor.setText(item.getHumor());
        holder.data.setText(item.getData());
        holder.id.setText(String.valueOf(position+1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descricao = item.getDescricao();
                Intent intent = new Intent(context, DiarioActivity.class);
                intent.putExtra("descricao", descricao);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                firebaseBDLocal.deleteRegistro(item.getId());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public List<Registro> getDataset(){
        return dataset;
    }
    public void getDataset(List<Registro> registros){
        dataset = registros;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView humor, data, id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            humor = itemView.findViewById(R.id.textRegistro_humor);
            data = itemView.findViewById(R.id.textRegistro_data);
            id = itemView.findViewById(R.id.textRegistro_id);
        }
    }
}
