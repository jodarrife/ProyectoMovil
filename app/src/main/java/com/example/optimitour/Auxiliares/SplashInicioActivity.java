package com.example.optimitour.Auxiliares;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.optimitour.Vistas.Home.HomeActivity;
import com.example.optimitour.Vistas.Login.BienvenidoActivity;
import com.example.optimitour.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashInicioActivity extends AppCompatActivity {
    //Variables
    private  static  int SPLASH_SCREEN = 4000;
    Animation topAnim, bottonAnim;
    ImageView image,letra;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_inicio);

/**setear*/

        mAuth = FirebaseAuth.getInstance();
        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottonAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.logo);
        letra = findViewById(R.id.letra);
        image.setAnimation(topAnim);
        letra.setAnimation(bottonAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent siguiente = new Intent(SplashInicioActivity.this, BienvenidoActivity.class);
                startActivity(siguiente);

                finish();
            }
        }, SPLASH_SCREEN);
    }

    //Si el usuario ya inicioo sesion y cerro la app le recordsremos a la app que el ya estaba logueado
    @Override
    protected void onStart() {
        super.onStart();
        //Si el usuario ya ha iniciado sesion/
        if (mAuth.getCurrentUser() != null) {
            Intent siguiente = new Intent(SplashInicioActivity.this, HomeActivity.class);
            startActivity(siguiente);
            finish();
        }
    }
}
