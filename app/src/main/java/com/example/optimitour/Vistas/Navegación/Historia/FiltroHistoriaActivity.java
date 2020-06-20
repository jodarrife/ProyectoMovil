package com.example.optimitour.Vistas.Navegación.Historia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.optimitour.R;
import com.example.optimitour.Vistas.Navegación.Sitios.SitiosActivity;

public class FiltroHistoriaActivity extends AppCompatActivity {


    //Variables
    String auxIdMunicipio;
    Button historia,juglares,leyendas,musica;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_historia);

        //Recibo info para mandar
        Bundle extras = getIntent().getExtras();
        auxIdMunicipio = extras.getString("dato_Municipio");
        final String aux = auxIdMunicipio;
      //  Toast.makeText(FiltroHistoriaActivity.this, "llego: " + aux, Toast.LENGTH_LONG).show();

        //Seteo con funcion click
        back = findViewById(R.id.btn_menu);


        historia = findViewById(R.id.btnHistoria);
        historia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FiltroHistoriaActivity.this, HistoriaActivity.class);
                String tipo= "Historia";
                i.putExtra("dato_Municipio", aux);
                i.putExtra("dato_Tipo", tipo);
                startActivity(i);
            }
        });
        juglares = findViewById(R.id.btnJuglares);
        juglares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FiltroHistoriaActivity.this, HistoriaActivity.class);
                String tipo= "Juglares";
                i.putExtra("dato_Municipio", aux);
                i.putExtra("dato_Tipo", tipo);
                startActivity(i);
            }
        });
        leyendas = findViewById(R.id.btnLeyendas);
        leyendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FiltroHistoriaActivity.this, HistoriaActivity.class);
                String tipo= "Leyenda";
                i.putExtra("dato_Municipio", aux);
                i.putExtra("dato_Tipo", tipo);
                startActivity(i);
            }
        });
        musica= findViewById(R.id.btnMusica);
        musica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FiltroHistoriaActivity.this, HistoriaActivity.class);
                String tipo= "Musica";
                i.putExtra("dato_Municipio", aux);
                i.putExtra("dato_Tipo", tipo);
                startActivity(i);
            }
        });
        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiltroHistoriaActivity.super.onBackPressed();
            }
        });
    }
}
