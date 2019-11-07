package com.innovasoft.conmenu.Model;

public class orden {

        private String IDproducto;
        private String NombreProducto;
        private String Cantidad;
        private String Precio;
        private String Descuento;

    public orden() {
    }



    public String getIDproducto() {
        return IDproducto;
    }

    public void setIDproducto(String IDproducto) {
        this.IDproducto = IDproducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getDescuento() {
        return Descuento;
    }

    public void setDesctueno(String descuento) {
        Descuento = descuento;
    }
    public orden(String IDproducto, String nombreProducto, String cantidad, String precio, String descuento) {
        this.IDproducto = IDproducto;
        NombreProducto = nombreProducto;
        Cantidad = cantidad;
        Precio = precio;
        Descuento = descuento;
    }
}


