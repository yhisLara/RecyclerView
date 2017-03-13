package com.example.yhisl.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yhisl on 08/03/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> names;
    private int layout;
    private OnItemClickListener itemClickListener;

    public MyAdapter (List<String> names, int layout, OnItemClickListener itemClickListener){
        this.names = names;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se infla la vista
    View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        //se crea el view holder y se retorna
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(names.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewname;

        public ViewHolder(View itemView){
            super(itemView);
            this.textViewname=(TextView) itemView.findViewById(R.id.textViewName);

        }

        public void bind(final String name, final OnItemClickListener listener){
            this.textViewname.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(name,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String name, int position);
    }
}
