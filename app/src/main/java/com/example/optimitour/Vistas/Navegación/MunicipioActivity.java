package com.example.optimitour.Vistas.Navegación;

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

import com.example.optimitour.Auxiliares.AdapterMunicipios;
import com.example.optimitour.Data.ConexionSQLiteHelper;
import com.example.optimitour.Data.Utilidades;
import com.example.optimitour.Entidades.Municipio;
import com.example.optimitour.R;
import com.example.optimitour.View.iComunicaFragments;
import com.example.optimitour.Vistas.Navegación.Historia.FiltroHistoriaActivity;
import com.example.optimitour.Vistas.Navegación.Sitios.FiltroSitioActivity;
import com.example.optimitour.Vistas.Navegación.Turismo.TurismoActivity;

import java.util.ArrayList;

public class MunicipioActivity extends AppCompatActivity {

    //VARIABLE
    ImageView back;
    AdapterMunicipios adapter;
    RecyclerView recyclerView;
    ArrayList<Municipio> listaMunicipio;
    SQLiteDatabase db;
    TextView textHome, textVacio;
    String auxIdMunicipio, auxIdTipoFiltro;
    //referencias para comunicar fragment
    Activity activity;
    iComunicaFragments comunicaFragments;
    String auxSitio,aux,sitio,historia,turismo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio);

        //Recibo info para mandar
        Bundle extras = getIntent().getExtras();
        auxSitio = extras.getString("dato_Accion");
        aux = auxSitio;
        sitio="Sitios";
        historia="Historias" ;
        turismo="Turismo";

        //Seteo
        back = findViewById(R.id.btn_back);
        textHome = findViewById(R.id.textHome);
        recyclerView = findViewById(R.id.recyclerView);
        listaMunicipio = new ArrayList<>();
        textVacio = findViewById(R.id.textVacio);
        //cargar lista
        cargarLista();
        //mostrar datos
        mostrarData();

        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MunicipioActivity.super.onBackPressed();
            }
        });

    }


    //cargar datos en la lista
    public void cargarLista() {

        ConexionSQLiteHelper dbHelper = new ConexionSQLiteHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_MUNICIPIO, null);

        if(cursor.moveToFirst()){
            do {
                Municipio m  = new Municipio();
                m.setId_Municipio(cursor.getInt(0));
                m.setNombre_Municipio(cursor.getString(1));
                m.setImagenId(cursor.getInt(2));
                listaMunicipio.add(m);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }

    public void mostrarData() {

        //Recibo info para mandar
        Bundle extras = getIntent().getExtras();
        auxSitio = extras.getString("dato_Accion");
        aux = auxSitio;
        sitio="Sitios";
        historia="Historias" ;
        turismo="Turismo";

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterMunicipios(this, listaMunicipio);
        if (!listaMunicipio.isEmpty()) {
            recyclerView.setAdapter(adapter);
            adapter.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int ide = listaMunicipio.get(recyclerView.getChildAdapterPosition(v)).getId_Municipio();
                    String  ayuda = String.valueOf(ide);
                    //Toast.makeText(MunicipioActivity.this, "ojo: " + ayuda, Toast.LENGTH_SHORT).show();
                    if (aux.equals(sitio)){
                        Intent i = new Intent(MunicipioActivity.this, FiltroSitioActivity.class);
                        i.putExtra("dato_Municipio", ayuda);
                        startActivity(i);
                    }else if (aux.equals(historia)){

                        Intent i = new Intent(MunicipioActivity.this, FiltroHistoriaActivity.class);
                        i.putExtra("dato_Municipio", ayuda);
                        startActivity(i);
                    }else if(aux.equals(turismo)){

                        Intent i = new Intent(MunicipioActivity.this, TurismoActivity.class);
                        i.putExtra("dato_Municipio", ayuda);
                        startActivity(i);
                    }
                   // Toast.makeText(MunicipioActivity.this, "Selección: " + ide, Toast.LENGTH_SHORT).show();
                    //  comunicaFragments.enviarSitio(listaSitios.get(recyclerView.getChildAdapterPosition(v)));
                }
            });
        } else {
            recyclerView.setVisibility(View.GONE);
            textVacio.setVisibility(View.VISIBLE);
        }

    }
}
