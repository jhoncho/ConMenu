package com.innovasoft.conmenu.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.innovasoft.conmenu.Interface.ItemClickListener;
import com.innovasoft.conmenu.R;
import com.innovasoft.conmenu.Model.orden;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CarritoViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txt_carrito_nombre, txt_precio;
    public ImageView imagen_carrito_contar;
    private ItemClickListener itemClickListener;

    public void setTxt_carrito_nombre(TextView txt_carrito_nombre)
    {
        this.txt_carrito_nombre=txt_carrito_nombre;

    }

    public CarritoViewHolder(@NonNull View itemView)
    {
        super( itemView );
        txt_carrito_nombre=(TextView)itemView.findViewById( R.id.carrito_item_nombre );
        txt_precio=(TextView)itemView.findViewById( R.id.carrito_item_precio );
        imagen_carrito_contar=(ImageView)itemView.findViewById( R.id.carrito_item_contar );
    }

    @Override
    public void onClick(View v) {

    }
}

public class CarritoAdapter extends RecyclerView.Adapter<CarritoViewHolder>  {

    private List<orden>listData= new ArrayList<>(  );
    private Context contex;

    public CarritoAdapter(List<orden> listData, Context contex) {
        this.listData = listData;
        this.contex = contex;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( contex );
        View itemView = inflater.inflate( R.layout.carrito_layout,parent,false );
        return new CarritoViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder holder, int position) {
        TextDrawable drawable =TextDrawable.builder().buildRound( ""+listData.get( position ).getCantidad(), Color.RED );
        holder.imagen_carrito_contar.setImageDrawable( drawable );

        Locale locale = new Locale( "es","Bs" );
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price =(Integer.parseInt( listData.get( position ).getPrecio()))*(Integer.parseInt( listData.get( position ).getCantidad()));
        holder.txt_precio.setText( fmt.format( price ));

        holder.txt_carrito_nombre.setText( listData.get( position ).getNombreProducto() );

    }

    @Override
    public int getItemCount()
    {
        return listData.size();
    }
}
