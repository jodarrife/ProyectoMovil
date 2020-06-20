package com.example.optimitour.Vistas.Navegación.Perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.optimitour.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilActivity extends AppCompatActivity {


    //Variables
    TextView email_text, nombre_text,textView11;
    ImageView back;
    //base de datos
    FirebaseDatabase database;
    DatabaseReference userRef;
    FirebaseUser user;
    static final String USERS = "Users";
    String email,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);



        //Seteo
        back = findViewById(R.id.btn_back);
        email_text = findViewById(R.id.email_text);
        nombre_text = findViewById(R.id.nombre_text);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerfilActivity.super.onBackPressed();
            }
        });

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

        user = FirebaseAuth.getInstance().getCurrentUser();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            //obtemer los datos
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //una validacion si existe el objeto
                if (dataSnapshot.exists() && user != null){
                    email = user.getEmail();
                //    Toast.makeText(PerfilActivity.this, email, Toast.LENGTH_LONG).show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        if (snapshot.child("email").getValue().equals(email)){
                         //   Toast.makeText(PerfilActivity.this, "Se encontro", Toast.LENGTH_LONG).show();
                            nombre_text.setText(snapshot.child("name").getValue(String.class));
                            email_text.setText(email);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
