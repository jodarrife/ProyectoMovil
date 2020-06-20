package com.example.optimitour.Vistas.Navegación.Historia;

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

import com.example.optimitour.Auxiliares.AdapterHistoria;
import com.example.optimitour.Data.ConexionSQLiteHelper;
import com.example.optimitour.Data.Utilidades;
import com.example.optimitour.Entidades.Historias;
import com.example.optimitour.R;
import com.example.optimitour.View.iComunicaFragments;
import com.example.optimitour.Vistas.Navegación.Sitios.DetalleSitioActivity;

import java.util.ArrayList;

public class HistoriaActivity extends AppCompatActivity {


    //VARIABLE
    ImageView back;
    AdapterHistoria adapterHistoria;
    RecyclerView recyclerView;
    ArrayList<Historias> listaHistorias;
    SQLiteDatabase db;
    TextView textHome, textVacio;
    String auxIdMunicipio, auxIdTipoFiltro;
    //referencias para comunicar fragment
    Activity activity;
    iComunicaFragments comunicaFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

        //Seteo
        back = findViewById(R.id.btn_back);
        textHome = findViewById(R.id.textHome);
        recyclerView = findViewById(R.id.recyclerView);
        listaHistorias = new ArrayList<>();
        textVacio = findViewById(R.id.textVacio);
        //cargar lista
        cargarLista();
        //mostrar datos
        mostrarData();

        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoriaActivity.super.onBackPressed();
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

       // Toast.makeText(HistoriaActivity.this, "llego: " + auxId, Toast.LENGTH_LONG).show();
        //Toast.makeText(HistoriaActivity.this, "filtro: " + auxFiltro, Toast.LENGTH_LONG).show();


        ConexionSQLiteHelper dbHelper = new ConexionSQLiteHelper(this);
        db = dbHelper.getReadableDatabase();

        String[] parametroId = {auxId, auxFiltro};
        //String[] parametroTipo = {auxFiltro};
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_HISTORIA +
                " WHERE " + Utilidades.CAMPO_ID_MUNICIPIO_HISTORIA + "=? " +
                " AND " + Utilidades.CAMPO_TIPO_HISTORIA + "=? ", parametroId);

        while (cursor.moveToNext()) {
            Historias historias = new Historias();
            historias.setId_Historia(cursor.getInt(0));
            historias.setNombre_Historia(cursor.getString(1));
            historias.setContenido_Historia(cursor.getString(3));
            historias.setImagenId(cursor.getInt(4));
            listaHistorias.add(historias);
        }
        cursor.close();
        db.close();
    }

    public void mostrarData() {


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterHistoria = new AdapterHistoria (this, listaHistorias);
        if (!listaHistorias.isEmpty()) {
            recyclerView.setAdapter(adapterHistoria);
            adapterHistoria.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String nombre = listaHistorias.get(recyclerView.getChildAdapterPosition(v)).getNombre_Historia();
                    Intent i = new Intent(HistoriaActivity.this, DetalleHistoriaActivity.class);
                    i.putExtra("dato_Nombre", nombre);
                    startActivity(i);
                    Toast.makeText(HistoriaActivity.this, "Selección: " + nombre, Toast.LENGTH_SHORT).show();
                    //  comunicaFragments.enviarSitio(listaSitios.get(recyclerView.getChildAdapterPosition(v)));
                }
            });
        } else {
            recyclerView.setVisibility(View.GONE);
            textVacio.setVisibility(View.VISIBLE);
        }

    }
}
