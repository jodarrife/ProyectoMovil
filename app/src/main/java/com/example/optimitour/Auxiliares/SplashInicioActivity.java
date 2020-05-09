package com.example.optimitour.Auxiliares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.optimitour.LoginActivity;
import com.example.optimitour.MainActivity;
import com.example.optimitour.R;

public class SplashInicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_inicio);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent siguiente = new Intent(SplashInicioActivity.this, LoginActivity.class);
                startActivity(siguiente);
                finish();
            }
        },3000);
    }
}
