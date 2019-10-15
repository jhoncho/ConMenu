package com.innovasoft.conmenu.Model;

public class Usuarios {
    private String Nombre;
    private String Password;

    public Usuarios() {

    }

    public Usuarios(String nombre, String password) {
        Nombre = nombre;
        Password = password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
