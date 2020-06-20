package com.example.optimitour.Entidades;

import java.io.Serializable;

public class Favoritos implements Serializable {
    private Integer id_Favoritos;
    private String nombre_Favoritos;

    public Favoritos() {
    }

    public Favoritos(Integer id_Favoritos, String nombre_Favoritos) {
        this.id_Favoritos = id_Favoritos;
        this.nombre_Favoritos = nombre_Favoritos;
    }

    public Integer getId_Favoritos() {
        return id_Favoritos;
    }

    public void setId_Favoritos(Integer id_Favoritos) {
        this.id_Favoritos = id_Favoritos;
    }

    public String getNombre_Favoritos() {
        return nombre_Favoritos;
    }

    public void setNombre_Favoritos(String nombre_Favoritos) {
        this.nombre_Favoritos = nombre_Favoritos;
    }
}
