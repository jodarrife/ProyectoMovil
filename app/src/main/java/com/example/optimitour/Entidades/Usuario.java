package com.example.optimitour.Entidades;

public class Usuario {
    private String nombre;
    private String correo;

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public String setNombre(String nombre) {
        this.nombre = nombre;
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String setCorreo(String correo) {
        this.correo = correo;
        return correo;
    }
}
