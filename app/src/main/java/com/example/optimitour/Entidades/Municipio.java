package com.example.optimitour.Entidades;

import java.io.Serializable;

public class Municipio implements Serializable {

    private Integer id_Municipio;
    private String nombre_Municipio;
    private int imagenId;

    public Municipio() {
    }

    public Municipio(Integer id_Municipio, String nombre_Municipio, int imagenId) {
        this.id_Municipio = id_Municipio;
        this.nombre_Municipio = nombre_Municipio;
        this.imagenId = imagenId;
    }

    public Integer getId_Municipio() {
        return id_Municipio;
    }

    public void setId_Municipio(Integer id_Municipio) {
        this.id_Municipio = id_Municipio;
    }

    public String getNombre_Municipio() {
        return nombre_Municipio;
    }

    public void setNombre_Municipio(String nombre_Municipio) {
        this.nombre_Municipio = nombre_Municipio;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}

