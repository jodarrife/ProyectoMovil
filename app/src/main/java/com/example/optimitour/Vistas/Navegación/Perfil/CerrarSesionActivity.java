package com.example.optimitour.Vistas.Navegación.Perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.optimitour.R;
import com.example.optimitour.Vistas.Login.BienvenidoActivity;
import com.google.firebase.auth.FirebaseAuth;

public class CerrarSesionActivity extends AppCompatActivity {

    public CerrarSesionActivity() {
        // Required empty public constructor
    }

    //VARIABLE
    ImageButton btnCerrar;
    ImageView back;
    //FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_sesion);
        //Seteo
        back = findViewById(R.id.btn_back);
        btnCerrar = findViewById(R.id.btnCerrar);
        progressBar = findViewById(R.id.progressBar);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //Cerrar Sesion
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                auxProgress();
                Toast.makeText(CerrarSesionActivity.this,"Cerrando sesion", Toast.LENGTH_LONG).show();
                startActivity(new Intent(CerrarSesionActivity.this, BienvenidoActivity.class));
                finish();
            }
        });

        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarSesionActivity.super.onBackPressed();
            }
        });
    }

    private void auxProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }
}
