package com.example.brainlity.Insight;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.brainlity.R;

import java.util.List;

public class AdapterShorts extends RecyclerView.Adapter<AdapterShorts.MyViewHolder> {
    private List<Insight> dataSet;


    public AdapterShorts(List<Insight> dataSet) {
        this.dataSet = dataSet;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shorts_card,parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Insight item = dataSet.get(position);

        holder.autorTextView.setText(item.getAuthor());
        holder.textoTextView.setText(item.getText());
        int id = item.getFundo();
        switch (id){
            case 1:
                holder.imageView.setImageResource(R.drawable.image_shorts1);
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.image_shorts2);
                break;
            case 3:
                holder.imageView.setImageResource(R.drawable.image_shorts3);
                break;
            case 4:
                holder.imageView.setImageResource(R.drawable.image_shorts4);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public View view;
        public TextView autorTextView;
        public TextView textoTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_shorts1);
            autorTextView = itemView.findViewById(R.id.textView_author);
            view = itemView.findViewById(R.id.view);
            textoTextView = itemView.findViewById(R.id.textView_itemCard);

            int corEscura = Color.argb(100, 0, 0, 0);
            view.setBackgroundColor(corEscura);

        }
    }
}
