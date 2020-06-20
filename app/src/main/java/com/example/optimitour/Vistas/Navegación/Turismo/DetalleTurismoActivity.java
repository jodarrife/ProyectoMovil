package com.example.optimitour.Vistas.Navegación.Turismo;

import androidx.appcompat.app.AppCompatActivity;

import com.example.optimitour.Data.ConexionSQLiteHelper;
import com.example.optimitour.Data.Utilidades;
import com.example.optimitour.Entidades.Sitios;
import com.example.optimitour.Entidades.Turismo;
import com.example.optimitour.R;
import com.example.optimitour.Vistas.Home.HomeActivity;
import com.example.optimitour.Vistas.Navegación.Sitios.DetalleSitioActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetalleTurismoActivity extends AppCompatActivity {

    //VARIABLE
    ArrayList<Turismo> listaTurismo;
    SQLiteDatabase db;
    String auxnameMunicipio;
    ImageView back, home;
    TextView nombreTurismo;
    TextView descripcionTurismo;
    TextView horarioTurismo;
    TextView ubicacionTurismo;
    ImageView imagenTurismo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_turismo);

        //Seteo
        back = findViewById(R.id.btn_back);
        home = findViewById(R.id.btn_Home);
        nombreTurismo = findViewById(R.id.nombre_turismo);
        imagenTurismo = findViewById(R.id.imagen_detalle);
        descripcionTurismo = findViewById(R.id.descripcion_turismo);
        //horarioTurismo = findViewById(R.id.horario_turismo);
        ubicacionTurismo = findViewById(R.id.ubicacion_turismo);

        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalleTurismoActivity.super.onBackPressed();
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
       // final int idmin;
        //Toast.makeText(DetalleTurismoActivity.this, "llego: " + auxId, Toast.LENGTH_LONG).show();

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {auxId};

        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT * FROM " + Utilidades.TABLE_TURISMO +
                    " WHERE " + Utilidades.CAMPO_NOMBRE_TURISMO + "=? ", parametros);
            cursor.moveToFirst();
            Turismo turismo = new Turismo();
            turismo.setId_turismo(cursor.getInt(0));
            turismo.setNombre_turismo(cursor.getString(1));
            turismo.setContenido_turismo(cursor.getString(2));
            turismo.setDireccion_turismo(cursor.getString(3));
            turismo.setContacto_turismo(cursor.getString(3));
            turismo.setImagenId(cursor.getInt(5));


            imagenTurismo.setImageResource(turismo.getImagenId());
            //Toast.makeText(DetalleSitioActivity.this, "imagen: " + imagenSitio, Toast.LENGTH_LONG).show();
            nombreTurismo.setText(turismo.getNombre_turismo());
            descripcionTurismo.setText(turismo.getContenido_turismo());
            ubicacionTurismo.setText(turismo.getDireccion_turismo());

            cursor.close();
            db.close();
            //return true;
        } catch (Exception e) {
            Toast.makeText(DetalleTurismoActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }

    }
}
