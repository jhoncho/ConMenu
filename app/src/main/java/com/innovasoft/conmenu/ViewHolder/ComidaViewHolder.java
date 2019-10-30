package com.innovasoft.conmenu.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.innovasoft.conmenu.Interface.ItemClickListener;
import com.innovasoft.conmenu.R;

public class ComidaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView comida_nombre;
    public ImageView comida_imagen;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ComidaViewHolder(@NonNull View itemView) {
        super( itemView );
        comida_nombre=(TextView)itemView.findViewById( R.id.comida_nombre );
        comida_imagen=(ImageView)itemView.findViewById( R.id.comida_imagen );

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick( view,getAdapterPosition(),false );

    }
}
