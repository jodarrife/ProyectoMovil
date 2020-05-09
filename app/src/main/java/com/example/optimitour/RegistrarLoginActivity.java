package com.example.optimitour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistrarLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_login);
    }


    public void goLogin(View view) {
        Intent siguiente = new Intent(this,LoginActivity.class);
        startActivity(siguiente);
    }
}
