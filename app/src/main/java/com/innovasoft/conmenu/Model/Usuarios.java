package com.innovasoft.conmenu.Model;

public class Usuarios {
    private String Nombre;
    private String Password;
    private String Telefono;

    public Usuarios() {

    }

    public Usuarios(String nombre, String password) {
        Nombre = nombre;
        Password = password;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
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
