package com.example.optimitour.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.optimitour.Entidades.Usuario;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Proyecto1";
    private static final int DATABASE_VERSION = 2;

    public ConexionSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creacion de tablas

        db.execSQL(Utilidades.CREATE_TABLE_MUNICIPIO);
        db.execSQL(Utilidades.CREATE_TABLE_HISTORIA);
        db.execSQL(Utilidades.CREATE_TABLE_SITIO);
        db.execSQL(Utilidades.CREATE_TABLE_CALENDARIO);
        db.execSQL(Utilidades.CREATE_TABLE_TURISMO);
        db.execSQL(Utilidades.CREATE_TABLE_FAVORITO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Si existe Drop
        /*(db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLE_MUNICIPIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLE_HISTORIA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLE_SITIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLE_CALENDARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLE_TURISMO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLE_FAVORITO);)*/
        onCreate(db);
    }


}
