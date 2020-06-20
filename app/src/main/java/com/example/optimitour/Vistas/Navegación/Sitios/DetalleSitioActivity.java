package com.example.optimitour.Vistas.Navegación.Sitios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.optimitour.Data.ConexionSQLiteHelper;
import com.example.optimitour.Data.Utilidades;
import com.example.optimitour.Entidades.Favoritos;
import com.example.optimitour.Entidades.Sitios;
import com.example.optimitour.R;
import com.example.optimitour.Vistas.Home.HomeActivity;
import com.example.optimitour.Vistas.Navegación.Favoritos.FavoritoActivity;
import com.example.optimitour.Vistas.Navegación.Turismo.DetalleTurismoActivity;

import java.util.ArrayList;

public class DetalleSitioActivity extends AppCompatActivity {

    //VARIABLE
    ArrayList<Sitios> listaSitios;
    SQLiteDatabase db;
    String auxnameMunicipio;
    ImageView back, home;
    TextView nombreSitio;
    TextView descripcionSitio;
    TextView horarioSitio;
    TextView ubicacionSitio;
    ImageView imagenSitio;
    String auxId, existe, existe2, force;
    ImageView btnFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__sitio);

        //Seteo
        back = findViewById(R.id.btn_back);
        home = findViewById(R.id.btn_Home);
        nombreSitio = findViewById(R.id.nombre_sitio);
        imagenSitio = findViewById(R.id.imagen_detalle);
        descripcionSitio = findViewById(R.id.descripcion_sitio);
        horarioSitio = findViewById(R.id.horario_sitio);
        ubicacionSitio = findViewById(R.id.ubicacion_sitio);
        btnFavorito = findViewById(R.id.btnFavorito);
        //REcibir datos de municipio
        Bundle extras = getIntent().getExtras();
        auxnameMunicipio = extras.getString("dato_Nombre");
        auxId = auxnameMunicipio;
        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //base de datos Local
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(DetalleSitioActivity.this);
                SQLiteDatabase database = conn.getWritableDatabase();


                try {
                    String[] parametrosaux = {auxId};

                    Cursor cursor6 = database.rawQuery("SELECT * FROM " + Utilidades.TABLE_FAVORITO +
                            " WHERE " + Utilidades.CAMPO_NOMBRE_FAVORITO + "=? ", parametrosaux);
                    cursor6.moveToFirst();
                    Favoritos favoritos = new Favoritos();
                    favoritos.setId_Favoritos(cursor6.getInt(0));
                    favoritos.setNombre_Favoritos(cursor6.getString(1));
                    force = cursor6.getString(1);
                    //Toast.makeText(DetalleSitioActivity.this, "existe " + force, Toast.LENGTH_LONG).show();
                    existe2 = "si";
                    cursor6.close();
                } catch (Exception e) {
                    existe2 = "no";
                }
                existe="si";
                if (existe == existe2) {
                    Toast.makeText(DetalleSitioActivity.this, "Ya esta agregado a Favoritos", Toast.LENGTH_LONG).show();
                } else {
                    //Despues se hace una consulta a la tabla sqlite
                    database = conn.getReadableDatabase();
                    String insert = "INSERT INTO " + Utilidades.TABLE_FAVORITO
                            + " (" + Utilidades.CAMPO_NOMBRE_FAVORITO + ")" +
                            " VALUES ('" + auxId + "');";
                    database.execSQL(insert);
                    database.close();
                    Toast.makeText(DetalleSitioActivity.this, "SE AGREGO: " + auxId, Toast.LENGTH_LONG).show();
                }
            }
        });
        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalleSitioActivity.super.onBackPressed();
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

        // Toast.makeText(DetalleSitioActivity.this, "llego: " + auxId, Toast.LENGTH_LONG).show();

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {auxId};

        Cursor cursor = database.rawQuery("SELECT * FROM " + Utilidades.TABLE_SITIO +
                " WHERE " + Utilidades.CAMPO_NOMBRE_SITIO + "=? ", parametros);
        cursor.moveToFirst();
        Sitios sitio = new Sitios();
        sitio.setId_Sitio(cursor.getInt(0));
        sitio.setNombre_Sitio(cursor.getString(1));
        sitio.setDirecion_Sitio(cursor.getString(3));
        sitio.setContenido_Sitio(cursor.getString(4));
        sitio.setImagenId(cursor.getInt(5));


        imagenSitio.setImageResource(sitio.getImagenId());
        //Toast.makeText(DetalleSitioActivity.this, "imagen: " + imagenSitio, Toast.LENGTH_LONG).show();
        nombreSitio.setText(sitio.getNombre_Sitio());
        descripcionSitio.setText(sitio.getContenido_Sitio());
        ubicacionSitio.setText(sitio.getDirecion_Sitio());

        cursor.close();
       // db.close();
        //return true;
       }
}
