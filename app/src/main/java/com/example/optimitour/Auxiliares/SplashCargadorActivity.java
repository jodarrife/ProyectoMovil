package com.example.optimitour.Auxiliares;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.optimitour.R;
import com.example.optimitour.Vistas.Login.RegistrarLoginActivity;

public class SplashCargadorActivity extends AppCompatActivity {

    ProgressBar splashProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cargador);
        splashProgress=findViewById(R.id.splasProgress);
        ObjectAnimator.ofInt(splashProgress, "progress", 100).setDuration(5000).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashCargadorActivity.this,RegistrarLoginActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
        Toast.makeText(SplashCargadorActivity.this, "Registro exitoso.", Toast.LENGTH_LONG).show();
    }
}
