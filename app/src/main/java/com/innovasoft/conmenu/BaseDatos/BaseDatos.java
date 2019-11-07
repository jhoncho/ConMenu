package com.innovasoft.conmenu.BaseDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;


import com.innovasoft.conmenu.Model.orden;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos extends SQLiteAssetHelper {
    private static final String DB_NOMBRE="conmenu.db";
    private static final int DB_VER=1;

    public BaseDatos(Context context) {
        super( context, DB_NOMBRE, null, DB_VER );
    }

    public List<orden> getCarritos()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();

        String[] sqlSelect ={"IDproducto","NombreProducto","Cantidad", "Precio","Descuento"};
        String sqlTable ="OrdenDetalle";

        qb.setTables( sqlTable );
        Cursor c=qb.query(db,sqlSelect,null,null,null,null,null);
        final  List<orden> result= new ArrayList<>(  );
        if (c.moveToFirst())
        {
            do
                {
                    result.add( new orden (c.getString( c.getColumnIndex( "IDproducto" )),
                            c.getString( c.getColumnIndex("NombreProducto" )),
                            c.getString( c.getColumnIndex("Cantidad" )),
                            c.getString( c.getColumnIndex("Precio" )),
                            c.getString( c.getColumnIndex("Descuento" ))
                    ));

                }while (c.moveToNext());
        }
        return result;
    }

    public void adicionarCarrito(orden Orden) {

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrdenDetalle(IDproducto,NombreProducto,Cantidad,Precio,Descuento) VALUES ('%s','%s','%s','%s','%s');",
                Orden.getIDproducto(),
                Orden.getNombreProducto(),
                Orden.getCantidad(),
                Orden.getPrecio(),
                Orden.getDescuento());
        db.execSQL(query);
    }
    public void EliminarCarrito() {

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrdenDetalle");
        db.execSQL(query);

    }

}
