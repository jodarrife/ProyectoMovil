package com.example.optimitour.Entidades;

import java.io.Serializable;

public class Historias implements Serializable {

    private Integer id_Historia;
    private String nombre_Historia;
    private String municipio_Historia;
    private String contenido_Historia;
    private int imagenId;


    public Historias(Integer id_Historia, String nombre_Historia, String municipio_Historia, String contenido_Historia, int imagenId) {
        this.id_Historia = id_Historia;
        this.nombre_Historia = nombre_Historia;
        this.municipio_Historia = municipio_Historia;
        this.contenido_Historia = contenido_Historia;
        this.imagenId = imagenId;
    }

    public Historias() {

    }

    public Integer getId_Historia() {
        return id_Historia;
    }

    public void setId_Historia(Integer id_Historia) {
        this.id_Historia = id_Historia;
    }

    public String getNombre_Historia() {
        return nombre_Historia;
    }

    public void setNombre_Historia(String nombre_Historia) {
        this.nombre_Historia = nombre_Historia;
    }

    public String getMunicipio_Historia() {
        return municipio_Historia;
    }

    public void setMunicipio_Historia(String municipio_Historia) {
        this.municipio_Historia = municipio_Historia;
    }

    public String getContenido_Historia() {
        return contenido_Historia;
    }

    public void setContenido_Historia(String contenido_Historia) {
        this.contenido_Historia = contenido_Historia;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}
