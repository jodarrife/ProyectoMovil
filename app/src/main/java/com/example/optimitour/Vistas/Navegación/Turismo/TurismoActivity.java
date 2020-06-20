package com.example.optimitour.Vistas.Navegación.Turismo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.optimitour.Auxiliares.AdapterTurismo;
import com.example.optimitour.Data.ConexionSQLiteHelper;
import com.example.optimitour.Data.Utilidades;
import com.example.optimitour.Entidades.Turismo;
import com.example.optimitour.R;
import com.example.optimitour.View.iComunicaFragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TurismoActivity extends AppCompatActivity {

    //VARIABLE
    ImageView back;
    AdapterTurismo adapterTurismo;
    RecyclerView recyclerView;
    ArrayList<Turismo> listaTurismo;
    SQLiteDatabase db;
    TextView textHome, textVacio;
    String auxIdMunicipio, auxIdTipoFiltro;
    //referencias para comunicar fragment
    Activity activity;
    iComunicaFragments comunicaFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turismo);

        //Seteo
        back = findViewById(R.id.btn_back);
        textHome = findViewById(R.id.textHome);
        recyclerView = findViewById(R.id.recyclerView);
        listaTurismo = new ArrayList<>();
        textVacio = findViewById(R.id.textVacio);
        //cargar lista
        cargarLista();
        //mostrar datos
        mostrarData();


        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurismoActivity.super.onBackPressed();
            }
        });
    }

    //cargar datos en la lista
    public void cargarLista() {
        //REcibir datos de municipio
        Bundle extras = getIntent().getExtras();
        auxIdMunicipio = extras.getString("dato_Municipio");

        String auxId = auxIdMunicipio;
       // String auxFiltro = auxIdTipoFiltro;
        //  textHome.setText(auxFiltro);

       // Toast.makeText(TurismoActivity.this, "llego: " + auxId, Toast.LENGTH_LONG).show();
      // Toast.makeText(SitiosActivity.this, "filtro: " + auxFiltro, Toast.LENGTH_LONG).show();


        ConexionSQLiteHelper dbHelper = new ConexionSQLiteHelper(this);
        db = dbHelper.getReadableDatabase();

        String[] parametroId = {auxId};
        //String[] parametroTipo = {auxFiltro};
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_TURISMO +
                " WHERE " + Utilidades.CAMPO_ID_MUNICIPIO_TURISMO + "=? ", parametroId);

        while (cursor.moveToNext()) {
            Turismo turismo = new Turismo();
            turismo.setId_turismo(cursor.getInt(0));
            turismo.setNombre_turismo(cursor.getString(1));
            turismo.setContenido_turismo(cursor.getString(2));
            turismo.setDireccion_turismo(cursor.getString(3));
            turismo.setContacto_turismo(cursor.getString(3));
            turismo.setImagenId(cursor.getInt(5));
            listaTurismo.add(turismo);
        }
        cursor.close();
        db.close();
    }

    public void mostrarData() {


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterTurismo = new AdapterTurismo(this, listaTurismo);
        if (!listaTurismo.isEmpty()) {
            recyclerView.setAdapter(adapterTurismo);
            adapterTurismo.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String nombre = listaTurismo.get(recyclerView.getChildAdapterPosition(v)).getNombre_turismo();
                    Intent i = new Intent(TurismoActivity.this, DetalleTurismoActivity.class);
                    i.putExtra("dato_Nombre", nombre);
                    startActivity(i);
                    Toast.makeText(TurismoActivity.this, "Selección: " + nombre, Toast.LENGTH_SHORT).show();
                    //  comunicaFragments.enviarSitio(listaSitios.get(recyclerView.getChildAdapterPosition(v)));
                }
            });
        } else {
            recyclerView.setVisibility(View.GONE);
            textVacio.setVisibility(View.VISIBLE);
        }

    }

}

