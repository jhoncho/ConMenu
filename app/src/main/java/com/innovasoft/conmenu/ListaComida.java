package com.innovasoft.conmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.innovasoft.conmenu.Interface.ItemClickListener;
import com.innovasoft.conmenu.Model.Comida;
import com.innovasoft.conmenu.ViewHolder.ComidaViewHolder;
import com.squareup.picasso.Picasso;

public class ListaComida extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference listacomida;

    String categoriaId="";

    FirebaseRecyclerAdapter<Comida, ComidaViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_comida );

        // Coneccion con Firebase

        database = FirebaseDatabase.getInstance();
        listacomida = database.getReference("Comida");

        recyclerView = findViewById(R.id.recycler_comida);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //
        if (getIntent() != null)
         categoriaId = getIntent().getStringExtra("CategoriaId");
              if (!categoriaId.isEmpty()&& categoriaId != null)
              {
                    cargandoListaComida(categoriaId);
              }
    }

    private void cargandoListaComida(String categoriaId)
    {
        FirebaseRecyclerOptions<Comida> options =
                new FirebaseRecyclerOptions.Builder<Comida>().setQuery(listacomida.orderByChild("menuId").equalTo(categoriaId), Comida.class).build();

        adapter = new FirebaseRecyclerAdapter<Comida, ComidaViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ComidaViewHolder comidaViewHolder, int i, @NonNull final Comida comida) {
                comidaViewHolder.comida_nombre.setText(comida.getNombre());
                Picasso.get().load(comida.getImagen()).into(comidaViewHolder.comida_imagen);

                final Comida local = comida;
                comidaViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        // Start activity of food details
                        //Intent foodDetails = new Intent(FoodList.this, FoodDetails.class);
                      //  foodDetails.putExtra("FoodId", adapter.getRef(position).getKey()); //send FoodId to new Activity
                       // startActivity(foodDetails);
                    }
                });

            }

            @NonNull
            @Override
            public ComidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comida_item, parent, false);
                return new ComidaViewHolder( view );
            }
        };
        //set Adapter
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

}
