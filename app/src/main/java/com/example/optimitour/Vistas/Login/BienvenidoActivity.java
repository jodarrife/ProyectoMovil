package com.example.optimitour.Vistas.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.optimitour.Data.ConexionSQLiteHelper;
import com.example.optimitour.Data.Utilidades;
import com.example.optimitour.HomeSinInternetActivity;
import com.example.optimitour.R;
import com.example.optimitour.Vistas.Home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class BienvenidoActivity extends AppCompatActivity {

    //Variables
    Animation topAnim, bottonAnim;
    ImageView arriba, abajo;
    Button login, invitado, btnInicioLogin2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottonAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        /**setear*/

        mAuth = FirebaseAuth.getInstance();
        arriba = findViewById(R.id.ima);
        abajo = findViewById(R.id.ima2);
        login = findViewById(R.id.btnInicioLogin);
        invitado = findViewById(R.id.btnInicioInvitado);
        arriba.setAnimation(topAnim);
        abajo.setAnimation(bottonAnim);
        login.setAnimation(topAnim);
        invitado.setAnimation(bottonAnim);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(BienvenidoActivity.this, LoginActivity.class);
                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View, String>(arriba, "logo_image");
                pairs[1] = new Pair<View, String>(login, "user_trans");
                pairs[2] = new Pair<View, String>(abajo, "pass_trans");
                pairs[3] = new Pair<View, String>(invitado, "abajo_image");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(BienvenidoActivity.this, pairs);
                    startActivity(siguiente, options.toBundle());
                    finish();
                }
            }
        });

        invitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(BienvenidoActivity.this, HomeSinInternetActivity.class));

            }
        });
        agregarTODO();

    }

    //Si el usuario ya inicioo sesion y cerro la app le recordsremos a la app que el ya estaba logueado
    @Override
    protected void onStart() {
        super.onStart();
        //Si el usuario ya ha iniciado sesion/
        if (mAuth.getCurrentUser() != null) {
            Intent siguiente = new Intent(BienvenidoActivity.this, HomeActivity.class);
            startActivity(siguiente);
            finish();
        }
    }

    //Registrar Municipios
    private void agregarTODO() {
        registrarMunicipios();
        registrarSitios();
        registroHistorias();
        RegistrarTurismo();

    }
    private void registrarMunicipios() {
        String  id_municipio = "1";

        //base de datos Local
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();

        //Despues se hace una consulta a la tabla sqlite
        database = conn.getReadableDatabase();
        String query = "Select * from " + Utilidades.TABLE_MUNICIPIO + " where id_municipio= '" + id_municipio + "'";
        Cursor cursor = database.rawQuery(query, null);
       // Toast.makeText(BienvenidoActivity.this, "busco"+query, Toast.LENGTH_LONG).show();
        if (cursor.getCount() == 0)//si la tabla esta vacia insertara los datos sin problema
        {
            Integer[] IdMunicipios = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
            String[] Municipios = {
                    "Aguachica", "Agustin Codazzi",
                    "Astrea", "Becerril",
                    "Bosconia", "Chimichagua",
                    "Chiriguana", "Curumani",
                    "El Copey", "El Paso",
                    "Gamarra", "Gonzalez",
                    "La Gloria", "La Jagua de Ibirico",
                    "La Paz", "Manaure Balcón del Cesar",
                    "Pailitas", "Pelaya",
                    "Pueblo Bello", "Rio de Oro",
                    "San Alberto", "San Diego",
                    "San Martin", "Tamalameque",
                    "Valledupar"};
            Integer[] imagenPormunicipio ={
                    R.drawable.croquis_aguachica,R.drawable.croquis_codazzi,
                    R.drawable.croquis_astrea,R.drawable.croquis_becerril,
                    R.drawable.croquis_bosconia,R.drawable.croquis_chimichagua,
                    R.drawable.croquis_chiriguana,R.drawable.croquis_curumani,
                    R.drawable.croquis_copey,R.drawable.croquis_elpaso,
                    R.drawable.croquis_gamarra,R.drawable.croquis_gonzalez,
                    R.drawable.croquis_lagloria,R.drawable.croquis_lajagua,
                    R.drawable.croquis_lapaz,R.drawable.croquis_manaure,
                    R.drawable.croquis_pailitas,R.drawable.croquis_pelaya,
                    R.drawable.croquis_pueblobello,R.drawable.croquis_riodeoro,
                    R.drawable.croquis_sanalberto,R.drawable.croquis_sandiego,
                    R.drawable.croquis_sanmartin,R.drawable.croquis_tamalameque,
                    R.drawable.croquis_valledupar};


            for (int j = 0; j < Municipios.length; j++) {
                final Integer auxidemu = IdMunicipios[j];
                final String auxNamemu = Municipios[j];
                final Integer auximagenesmu = imagenPormunicipio[j];
                String insert = "INSERT INTO " + Utilidades.TABLE_MUNICIPIO
                        + " ("
                        + Utilidades.CAMPO_ID_MUNICIPIO_UNICA + ", "
                        + Utilidades.CAMPO_NOMBRE_MUNICIPIO  + ", "
                        + Utilidades.CAMPO_IMAGEN_MUNICIPIO_UNICA + ")" +
                        " VALUES ("
                        + auxidemu +
                        ", '" + auxNamemu + "'" +
                        ", " + auximagenesmu + ");";
                database.execSQL(insert);
            }
         //   Toast.makeText(BienvenidoActivity.this, "Municipio", Toast.LENGTH_LONG).show();
        } else {
           // Toast.makeText(BienvenidoActivity.this, "Ya esta Cargada la lista Municipio", Toast.LENGTH_LONG).show();
        }
        database.close();
    }
    private void ConsultarMunicipios() {
        //base de datos Local
        final String campoId = "24";
        final String nombre, ide;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {campoId};
        String[] campos = {Utilidades.CAMPO_ID_MUNICIPIO_UNICA, Utilidades.CAMPO_NOMBRE_MUNICIPIO};
        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_ID_MUNICIPIO_UNICA +
                    ", " + Utilidades.CAMPO_NOMBRE_MUNICIPIO +
                    " FROM " + Utilidades.TABLE_MUNICIPIO +
                    " WHERE " + Utilidades.CAMPO_ID_MUNICIPIO_UNICA + "=? ", parametros);
            cursor.moveToFirst();
            nombre = cursor.getString(1);

            // Toast.makeText(BienvenidoActivity.this, "se encontro (24): " + nombre, Toast.LENGTH_LONG).show();
            //return true;
            cursor.close();
        } catch (Exception e) {
            //Toast.makeText(BienvenidoActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }
    }
    private void registrarSitios() {
        int id_sitio = 1;

        //base de datos Local
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();

        //Despues se hace una consulta a la tabla sqlite
        database = conn.getReadableDatabase();
        String query = "Select * from " + Utilidades.TABLE_SITIO + " where id_sitio= '" + id_sitio + "'";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 0)//si la tabla esta vacia insertara los datos sin problema
        {

            String TipoRestaurante = "Restaurante";
            //region RESTAURANTES
            //0-AGUACHICA
            //region Restaurantes Aguachica
            int auxIdAguachica = 0;
            Integer[] auxIdSitioAguachica = {0, 1, 2};
            String[] auxNombreSitiosAguachica = {"La Brasa", "Burger Site", "Juglares Bar"};
            String[] auxDireccionSitiosAguachica = {"Carrera 40 Vía al mar entre calle 3 y 4.", "Calle 5 #33-44", "Calle 5 #29-52"};
            String[] auxContenidoSitiosAguachica = {"Con una tradición de mas de 40 años, este restaurante vive evolucionando para entregar el exquisito sabor con el que se identifica que es ideal para compartir en familia y alimentarse de la mejor manera.",
                    "Con una tradición de más de 40 años, este restaurante vive evolucionando para entregar el exquisito sabor con el que se identifica que es ideal para compartir en familia y alimentarse de la mejor manera.",
                    "Un lugar agradable, buena música, ambiente en su mayoría de vallenato puro, buenos licores fríos. El sitio es hermosos y con un excelente ambiente impecable. Un lugar donde puedes hablar, tomar y hacer negocios."};
            Integer[] auxImagenesSitioAguachica = {R.drawable.rest_aguachica_brasa, R.drawable.rest_aguachica_burger, R.drawable.rest_aguachica_juglares};
            for (int j = 0; j < auxNombreSitiosAguachica.length; j++) {

                final Integer auxIdSitio = auxIdSitioAguachica[j];
                final String auxNameSitio = auxNombreSitiosAguachica[j];
                final String auxDireSitio = auxDireccionSitiosAguachica[j];
                final String auxContenidoSitio = auxContenidoSitiosAguachica[j];
                final Integer auxImagenSitio = auxImagenesSitioAguachica[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdAguachica + "');";
                database.execSQL(insert);
            }
            //endregion

            //1-CODAZZI
            //region Restaurantes Codazzi
            int auxIdCodazzi = 1;
            Integer[] auxIdSitioCodazzi = {3, 4};
            String[] auxNombreSitiosCodazzi = {"Cevichería y pescadería Ela", "El Rancho"};
            String[] auxDireccionSitiosCodazzi = {"Calle 11b #13ª–45", "Cra. 12 #122"};
            String[] auxContenidoSitiosCodazzi = {"Cevichería y pescadería Ela, lugar perfecto para comer en familia",
                    "Posee excelente prestación de atención al cliente, calidad y servicio en especial, en comida exquisitas a buen precio y muy particulares. Ambiente agradable y buena ubicación."};
            Integer[] auxImagenesSitioCodazzi = {R.drawable.rest_codazzi_ela, R.drawable.rest_codazzi_rancho};
            for (int j = 0; j < auxNombreSitiosCodazzi.length; j++) {

                final Integer auxIdSitio = auxIdSitioCodazzi[j];
                final String auxNameSitio = auxNombreSitiosCodazzi[j];
                final String auxDireSitio = auxDireccionSitiosCodazzi[j];
                final String auxContenidoSitio = auxContenidoSitiosCodazzi[j];
                final Integer auxImagenSitio = auxImagenesSitioCodazzi[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdCodazzi + "');";
                database.execSQL(insert);
            }
            //endregion

            //2-ASTREA
            //region Restaurantes Astrea
            int auxIdAstrea = 2;
            Integer[] auxIdSitioAstrea = {5, 6};
            String[] auxNombreSitiosAstrea = {"Restaurante donde Bryan", "Restaurante el sabor costeño"};
            String[] auxDireccionSitiosAstrea = {"Calle 11 # 2-1", "Calle 5 #2-2"};
            String[] auxContenidoSitiosAstrea = {"Ofrece comidas con buena sazón y exquisitas y a buen precio, razonable con cada comida. ",
                    "Ofrece comidas con buena sazón y exquisitas y a buen precio, razonable con cada comida. Excelente atención y gran amabilidad agradable que se brinda. Ofrece Comidas para llevar. Ubicación excelente, en buena zona, y ambiente agradable para ser restaurante de paso."};
            Integer[] auxImagenesSitioAstrea = {R.drawable.rest_astrea_bryan, R.drawable.restl_astrea_rest};
            for (int j = 0; j < auxNombreSitiosAstrea.length; j++) {

                final Integer auxIdSitio = auxIdSitioAstrea[j];
                final String auxNameSitio = auxNombreSitiosAstrea[j];
                final String auxDireSitio = auxDireccionSitiosAstrea[j];
                final String auxContenidoSitio = auxContenidoSitiosAstrea[j];
                final Integer auxImagenSitio = auxImagenesSitioAstrea[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdAstrea + "');";
                database.execSQL(insert);
            }
            //endregion

            //3-BECERRIL
            //region Restaurantes Becerril
            int auxIdBecerril = 3;
            Integer[] auxIdSitioBecerril = {7, 8};
            String[] auxNombreSitiosBecerril = {"Coco Parrilla Restaurant", "Restaurante y Asadero El buen Sazón"};
            String[] auxDireccionSitiosBecerril = {"Calle 5 # 8 esquina.", "Calle 11 #4a"};
            String[] auxContenidoSitiosBecerril = {"Ofrece las mejores comidas  de becerril que se puedan probar, con excelentes asados y picadas deliciosas",
                    "Ofrece las mejores comidas  de becerril que se puedan probar, con excelentes asados y picadas deliciosas, con buen sazon y sabor típico de las comidas de la costa. Atención excelente que lleva consigo una gran amabilidad y gentileza por parte del personal."};
            Integer[] auxImagenesSitioBecerril = {R.drawable.rest_becerril_coco, R.drawable.rest_becerril_sazon};
            for (int j = 0; j < auxNombreSitiosBecerril.length; j++) {

                final Integer auxIdSitio = auxIdSitioBecerril[j];
                final String auxNameSitio = auxNombreSitiosBecerril[j];
                final String auxDireSitio = auxDireccionSitiosBecerril[j];
                final String auxContenidoSitio = auxContenidoSitiosBecerril[j];
                final Integer auxImagenSitio = auxImagenesSitioBecerril[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdBecerril + "');";
                database.execSQL(insert);
            }
            //endregion

            //4-BOSCONIA
            //region Restaurantes Bosconia
            int auxIdBosconia = 4;
            Integer[] auxIdSitioBosconia = {9, 10};
            String[] auxNombreSitiosBosconia = {"Doña Sandra", "La Fogata"};
            String[] auxDireccionSitiosBosconia = {"Carrera 3 #3–13", "Calle 13 #2"};
            String[] auxContenidoSitiosBosconia = {"Excelente lugar acogedor, romántico, toda una sorpresa para los visitantes, un muy buen ejemplo para imitar en tantos municipios de la costa.",
                    "El más famoso del municipio. Restaurante de paso, ubicado en un buen lugar para comer, ofrece un delicioso chicharrón de cerdo (comida típica del municipio), refrescantes bebidas a cualquier hora del día. Hacemos jugo natural y también procesados. Sus salones tienen aire acondicionado y si cuentas con suerte puedes estacionar tu vehículo al frente."};
            Integer[] auxImagenesSitioBosconia = {R.drawable.rest_bosconia_sandra, R.drawable.rest_bosconia_fogata};
            for (int j = 0; j < auxNombreSitiosBosconia.length; j++) {

                final Integer auxIdSitio = auxIdSitioBosconia[j];
                final String auxNameSitio = auxNombreSitiosBosconia[j];
                final String auxDireSitio = auxDireccionSitiosBosconia[j];
                final String auxContenidoSitio = auxContenidoSitiosBosconia[j];
                final Integer auxImagenSitio = auxImagenesSitioBosconia[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdBosconia + "');";
                database.execSQL(insert);
            }
            //endregion

            //5-CHIMICHAGUA
            //region Restaurantes Chimichagua
            int auxIdChimichagua = 5;
            Integer[] auxIdSitioChimichagua = {11, 12};
            String[] auxNombreSitiosChimichagua = {"El Gordo Saul", "Muelle turístico C Chimichagua"};
            String[] auxDireccionSitiosChimichagua = {"Calle 5 #8", "Calle 7 Puerto Arenal"};
            String[] auxContenidoSitiosChimichagua = {"Posee excelente prestación de atención al cliente, calidad y servicio en especial",
                    "Excelente lugar acogedor, romántico, toda una sorpresa para los visitantes, un muy buen ejemplo para imitar en tantos municipios de la costa.  Es buen lugar para almorzar o pasar un rato, pues, es un sitio tranquilo, el aire que se respira es totalmente limpio, con una vista impresionante hacia la ciénaga Zapatosa."};
            Integer[] auxImagenesSitioChimichagua = {R.drawable.rest_chimichagua_gordo, R.drawable.rest_chimichagua_muelle};
            for (int j = 0; j < auxNombreSitiosChimichagua.length; j++) {

                final Integer auxIdSitio = auxIdSitioChimichagua[j];
                final String auxNameSitio = auxNombreSitiosChimichagua[j];
                final String auxDireSitio = auxDireccionSitiosChimichagua[j];
                final String auxContenidoSitio = auxContenidoSitiosChimichagua[j];
                final Integer auxImagenSitio = auxImagenesSitioChimichagua[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdChimichagua + "');";
                database.execSQL(insert);
            }
            //endregion

            //6-CHIRIGUANÁ
            //region Restaurantes Chiriguana
            int auxIdChiriguana = 6;
            Integer[] auxIdSitioChiriguana = {13, 14};
            String[] auxNombreSitiosChiriguana = {"Costa Dorada", "Shimar"};
            String[] auxDireccionSitiosChiriguana = {"Plaza Central, Calle 7 #4–156", "Cl. 7 #4-198"};
            String[] auxContenidoSitiosChiriguana = {"Posee excelente prestación de atención al cliente, calidad y servicio en especial",
                    "Ofrece excelentes comidas (en especial de mar) contando con una buena atención. Ubicado en un sitio agradable para compartir."};
            Integer[] auxImagenesSitioChiriguana = {R.drawable.rest_chiriguana_costa, R.drawable.rest_chiriguana_shimar};
            for (int j = 0; j < auxNombreSitiosChiriguana.length; j++) {

                final Integer auxIdSitio = auxIdSitioChiriguana[j];
                final String auxNameSitio = auxNombreSitiosChiriguana[j];
                final String auxDireSitio = auxDireccionSitiosChiriguana[j];
                final String auxContenidoSitio = auxContenidoSitiosChiriguana[j];
                final Integer auxImagenSitio = auxImagenesSitioChiriguana[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdChiriguana + "');";
                database.execSQL(insert);
            }
            //endregion

            //7-CURUMANÍ
            //region Restaurantes Curumani
            int auxIdCurumani = 7;
            Integer[] auxIdSitioCurumani = {15, 16};
            String[] auxNombreSitiosCurumani = {"Brasas Gourmet", "Brisas del Oriente"};
            String[] auxDireccionSitiosCurumani = {"Calle 9 #16-02.", "Transv. 5 #14-165"};
            String[] auxContenidoSitiosCurumani = {"Brasas Gourmet (Comidas Rápidas)",
                    "Ofrece una gran variedad de comidas típicas del municipio y de la región, con una excelente sazón que le aporta un exquisito sabor, con precios cómodos. Con excelente atención al cliente. Un lugar muy limpio y agradable Es uno de los mejores restaurantes de paso del municipio que ofrece servicios de restaurante, alojamiento (alojamiento está dispuesto preferiblemente para transportadores y viajeros que se desplazan desde el interior del país hacia la costa Atlántica o viceversa) y parqueadero vigilado. Los precios son cómodos y la atención está bien."};
            Integer[] auxImagenesSitioCurumani = {R.drawable.rest_curumani_brasas, R.drawable.rest_curumani_brisa};
            for (int j = 0; j < auxNombreSitiosCurumani.length; j++) {

                final Integer auxIdSitio = auxIdSitioCurumani[j];
                final String auxNameSitio = auxNombreSitiosCurumani[j];
                final String auxDireSitio = auxDireccionSitiosCurumani[j];
                final String auxContenidoSitio = auxContenidoSitiosCurumani[j];
                final Integer auxImagenSitio = auxImagenesSitioCurumani[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdCurumani + "');";
                database.execSQL(insert);
            }
            //endregion

            //8-EL COPEY
            //region Restaurantes Copey
            int auxIdCopey = 8;
            Integer[] auxIdSitioCopey = {17};
            String[] auxNombreSitiosCopey = {"El Sabor Zuliano"};
            String[] auxDireccionSitiosCopey = {"Calle 5 #8–88"};
            String[] auxContenidoSitiosCopey = {"presentación en las comidas que son muy variadas acompañadas de bebidas a buen precio. Buena atención y excelente personal, lo que lo hacen un excelente lugar para compartir en familia con comidas de calidad. Ambiente agradable, hermoso, y buena higiene."};
            Integer[] auxImagenesSitioCopey = {R.drawable.rest_copey_zuliano};
            for (int j = 0; j < auxNombreSitiosCopey.length; j++) {

                final Integer auxIdSitio = auxIdSitioCopey[j];
                final String auxNameSitio = auxNombreSitiosCopey[j];
                final String auxDireSitio = auxDireccionSitiosCopey[j];
                final String auxContenidoSitio = auxContenidoSitiosCopey[j];
                final Integer auxImagenSitio = auxImagenesSitioCopey[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdCopey + "');";
                database.execSQL(insert);
            }
            //endregion

            //falta uno de cada uno pa abajo
            //9-El PASO
            //region Restaurantes Paso
            int auxIdPaso = 9;
            Integer[] auxIdSitioPaso = {18, 189};
            String[] auxNombreSitiosPaso = {"Donde Maira", "Zonafrancarestbar"};
            String[] auxDireccionSitiosPaso = {"Intesección entre calle 2a y calle 2.", "Calle 3"};
            String[] auxContenidoSitiosPaso = {"Un lugar agradable, buena música, ambiente en su mayoría de vallenato puro.",
                    "llega a El Paso-Cesar Resto-Bar Zona Franca y disfruta en familia o amigos."};
            Integer[] auxImagenesSitioPaso = {R.drawable.rest_elpaso_donde, R.drawable.rest_elpaso_zona};
            for (int j = 0; j < auxNombreSitiosPaso.length; j++) {

                final Integer auxIdSitio = auxIdSitioPaso[j];
                final String auxNameSitio = auxNombreSitiosPaso[j];
                final String auxDireSitio = auxDireccionSitiosPaso[j];
                final String auxContenidoSitio = auxContenidoSitiosPaso[j];
                final Integer auxImagenSitio = auxImagenesSitioPaso[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdPaso + "');";
                database.execSQL(insert);
            }
            //endregion

            //10-GAMARRA
            //region Restaurantes Gamarra
            int auxIdGamarra = 10;
            Integer[] auxIdSitioGamarra = {19, 78};
            String[] auxNombreSitiosGamarra = {"Donde Helio", "Terra & Mar"};
            String[] auxDireccionSitiosGamarra = {"Plaza central", "Gamarra-Aguachica"};
            String[] auxContenidoSitiosGamarra = {"Un lugar agradable, buena música, ambiente en su mayoría de vallenato puro.",
                    "Buena atención y excelente servicio, sitio adecuado para que pases un rato agradable en familia."};
            Integer[] auxImagenesSitioGamarra = {R.drawable.rest_gamarra_donde, R.drawable.rest_gamarra_tierra};
            for (int j = 0; j < auxNombreSitiosGamarra.length; j++) {

                final Integer auxIdSitio = auxIdSitioGamarra[j];
                final String auxNameSitio = auxNombreSitiosGamarra[j];
                final String auxDireSitio = auxDireccionSitiosGamarra[j];
                final String auxContenidoSitio = auxContenidoSitiosGamarra[j];
                final Integer auxImagenSitio = auxImagenesSitioGamarra[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdGamarra + "');";
                database.execSQL(insert);
            }
            //endregion

            //11-GONZALEZ
            //region Restaurantes Gonzalez
            int auxIdGonzalez = 11;
            Integer[] auxIdSitioGonzalez = {20};
            String[] auxNombreSitiosGonzalez = {"La Terraza de Checho"};
            String[] auxDireccionSitiosGonzalez = {"Calle 2 #3-25"};
            String[] auxContenidoSitiosGonzalez = {" "};
            Integer[] auxImagenesSitioGonzalez = {R.drawable.rest_gonzalez_terraza};
            for (int j = 0; j < auxNombreSitiosGonzalez.length; j++) {

                final Integer auxIdSitio = auxIdSitioGonzalez[j];
                final String auxNameSitio = auxNombreSitiosGonzalez[j];
                final String auxDireSitio = auxDireccionSitiosGonzalez[j];
                final String auxContenidoSitio = auxContenidoSitiosGonzalez[j];
                final Integer auxImagenSitio = auxImagenesSitioGonzalez[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdGonzalez + "');";
                database.execSQL(insert);
            }
            //endregion

            //12-LA GLORIA

            //13- LA JAGUA DE IBIRICO
            //region Restaurantes Jagua
            int auxIdJagua = 13;
            Integer[] auxIdSitioJagua = {21};
            String[] auxNombreSitiosJagua = {"Restaurante Donde Alfonso"};
            String[] auxDireccionSitiosJagua = {"Calle 1 # 11 – 58"};
            String[] auxContenidoSitiosJagua = {" "};
            Integer[] auxImagenesSitioJagua = {R.drawable.rest_lajagua_donde};
            for (int j = 0; j < auxNombreSitiosJagua.length; j++) {

                final Integer auxIdSitio = auxIdSitioJagua[j];
                final String auxNameSitio = auxNombreSitiosJagua[j];
                final String auxDireSitio = auxDireccionSitiosJagua[j];
                final String auxContenidoSitio = auxContenidoSitiosJagua[j];
                final Integer auxImagenSitio = auxImagenesSitioJagua[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdJagua + "');";
                database.execSQL(insert);
            }
            //endregion

            //14- LA PAZ
            //region Restaurantes la Paz
            int auxIdPaz = 14;
            Integer[] auxIdSitioPaz = {22};
            String[] auxNombreSitiosPaz = {"La Paz Carne Asada"};
            String[] auxDireccionSitiosPaz = {"Transversal 4 #6–74."};
            String[] auxContenidoSitiosPaz = {" "};
            Integer[] auxImagenesSitioPaz = {R.drawable.rest_lapaz_carne};
            for (int j = 0; j < auxNombreSitiosPaz.length; j++) {

                final Integer auxIdSitio = auxIdSitioPaz[j];
                final String auxNameSitio = auxNombreSitiosPaz[j];
                final String auxDireSitio = auxDireccionSitiosPaz[j];
                final String auxContenidoSitio = auxContenidoSitiosPaz[j];
                final Integer auxImagenSitio = auxImagenesSitioPaz[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdPaz + "');";
                database.execSQL(insert);
            }
            //endregion

            //15- LA MANAURE
            //region Restaurantes Manaure
            int auxIdManaure = 15;
            Integer[] auxIdSitioManaure = {23};
            String[] auxNombreSitiosManaure = {"Brasa Manaurera"};
            String[] auxDireccionSitiosManaure = {"Calle 2 #6–15."};
            String[] auxContenidoSitiosManaure = {" "};
            Integer[] auxImagenesSitioManaure = {R.drawable.rest_manaure_brasa};
            for (int j = 0; j < auxNombreSitiosManaure.length; j++) {

                final Integer auxIdSitio = auxIdSitioManaure[j];
                final String auxNameSitio = auxNombreSitiosManaure[j];
                final String auxDireSitio = auxDireccionSitiosManaure[j];
                final String auxContenidoSitio = auxContenidoSitiosManaure[j];
                final Integer auxImagenSitio = auxImagenesSitioManaure[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdManaure + "');";
                database.execSQL(insert);
            }
            //endregion

            //16- PAILITAS
            //region Restaurantes Pailitas
            int auxIdPailitas = 16;
            Integer[] auxIdSitioPailitas = {24};
            String[] auxNombreSitiosPailitas = {"Rancho Grande"};
            String[] auxDireccionSitiosPailitas = {"Carrera 9 #8–30"};
            String[] auxContenidoSitiosPailitas = {" "};
            Integer[] auxImagenesSitioPailitas = {R.drawable.rest_pailitas_rancho};
            for (int j = 0; j < auxNombreSitiosPailitas.length; j++) {

                final Integer auxIdSitio = auxIdSitioPailitas[j];
                final String auxNameSitio = auxNombreSitiosPailitas[j];
                final String auxDireSitio = auxDireccionSitiosPailitas[j];
                final String auxContenidoSitio = auxContenidoSitiosPailitas[j];
                final Integer auxImagenSitio = auxImagenesSitioPailitas[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdPailitas + "');";
                database.execSQL(insert);
            }
            //endregion

            //17- PELAYA
            //region Restaurantes Pelaya
            int auxIdPelaya = 17;
            Integer[] auxIdSitioPelaya = {25};
            String[] auxNombreSitiosPelaya = {"El Punto Del Sabor"};
            String[] auxDireccionSitiosPelaya = {"Calle 11 entre calle 4 y 6A"};
            String[] auxContenidoSitiosPelaya = {" "};
            Integer[] auxImagenesSitioPelaya = {R.drawable.rest_pelaya_punto};
            for (int j = 0; j < auxNombreSitiosPelaya.length; j++) {

                final Integer auxIdSitio = auxIdSitioPelaya[j];
                final String auxNameSitio = auxNombreSitiosPelaya[j];
                final String auxDireSitio = auxDireccionSitiosPelaya[j];
                final String auxContenidoSitio = auxContenidoSitiosPelaya[j];
                final Integer auxImagenSitio = auxImagenesSitioPelaya[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdPelaya + "');";
                database.execSQL(insert);
            }
            //endregion

            //18- PUEBLO BELLO
            //region Restaurantes Pueblo Bello
            int auxIdPuebloBello = 18;
            Integer[] auxIdSitioPuebloBello = {26};
            String[] auxNombreSitiosPuebloBello = {"Mi Pueblo Bello"};
            String[] auxDireccionSitiosPuebloBello = {"Avenida Paz P Bello"};
            String[] auxContenidoSitiosPuebloBello = {"Excelente lugar acogedor, romántico, toda una sorpresa para los visitantes, un muy buen ejemplo para imitar en tantos municipios de la costa"};
            Integer[] auxImagenesSitioPuebloBello = {R.drawable.rest_pueblobello_pueblo};
            for (int j = 0; j < auxNombreSitiosPuebloBello.length; j++) {

                final Integer auxIdSitio = auxIdSitioPuebloBello[j];
                final String auxNameSitio = auxNombreSitiosPuebloBello[j];
                final String auxDireSitio = auxDireccionSitiosPuebloBello[j];
                final String auxContenidoSitio = auxContenidoSitiosPuebloBello[j];
                final Integer auxImagenSitio = auxImagenesSitioPuebloBello[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdPuebloBello + "');";
                database.execSQL(insert);
            }
            //endregion

            //19- RIO DE ORO
            //region Restaurantes Rio de oro
            int auxIdRio = 19;
            Integer[] auxIdSitioRio = {27};
            String[] auxNombreSitiosRio = {"El Faro"};
            String[] auxDireccionSitiosRio = {"Calle 3"};
            String[] auxContenidoSitiosRio = {" "};
            Integer[] auxImagenesSitioRio = {R.drawable.rest_riodeoro_faro};
            for (int j = 0; j < auxNombreSitiosRio.length; j++) {

                final Integer auxIdSitio = auxIdSitioRio[j];
                final String auxNameSitio = auxNombreSitiosRio[j];
                final String auxDireSitio = auxDireccionSitiosRio[j];
                final String auxContenidoSitio = auxContenidoSitiosRio[j];
                final Integer auxImagenSitio = auxImagenesSitioRio[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdRio + "');";
                database.execSQL(insert);
            }
            //endregion

            //20- SAN ALBERTO
            //region Restaurantes San Alberto
            int auxIdSanAlberto = 20;
            Integer[] auxIdSitioSanAlberto = {28};
            String[] auxNombreSitiosSanAlberto = {"la Hormiga"};
            String[] auxDireccionSitiosSanAlberto = {"Calle 3 #2–24"};
            String[] auxContenidoSitiosSanAlberto = {" "};
            Integer[] auxImagenesSitioSanAlberto = {R.drawable.rest_sanalberto_hormiga};
            for (int j = 0; j < auxNombreSitiosSanAlberto.length; j++) {

                final Integer auxIdSitio = auxIdSitioSanAlberto[j];
                final String auxNameSitio = auxNombreSitiosSanAlberto[j];
                final String auxDireSitio = auxDireccionSitiosSanAlberto[j];
                final String auxContenidoSitio = auxContenidoSitiosSanAlberto[j];
                final Integer auxImagenSitio = auxImagenesSitioSanAlberto[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdSanAlberto + "');";
                database.execSQL(insert);
            }
            //endregion

            //21- SAN DIEGO
            //region Restaurantes San Diego
            int auxIdSanDiego = 21;
            Integer[] auxIdSitioSanDiego = {29};
            String[] auxNombreSitiosSanDiego = {"La Lule"};
            String[] auxDireccionSitiosSanDiego = {"Carrera 7 #576"};
            String[] auxContenidoSitiosSanDiego = {" "};
            Integer[] auxImagenesSitioSanDiego = {R.drawable.rest_sandiego_lule};
            for (int j = 0; j < auxNombreSitiosSanDiego.length; j++) {

                final Integer auxIdSitio = auxIdSitioSanDiego[j];
                final String auxNameSitio = auxNombreSitiosSanDiego[j];
                final String auxDireSitio = auxDireccionSitiosSanDiego[j];
                final String auxContenidoSitio = auxContenidoSitiosSanDiego[j];
                final Integer auxImagenSitio = auxImagenesSitioSanDiego[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdSanDiego + "');";
                database.execSQL(insert);
            }
            //endregion

            //22- SAN MARTÍN
            //region Restaurantes San Martin
            int auxIdSanMartin = 22;
            Integer[] auxIdSitioSanMartin = {30};
            String[] auxNombreSitiosSanMartin = {"El Punto Del Sabor"};
            String[] auxDireccionSitiosSanMartin = {"Carrera 7 #16–45"};
            String[] auxContenidoSitiosSanMartin = {" "};
            Integer[] auxImagenesSitioSanMartin = {R.drawable.rest_sanmartin_punto};
            for (int j = 0; j < auxNombreSitiosSanMartin.length; j++) {

                final Integer auxIdSitio = auxIdSitioSanMartin[j];
                final String auxNameSitio = auxNombreSitiosSanMartin[j];
                final String auxDireSitio = auxDireccionSitiosSanMartin[j];
                final String auxContenidoSitio = auxContenidoSitiosSanMartin[j];
                final Integer auxImagenSitio = auxImagenesSitioSanMartin[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdSanMartin + "');";
                database.execSQL(insert);
            }
            //endregion

            //23- TAMALAMEQUE
            //region Restaurantes Tamalameque
            int auxIdTamalameque = 23;
            Integer[] auxIdSitioTamalameque = {31};
            String[] auxNombreSitiosTamalameque = {"El buen sabor de mi morenita"};
            String[] auxDireccionSitiosTamalameque = {"Calle 1 #3–1"};
            String[] auxContenidoSitiosTamalameque = {" "};
            Integer[] auxImagenesSitioTamalameque = {R.drawable.rest_tamalameque_sabor};
            for (int j = 0; j < auxNombreSitiosTamalameque.length; j++) {

                final Integer auxIdSitio = auxIdSitioTamalameque[j];
                final String auxNameSitio = auxNombreSitiosTamalameque[j];
                final String auxDireSitio = auxDireccionSitiosTamalameque[j];
                final String auxContenidoSitio = auxContenidoSitiosTamalameque[j];
                final Integer auxImagenSitio = auxImagenesSitioTamalameque[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdTamalameque + "');";
                database.execSQL(insert);
            }
            //endregion

            //24-VALLEDUPAR
            //region Restaurantes Valledupar
            int auxIdValledupar = 24;
            Integer[] auxIdSitioValledupar = {32, 33, 34, 87, 98, 99};
            String[] auxNombreSitiosValledupar = {"Montacarga Cañahuate", "Compae Chipuco", "María Namen", "Santa Brasa", "Montacargas del Norte"};
            String[] auxDireccionSitiosValledupar = {"Carrera 13B #8-65", "Carrera 6 #16–24", "Calle 6 #9–24", "Carrera 11 a #9d-06", "Carrera 19 #5-30"};
            String[] auxContenidoSitiosValledupar = {"Montacarga cañahuate es un restaurante que ofrece comida típica colombiana, se pueden realizar reservas, tienen servicio de Wi-Fi gratis, tienen un rango de precios que van desde 7.547 pesos hasta los 34.000 pesos, un buen lugar para conocer la gastronomía vallenata.",
                    "Compae chipuco es un acogedor restaurante fundado en 28 de enero 2006, al estilo de un patio típico del Valle del cacique Upar, decorado al estilo colonial, la tradición vive en cada rincón, objeto, pintura y detalles que enriquecen nuestro bagaje cultural donde podrá apreciar la calidad y el toque especial de los sabores de Valledupar y sus alrededores. Es una fiesta en el paladar, un juego de pupilas que traslada a turistas y viajeros a aquel viejo Valledupar lleno de costumbres, historias y leyendas a través de los versos cantados en las notas de la caja, guacharaca y acordeón.",
                    "El restaurante maría namen, ofrece asados, conocido por ser un restaurante típico de la región, con una excelente atención al público logra destacar, de igual forma con sus precios asequibles a sus clientes.",
                    "Posee un encantador ambiente y sofisticada decoración, cuenta con salón vip, terraza exterior, todo muy bien organizado. La satisfacción del cliente es uno de los principales objetivos de Santa Brasa, y su servicio simplifica mucho esta tarea, pues se centra en prestar una excelente atención, contando con meseros atentos a las peticiones los clientes . Los precios de cada comida son razonables, ofrece deliciosos platos, con una creatividad peculiar en su decoración, además, ofrece platos típicos de la region. Este lugar ha logrado un 4,6 según el baremo de valoraciones de Google.",
                    "Valledupar Colombia. Entrega a domicilio, Comida para llevar, Asientos al aire libre, Asiento, Acceso para silla de ruedas, Servicio de mesa. Sus almuerzo generosos en cuanto a calidad con porciones muy grandes y precios muy adecuado . Posee  platos tipicos particulares (Asados variados, ricos jugos, entre otros). Considerado  uno de los mejores de Valledupar, desde el servicio hasta el pago."};
            Integer[] auxImagenesSitioValledupar = {R.drawable.rest_valledupar_montacarga, R.drawable.rest_valledupar_chipuco, R.drawable.rest_valledupar_namen, R.drawable.rest_valledupar_santa, R.drawable.rest_valledupar_montacarganorte};
            for (int j = 0; j < auxNombreSitiosValledupar.length; j++) {

                final Integer auxIdSitio = auxIdSitioValledupar[j];
                final String auxNameSitio = auxNombreSitiosValledupar[j];
                final String auxDireSitio = auxDireccionSitiosValledupar[j];
                final String auxContenidoSitio = auxContenidoSitiosValledupar[j];
                final Integer auxImagenSitio = auxImagenesSitioValledupar[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoRestaurante + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdValledupar + "');";
                database.execSQL(insert);
            }
            //endregion

            //endregion

            String TipoMuseo = "Museo";
            //region MUSEOS
            //0-AGUACHICA
            //1-CODAZZI
            //2-ASTREA
            //3-BECERRIL
            //4-BOSCONIA
            //5-CHIMICHAGUA
            //6-CHIRIGUANÁ
            //7-CURUMANÍ
            //region Museos Curumani
            int auxIdCurumaniMuseos = 7;
            Integer[] auxIdSitioCurumaniMuseos = {35};
            String[] auxNombreSitiosCurumaniMuseos = {"Macu"};
            String[] auxDireccionSitiosCurumaniMuseos = {"Carrera 14 #6–1"};
            String[] auxContenidoSitiosCurumaniMuseos = {"El MACU, surge como una iniciativa de la licenciada Nohora, que desde 1980, sembró la idea de crear el museo arqueológico de curumaní, para que los actuales habitantes de la región comprometieran con ella su admiración y respeto por la cultura precolombina del pueblo malibú.\n" +
                    "Hoy día el MAC   se constituye en su mayor legado histórico y cultural que nos habla de nuestro pasado y su mundo mágico real.\n"};
            Integer[] auxImagenesSitioCurumaniMuseos = {R.drawable.museo_macu_curumani};
            for (int j = 0; j < auxNombreSitiosCurumaniMuseos.length; j++) {

                final Integer auxIdSitio = auxIdSitioCurumaniMuseos[j];
                final String auxNameSitio = auxNombreSitiosCurumaniMuseos[j];
                final String auxDireSitio = auxDireccionSitiosCurumaniMuseos[j];
                final String auxContenidoSitio = auxContenidoSitiosCurumaniMuseos[j];
                final Integer auxImagenSitio = auxImagenesSitioCurumaniMuseos[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoMuseo + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdCurumaniMuseos + "');";
                database.execSQL(insert);
            }
            //endregion
            //8-EL COPEY
            //9-El PASO
            //10-GAMARRA
            //11-GONZALEZ
            //12-LA GLORIA
            //13- LA JAGUA DE IBIRICO
            //14- LA PAZ
            //15- LA MANAURE
            //16- PAILITAS
            //17- PELAYA
            //18- PUEBLO BELLO
            //region Museo PuebloBello
            int auxIdPuebloBelloMuseo = 18;
            Integer[] auxIdSitioPuebloBelloMuseo = {36};
            String[] auxNombreSitiosPuebloBelloMuseo = {"Nabusímake"};
            String[] auxDireccionSitiosPuebloBelloMuseo = {"Pueblo bello, Cesar – Colombia"};
            String[] auxContenidoSitiosPuebloBelloMuseo = {"Pueblo sagrado de los indígenas Arhuacos, que lleva su nombre como alusión al lugar donde nace el sol, se encuentra enclavado en la Sierra Nevada a 1.800 m.s.n.m, es un lugar maravilloso, exótico, lleno de energías de la conjugación de la cultura y la naturaleza, es un lugar de paz, de paisajes que invitan a no querer salir de las tierras de nuestros antepasados. Nabusímake queda a medio camino entre Pueblo Bello y el Salto de Atisereche, siendo este último un lugar de ritual y pagamento de los indios Arhuacos. Se proponen actividades relacionadas al turismo arqueológico y el etnoturismo. Visitar al mamo, recorrer el pueblito de casas y bohíos con paredes de barro pintadas de blanco, bases en piedras sacadas del río, acompañado por un indígena quien puede ser el guía que explique la importancia de cada lugar para la etnia, y en los largos caminos hacia el Pozo del Diablo, cuente las fantásticas historias de cada una de sus creencias, es lo que se espera al llegar a ese maravilloso lugar conectado espiritualmente con nuestros ancestros."};
            Integer[] auxImagenesSitioPuebloBelloMuseo = {R.drawable.museo_nabusimake_pueblobello};
            for (int j = 0; j < auxNombreSitiosPuebloBelloMuseo.length; j++) {

                final Integer auxIdSitio = auxIdSitioPuebloBelloMuseo[j];
                final String auxNameSitio = auxNombreSitiosPuebloBelloMuseo[j];
                final String auxDireSitio = auxDireccionSitiosPuebloBelloMuseo[j];
                final String auxContenidoSitio = auxContenidoSitiosPuebloBelloMuseo[j];
                final Integer auxImagenSitio = auxImagenesSitioCurumaniMuseos[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoMuseo + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdPuebloBelloMuseo + "');";
                database.execSQL(insert);
            }
            //endregion
            //19- RIO DE ORO
            //20- SAN ALBERTO
            //21- SAN DIEGO
            //22- SAN MARTÍN
            //23- TAMALAMEQUE
            //24-VALLEDUPAR
            //region Museos Valledupar
            int auxIdValleduparMuseo = 24;
            Integer[] auxIdSitioValleduparMuseo = {37, 38, 39};
            String[] auxNombreSitiosValleduparMuseo = {"Museo del Acordeon", "El Cuartico", "Museo Galería de Sayco"};
            String[] auxDireccionSitiosValleduparMuseo = {"Carrera 17 #9A -18", "Carrera 14 A #9C-50 ", "Carrera 5 #13C–40"};
            String[] auxContenidoSitiosValleduparMuseo = {"La casa Museo del Acordeón ofrece un recorrido guiado en el que se cuenta la historia del principal instrumento musical que le da vida a la música vallenata y se muestra las piezas que alberga el museo. El ingreso al museo tiene un costo/donación de $ 20.000, para el mantenimiento y preservación del recinto. Es necesario llamar para reservar el cupo/ingreso al museo.\n" +
                    "Horario\n" +
                    "Dos entradas al día. Recorridos de 45 minutos a las 10:00 am y 3:00 pm.\n",
                    "Compae chipuco es un acogedor restaurante fundado en 28 de enero 2006, al estilo de un patio típico del Valle del cacique Upar, decorado al estilo colonial, la tradición vive en cada rincón, objeto, pintura y detalles que enriquecen nuestro bagaje cultural donde podrá apreciar la calidad y el toque especial de los sabores de Valledupar y sus alrededores. Es una fiesta en el paladar, un juego de pupilas que traslada a turistas y viajeros a aquel viejo Valledupar lleno de costumbres, historias y leyendas a través de los versos cantados en las notas de la caja, guacharaca y acordeón.Casa ubicada en el barrio San Joaquín de Valledupar, donde puede recrear el modo de vida de una familia vallenata del siglo XX. Allí se guarda la historia del Valledupar de antaño. Es un lugar donde se conserva la esencia, las costumbres y tradiciones del viejo Valledupar. Construido con madera, techo de teja y piso de barro; Centro de Memoria para las futuras generaciones\n" +
                            "\n" +
                            "Horario\n" +
                            "\n" +
                            "Lunes a viernes de 3:00 a 7:00 p.m.\nReconocimiento a la cultura vallenata donde se exhiben galardones, instrumentos musicales y objetos personales de los mayores exponentes del género vallenato como Rafael Orozco, Jorge Oñate, Diomedes Diaz y demás.\n" +
                            "Horario\n" +
                            "Lunes a viernes de 7:30 a 12:00 y 14:00 a 17:30\n", "hola", "hola"};
            Integer[] auxImagenesSitioValleduparMuseo = {R.drawable.museo_acordeon_valledupar, R.drawable.museo_cuartico_valledupar, R.drawable.museo_sayco_valledupar};
            for (int j = 0; j < auxNombreSitiosValleduparMuseo.length; j++) {

                final Integer auxIdSitio = auxIdSitioValleduparMuseo[j];
                final String auxNameSitio = auxNombreSitiosValleduparMuseo[j];
                final String auxDireSitio = auxDireccionSitiosValleduparMuseo[j];
                final String auxContenidoSitio = auxContenidoSitiosValleduparMuseo[j];
                final Integer auxImagenSitio = auxImagenesSitioValleduparMuseo[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoMuseo + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdValleduparMuseo + "');";
                database.execSQL(insert);
            }
            //endregion

            //endregion

            String TipoHoteles = "Hotel";
            //region HOTELES

            //0-AGUACHICA
            //region Hotel Aguachica
            int auxIdAguachicaHotel = 0;
            Integer[] auxIdSitioAguachicaHotel = {40, 433};
            String[] auxNombreSitiosAguachicaHotel = {"Prado Country", "Casino Royal Caribe"};
            String[] auxDireccionSitiosAguachicaHotel = {"Carrera 30 # 6-3", "Calle 3 #15-62"};
            String[] auxContenidoSitiosAguachicaHotel = {"El Hotel Prado Country cuenta con restaurante, bar, salón compartido y jardín en Aguachica. Cuenta con recepción 24 horas, servicio de habitaciones y Wifi gratuita en todas las instalaciones. Se ofrece aparcamiento privado por un suplemento.\n" +
                    "Todas las habitaciones del hotel están equipadas con escritorio, TV de pantalla plana y baño privado. Los alojamientos del Hotel Prado Country cuentan con aire acondicionado y armario.\n" +
                    "Precios\n" +
                    "Habitación para dos personas por una noche oscila entre 80.000 pesos.\n",
                    "Ofrece alojamiento en Aguachica. Todas las habitaciones cuentan con TV de pantalla plana con canales por cable y baño privado. Hay una terraza, conexión Wifi-gratuita y aparcamiento privado gratuito.\n" +
                            "Las habitaciones están equipadas con patio. Todas las habitaciones del HOTEL CASINO ROYAL CARIBE tienen aire acondicionado y armario.\n" +
                            "En la recepción se proporciona información sobre la zona.\n" +
                            "Precios\n" +
                            "Habitación para dos personas el precio oscila entre los 65.000 pesos.\n"};
            Integer[] auxImagenesSitioAguachicaHotel = {R.drawable.hotel_prado_aguachica, R.drawable.hotel_royal_aguachica};
            for (int j = 0; j < auxNombreSitiosAguachicaHotel.length; j++) {

                final Integer auxIdSitio = auxIdSitioAguachicaHotel[j];
                final String auxNameSitio = auxNombreSitiosAguachicaHotel[j];
                final String auxDireSitio = auxDireccionSitiosAguachicaHotel[j];
                final String auxContenidoSitio = auxContenidoSitiosAguachicaHotel[j];
                final Integer auxImagenSitio = auxImagenesSitioAguachicaHotel[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoHoteles + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdAguachicaHotel + "');";
                database.execSQL(insert);
            }
            //endregion
            //1-CODAZZI
            //region Hotel Codazzi
            int auxIdCodazziHotel = 1;
            Integer[] auxIdSitioCodazziHotel = {41};
            String[] auxNombreSitiosCodazziHotel = {"Europa"};
            String[] auxDireccionSitiosCodazziHotel = {"Calle 12 A #15–90"};
            String[] auxContenidoSitiosCodazziHotel = {"Este hotel cuenta con aire acondicionado en todas las habitaciones, televisión, ubicado en la zona céntrica del municipio."};
            Integer[] auxImagenesSitioCodazziHotel = {R.drawable.hotel_europa_codazzi};
            for (int j = 0; j < auxNombreSitiosCodazziHotel.length; j++) {

                final Integer auxIdSitio = auxIdSitioCodazziHotel[j];
                final String auxNameSitio = auxNombreSitiosCodazziHotel[j];
                final String auxDireSitio = auxDireccionSitiosCodazziHotel[j];
                final String auxContenidoSitio = auxContenidoSitiosCodazziHotel[j];
                final Integer auxImagenSitio = auxImagenesSitioCodazziHotel[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoHoteles + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdCodazziHotel + "');";
                database.execSQL(insert);
            }
            //endregion
            //2-ASTREA
            //region Hotel Astrea
            int auxIdAstreaHotel = 2;
            Integer[] auxIdSitioAstreaHotel = {42};
            String[] auxNombreSitiosAstreaHotel = {"El Carrizal"};
            String[] auxDireccionSitiosAstreaHotel = {"Calle 5 #41-20"};
            String[] auxContenidoSitiosAstreaHotel = {"Habitaciones cómodas, confortables, con aire acondicionado."};
            Integer[] auxImagenesSitioAstreaHotel = {R.drawable.hotel_carrizal_astrea};
            for (int j = 0; j < auxNombreSitiosAstreaHotel.length; j++) {

                final Integer auxIdSitio = auxIdSitioAstreaHotel[j];
                final String auxNameSitio = auxNombreSitiosAstreaHotel[j];
                final String auxDireSitio = auxDireccionSitiosAstreaHotel[j];
                final String auxContenidoSitio = auxContenidoSitiosAstreaHotel[j];
                final Integer auxImagenSitio = auxImagenesSitioAstreaHotel[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoHoteles + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdAstreaHotel + "');";
                database.execSQL(insert);
            }
            //endregion
            //3-BECERRIL
            //region Hotel Becerril
            int auxIdBecerrilHotel = 3;
            Integer[] auxIdSitioBecerrilHotel = {43};
            String[] auxNombreSitiosBecerrilHotel = {"las Delicias"};
            String[] auxDireccionSitiosBecerrilHotel = {"Carrera 5 #5-27"};
            String[] auxContenidoSitiosBecerrilHotel = {"La mejor ubicación de becerril, frente al nuevo parque principal y totalmente acreditado. Cuenta con 13 habitaciones amobladas, aire acondicionado y wifi, servicio de restaurante y transformador propio."};
            Integer[] auxImagenesSitioBecerrilHotel = {R.drawable.hotel_becerril_lasdelicias};
            for (int j = 0; j < auxNombreSitiosBecerrilHotel.length; j++) {

                final Integer auxIdSitio = auxIdSitioBecerrilHotel[j];
                final String auxNameSitio = auxNombreSitiosBecerrilHotel[j];
                final String auxDireSitio = auxDireccionSitiosBecerrilHotel[j];
                final String auxContenidoSitio = auxContenidoSitiosBecerrilHotel[j];
                final Integer auxImagenSitio = auxImagenesSitioBecerrilHotel[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoHoteles + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdBecerrilHotel + "');";
                database.execSQL(insert);
            }
            //endregion
            //4-BOSCONIA
            //5-CHIMICHAGUA
            //6-CHIRIGUANÁ
            //7-CURUMANÍ
            //8-EL COPEY
            //9-El PASO
            //10-GAMARRA
            //11-GONZALEZ
            //12-LA GLORIA
            //13- LA JAGUA DE IBIRICO
            //14- LA PAZ
            //15- LA MANAURE
            //region Hotel Manaure
            int auxIdManaureHotel = 15;
            Integer[] auxIdSitioManaureHotel = {44};
            String[] auxNombreSitiosManaureHotel = {"El Tucán Casa Campo"};
            String[] auxDireccionSitiosManaureHotel = {"El tucán Ecoturismo Km 2 Manaure A Sabana Rubia"};
            String[] auxContenidoSitiosManaureHotel = {"El Tucán Casa Campo se encuentra en Manaure Balcón del Cesar, en la región de Cesar, y ofrece jardín. El establecimiento se encuentra a 38 km de Valledupar y proporciona aparcamiento privado gratuito.\n" +
                    "Esta casa rural cuenta con balcón, vistas al jardín, 3 dormitorios, sala de estar, TV de pantalla plana vía satélite, cocina equipada y 2 baños con bañera de hidromasaje y bañera o ducha.\n" +
                    "Precios\n" +
                    "Esta casa tiene una capacidad para 12 personas y una noche cuesta 450.000\n"};
            Integer[] auxImagenesSitioManaureHotel = {R.drawable.hotel_tucan_manaure};
            for (int j = 0; j < auxNombreSitiosManaureHotel.length; j++) {

                final Integer auxIdSitio = auxIdSitioManaureHotel[j];
                final String auxNameSitio = auxNombreSitiosManaureHotel[j];
                final String auxDireSitio = auxDireccionSitiosManaureHotel[j];
                final String auxContenidoSitio = auxContenidoSitiosManaureHotel[j];
                final Integer auxImagenSitio = auxImagenesSitioManaureHotel[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoHoteles + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdManaureHotel + "');";
                database.execSQL(insert);
            }
            //endregion
            //16- PAILITAS
            //17- PELAYA
            //18- PUEBLO BELLO
            //19- RIO DE ORO
            //20- SAN ALBERTO
            //21- SAN DIEGO
            //22- SAN MARTÍN
            //23- TAMALAMEQUE
            //24-VALLEDUPAR
            //region Hotel Valledupar
            int auxIdValleduparHotel = 24;
            Integer[] auxIdSitioValleduparHotel = {45, 46, 47};
            String[] auxNombreSitiosValleduparHotel = {"Hotel Juglar", "Hilton Valledupar", "Sonesta Valledupar"};
            String[] auxDireccionSitiosValleduparHotel = {"Carrera 18D #22C–25", "Calle 30 #6a -133", "Diagonal 10 # 6N – 15"};
            String[] auxContenidoSitiosValleduparHotel = {"El Hotel Juglar está situado en Valledupar y dispone de bar, terraza y Wifi gratuita en todas las instalaciones. Todas las habitaciones cuentan con TV de pantalla plana con canales por cable y baño privado. El alojamiento ofrece entretenimiento nocturno y recepción 24 horas.\n" +
                    "Todas las habitaciones tienen balcón. Todas las habitaciones del Hotel Juglar cuentan con aire acondicionado y armario.\n" +
                    "El aeropuerto más cercano es el Alfonso López Pumarejo, situado a 3,9 km. El establecimiento ofrece un servicio de enlace con el aeropuerto por un suplemento.\n" +
                    "Precios\n" +
                    "Una habitación para dos personas tiene el costo de 75.000 pesos por noche.\n",

                    "El Hampton By Hilton Valledupar, situado a pocos minutos del distrito financiero y la zona de ocio de Valledupar, cuenta con una bañera de hidromasaje y alberga un centro de fitness. El hotel también tiene una terraza y sirve un desayuno diario gratuito. La conexión Wifi está disponible de forma gratuita.\n" +
                            "Las habitaciones cuentan con TV de pantalla plana, escritorio, nevera, caja fuerte, utensilios de planchado, aire acondicionado y baño privado con ducha y secador de pelo.\n" +
                            "El hotel Hampton By Hilton de Valledupar dispone de recepción 24 horas, centro de negocios y salas para banquetes. También hay un bar y se ofrece un servicio de lavandería. Además, se proporciona aparcamiento gratuito.\n" +
                            "Precios\n" +
                            "Una habitación para dos personas la noche oscila entre 180.000 pesos\n",

                    "Este hotel moderno se encuentra a 4 km de la Plaza Alfonso López y cuenta con vistas panorámicas a la Sierra Nevada de Santa Marta. Hay Wifi y aparcamiento gratuitos.\n" +
                            "Las habitaciones del Sonesta Valledupar, de 5 estrellas, son amplias y tienen aire acondicionado, TV por cable, teléfono, minibar, tonos neutros, ventanas amplias y escritorio con silla ergonómica.\n" +
                            "El restaurante Mamankana ofrece desayuno buffet y especialidades colombianas caribeñas a la carta en una sala luminosa con vistas al jardín y decoración típica de la zona. El bar Nabusímake prepara bebidas exóticas, como el Martini de tamarindo, y tiene vistas a la Sierra Nevada de Santa Marta.\n" +
                            "Precios\n" +
                            "Una habitación para dos personas tiene un costo de 215.000 pesos por una noche\n"};
            Integer[] auxImagenesSitioValleduparHotel = {R.drawable.hotel_juglar_valledupar, R.drawable.hotel_hampton_valledupar, R.drawable.hotel_sonesta_valledupar};
            for (int j = 0; j < auxNombreSitiosValleduparHotel.length; j++) {

                final Integer auxIdSitio = auxIdSitioValleduparHotel[j];
                final String auxNameSitio = auxNombreSitiosValleduparHotel[j];
                final String auxDireSitio = auxDireccionSitiosValleduparHotel[j];
                final String auxContenidoSitio = auxContenidoSitiosValleduparHotel[j];
                final Integer auxImagenSitio = auxImagenesSitioValleduparHotel[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoHoteles + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdValleduparHotel + "');";
                database.execSQL(insert);
            }
            //endregion

            //endregion

            String TipoBares = "Bares";
            //region BARES

            //0-AGUACHICA
            //1-CODAZZI
            //2-ASTREA
            //3-BECERRIL
            //4-BOSCONIA
            //5-CHIMICHAGUA
            //6-CHIRIGUANÁ
            //7-CURUMANÍ
            //8-EL COPEY
            //9-El PASO
            //10-GAMARRA
            //11-GONZALEZ
            //12-LA GLORIA
            //13- LA JAGUA DE IBIRICO
            //14- LA PAZ
            //15- LA MANAURE
            //16- PAILITAS
            //17- PELAYA
            //18- PUEBLO BELLO
            //19- RIO DE ORO
            //20- SAN ALBERTO
            //21- SAN DIEGO
            //22- SAN MARTÍN
            //23- TAMALAMEQUE
            //24-VALLEDUPAR
            //region Bares Valledupar
            int auxIdVBares = 24;
            Integer[] auxIdSitioVBares = {48};
            String[] auxNombreSitiosVBares = {"La placita Club"};
            String[] auxDireccionSitiosVBares = {"Calle 15 #6-70"};
            String[] auxContenidoSitiosVBares = {"Excelente lugar con ambiente de discoteca cuenta con restaurante y un patio parrandero que te hace" +
                    " sentir como en una parranda de Valledupar. Presentación de artistas nacionales e internacionales, parqueadero privado, cuenta con" +
                    " el paseo de la fama de músicos vallenatos."};
            Integer[] auxImagenesSitioVBares = {R.drawable.bar_placita_valledupar};
            for (int j = 0; j < auxNombreSitiosVBares.length; j++) {

                final Integer auxIdSitio = auxIdSitioVBares[j];
                final String auxNameSitio = auxNombreSitiosVBares[j];
                final String auxDireSitio = auxDireccionSitiosVBares[j];
                final String auxContenidoSitio = auxContenidoSitiosVBares[j];
                final Integer auxImagenSitio = auxImagenesSitioVBares[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoBares + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdVBares + "');";
                database.execSQL(insert);
            }
            //endregion

            //endregion

            String TipoOtros = "Otro";
            //region OTROS

            //0-AGUACHICA

            //1-CODAZZI

            //2-ASTREA

            //3-BECERRIL

            //4-BOSCONIA
            //5-CHIMICHAGUA
            //6-CHIRIGUANÁ
            //7-CURUMANÍ
            //8-EL COPEY
            //9-El PASO
            //10-GAMARRA
            //11-GONZALEZ
            //12-LA GLORIA
            //13- LA JAGUA DE IBIRICO
            //14- LA PAZ
            //15- LA MANAURE

            //16- PAILITAS
            //17- PELAYA
            //18- PUEBLO BELLO
            //19- RIO DE ORO
            //20- SAN ALBERTO
            //21- SAN DIEGO
            //22- SAN MARTÍN
            //23- TAMALAMEQUE
            //24-VALLEDUPAR
            //region Otros Valledupar
            int auxIdValleduparOtros = 24;
            Integer[] auxIdSitioValleduparOtros = {49};
            String[] auxNombreSitiosValleduparOtros = {"Roo Guatapuri "};
            String[] auxDireccionSitiosValleduparOtros = {"Valledupar - Colombia"};
            String[] auxContenidoSitiosValleduparOtros = {"Nace en la laguna Curigua, de la Sierra Nevada de Santa Marta, y" +
                    " en un vertiginoso descenso de 80 kilómetros entrega sus aguas a Valledupar. Su balneario Hurtado, de " +
                    "cristalinas aguas, genera un ambiente refrescante donde los turistas pueden bañarse; sus contorneadas rocas " +
                    "enmarcan paisajes que han contribuido a crear misteriosas leyendas y son fuente de inspiración de melodías del folclor vallenato\n" +
                    "Horario \n" +
                    "Hasta las 6:00 pm\n"};
            Integer[] auxImagenesSitioValleduparOtros = {R.drawable.otros_hurtado_valledupar};
            for (int j = 0; j < auxNombreSitiosValleduparOtros.length; j++) {

                final Integer auxIdSitio = auxIdSitioValleduparOtros[j];
                final String auxNameSitio = auxNombreSitiosValleduparOtros[j];
                final String auxDireSitio = auxDireccionSitiosValleduparOtros[j];
                final String auxContenidoSitio = auxContenidoSitiosValleduparOtros[j];
                final Integer auxImagenSitio = auxImagenesSitioValleduparOtros[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_SITIO
                        + " ("
                        + Utilidades.CAMPO_ID_SITIO + ", "
                        + Utilidades.CAMPO_NOMBRE_SITIO + ", "
                        + Utilidades.CAMPO_TIPO_SITIO + ", "
                        + Utilidades.CAMPO_DIRECCION_SITIO + ", "
                        + Utilidades.CAMPO_CONTENIDO_SITIO + ", "
                        + Utilidades.CAMPO_IMAGEN_SITIO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIOO + ")" +
                        " VALUES (" + auxIdSitio +
                        ", '" + auxNameSitio + "'" +
                        ", '" + TipoOtros + "'" +
                        ", '" + auxDireSitio + "'" +
                        ", '" + auxContenidoSitio + "'" +
                        ", " + auxImagenSitio +
                        ", '" + auxIdValleduparOtros + "');";
                database.execSQL(insert);
            }
            //endregion

            //endregion
            //Toast.makeText(BienvenidoActivity.this, "se agregaron sitios", Toast.LENGTH_LONG).show();

        } else {
            //Toast.makeText(BienvenidoActivity.this, "Ya esta Cargada la lista SITIOS", Toast.LENGTH_LONG).show();
        }
        database.close();
    }
    private void ConsultarRestaurantes() {
        //base de datos Local
        final String campoId = "32";
        final String nombre, ide, tipo, contenido, idmin, dire;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {campoId};

        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_ID_SITIO +
                    ", " + Utilidades.CAMPO_NOMBRE_SITIO +
                    ", " + Utilidades.CAMPO_TIPO_SITIO +
                    ", " + Utilidades.CAMPO_DIRECCION_SITIO +
                    ", " + Utilidades.CAMPO_CONTENIDO_SITIO +
                    ", " + Utilidades.CAMPO_ID_MUNICIPIOO +
                    " FROM " + Utilidades.TABLE_SITIO +
                    " WHERE " + Utilidades.CAMPO_ID_SITIO + "=? ", parametros);
            cursor.moveToFirst();
            ide = cursor.getString(0);
            nombre = cursor.getString(1);
            tipo = cursor.getString(2);
            dire = cursor.getString(3);
            contenido = cursor.getString(4);
            idmin = cursor.getString(5);


            // Toast.makeText(BienvenidoActivity.this, "se encontro id: " + ide, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro nombre: " + nombre, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro Tipo: " + tipo, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro dire: " + dire, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro contenido: " + contenido, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro idmun: " + idmin, Toast.LENGTH_LONG).show();
            cursor.close();
            //return true;
        } catch (Exception e) {
           // Toast.makeText(BienvenidoActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }
    }
    private void ConsultarMuseo() {
        //base de datos Local
        final String campoId = "35";
        final String nombre, ide, tipo, contenido, idmin, dire;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {campoId};

        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_ID_SITIO +
                    ", " + Utilidades.CAMPO_NOMBRE_SITIO +
                    ", " + Utilidades.CAMPO_TIPO_SITIO +
                    ", " + Utilidades.CAMPO_DIRECCION_SITIO +
                    ", " + Utilidades.CAMPO_CONTENIDO_SITIO +
                    ", " + Utilidades.CAMPO_ID_MUNICIPIOO +
                    " FROM " + Utilidades.TABLE_SITIO +
                    " WHERE " + Utilidades.CAMPO_ID_SITIO + "=? ", parametros);
            cursor.moveToFirst();
            ide = cursor.getString(0);
            nombre = cursor.getString(1);
            tipo = cursor.getString(2);
            dire = cursor.getString(3);
            contenido = cursor.getString(4);
            idmin = cursor.getString(5);


            //Toast.makeText(BienvenidoActivity.this, "se encontro id: " + ide, Toast.LENGTH_LONG).show();
            //  Toast.makeText(BienvenidoActivity.this, "se encontro nombre: " + nombre, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro Tipo: " + tipo, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro dire: " + dire, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro contenido: " + contenido, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro idmun: " + idmin, Toast.LENGTH_LONG).show();
            cursor.close();
            //return true;
        } catch (Exception e) {
            Toast.makeText(BienvenidoActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }
    }
    private void ConsultarHoteles() {
        //base de datos Local
        final String campoId = "40";
        final String nombre, ide, tipo, contenido, idmin, dire;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {campoId};

        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_ID_SITIO +
                    ", " + Utilidades.CAMPO_NOMBRE_SITIO +
                    ", " + Utilidades.CAMPO_TIPO_SITIO +
                    ", " + Utilidades.CAMPO_DIRECCION_SITIO +
                    ", " + Utilidades.CAMPO_CONTENIDO_SITIO +
                    ", " + Utilidades.CAMPO_ID_MUNICIPIOO +
                    " FROM " + Utilidades.TABLE_SITIO +
                    " WHERE " + Utilidades.CAMPO_ID_SITIO + "=? ", parametros);
            cursor.moveToFirst();
            ide = cursor.getString(0);
            nombre = cursor.getString(1);
            tipo = cursor.getString(2);
            dire = cursor.getString(3);
            contenido = cursor.getString(4);
            idmin = cursor.getString(5);


            // Toast.makeText(BienvenidoActivity.this, "se encontro id: " + ide, Toast.LENGTH_LONG).show();
            // Toast.makeText(BienvenidoActivity.this, "se encontro nombre: " + nombre, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro Tipo: " + tipo, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro dire: " + dire, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro contenido: " + contenido, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro idmun: " + idmin, Toast.LENGTH_LONG).show();
            cursor.close();
            //return true;
        } catch (Exception e) {
          //  Toast.makeText(BienvenidoActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }
    }
    private void ConsultarBares() {
        //base de datos Local
        final String campoId = "48";
        final String nombre, ide, tipo, contenido, idmin, dire;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {campoId};

        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_ID_SITIO +
                    ", " + Utilidades.CAMPO_NOMBRE_SITIO +
                    ", " + Utilidades.CAMPO_TIPO_SITIO +
                    ", " + Utilidades.CAMPO_DIRECCION_SITIO +
                    ", " + Utilidades.CAMPO_CONTENIDO_SITIO +
                    ", " + Utilidades.CAMPO_ID_MUNICIPIOO +
                    " FROM " + Utilidades.TABLE_SITIO +
                    " WHERE " + Utilidades.CAMPO_ID_SITIO + "=? ", parametros);
            cursor.moveToFirst();
            ide = cursor.getString(0);
            nombre = cursor.getString(1);
            tipo = cursor.getString(2);
            dire = cursor.getString(3);
            contenido = cursor.getString(4);
            idmin = cursor.getString(5);


            // Toast.makeText(BienvenidoActivity.this, "se encontro id: " + ide, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro nombre: " + nombre, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro Tipo: " + tipo, Toast.LENGTH_LONG).show();
            // Toast.makeText(BienvenidoActivity.this, "se encontro dire: " + dire, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro contenido: " + contenido, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro idmun: " + idmin, Toast.LENGTH_LONG).show();
            cursor.close();
            //return true;
        } catch (Exception e) {
          //  Toast.makeText(BienvenidoActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }
    }
    private void ConsultarOtros() {
        //base de datos Local
        final String campoId = "49";
        final String nombre, ide, tipo, contenido, idmin, dire;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {campoId};

        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_ID_SITIO +
                    ", " + Utilidades.CAMPO_NOMBRE_SITIO +
                    ", " + Utilidades.CAMPO_TIPO_SITIO +
                    ", " + Utilidades.CAMPO_DIRECCION_SITIO +
                    ", " + Utilidades.CAMPO_CONTENIDO_SITIO +
                    ", " + Utilidades.CAMPO_ID_MUNICIPIOO +
                    " FROM " + Utilidades.TABLE_SITIO +
                    " WHERE " + Utilidades.CAMPO_ID_SITIO + "=? ", parametros);
            cursor.moveToFirst();
            ide = cursor.getString(0);
            nombre = cursor.getString(1);
            tipo = cursor.getString(2);
            dire = cursor.getString(3);
            contenido = cursor.getString(4);
            idmin = cursor.getString(5);


            //Toast.makeText(BienvenidoActivity.this, "se encontro id: " + ide, Toast.LENGTH_LONG).show();
            // Toast.makeText(BienvenidoActivity.this, "se encontro nombre: " + nombre, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro Tipo: " + tipo, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro dire: " + dire, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro contenido: " + contenido, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro idmun: " + idmin, Toast.LENGTH_LONG).show();
            cursor.close();
            //return true;
        } catch (Exception e) {
          //  Toast.makeText(BienvenidoActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }
    }
    private void registroHistorias() {
        int id_sitio = 1;

        //base de datos Local
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();

        //Despues se hace una consulta a la tabla sqlite
        database = conn.getReadableDatabase();
        String query = "Select * from " + Utilidades.TABLE_HISTORIA + " where id_historia = '" + id_sitio + "'";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 0)//si la tabla esta vacia insertara los datos sin problema
        {

            String TipoHistorias = "Historia";
            //region HISTORIAS

            //0-AGUACHICA
            //1-CODAZZI
            //2-ASTREA
            //3-BECERRIL
            //4-BOSCONIA
            //5-CHIMICHAGUA
            //6-CHIRIGUANÁ
            //7-CURUMANÍ
            //8-EL COPEY
            //9-El PASO
            //10-GAMARRA
            //11-GONZALEZ
            //12-LA GLORIA
            //13- LA JAGUA DE IBIRICO
            //14- LA PAZ
            //15- LA MANAURE
            //16- PAILITAS
            //17- PELAYA
            //18- PUEBLO BELLO
            //19- RIO DE ORO
            //20- SAN ALBERTO
            //21- SAN DIEGO
            //22- SAN MARTÍN
            //23- TAMALAMEQUE
            //24-VALLEDUPAR
            //region Historia Valledupar
            int auxIdVHistoria = 24;
            Integer[] auxIdValledparHistoria = {0};
            String[] auxNombreValledparHistoria = {"Valledupar"};
            String[] auxContenidoValledparHistoria = {"Valledupar, también llamada Ciudad de los Santos Reyes del Valle de Upar, es un municipio colombiano, capital del departamento del Cesar. Es la cabecera del municipio homónimo, el cual tiene una extensión de 4493 km², 493 342 habitantes y junto a su área metropolitana reúne 677.9411 habitantes; está conformado por 25 corregimientos y 102 veredas.\n" +
                    "\n" +
                    "Está ubicada al nororiente de la Costa Atlántica colombiana, a orillas del río Guatapurí, en el valle del río Cesar formado por la Sierra Nevada de Santa Marta al occidente y la serranía del Perijá al oriente.\n" +
                    "\n" +
                    "La ciudad es un importante centro para la producción agrícola, agroindustrial y ganadera en la región comprendida entre el norte del departamento del Cesar y el sur del departamento de La Guajira, en el punto intermedio de las dos cuencas de explotación carbonífera más grandes del país: Cerrejón al norte y el complejo minero operado por Glencor La Loma-La Jagua al sur. También es uno de los principales epicentros musicales, culturales y folclóricos de Colombia por ser la cuna del vallenato, género musical de mayor popularidad en el país y actualmente símbolo de la música colombiana. Anualmente atrae a miles de visitantes de Colombia y del exterior durante el Festival de la Leyenda Vallenata, máximo evento del vallenato."};
            Integer[] auxImagenesSitioValledparHistoria = {R.drawable.historias_valledupar};
            for (int j = 0; j < auxNombreValledparHistoria.length; j++) {

                final Integer auxIdHistoria = auxIdValledparHistoria[j];
                final String auxNameHistoria = auxNombreValledparHistoria[j];
                final String auxContenidoHistoria = auxContenidoValledparHistoria[j];
                final Integer auxImagenHistoria = auxImagenesSitioValledparHistoria[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_HISTORIA
                        + " ("
                        + Utilidades.CAMPO_ID_HISTORIA + ", "
                        + Utilidades.CAMPO_NOMBRE_HISTORIA + ", "
                        + Utilidades.CAMPO_TIPO_HISTORIA + ", "
                        + Utilidades.CAMPO_CONTENIDO_HISTORIA + ", "
                        + Utilidades.CAMPO_IMAGEN_HISTORIA + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIO_HISTORIA + ")" +
                        " VALUES (" + auxIdHistoria +
                        ", '" + auxNameHistoria + "'" +
                        ", '" + TipoHistorias + "'" +
                        ", '" + auxContenidoHistoria + "'" +
                        ", " + auxImagenHistoria +
                        ", '" + auxIdVHistoria + "');";
                database.execSQL(insert);
            }
            //endregion

            //endregion

            String TipoLeyendas = "Leyenda";
            //region LEYENDAS

            //0-AGUACHICA
            //1-CODAZZI
            //2-ASTREA
            //3-BECERRIL
            //4-BOSCONIA
            //5-CHIMICHAGUA
            //6-CHIRIGUANÁ
            //7-CURUMANÍ
            //8-EL COPEY
            //9-El PASO
            //10-GAMARRA
            //11-GONZALEZ
            //12-LA GLORIA
            //13- LA JAGUA DE IBIRICO
            //14- LA PAZ
            //15- LA MANAURE
            //16- PAILITAS
            //17- PELAYA
            //18- PUEBLO BELLO
            //19- RIO DE ORO
            //20- SAN ALBERTO
            //21- SAN DIEGO
            //22- SAN MARTÍN
            //23- TAMALAMEQUE
            //24-VALLEDUPAR
            //region Leyenda Valledupar
            int auxIdVLeyenda = 24;
            Integer[] auxIdValledparLeyenda = {1};
            String[] auxNombreValledparLeyenda = {"La Sirena de Hurtado"};
            String[] auxContenidoValledparLeyenda = {"Dorada y erguida sobre un trono, la sirena resplandece. Domina el río Guatapurí y fascina al paseante que la observa desde el puente.\n" +
                    "Ella es uno de los símbolos más representativos de Valledupar. Un monumento radiante en medio de la vegetación, frente a una orilla llena de jóvenes que disfrutan del agua fría y saltan desde las rocas. Su cola mira hacia el cielo. Su semblante se queda fijo hacia delante. Toda su postura es enigmática, pero qué sugiere ella. ¿Cuál es la historia de la sirena de Hurtado?. Existen muchas leyendas entorno a la Sirena. Una de las más conocidas habla de ella como una niña preciosa del barrio del Cañaguate en Valledupar que, después de que sus padres le prohibieran bañarse en el río un Jueves Santo, se apresuró en desobedecer. \n" +
                    "La joven salió de su casa sin el permiso de sus familiares y, después de contemplar un rato las aguas trémulas del río, decidió desnudarse cerca de un pozo profundo. Allí, impelida por el calor de la tarde –eran las dos de la tarde– y el frío atractivo de las aguas, la muchacha se lanzó desde las altas rocas.\n" +
                    "\n" +
                    "La alegría causada por el baño fue inmensa, pero relativa. De repente, las aguas se turbaron. El cielo se oscureció, las nubes tronaron y una brisa rebelde se despertó, barriendo de lado a lado las orillas del río.\n" +
                    "La niña, llamada Rosario Arciniegas, trató de salir del agua. Entendía que algo amenazante estaba ocurriendo, pero no lograba escapar. Su cuerpo se había entorpecido hasta el punto de perder el control sobre él.\n" +
                    "Llegando a la orilla, Rosario pudo comprobar la grandeza de la sorpresa. Sus piernas se habían convertido en una cola enorme de pescado. Una cola fría y vivaz que se agitaba con insistencia, como si actuara independientemente de las órdenes de la niña.\n" +
                    "Percatándose de la ausencia de su hija, la madre salió a buscarla. Se acercó al río y, gritando su nombre con fuerzas, trató de llamar su atención. Pero nada. Rosario no contestaba.\n" +
                    "El temor fue extendiéndose y, poco a poco, el pueblo se sumó a la búsqueda. Hombres y mujeres exploraron las orillas para descubrir a la niña. Muchos pensaban que se había ahogado y lamentaban el horror causado en pleno Jueves Santo.\n" +
                    "Sólo el día siguiente de Viernes Santo fue cuando los gritos y los sollozos fueron apagándose. La vista de una sirena en la roca más alta puso un punto final a todas las expectativas. Esa sirena tenía la cara de Rosario y el cuerpo de pescado.\n" +
                    "Antes de tirarse al agua y desaparecer, la sirena se despidió de sus seres queridos. Algunos claman que sigue apareciendo de vez en cuando y que su llanto expresa el remordimiento causado por su desobediencia. Pero eso es otra leyenda.\n" +
                    "Lo seguro es que su estatua sigue fija en lo alto del río y que, sin duda, es uno de los monumentos más hermosos de Valledupar. \n"};
            Integer[] auxImagenesSitioValledparLeyenda = {R.drawable.leyendas_valledupar_sirena};
            for (int j = 0; j < auxNombreValledparLeyenda.length; j++) {

                final Integer auxIdHistoria = auxIdValledparLeyenda[j];
                final String auxNameHistoria = auxNombreValledparLeyenda[j];
                final String auxContenidoHistoria = auxContenidoValledparLeyenda[j];
                final Integer auxImagenHistoria = auxImagenesSitioValledparLeyenda[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_HISTORIA
                        + " ("
                        + Utilidades.CAMPO_ID_HISTORIA + ", "
                        + Utilidades.CAMPO_NOMBRE_HISTORIA + ", "
                        + Utilidades.CAMPO_TIPO_HISTORIA + ", "
                        + Utilidades.CAMPO_CONTENIDO_HISTORIA + ", "
                        + Utilidades.CAMPO_IMAGEN_HISTORIA + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIO_HISTORIA + ")" +
                        " VALUES (" + auxIdHistoria +
                        ", '" + auxNameHistoria + "'" +
                        ", '" + TipoLeyendas + "'" +
                        ", '" + auxContenidoHistoria + "'" +
                        ", " + auxImagenHistoria +
                        ", '" + auxIdVLeyenda + "');";
                database.execSQL(insert);
            }
            //endregion

            //endregion

            String TipoJuglares = "Juglares";
            //region Juglares Valledupar
            int auxIdVJuglares = 24;
            Integer[] auxIdValledparJuglares = {2};
            String[] auxNombreValledparJuglares = {"Rafael Escalona"};
            String[] auxContenidoValledparJuglares = {"Rafael Calixto Escalona Martínez (Valledupar, 26 de mayo de 1927- Bogotá, 13 de mayo de 2009) conocido como \"El maestro Escalona\", fue un compositor colombiano, considerado uno de los más grandes compositores de la música vallenata." +
                    "\n" +
                    "Escalona fue cofundador del Festival de la Leyenda Vallenata junto a la gestora cultural Consuelo Araújo Noguera y el político liberal Alfonso López Michelsen. Por su amistad con López, Escalona fue nombrado Cónsul de Colombia en Colón, Panamá una vez López llegó a la presidencia de Colombia."};
            Integer[] auxImagenesSitioValledparJuglares = {R.drawable.juglares_valledupar_escalona};
            for (int j = 0; j < auxNombreValledparJuglares.length; j++) {

                final Integer auxIdHistoria = auxIdValledparJuglares[j];
                final String auxNameHistoria = auxNombreValledparJuglares[j];
                final String auxContenidoHistoria = auxContenidoValledparJuglares[j];
                final Integer auxImagenHistoria = auxImagenesSitioValledparJuglares[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_HISTORIA
                        + " ("
                        + Utilidades.CAMPO_ID_HISTORIA + ", "
                        + Utilidades.CAMPO_NOMBRE_HISTORIA + ", "
                        + Utilidades.CAMPO_TIPO_HISTORIA + ", "
                        + Utilidades.CAMPO_CONTENIDO_HISTORIA + ", "
                        + Utilidades.CAMPO_IMAGEN_HISTORIA + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIO_HISTORIA + ")" +
                        " VALUES (" + auxIdHistoria +
                        ", '" + auxNameHistoria + "'" +
                        ", '" + TipoJuglares + "'" +
                        ", '" + auxContenidoHistoria + "'" +
                        ", " + auxImagenHistoria +
                        ", '" + auxIdVJuglares + "');";
                database.execSQL(insert);
            }
            //endregion

            String TipoMusica = "Musica";
            //region MUSICA

            //0-AGUACHICA
            //1-CODAZZI
            //2-ASTREA
            //3-BECERRIL
            //4-BOSCONIA
            //5-CHIMICHAGUA
            //6-CHIRIGUANÁ
            //7-CURUMANÍ
            //8-EL COPEY
            //9-El PASO
            //10-GAMARRA
            //11-GONZALEZ
            //12-LA GLORIA
            //13- LA JAGUA DE IBIRICO
            //14- LA PAZ
            //15- LA MANAURE
            //16- PAILITAS
            //17- PELAYA
            //18- PUEBLO BELLO
            //19- RIO DE ORO
            //20- SAN ALBERTO
            //21- SAN DIEGO
            //22- SAN MARTÍN
            //23- TAMALAMEQUE
            //24-VALLEDUPAR
            //region Musica Valledupar
            int auxIdValleduparMusica = 24;
            Integer[] auxIdSitioVMusica = {3};
            String[] auxNombreSitiosVMusica = {"Vallenato"};
            String[] auxContenidoSitiosVMusica = {"El vallenato es un género musical autóctono de la Región Caribe de Colombia con su origen en la antigua provincia de Padilla (actuales sur de La Guajira, norte del Cesar y oriente del Magdalena). Tiene notable influencia de la inmigración europea, ya que el acordeón fue traído por pobladores alemanes a Riohacha, La Guajira, a finales del siglo XIX, y tanto la organización estrófica como la métrica se valen de la tradición española; por otra parte, el componente de los esclavos afrocolombianos hace presencia con la caja vallenata, una especie de tambor que en gran medida le da el ritmo a la melodía del acordeón, y por último lo indígena se evidencia con la guacharaca.1\u200B Su popularidad se ha extendido hoy a todas las regiones de Colombia, a países vecinos como Ecuador, Panamá, Venezuela e incluso países de Europa. Se interpreta tradicionalmente con tres instrumentos: el acordeón diatónico, la guacharaca y la caja vallenata. Los ritmos o aires musicales del vallenato son el paseo, el merengue, la puya, el son y la tambora. El vallenato también se interpreta con guitarra y con la instrumentación de la cumbia en cumbiambas y grupos de millo.\n" +
                    "\n" +
                    "IMPORTANCIA\n" +
                    "\n" +
                    "En las últimas décadas del siglo XX el vallenato adquirió gran importancia. Llevó a la organización de festivales en los que los acordeoneros compiten por el honor de ser declarado el más hábil ejecutor de cada uno de los aires tradicionales (a excepción, inexplicablemente, de la tambora). El más célebre de estos festivales es el Festival de la Leyenda Vallenata, que se celebra anualmente a fines de abril en Valledupar, y cuya primera versión se disputó en 1968. Desde 1987, el Festival Cuna de Acordeones de Villanueva, Guajira, se ha convertido en el segundo de mayor importancia. En el vallenato el modo de uso del acordeón diatónico requiere usar simultáneamente ambos lados del acordeón. Lo anterior caracteriza al acordeonero colombiano y diferencia al vallenato de los otros géneros musicales con acordeón, donde generalmente se suprime o subutiliza la parte de los bajos (ejecutados con la mano izquierda): En Colombia, la forma armónica y rítmica con que el acordeonero maneja los bajos es un factor relevante de calificación en los festivales vallenatos.\n" +
                    "\n" +
                    "CARACTERÍSTICAS\n" +
                    "\n" +
                    "El vallenato o la música vallenata hace parte de la música folclórica de la Costa Caribe colombiana. Es el ritmo musical colombiano que ha alcanzado más popularidad, tanto a nivel nacional como internacional. Lo que hace característico al vallenato tradicional es ser interpretado sólo con tres instrumentos que no requieren de amplificación alguna: dos de percusión (la caja y la guacharaca), que marcan el ritmo, y el acordeón diatónico (de origen europeo) con el que se interpreta la melodía."};
            Integer[] auxImagenesSitioVMusica = {R.drawable.musica_valledupar};
            for (int j = 0; j < auxNombreSitiosVMusica.length; j++) {

                final Integer auxIdHistoria = auxIdSitioVMusica[j];
                final String auxNameHistoria = auxNombreSitiosVMusica[j];
                final String auxContenidoHistoria = auxContenidoSitiosVMusica[j];
                final Integer auxImagenHistoria = auxImagenesSitioVMusica[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_HISTORIA
                        + " ("
                        + Utilidades.CAMPO_ID_HISTORIA + ", "
                        + Utilidades.CAMPO_NOMBRE_HISTORIA + ", "
                        + Utilidades.CAMPO_TIPO_HISTORIA + ", "
                        + Utilidades.CAMPO_CONTENIDO_HISTORIA + ", "
                        + Utilidades.CAMPO_IMAGEN_HISTORIA + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIO_HISTORIA + ")" +
                        " VALUES (" + auxIdHistoria +
                        ", '" + auxNameHistoria + "'" +
                        ", '" + TipoMusica + "'" +
                        ", '" + auxContenidoHistoria + "'" +
                        ", " + auxImagenHistoria +
                        ", '" + auxIdValleduparMusica + "');";
                database.execSQL(insert);
            }
            //endregion

            //endregion

        } else {
           // Toast.makeText(BienvenidoActivity.this, "Ya esta Cargada la lista Historias", Toast.LENGTH_LONG).show();
        }
        database.close();
    }
    private void ConsultarHistorias() {
        //base de datos Local
        final String campoId = "3";
        final String nombre, ide, tipo, contenido, idmin, dire;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {campoId};

        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT * FROM " + Utilidades.TABLE_HISTORIA +
                    " WHERE " + Utilidades.CAMPO_ID_HISTORIA + "=? ", parametros);
            cursor.moveToFirst();
            ide = cursor.getString(0);
            nombre = cursor.getString(1);
            tipo = cursor.getString(2);
            //dire = cursor.getString(3);
            contenido = cursor.getString(3);
            idmin = cursor.getString(4);


            //Toast.makeText(BienvenidoActivity.this, "se encontro id: " + ide, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro nombre (3): " + nombre, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro Tipo: " + tipo, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro dire: " + dire, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro contenido: " + contenido, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro idmun: " + idmin, Toast.LENGTH_LONG).show();
            cursor.close();
            //return true;
        } catch (Exception e) {
           // Toast.makeText(BienvenidoActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }
    }
    private void RegistrarTurismo() {
        int id_sitio = 1;

        //base de datos Local
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();

        //Despues se hace una consulta a la tabla sqlite
        database = conn.getReadableDatabase();
        String query = "Select * from " + Utilidades.TABLE_TURISMO + " where id_turismo = '" + id_sitio + "'";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() == 0)//si la tabla esta vacia insertara los datos sin problema
        {
            //15- LA MANAURE
            int auxIdManaureTurismo = 15;
            Integer[] auxIdTurismoManaure = {0};
            String[] auxNombreTurismoManaure = {"Parapente"};
            String[] auxContenidoTurismoManaure = {"Admira la majestuosidad de la imponente Sierra Nevada de Santamarta desde el aire, disfruta de la belleza de nuestros atardeceres, sobrevuela un área con el espíritu mágico de la ancestral cultura Arhuaca."};
            String[] auxDireTurismoManaure= {"Voladero Manaure"};
            String[] auxContactoTurismoManaure = {"Teléfono: +57 317 6570007" +
                    "\n" +
                    "Correo: manaureaventura@gmail.com"};
            Integer[] auxImagenesTurismoManaure = {R.drawable.turismo_manaure_parapente};
            for (int j = 0; j < auxNombreTurismoManaure.length; j++) {

                final Integer auxIdHistoria = auxIdTurismoManaure[j];
                final String auxNameHistoria = auxNombreTurismoManaure[j];
                final String auxContenidoHistoria = auxContenidoTurismoManaure[j];
                final String auxDireccionHistoria = auxDireTurismoManaure[j];
                final String auxContactidoHistoria = auxContactoTurismoManaure[j];
                final Integer auxImagenHistoria = auxImagenesTurismoManaure[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_TURISMO
                        + " ("
                        + Utilidades.CAMPO_ID_TURISMO + ", "
                        + Utilidades.CAMPO_NOMBRE_TURISMO + ", "
                        + Utilidades.CAMPO_CONTENIDO_TURISMO + ", "
                        + Utilidades.CAMPO_DIRECCION_TURISMO + ", "
                        + Utilidades.CAMPO_CONTACTO_TURISMO + ", "
                        + Utilidades.CAMPO_IMAGEN_TURISMO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIO_TURISMO + ")" +
                        " VALUES (" + auxIdHistoria +
                        ", '" + auxNameHistoria + "'" +
                        ", '" + auxContenidoHistoria + "'" +
                        ", '" + auxDireccionHistoria + "'" +
                        ", '" + auxContactidoHistoria + "'" +
                        ", " + auxImagenHistoria +
                        ", '" + auxIdManaureTurismo + "');";
                database.execSQL(insert);
            }
            //24-VALLEDUPAR
            int auxIdValleduparTurismo = 24;
            Integer[] auxIdTurismoValledupar = {1};
            String[] auxNombreTurismoValledupar = {"Senderismo"};
            String[] auxContenidoTurismoValledupar = {"Este plan es perfecto para todo aquel que disfrute ejercitar su cuerpo, disfrutando de la naturaleza y el clima fresco de la mañana  Ropa y calzado adecuados. Si realizas estas actividades en Valledupar ten en cuenta que el clima es cálido y seco, la hidratación es fundamental." +
                    "\n" +
                    " En Manaure ten en cuenta que la mayoría de los senderos son en altura; en el caso de Sabana Rubia llegarás hasta los 3.000 m.s.n.m por lo que debes llevar ropa para clima frio." +
                    "\n" +
                    "Mirador del Santo Ecce Homo. Si visitas Valledupar puedes realizar caminatas por el parque lineal del rio Guatapurí y conectar con los senderos del cerro del mirador del Santo Ecce Homo. "};
            String[] auxDireTurismoValledupar= {"Mirador del Santo Ecce Homo."};
            String[] auxContactoTurismoValledupar = {"Sin información"};
            Integer[] auxImagenesTurismoValledupar = {R.drawable.turismo_valledupar_senderismo};
            for (int j = 0; j < auxNombreTurismoValledupar.length; j++) {

                final Integer auxIdHistoria = auxIdTurismoValledupar[j];
                final String auxNameHistoria = auxNombreTurismoValledupar[j];
                final String auxContenidoHistoria = auxContenidoTurismoValledupar[j];
                final String auxDireccionHistoria = auxDireTurismoValledupar[j];
                final String auxContactidoHistoria = auxContactoTurismoValledupar[j];
                final Integer auxImagenHistoria = auxImagenesTurismoValledupar[j];

                String insert = "INSERT INTO " + Utilidades.TABLE_TURISMO
                        + " ("
                        + Utilidades.CAMPO_ID_TURISMO + ", "
                        + Utilidades.CAMPO_NOMBRE_TURISMO + ", "
                        + Utilidades.CAMPO_CONTENIDO_TURISMO + ", "
                        + Utilidades.CAMPO_DIRECCION_TURISMO + ", "
                        + Utilidades.CAMPO_CONTACTO_TURISMO + ", "
                        + Utilidades.CAMPO_IMAGEN_TURISMO + ", "
                        + Utilidades.CAMPO_ID_MUNICIPIO_TURISMO + ")" +
                        " VALUES (" + auxIdHistoria +
                        ", '" + auxNameHistoria + "'" +
                        ", '" + auxContenidoHistoria + "'" +
                        ", '" + auxDireccionHistoria + "'" +
                        ", '" + auxContactidoHistoria + "'" +
                        ", " + auxImagenHistoria +
                        ", '" + auxIdValleduparTurismo + "');";
                database.execSQL(insert);
            }

        } else {
          //  Toast.makeText(BienvenidoActivity.this, "Ya esta Cargada la lista Historias", Toast.LENGTH_LONG).show();
        }
        database.close();

    }
    private void ConsultarTurismo() {
        //base de datos Local
        final String campoId = "0";
        final String nombre, ide, tipo, contenido, idmin, dire;
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase database = conn.getWritableDatabase();
        String[] parametros = {campoId};

        try {
            // Cursor cursor = database.query(Utilidades.TABLE_MUNICIPIO, campos, Utilidades.CAMPO_ID_MUNICIPI + "=?", parametros, null, null, null);
            Cursor cursor = database.rawQuery("SELECT * FROM " + Utilidades.TABLE_TURISMO +
                    " WHERE " + Utilidades.CAMPO_ID_TURISMO + "=? ", parametros);
            cursor.moveToFirst();
            ide = cursor.getString(0);
            nombre = cursor.getString(1);
            tipo = cursor.getString(2);
            //dire = cursor.getString(3);
            contenido = cursor.getString(3);
            idmin = cursor.getString(4);


            //Toast.makeText(BienvenidoActivity.this, "se encontro id: " + ide, Toast.LENGTH_LONG).show();
           // Toast.makeText(BienvenidoActivity.this, "se encontro nombre (3): " + nombre, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro Tipo: " + tipo, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro dire: " + dire, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro contenido: " + contenido, Toast.LENGTH_LONG).show();
            //Toast.makeText(BienvenidoActivity.this, "se encontro idmun: " + idmin, Toast.LENGTH_LONG).show();
            cursor.close();
            //return true;
        } catch (Exception e) {
            //Toast.makeText(BienvenidoActivity.this, "no existe ", Toast.LENGTH_LONG).show();
            //    return false;
        }
    }
}