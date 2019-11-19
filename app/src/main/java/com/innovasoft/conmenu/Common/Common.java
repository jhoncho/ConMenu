package com.innovasoft.conmenu.Common;


import com.innovasoft.conmenu.Model.Usuarios;

public class Common {
    public static Usuarios currentUser;

    public static String convertCodeToEstado(String estados) {

            if (estados.equals( "0" ))
                return "Pedido Ingresado";
            else if (estados.equals( "1" ))
                return "En Camino";
            else
                return "Enviado";
         }
}
