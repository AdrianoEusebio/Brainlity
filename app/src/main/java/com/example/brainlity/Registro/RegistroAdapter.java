package com.example.brainlity.Registro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainlity.R;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.MyViewHolder>{
    @NonNull
    @Override
    public RegistroAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registro_card,parent, false);
        return new RegistroAdapter.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
