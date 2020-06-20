package com.example.optimitour.Vistas.Navegación.Sitios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.optimitour.R;

public class FiltroSitioActivity extends AppCompatActivity {


    //Variables
    String auxIdMunicipio;
    Button restaurantes,bares,hoteles,otros,museos;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_sitio);

        //Recibo info para mandar
        Bundle extras = getIntent().getExtras();
        auxIdMunicipio = extras.getString("dato_Municipio");
        final String aux = auxIdMunicipio;
        //Toast.makeText(FiltroSitioActivity.this, "llego: " + aux, Toast.LENGTH_LONG).show();

        //Seteo con funcion click
        back = findViewById(R.id.btn_menu);


        restaurantes = findViewById(R.id.btnRestaurante);
        restaurantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FiltroSitioActivity.this, SitiosActivity.class);
                String tipo= "Restaurante";
                i.putExtra("dato_Municipio", aux);
                i.putExtra("dato_Tipo", tipo);
                startActivity(i);
            }
        });
        bares = findViewById(R.id.btnBares);
        bares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FiltroSitioActivity.this, SitiosActivity.class);
                String tipo= "Bares";
                i.putExtra("dato_Municipio", aux);
                i.putExtra("dato_Tipo", tipo);
                startActivity(i);
            }
        });
        hoteles = findViewById(R.id.btnHoteles);
        hoteles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FiltroSitioActivity.this, SitiosActivity.class);
                String tipo= "Hotel";
                i.putExtra("dato_Municipio", aux);
                i.putExtra("dato_Tipo", tipo);
                startActivity(i);
            }
        });
        otros = findViewById(R.id.btnOtros);
        otros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FiltroSitioActivity.this, SitiosActivity.class);
                String tipo= "Otro";
                i.putExtra("dato_Municipio", aux);
                i.putExtra("dato_Tipo", tipo);
                startActivity(i);
            }
        });
        museos= findViewById(R.id.btnMuseo);
        museos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FiltroSitioActivity.this, SitiosActivity.class);
                String tipo= "Museo";
                i.putExtra("dato_Municipio", aux);
                i.putExtra("dato_Tipo", tipo);
                startActivity(i);
            }
        });
        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiltroSitioActivity.super.onBackPressed();
            }
        });

    }


}
