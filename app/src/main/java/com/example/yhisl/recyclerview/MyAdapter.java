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

    //variables
    private List<String> names;
    private int layout;
    private OnItemClickListener itemClickListener;

    //constructor de la clase
    public MyAdapter (List<String> names, int layout, OnItemClickListener itemClickListener){
        this.names = names;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    //se crea el viewholder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se infla la vista
    View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        //se crea el view holder y se retorna
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //se volva el dato del textview, este metodo solo es llamado cuando se crea el recyclerView y cuadno se agregan nuevos elementos
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(names.get(position), itemClickListener);
    }

    //numero de items
    @Override
    public int getItemCount() {
        return names.size();
    }

    //clase de la que extiende el recyclerview.adapter
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewname;

        //constructor de la clase viewholder
        public ViewHolder(View itemView){
            super(itemView);
            this.textViewname=(TextView) itemView.findViewById(R.id.textViewName);

        }

        public void bind(final String name, final OnItemClickListener listener){
            this.textViewname.setText(name);

            //el itemView sale del parametro de la clase viewholder declarada mas arriba
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(name,getAdapterPosition());
                }
            });
        }
    }

    //evento click que hay que crear para los recyclerview
    public interface OnItemClickListener{
        void onItemClick(String name, int position);
    }
}
