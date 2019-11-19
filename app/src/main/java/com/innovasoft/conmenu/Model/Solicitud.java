package com.innovasoft.conmenu.Model;

import java.util.List;

public class Solicitud implements CharSequence {

    private String telefono;
    private String nombre;
    private String direccion;
    private String total;
    private String estado;

    private List<orden> comidas; // lista de orden de comida


    public Solicitud() {
    }

    public Solicitud(String telefono, String nombre, String direccion, String total, List<orden> comidas) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.direccion = direccion;
        this.total = total;
        this.comidas = comidas;
        this.estado = "0"; //por default 0
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<orden> getComidas() {
        return comidas;
    }

    public void setComidas(List<orden> comidas) {
        this.comidas = comidas;
    }


    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
