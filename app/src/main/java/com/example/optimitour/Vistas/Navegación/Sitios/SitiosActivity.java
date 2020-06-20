package com.example.optimitour.Vistas.Navegación.Sitios;

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

import java.util.ArrayList;

public class SitiosActivity extends AppCompatActivity {

    public SitiosActivity() {
        // Required empty public constructor
    }

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
        setContentView(R.layout.activity_sitios);

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
                SitiosActivity.super.onBackPressed();
            }
        });


    }

    //cargar datos en la lista
    public void cargarLista() {
        //REcibir datos de municipio
        Bundle extras = getIntent().getExtras();
        auxIdMunicipio = extras.getString("dato_Municipio");
        auxIdTipoFiltro = extras.getString("dato_Tipo");
        String auxId = auxIdMunicipio;
        String auxFiltro = auxIdTipoFiltro;
        textHome.setText(auxFiltro);

        //Toast.makeText(SitiosActivity.this, "llego: " + auxId, Toast.LENGTH_LONG).show();
        //Toast.makeText(SitiosActivity.this, "filtro: " + auxFiltro, Toast.LENGTH_LONG).show();


        ConexionSQLiteHelper dbHelper = new ConexionSQLiteHelper(this);
        db = dbHelper.getReadableDatabase();

        String[] parametroId = {auxId, auxFiltro};
        //String[] parametroTipo = {auxFiltro};
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_SITIO +
                " WHERE " + Utilidades.CAMPO_ID_MUNICIPIOO + "=? " +
                " AND " + Utilidades.CAMPO_TIPO_SITIO + "=? ", parametroId);

        while (cursor.moveToNext()) {
            Sitios sitio = new Sitios();
            sitio.setId_Sitio(cursor.getInt(0));
            sitio.setNombre_Sitio(cursor.getString(1));
            sitio.setDirecion_Sitio(cursor.getString(3));
            sitio.setContenido_Sitio(cursor.getString(4));
            sitio.setImagenId(cursor.getInt(5));
            listaSitios.add(sitio);
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
                    Intent i = new Intent(SitiosActivity.this, DetalleSitioActivity.class);
                    i.putExtra("dato_Nombre", nombre);
                    startActivity(i);
                    Toast.makeText(SitiosActivity.this, "Selección: " + nombre, Toast.LENGTH_SHORT).show();
                    //  comunicaFragments.enviarSitio(listaSitios.get(recyclerView.getChildAdapterPosition(v)));
                }
            });
        } else {
            recyclerView.setVisibility(View.GONE);
            textVacio.setVisibility(View.VISIBLE);
        }

    }


}
