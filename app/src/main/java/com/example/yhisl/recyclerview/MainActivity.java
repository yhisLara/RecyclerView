package com.example.yhisl.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //lista de nombres
    private List<String> names;

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
        names = this.getAllNames();

        //variable del layout
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //muestra elementos en una lista vertical u horizontal
        mLayoutManager = new LinearLayoutManager(this);

        //aqui se instancia el adaptador, llamado a la clase, se llama al onItemClickListener que hemos creado en el adaptador
        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
               // Toast.makeText(MainActivity.this, name + " - " + position,Toast.LENGTH_LONG).show();
                deleteName(position);
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
                this.addname(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //metodo que devuelve una lista de nombres
    private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("Yhis");
            add("criis");
            add("simo");
            add("nano");
            add("vero");
        }};
    }

    //funciòn añadir
    public void addname(int position){
        names.add(position,"New Name "+(++counter));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);

    }

    //funciòn eliminar
    public void deleteName(int position){
        names.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
