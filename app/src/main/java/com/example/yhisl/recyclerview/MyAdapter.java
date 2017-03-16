package com.example.yhisl.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yhisl on 08/03/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //variables
    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

    //constructor de la clase
    public MyAdapter (List<Movie> movies, int layout, OnItemClickListener itemClickListener){
        this.movies = movies;
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
        context = parent.getContext();
        return vh;
    }

    //se volva el dato del textview, este metodo solo es llamado cuando se crea el recyclerView y cuadno se agregan nuevos elementos
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    //numero de items
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //clase de la que extiende el recyclerview.adapter
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewname;
        public ImageView imageViewPoster;

        //constructor de la clase viewholder
        public ViewHolder(View itemView){
            super(itemView);
            textViewname = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movies, final OnItemClickListener listener){
            //procesar datos o renderizar
            textViewname.setText(movies.getName());
            //cambia tamaño de las imagenes para que ocupe el tamaño completo
            Picasso.with(context).load(movies.getPoster()).fit().into(imageViewPoster);
            imageViewPoster.setImageResource(movies.getPoster());

            //el itemView sale del parametro de la clase viewholder declarada mas arriba
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movies,getAdapterPosition());
                }
            });
        }
    }

    //evento click que hay que crear para los recyclerview
    public interface OnItemClickListener{
        void onItemClick(Movie movies, int position);
    }
}
