package com.example.optimitour.Vistas.Navegación.Favoritos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.optimitour.Auxiliares.AdapterPlace;
import com.example.optimitour.Data.ConexionSQLiteHelper;
import com.example.optimitour.Data.Utilidades;
import com.example.optimitour.Entidades.Sitios;
import com.example.optimitour.R;
import com.example.optimitour.View.iComunicaFragments;
import com.example.optimitour.Vistas.Navegación.Sitios.DetalleSitioActivity;

import java.util.ArrayList;

public class FavoritoActivity extends AppCompatActivity {

    //VARIABLE
    ImageView back;
    AdapterPlace adapterPlace;
    RecyclerView recyclerView;
    ArrayList<Sitios> listaSitios;
    SQLiteDatabase db;
    TextView textHome, textVacio;
    String auxIdMunicipio, auxIdTipoFiltro;
    //referencias para comunicar fragment
    Activity activity;
    iComunicaFragments comunicaFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorito);

        //Seteo
        back = findViewById(R.id.btn_back);
        textHome = findViewById(R.id.textHome);
        recyclerView = findViewById(R.id.recyclerView);
        listaSitios = new ArrayList<>();
        textVacio = findViewById(R.id.textVacio);
        //cargar lista
        cargarLista();
        //mostrar datos
        mostrarData();

        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoritoActivity.super.onBackPressed();
            }
        });

    }


    //cargar datos en la lista
    public void cargarLista() {
        ConexionSQLiteHelper dbHelper = new ConexionSQLiteHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_FAVORITO, null);

        while (cursor.moveToNext()) {

            String nombre = cursor.getString(1);
            String[] parametroId = {nombre};

            Cursor cursor2 = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_SITIO +
                    " WHERE " + Utilidades.CAMPO_NOMBRE_SITIO + "=? ", parametroId);
            while (cursor2.moveToNext()) {
                Sitios sitio = new Sitios();
                sitio.setId_Sitio(cursor2.getInt(0));
                sitio.setNombre_Sitio(cursor2.getString(1));
                sitio.setDirecion_Sitio(cursor2.getString(3));
                sitio.setContenido_Sitio(cursor2.getString(4));
                sitio.setImagenId(cursor2.getInt(5));
                listaSitios.add(sitio);
            }
            cursor2.close();

        }
        cursor.close();
        db.close();

    }

    public void mostrarData() {


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterPlace = new AdapterPlace(this, listaSitios);
        if (!listaSitios.isEmpty()) {
            recyclerView.setAdapter(adapterPlace);
            adapterPlace.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String nombre = listaSitios.get(recyclerView.getChildAdapterPosition(v)).getNombre_Sitio();
                    Intent i = new Intent(FavoritoActivity.this, DetalleSitioActivity.class);
                    i.putExtra("dato_Nombre", nombre);
                    startActivity(i);
                    Toast.makeText(FavoritoActivity.this, "Selección: " + nombre, Toast.LENGTH_SHORT).show();
                    //  comunicaFragments.enviarSitio(listaSitios.get(recyclerView.getChildAdapterPosition(v)));
                }
            });
        } else {
            recyclerView.setVisibility(View.GONE);
            textVacio.setVisibility(View.VISIBLE);
        }

    }

}
