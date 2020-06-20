package com.example.optimitour.Vistas.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.optimitour.Vistas.Navegación.Favoritos.FavoritoActivity;
import com.example.optimitour.Vistas.Navegación.MunicipioActivity;
import com.example.optimitour.Vistas.Navegación.Calendario.CalendarioActivity;
import com.example.optimitour.Vistas.Navegación.MunicipioListaActivity;
import com.example.optimitour.Vistas.Navegación.Perfil.CerrarSesionActivity;
import com.example.optimitour.Vistas.Navegación.Perfil.PerfilActivity;
import com.example.optimitour.R;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static final float END_SCALE = 0.7f;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    ImageView menu_icon;
    TextView textView;
    LinearLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*---------------------Hooks------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        textView = findViewById(R.id.textView);
        menu_icon = findViewById(R.id.btn_menu);
        contentView = findViewById(R.id.content);

        navigationDrawer();
    }
    //funciones del navigation
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        animateNavigationDrawer();
    }
    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
                break;
            case R.id.nav_Logout:
                startActivity(new Intent(getApplicationContext(), CerrarSesionActivity.class));
                break;
            case R.id.nav_place:
                Intent i = new Intent(HomeActivity.this, MunicipioActivity.class);
                i.putExtra("dato_Accion", "Sitios");
                startActivity(i);
                break;
            case R.id.nav_history:
                Intent i2 = new Intent(HomeActivity.this, MunicipioActivity.class);
                i2.putExtra("dato_Accion", "Historias");
                startActivity(i2);
                break;
            case R.id.nav_calendar:
                startActivity(new Intent(getApplicationContext(),  CalendarioActivity.class));
                break;
            case R.id.nav_favoritos:
                startActivity(new Intent(getApplicationContext(),  FavoritoActivity.class));
                break;
            case R.id.nav_turismo:
                Intent i3 = new Intent(HomeActivity.this, MunicipioActivity.class);
                i3.putExtra("dato_Accion", "Turismo");
                startActivity(i3);
                break;
            case R.id.nav_home:
                break;
        }
        return true;
    }
}
