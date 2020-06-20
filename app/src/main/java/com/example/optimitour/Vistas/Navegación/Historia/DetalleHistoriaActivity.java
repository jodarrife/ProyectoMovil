package com.example.optimitour.Vistas.Navegación.Historia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.optimitour.Data.ConexionSQLiteHelper;
import com.example.optimitour.Data.Utilidades;
import com.example.optimitour.Entidades.Historias;
import com.example.optimitour.Entidades.Sitios;
import com.example.optimitour.R;
import com.example.optimitour.Vistas.Home.HomeActivity;

import java.util.ArrayList;

public class DetalleHistoriaActivity extends AppCompatActivity {

    //VARIABLE
    ArrayList<Historias> listaHistorias;
    SQLiteDatabase db;
    String auxnameMunicipio;
    ImageView back, home;
    TextView nombreHistorias;
    TextView descripcionHistorias;
    TextView horarioHistorias;
    TextView ubicacionHistorias;
    ImageView imagenHistorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_historia);

        //Seteo
        back = findViewById(R.id.btn_back);
        home = findViewById(R.id.btn_Home);
        nombreHistorias = findViewById(R.id.nombre_Historia);
        imagenHistorias = findViewById(R.id.imagen_detalle);
        descripcionHistorias = findViewById(R.id.descripcion_Historia);
        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalleHistoriaActivity.super.onBackPressed();
            }
        });
        //home
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
        cargarLista();
    }

    //cargar datos en la lista
    public void cargarLista() {
        //REcibir datos de municipio
        Bundle extras = getIntent().getExtras();
        auxnameMunicipio = extras.getString("dato_Nombre");
        String auxId = auxnameMunicipio;
        final String nombre, ide, tipo, contenido,  dire;
        final int idmin;
        //Toast.makeText(DetalleHistoriaActivity.this, "llego: " + auxId, Toast.LENGTH_LONG).show();

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {auxId};

        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT * FROM " + Utilidades.TABLE_HISTORIA +
                    " WHERE " + Utilidades.CAMPO_NOMBRE_HISTORIA + "=? ", parametros);
            cursor.moveToFirst();
            Historias historias = new Historias();
            historias.setId_Historia(cursor.getInt(0));
            historias.setNombre_Historia(cursor.getString(1));
            historias.setContenido_Historia(cursor.getString(3));
            historias.setImagenId(cursor.getInt(4));

            imagenHistorias.setImageResource(historias.getImagenId());

            nombreHistorias.setText(historias.getNombre_Historia());
            descripcionHistorias.setText(historias.getContenido_Historia());

            cursor.close();

            //return true;
        } catch (Exception e) {
          //  Toast.makeText(DetalleHistoriaActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }
        database.close();
    }
}
