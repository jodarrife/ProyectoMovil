package com.example.optimitour.Vistas.Navegación;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.optimitour.Entidades.Turismo;
import com.example.optimitour.R;
import com.example.optimitour.Vistas.Navegación.Historia.FiltroHistoriaActivity;
import com.example.optimitour.Vistas.Navegación.Sitios.FiltroSitioActivity;
import com.example.optimitour.Vistas.Navegación.Turismo.TurismoActivity;

public class MunicipioListaActivity extends AppCompatActivity {
    public MunicipioListaActivity() {
        // Required empty public constructor
    }

    //VARIABLE
    ImageView back;
    Button btnAguachica, btnCodazzi, btnAstrea, btnBecerril, btnBosconia, btnChimichagua,
            btnChiriguana, btnCurumani, btnCopey, btnPaso, btnGamarra, btnGonzalez, btnLaGloria,
            btnLaJagua, btnLaPaz, btnManaure, btnPailitas, btnPelaya, btnPuebloBello, btnRioOro,
            btnSanAlberto, btnSanDiego, btnSanMartin, btnTamalameque, btnValledupar;
    String auxSitio;
    String aux,sitio,historia,turismo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mun_lista);

        //Recibo info para mandar
        Bundle extras = getIntent().getExtras();
        auxSitio = extras.getString("dato_Accion");
        aux = auxSitio;
        Toast.makeText(MunicipioListaActivity.this, "llego: " + aux, Toast.LENGTH_LONG).show();
        sitio="Sitios";
        historia="Historias" ;
        turismo="Turismo";
        //Seteo
        back = findViewById(R.id.btn_back);


        btnAguachica = findViewById(R.id.btnAguachica);
        btnAguachica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "0");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "0");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "0");
                    startActivity(i);
                }

            }
        });
        btnCodazzi = findViewById(R.id.btnCodazzi);
        btnCodazzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "1");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "1");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "1");
                    startActivity(i);
                }

            }
        });
        btnAstrea = findViewById(R.id.btnAstrea);
        btnAstrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "2");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "2");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "2");
                    startActivity(i);
                }

            }
        });
        btnBecerril = findViewById(R.id.btnBecerril);
        btnBecerril.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "3");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "3");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "3");
                    startActivity(i);
                }

            }
        });
        btnBosconia = findViewById(R.id.btnBosconia);
        btnBosconia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "4");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "4");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "4");
                    startActivity(i);
                }

            }
        });
        btnChimichagua = findViewById(R.id.btnChimichagua);
        btnChimichagua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "5");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "5");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "5");
                    startActivity(i);
                }

            }
        });
        btnChiriguana = findViewById(R.id.btnChiriguana);
        btnChiriguana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "6");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "6");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "6");
                    startActivity(i);
                }

            }
        });
        btnCurumani = findViewById(R.id.btnCurumani);
        btnCurumani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "7");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "7");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "7");
                    startActivity(i);
                }

            }
        });
        btnCopey = findViewById(R.id.btnCopey);
        btnCopey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "8");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "8");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "8");
                    startActivity(i);
                }

            }
        });
        btnPaso = findViewById(R.id.btnPaso);
        btnPaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "9");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "9");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "9");
                    startActivity(i);
                }

            }
        });
        btnGamarra = findViewById(R.id.btnGamarra);
        btnGamarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "10");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "10");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "10");
                    startActivity(i);
                }

            }
        });
        btnGonzalez = findViewById(R.id.btnGonzalez);
        btnGonzalez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "11");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "11");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "11");
                    startActivity(i);
                }

            }
        });
        btnLaGloria = findViewById(R.id.btnLaGloria);
        btnLaGloria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "12");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "12");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "12");
                    startActivity(i);
                }

            }
        });
        btnLaJagua = findViewById(R.id.btnLaJagua);
        btnLaJagua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "13");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "13");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "13");
                    startActivity(i);
                }
            }
        });
        btnLaPaz = findViewById(R.id.btnLaPaz);
        btnLaPaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "14");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "14");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "14");
                    startActivity(i);
                }

            }
        });
        btnManaure = findViewById(R.id.btnManaure);
        btnManaure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "15");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "15");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "15");
                    startActivity(i);
                }

            }
        });
        btnPailitas = findViewById(R.id.btnPailitas);
        btnPailitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "16");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "16");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "16");
                    startActivity(i);
                }

            }
        });
        btnPelaya = findViewById(R.id.btnPelaya);
        btnPelaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "17");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "17");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "17");
                    startActivity(i);
                }

            }
        });
        btnPuebloBello = findViewById(R.id.btnPuebloBello);
        btnPuebloBello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "18");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "18");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "18");
                    startActivity(i);
                }

            }
        });
        btnRioOro = findViewById(R.id.btnRioOro);
        btnRioOro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "19");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "19");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "19");
                    startActivity(i);
                }

            }
        });
        btnSanAlberto = findViewById(R.id.btnSanAlberto);
        btnSanAlberto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "20");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "20");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "20");
                    startActivity(i);
                }

            }
        });
        btnSanDiego = findViewById(R.id.btnSanDiego);
        btnSanDiego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "21");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "21");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "21");
                    startActivity(i);
                }

            }
        });
        btnSanMartin = findViewById(R.id.btnSanMartin);
        btnSanMartin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "22");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "22");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "22");
                    startActivity(i);
                }

            }
        });
        btnTamalameque = findViewById(R.id.btnTamalameque);
        btnTamalameque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "23");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "23");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "23");
                    startActivity(i);
                }

            }
        });
        btnValledupar = findViewById(R.id.btnValledupar);
        btnValledupar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux.equals(sitio)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroSitioActivity.class);
                    i.putExtra("dato_Municipio", "24");
                    startActivity(i);
                }else if (aux.equals(historia)){
                    Intent i = new Intent(MunicipioListaActivity.this, FiltroHistoriaActivity.class);
                    i.putExtra("dato_Municipio", "24");
                    startActivity(i);
                }else if(aux.equals(turismo)){
                    Intent i = new Intent(MunicipioListaActivity.this, TurismoActivity.class);
                    i.putExtra("dato_Municipio", "24");
                    startActivity(i);
                }

            }
        });

        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MunicipioListaActivity.super.onBackPressed();
            }
        });
    }
}
