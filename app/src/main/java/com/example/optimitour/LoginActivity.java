package com.example.optimitour;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.optimitour.Model.LoginInteractorImpl;
import com.example.optimitour.Presenter.LoginPresenter;
import com.example.optimitour.Presenter.LoginPresenterImpl;
import com.example.optimitour.View.LoginView;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {


    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**setear*/

        progressBar = findViewById(R.id.progressBar);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);

        findViewById(R.id.btnInicioSesion).setOnClickListener(this);

        loginPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl());


    }


    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
         loginPresenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUserError() {
        username.setError("Error de Usuario");
    }

    @Override
    public void setPasswordError() {
        password.setError("Error de Password");
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this,"BIENVENIDO", Toast.LENGTH_LONG).show();
        Intent siguiente = new Intent(this,MainActivity.class);
        startActivity(siguiente);
    }

    public void goRegistrer(View view) {
        Intent siguiente = new Intent(this,RegistrarLoginActivity.class);
        startActivity(siguiente);
    }
}
