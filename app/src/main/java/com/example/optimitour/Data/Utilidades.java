package com.example.optimitour.Data;

public class Utilidades {

    //Constantes de tabla 1
    public static final String TABLE_MUNICIPIO = "municipio";
    public static final String CAMPO_ID_MUNICIPIO_UNICA = "id_municipio";
    public static final String CAMPO_NOMBRE_MUNICIPIO = "nombre_municipio";
    public static final String CAMPO_IMAGEN_MUNICIPIO_UNICA = "imagen_municipio_unica";

    public static final String CREATE_TABLE_MUNICIPIO = "CREATE TABLE IF NOT EXISTS " + "" + TABLE_MUNICIPIO +
            " (" + CAMPO_ID_MUNICIPIO_UNICA + " " + " INTEGER PRIMARY KEY, "
            + CAMPO_NOMBRE_MUNICIPIO + " TEXT, "
            + CAMPO_IMAGEN_MUNICIPIO_UNICA + " INTEGER)";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constantes de tabla 2
    public static final String TABLE_HISTORIA = "historias";
    public static final String CAMPO_ID_HISTORIA = "id_historia";
    public static final String CAMPO_NOMBRE_HISTORIA = "nombre_historia";
    public static final String CAMPO_TIPO_HISTORIA = "tipo_historia";
    public static final String CAMPO_CONTENIDO_HISTORIA = "contenido_historia";
    public static final String CAMPO_IMAGEN_HISTORIA = "imagen_historia";
    public static final String CAMPO_ID_MUNICIPIO_HISTORIA = "id_municipio";

    public static final String CREATE_TABLE_HISTORIA = "CREATE TABLE IF NOT EXISTS " + "" + TABLE_HISTORIA +
            " (" + CAMPO_ID_HISTORIA + " " + " INTEGER PRIMARY KEY, "
            + CAMPO_NOMBRE_HISTORIA + " TEXT, "
            + CAMPO_TIPO_HISTORIA + " TEXT, "
            + CAMPO_CONTENIDO_HISTORIA + " TEXT, "
            + CAMPO_IMAGEN_HISTORIA + " INTEGER, "
            + CAMPO_ID_MUNICIPIO_HISTORIA + " INTEGER)";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constantes de tabla 3
    public static final String TABLE_SITIO = "sitios";
    public static final String CAMPO_ID_SITIO = "id_sitio";
    public static final String CAMPO_NOMBRE_SITIO = "nombre_sitio";
    public static final String CAMPO_TIPO_SITIO = "tipo_sitio";
    public static final String CAMPO_DIRECCION_SITIO = "direccion_sitio";
    public static final String CAMPO_CONTENIDO_SITIO = "contenido_sitio";
    public static final String CAMPO_IMAGEN_SITIO = "imagen_sitio";
    public static final String CAMPO_ID_MUNICIPIOO = "id_municipio";

    public static final String CREATE_TABLE_SITIO = "CREATE TABLE IF NOT EXISTS " + "" + TABLE_SITIO +
            " (" + CAMPO_ID_SITIO + " " + " INTEGER PRIMARY KEY, "
            + CAMPO_NOMBRE_SITIO + " TEXT, "
            + CAMPO_TIPO_SITIO + " TEXT, "
            + CAMPO_DIRECCION_SITIO + " TEXT, "
            + CAMPO_CONTENIDO_SITIO + " TEXT, "
            + CAMPO_IMAGEN_SITIO + " INTEGER, "
            + CAMPO_ID_MUNICIPIOO + " INTEGER)";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constantes de tabla 4
    public static final String TABLE_CALENDARIO = "calendario";
    public static final String CAMPO_ID_CALENDARIO = "id_calendario";
    public static final String CAMPO_FECHA_CALENDARIO = "fecha_calendario";
    public static final String CAMPO_NOMBRE_CALENDARIO = "nombre_calendario";
    public static final String CAMPO_CONTENIDO_CALENDARIO = "contenido_calendario";
    public static final String CAMPO_ID_MUNICIPIO_CALENDARIO = "id_municipio";

    public static final String CREATE_TABLE_CALENDARIO = "CREATE TABLE IF NOT EXISTS " + "" + TABLE_CALENDARIO +
            " (" + CAMPO_ID_CALENDARIO + " " + " INTEGER PRIMARY KEY, "
            + CAMPO_FECHA_CALENDARIO + " TEXT, "
            + CAMPO_NOMBRE_CALENDARIO + " TEXT, "
            + CAMPO_CONTENIDO_CALENDARIO + " TEXT, "
            + CAMPO_ID_MUNICIPIO_CALENDARIO + " INTEGER)";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constantes de tabla 5
    public static final String TABLE_TURISMO = "turismo";
    public static final String CAMPO_ID_TURISMO = "id_turismo";
    public static final String CAMPO_NOMBRE_TURISMO = "nombre_turismo";
    public static final String CAMPO_CONTENIDO_TURISMO = "contenido_turismo";
    public static final String CAMPO_DIRECCION_TURISMO = "direccion_turismo";
    public static final String CAMPO_CONTACTO_TURISMO = "contacto_turismo";
    public static final String CAMPO_IMAGEN_TURISMO = "imagen_sitio";
    public static final String CAMPO_ID_MUNICIPIO_TURISMO = "id_municipio";

    public static final String CREATE_TABLE_TURISMO = "CREATE TABLE IF NOT EXISTS " + "" + TABLE_TURISMO +
            " (" + CAMPO_ID_TURISMO + " " + " INTEGER PRIMARY KEY, "
            + CAMPO_NOMBRE_TURISMO + " TEXT, "
            + CAMPO_CONTENIDO_TURISMO + " TEXT, "
            + CAMPO_DIRECCION_TURISMO + " TEXT, "
            + CAMPO_CONTACTO_TURISMO + " TEXT, "
            + CAMPO_IMAGEN_TURISMO + " INTEGER, "
            + CAMPO_ID_MUNICIPIO_TURISMO + " INTEGER)";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Constantes de tabla 6
    public static final String TABLE_FAVORITO = "favorito";
    public static final String CAMPO_ID_FAVORITO = "id_favorito";
    public static final String CAMPO_NOMBRE_FAVORITO = "nombre_favorito";

    public static final String CREATE_TABLE_FAVORITO= "CREATE TABLE IF NOT EXISTS " + "" + TABLE_FAVORITO +
            " (" + CAMPO_ID_FAVORITO + " " + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_NOMBRE_FAVORITO + " TEXT)";

}
