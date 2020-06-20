
package com.example.optimitour.Entidades;

import java.io.Serializable;

public class Sitios implements Serializable {

    private Integer id_Sitio;
    private String nombre_Sitio;
    private String municipio_Sitio;
    private String contenido_Sitio;
    private String direcion_Sitio;
    private int imagenId;

    public Sitios(){}

    public Sitios(Integer id_Sitio, String nombre_Sitio, String municipio_Sitio, String contenido_Sitio, String direcion_Sitio, int imagenId) {
        this.id_Sitio = id_Sitio;
        this.nombre_Sitio = nombre_Sitio;
        this.municipio_Sitio = municipio_Sitio;
        this.contenido_Sitio = contenido_Sitio;
        this.direcion_Sitio = direcion_Sitio;
        this.imagenId = imagenId;
    }

    public Integer getId_Sitio() {
        return id_Sitio;
    }

    public void setId_Sitio(Integer id_Sitio) {
        this.id_Sitio = id_Sitio;
    }

    public String getNombre_Sitio() {
        return nombre_Sitio;
    }

    public void setNombre_Sitio(String nombre_Sitio) {
        this.nombre_Sitio = nombre_Sitio;
    }

    public String getMunicipio_Sitio() {
        return municipio_Sitio;
    }

    public void setMunicipio_Sitio(String municipio_Sitio) {
        this.municipio_Sitio = municipio_Sitio;
    }

    public String getContenido_Sitio() {
        return contenido_Sitio;
    }

    public void setContenido_Sitio(String contenido_Sitio) {
        this.contenido_Sitio = contenido_Sitio;
    }

    public String getDirecion_Sitio() {
        return direcion_Sitio;
    }

    public void setDirecion_Sitio(String direcion_Sitio) {
        this.direcion_Sitio = direcion_Sitio;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}




