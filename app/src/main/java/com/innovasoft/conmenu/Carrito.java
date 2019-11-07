package com.innovasoft.conmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.innovasoft.conmenu.BaseDatos.BaseDatos;
import com.innovasoft.conmenu.Common.Common;
import com.innovasoft.conmenu.Model.Solicitud;
import com.innovasoft.conmenu.Model.orden;
import com.innovasoft.conmenu.ViewHolder.CarritoAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Carrito extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase BasedeDatos;
    DatabaseReference request;

    TextView txtPrecioTotal;
    FButton btnPedido;

    List<orden> carritos = new ArrayList<>(  );

    CarritoAdapter adapter;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_carrito );

        // iniciamos firebase

        BasedeDatos = FirebaseDatabase.getInstance();
        request = BasedeDatos.getReference( "Pedidos" );

        recyclerView = (RecyclerView) findViewById( R.id.listaCarrito );
        recyclerView.setHasFixedSize( true );
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );


        txtPrecioTotal=(TextView) findViewById( R.id.total );
        btnPedido=findViewById( R.id.btnPedidos );

        btnPedido.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showAlertDialog();
            }
        } );


        loadListaCarrito();


    }

    private void showAlertDialog() {

        AlertDialog.Builder alertDialog= new AlertDialog.Builder( Carrito.this );
        alertDialog.setTitle( "Un paso mas" );
        alertDialog.setMessage( "Ingrese su direccion" );

        final EditText edtDireccion = new EditText( Carrito.this );
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtDireccion.setLayoutParams( lp );
        alertDialog.setView( edtDireccion );
        alertDialog.setIcon( R.drawable.ic_shopping_cart_black_24dp );

        alertDialog.setPositiveButton( "Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //creamos nueva solicitud
                Solicitud solicitud = new Solicitud(
                        Common.currentUser.getTelefono(),
                        Common.currentUser.getNombre(),
                        edtDireccion.getText().toString(),
                        txtPrecioTotal.getText().toString(),carritos

                );
                    //Enviamos a firebase

                request.child( String.valueOf( System.currentTimeMillis())).setValue( solicitud );

                //borramos el carrito
                new BaseDatos( getBaseContext() ).EliminarCarrito();
                Toast.makeText(Carrito.this,"Gracias por su Orden",Toast.LENGTH_SHORT).show();
                finish();
            }
        } );
        alertDialog.setNegativeButton( "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } );

        alertDialog.show();

    }

    private void loadListaCarrito()
    {
        carritos = new BaseDatos(this).getCarritos();
        adapter = new CarritoAdapter( carritos,this );
        recyclerView.setAdapter( adapter );

        // calculamos el precio total

        int total = 0;
        for (orden orden:carritos)
            total+=(Integer.parseInt( orden.getPrecio() ))* (Integer.parseInt( orden.getCantidad() ));
        Locale locale = new Locale( "es","BO" );
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtPrecioTotal.setText( fmt.format( total ) );


    }
}
