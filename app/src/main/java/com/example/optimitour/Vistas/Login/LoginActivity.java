package com.example.optimitour.Vistas.Login;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.optimitour.Vistas.Home.HomeActivity;
import com.example.optimitour.Model.LoginInteractorImpl;
import com.example.optimitour.Presenter.LoginPresenter;
import com.example.optimitour.Presenter.LoginPresenterImpl;
import com.example.optimitour.R;
import com.example.optimitour.View.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    //Variables
    Animation topAnim, abajoAnim;
    Button crearCuenta, login;
    ImageView image;
    TextInputLayout username, password;
    CheckBox checkBox;
    ProgressBar progressBar;
    private LoginPresenter loginPresenter;
    //Datos Auxiliares
    private String email = "";
    private String contra = "";
    //Variable de Base de datos
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        abajoAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        /**setear*/
        progressBar = findViewById(R.id.progressBar);
        image = findViewById(R.id.ima);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        crearCuenta = findViewById(R.id.btnCrearCuenta);
        login = findViewById(R.id.btnInicioSesion);
        checkBox = findViewById(R.id.remerberMe);
        image.setAnimation(topAnim);
        username.setAnimation(topAnim);
        password.setAnimation(abajoAnim);
        checkBox.setAnimation(abajoAnim);
        crearCuenta.setAnimation(abajoAnim);
        login.setAnimation(abajoAnim);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.btnInicioSesion).setOnClickListener(this);

        loginPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl());

        //boton crear cuenta
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent siguiente = new Intent(LoginActivity.this, RegistrarLoginActivity.class);
                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(username, "user_trans");
                pairs[2] = new Pair<View, String>(password, "pass_trans");
                pairs[3] = new Pair<View, String>(checkBox, "check_trans");
                pairs[4] = new Pair<View, String>(crearCuenta, "registrar_trans");
                pairs[5] = new Pair<View, String>(login, "button_trans");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(siguiente, options.toBundle());
                    finish();
                }
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        email = username.getEditText().getText().toString();
        contra = password.getEditText().getText().toString();
        loginPresenter.validateCredentials(email, contra);
        if (!validateCorreo() | !validateContraseña()) {
            return;
        }
        LoginUser();
    }

    //login
    private void LoginUser() {
        mAuth.signInWithEmailAndPassword(email, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent siguiente = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(siguiente);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesion, compruebe los datos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Validar
    private Boolean validateCorreo() {
        email = username.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty()) {
            username.setError("El Campo no puede estar vacio");
            return false;
        } else if (!email.matches(emailPattern)) {
            username.setError("Dirección de correo invalida");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private Boolean validateContraseña() {
        contra = password.getEditText().getText().toString();
        String passwordVal = "^" +
                //
                //
                //
                "(?=.*[a-zA-Z])" +
                //"(?=.*[@#$%^&+=])"+
                "(?=\\S+$)" +
                ".{4,}" +
                "$";
        if (contra.isEmpty()) {
            password.setError("El Campo no puede estar vacio");
            return false;
        } else if (contra.length() <= 5) {
            password.setError("La contraseña es demasiado corta");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }


    //Si el usuario ya inicioo sesion y cerro la app le recordsremos a la app que el ya estaba logueado
    @Override
    protected void onStart() {
        super.onStart();
        //Si el usuario ya ha iniciado sesion/
        if (mAuth.getCurrentUser() != null) {
            Intent siguiente = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(siguiente);
            finish();
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void auxProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUserError() {
        username.setError("Error de usuario");
    }

    @Override
    public void setPasswordError() {
        password.setError("Error de contraseña");
    }

    @Override
    public void navigateToHome() {


    }

}
