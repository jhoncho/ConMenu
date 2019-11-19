package com.innovasoft.conmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.innovasoft.conmenu.Common.Common;
import com.innovasoft.conmenu.Interface.ItemClickListener;
import com.innovasoft.conmenu.Model.Solicitud;
import com.innovasoft.conmenu.ViewHolder.OrderViewHolder;

public class OrderEstado extends AppCompatActivity {


    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Solicitud, OrderViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference pedidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order_estado );

        //firebase
        database = FirebaseDatabase.getInstance();
        pedidos = database.getReference( "Pedidos" );

        recyclerView = findViewById( R.id.OrdenLista );
        recyclerView.setHasFixedSize( true );
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );

        if (getIntent() == null)
            cargarOrden(Common.currentUser.getTelefono());
        else
           cargarOrden(getIntent().getStringExtra("Telefono"));

        cargarOrden(Common.currentUser.getTelefono());

    }

    private void cargarOrden(String telefono) {

        FirebaseRecyclerOptions<Solicitud> options = new FirebaseRecyclerOptions.Builder<Solicitud>().setQuery(pedidos
                .orderByChild("telefono").equalTo(telefono), Solicitud.class).build();

        adapter = new FirebaseRecyclerAdapter<Solicitud, OrderViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull Solicitud model) {
                holder.txtOrdenID.setText(adapter.getRef( position ).getKey());
                holder.txtOrdenEstado.setText(Common.convertCodeToEstado(model.getEstado()));
                holder.txtOrdenDireccion.setText(model.getDireccion());
                holder.txtOrdenTelefono.setText(model.getTelefono());

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View v, int position, boolean isLongClik) {

                    }
                });
            }

            @NonNull
            @Override
            public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.order_layout,parent,false );
                return new OrderViewHolder( view );
            }
        };

        //set adapter
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }


}
