package com.example.yhisl.recyclerview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yhisl.recyclerview.R;
import com.example.yhisl.recyclerview.adapter.MyAdapter;
import com.example.yhisl.recyclerview.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //lista de nombres
    private List<Movie> movies;

    //se declara el recyclerView y lo que necesitamos ( adaptador y layoutManager)
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relleno de la lista con los nombres
        movies = this.getAllMovies();

        //variable del layout
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //muestra elementos en una lista vertical u horizontal
        mLayoutManager = new LinearLayoutManager(this);
        //gridView
        mLayoutManager = new GridLayoutManager(this,2);
        //renderiza todo del mismo tamaño
        mLayoutManager =  new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        //aqui se instancia el adaptador, llamado a la clase, se llama al onItemClickListener que hemos creado en el adaptador
        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movies, int position) {
               // Toast.makeText(MainActivity.this, name + " - " + position,Toast.LENGTH_LONG).show();
                deleteMovie(position);
            }
        });

        //cuando sabemos que la cantidad de datos no variará
        mRecyclerView.setHasFixedSize(true);
        //para hacer una pequeña animación
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    //creando el menú con el layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //opciones del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
               this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //metodo que devuelve una lista de nombres
    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Big Hero 6",R.drawable.bighero));
            add(new Movie("La Lista de Schindler",R.drawable.lista));
            add(new Movie("Monster Inc",R.drawable.monsterinc));
            add(new Movie("Wall-e",R.drawable.walle));

        }};
    }

   //funciòn añadir
    public void addMovie(int position){
        movies.add(position,new Movie("New Movie nº "+(++counter),R.drawable.form));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);

    }

    //funciòn eliminar
    public void deleteMovie(int position){
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
