package com.example.yhisl.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
                Toast.makeText(MainActivity.this, name + " - " + position,Toast.LENGTH_LONG).show();
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
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
}
