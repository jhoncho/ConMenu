package com.innovasoft.conmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.innovasoft.conmenu.Model.Comida;
import com.squareup.picasso.Picasso;

public class DetalleComida extends AppCompatActivity
{

    TextView plato_nombre, plato_precio, plato_descripcion;
    ImageView plato_imagen;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCarrito;
    ElegantNumberButton numeroButton;

    String ComidaID = "";
    Comida comidaActual;

    FirebaseDatabase database;
    DatabaseReference ComidaDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalle_comida );

        // iniciamos firebase
        database = FirebaseDatabase.getInstance();
       ComidaDetalle = database.getReference( "Comida" );

        numeroButton = findViewById( R.id.numero_button );
        btnCarrito = findViewById( R.id.btnCarrito );

        plato_descripcion = findViewById( R.id.plato_descripcion );
        plato_nombre = findViewById( R.id.plato_nombre );
        plato_precio = findViewById( R.id.plato_precio );
        plato_imagen = findViewById( R.id.imagen_plato );

        collapsingToolbarLayout = findViewById( R.id.collapsing );
        collapsingToolbarLayout.setExpandedTitleTextAppearance( R.style.ExpandedAppbar );
        collapsingToolbarLayout.setExpandedTitleTextAppearance( R.style.CollapsedAppbar );

        //obtener identificación de comida del intent

        if (getIntent() != null)
             ComidaID = getIntent().getStringExtra( "MenuId" );
         if (!ComidaID.isEmpty() && ComidaID != null)
         {
             CargarDetalleComida( ComidaID );

         }

    }

    private void CargarDetalleComida(String comidaID)
    {
        ComidaDetalle.child( comidaID ).addValueEventListener( new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
             comidaActual = dataSnapshot.getValue(Comida.class);

             //establecemos la imagen
                Picasso.get().load(comidaActual.getImagen()).into(plato_imagen);

                collapsingToolbarLayout.setTitle( comidaActual.getNombre());

                plato_precio.setText( comidaActual.getPrecio() );
                plato_descripcion.setText( comidaActual.getDescripcion() );
                plato_nombre.setText( comidaActual.getNombre() );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        } );
    }
}