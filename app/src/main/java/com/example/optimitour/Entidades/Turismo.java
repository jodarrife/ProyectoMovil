package com.example.optimitour.Entidades;

import java.io.Serializable;

public class Turismo implements Serializable {

    private Integer id_turismo;
    private String nombre_turismo;
    private String contenido_turismo;
    private String direccion_turismo;
    private String contacto_turismo;
    private int imagenId;
    private String municipio_Turismo;

    public Turismo() {
    }

    public Turismo(Integer id_turismo, String nombre_turismo, String contenido_turismo, String direccion_turismo, String contacto_turismo, int imagenId, String municipio_Turismo) {
        this.id_turismo = id_turismo;
        this.nombre_turismo = nombre_turismo;
        this.contenido_turismo = contenido_turismo;
        this.direccion_turismo = direccion_turismo;
        this.contacto_turismo = contacto_turismo;
        this.imagenId = imagenId;
        this.municipio_Turismo = municipio_Turismo;
    }

    public Integer getId_turismo() {
        return id_turismo;
    }

    public void setId_turismo(Integer id_turismo) {
        this.id_turismo = id_turismo;
    }

    public String getNombre_turismo() {
        return nombre_turismo;
    }

    public void setNombre_turismo(String nombre_turismo) {
        this.nombre_turismo = nombre_turismo;
    }

    public String getContenido_turismo() {
        return contenido_turismo;
    }

    public void setContenido_turismo(String contenido_turismo) {
        this.contenido_turismo = contenido_turismo;
    }

    public String getDireccion_turismo() {
        return direccion_turismo;
    }

    public void setDireccion_turismo(String direccion_turismo) {
        this.direccion_turismo = direccion_turismo;
    }

    public String getContacto_turismo() {
        return contacto_turismo;
    }

    public void setContacto_turismo(String contacto_turismo) {
        this.contacto_turismo = contacto_turismo;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public String getMunicipio_Turismo() {
        return municipio_Turismo;
    }

    public void setMunicipio_Turismo(String municipio_Turismo) {
        this.municipio_Turismo = municipio_Turismo;
    }
}
