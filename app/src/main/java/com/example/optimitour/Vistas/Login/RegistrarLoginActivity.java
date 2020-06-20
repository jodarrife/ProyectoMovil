package com.example.optimitour.Vistas.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.optimitour.Entidades.Usuario;
import com.example.optimitour.R;
import com.example.optimitour.Auxiliares.SplashCargaMalActivity;
import com.example.optimitour.Auxiliares.SplashCargadorActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class RegistrarLoginActivity extends AppCompatActivity {

    Animation topAnim, abajoAnim;
    ImageView imageView;
    TextInputLayout mEditTextName, mEditTextCorreo, mEditTextContraseña, mEditTextditrepetContraseña;
    Button mButtonRegistrar, mButtonRegresar;

    private String name = "";
    private String email = "";
    private String password = "";
    private String passwordRepet = "";


    FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_login);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        abajoAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        //base de datos
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //seteo
        imageView = findViewById(R.id.ima);
        mEditTextName = findViewById(R.id.txtNombre);
        mEditTextCorreo = findViewById(R.id.txtCorreo);
        mEditTextContraseña = findViewById(R.id.txtContraseña);
        mEditTextditrepetContraseña = findViewById(R.id.txtRepetContraseña);
        mButtonRegistrar = findViewById(R.id.btnRegistrar);
        mButtonRegresar = findViewById(R.id.btnCuentaYa);
        imageView.setAnimation(topAnim);
        mEditTextName.setAnimation(topAnim);
        mEditTextCorreo.setAnimation(topAnim);
        mEditTextContraseña.setAnimation(abajoAnim);
        mEditTextditrepetContraseña.setAnimation(abajoAnim);
        mButtonRegistrar.setAnimation(abajoAnim);
        mButtonRegresar.setAnimation(abajoAnim);


        //registrar
        mButtonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validateCorreo() | !validateContraseña() | !validateContraseñaRepetir()) {
                 return;
                }
                RegistarUser();

            }
        });

        //Regresar al login
        mButtonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(RegistrarLoginActivity.this, LoginActivity.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(imageView, "logo_image");
                pairs[1] = new Pair<View, String>(mEditTextName, "user_trans");
                pairs[2] = new Pair<View, String>(mEditTextCorreo, "user_trans");
                pairs[3] = new Pair<View, String>(mEditTextContraseña, "user_trans");
                pairs[4] = new Pair<View, String>(mEditTextditrepetContraseña, "pass_trans");
                pairs[5] = new Pair<View, String>(mButtonRegistrar, "registrar_trans");
                pairs[6] = new Pair<View, String>(mButtonRegresar, "button_trans");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegistrarLoginActivity.this, pairs);
                    startActivity(siguiente, options.toBundle());
                    finish();
                }
                finish();
            }
        });
    }
    //Registrar usuario
    public void RegistarUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = mAuth.getCurrentUser().getUid();

                    Map<String, Object> map = new HashMap<>();
                    Usuario usuario = new Usuario();
                    final String nameAux = usuario.setNombre(name);
                    final String correoAux = usuario.setCorreo(email);
                    final String ContraAux = password;
                    map.put("name", usuario.setNombre(name));
                    map.put("email", email);
                    map.put("password", password);
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                Intent siguiente = new Intent(RegistrarLoginActivity.this, SplashCargadorActivity.class);
                                startActivity(siguiente);
                                finish();
                            } else {
                                Intent siguiente = new Intent(RegistrarLoginActivity.this, SplashCargaMalActivity.class);
                                startActivity(siguiente);
                             //  Toast.makeText(RegistrarLoginActivity.this, "No se pudo registrar correctamente", Toast.LENGTH_LONG).show();
                                ///Intent siguiente = new Intent(RegistrarLoginActivity.this, SplashCargaMalActivity.class);
                                //startActivity(siguiente);
                            }
                        }
                    });
                } else {
                    Intent siguiente = new Intent(RegistrarLoginActivity.this, SplashCargaMalActivity.class);
                    startActivity(siguiente);
                    Toast.makeText(RegistrarLoginActivity.this, "No se pudo registar este usuario", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //Validar
    private Boolean validateName(){
        name = mEditTextName.getEditText().getText().toString();
        if (name.isEmpty()){
            mEditTextName.setError("El Campo no puede estar vacio");
            return  false;
        }else{
            mEditTextName.setError(null);
            mEditTextName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateCorreo(){
        email = mEditTextCorreo.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty()){
            mEditTextCorreo.setError("El Campo no puede estar vacio");
            return  false;
        }else if(!email.matches(emailPattern)){
            mEditTextCorreo.setError("Dirección de correo invalida");
            return  false;
        }

        else{
            mEditTextCorreo.setError(null);
            return true;
        }
    }
    private Boolean validateContraseña(){
        password = mEditTextContraseña.getEditText().getText().toString();
        String passwordVal = "^" +
                //
        //
        //
        "(?=.*[a-zA-Z])"+
                //"(?=.*[@#$%^&+=])"+
                "(?=\\S+$)"+
                ".{4,}"+
                "$";
        if (password.isEmpty()){
            mEditTextContraseña.setError("El Campo no puede estar vacio");
            return  false;
        }else if(!password.matches(passwordVal)){
            mEditTextContraseña.setError("La contraseña es demasiado debil");
            return  false;
        }else if(password.length()<=5) {
            mEditTextContraseña.setError("La contraseña es demasiado corta");
            return false;
        }else{
            mEditTextContraseña.setError(null);
            mEditTextContraseña.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateContraseñaRepetir(){
        password = mEditTextContraseña.getEditText().getText().toString();
        passwordRepet = mEditTextditrepetContraseña.getEditText().getText().toString();
        String passwordVal = "^" +
                //
                //
                //
                "(?=.*[a-zA-Z])"+
                //"(?=.*[@#$%^&+=])"+
                "(?=\\S+$)"+
                ".{4,}"+
                "$";
        if (passwordRepet.isEmpty()){
            mEditTextditrepetContraseña.setError("El Campo no puede estar vacio");
            return  false;
        }else if(!passwordRepet.matches(passwordVal)){
            mEditTextditrepetContraseña.setError("La contraseña es demasiado debil");
            return  false;
        }
        else if(password.equals(passwordRepet)) {
            mEditTextditrepetContraseña.setError(null);
            mEditTextditrepetContraseña.setErrorEnabled(false);
            return true;

        }else{
            mEditTextditrepetContraseña.setError("Las contraseñas deben ser iguales");
            return false;
        }
    }


}