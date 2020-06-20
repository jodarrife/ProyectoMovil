package com.example.optimitour.Vistas.Navegación.Calendario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.optimitour.Auxiliares.EventoCalendario;
import com.example.optimitour.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;

public class CalendarioActivity extends AppCompatActivity {

    ImageView back;
    TextView fecha,nombre;
    MaterialCalendarView materialCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        //Seteo
        back = findViewById(R.id.btn_back);
        materialCalendar = findViewById(R.id.calendarView);
        fecha = findViewById(R.id.titleEvent);
        nombre = findViewById(R.id.municipioEvent);

        confiCalendar();
        //Regresar
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarioActivity.super.onBackPressed();
            }
        });
        materialCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
                Toast.makeText(CalendarioActivity.this, "" + FORMATTER.format(date.getDate()), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext(), "" + date, Toast.LENGTH_SHORT).show();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String eventos1 = format.format(date.getDate());
                if(eventos1.equals("2020-01-30") || eventos1.equals("2020-01-31") || eventos1.equals("2020-02-01") ){
                    fecha.setText("Festival de la Paletilla ");
                    nombre.setText("Becerril");
                }else if(eventos1.equals("2020-02-02")){
                    fecha.setText("Festival de la Paletilla" +
                            "              "+
                            "Fiesta Patronal de la Candelaria");
                    nombre.setText("Becerril|Cesar");
                }else if(eventos1.equals("2020-04-05") || eventos1.equals("2020-04-06") || eventos1.equals("2020-04-07")||eventos1.equals("2020-04-08") || eventos1.equals("2020-04-09") || eventos1.equals("2020-04-10")|| eventos1.equals("2020-04-11")){
                    fecha.setText("Semana Santa");
                    nombre.setText("Cesar");
                }else if(eventos1.equals("2020-04-25") || eventos1.equals("2020-04-26") || eventos1.equals("2020-04-27")||eventos1.equals("2020-04-28") || eventos1.equals("2020-04-29") || eventos1.equals("2020-04-30")|| eventos1.equals("2020-05-01")|| eventos1.equals("2020-05-02")){
                    fecha.setText("Festival Vallenato");
                    nombre.setText("Valledupar");
                }else if(eventos1.equals("2020-07-16")) {
                    fecha.setText("Virgen del Carmen");
                    nombre.setText("Cesar");
                }else if(eventos1.equals("2020-08-14") || eventos1.equals("2020-08-15") || eventos1.equals("2020-08-16") || eventos1.equals("2020-08-17")) {
                    fecha.setText("Feria ganadera");
                    nombre.setText("Valledupar");
                }else{
                    fecha.setText("Sin eventos");
                    nombre.setText("");
                }

            }
        });
        añadirPunto();
    }

    private  void confiCalendar(){
        materialCalendar.state().edit()
                .setMinimumDate(CalendarDay.from(2020, 0, 1))
                .setMaximumDate(CalendarDay.from(2022, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendar.setSelectedDate(CalendarDay.today());
    }
    private  void añadirPunto(){
        HashSet<CalendarDay> dates = new HashSet<>();
        dates.add(CalendarDay.from(2020,0,30));
        dates.add(CalendarDay.from(2020,0,31));
        dates.add(CalendarDay.from(2020,1,1));
        dates.add(CalendarDay.from(2020,1,2));

        dates.add(CalendarDay.from(2020,3,5));
        dates.add(CalendarDay.from(2020,3,6));
        dates.add(CalendarDay.from(2020,3,7));
        dates.add(CalendarDay.from(2020,3,8));
        dates.add(CalendarDay.from(2020,3,9));
        dates.add(CalendarDay.from(2020,3,10));
        dates.add(CalendarDay.from(2020,3,11));

        dates.add(CalendarDay.from(2020,3,25));
        dates.add(CalendarDay.from(2020,3,26));
        dates.add(CalendarDay.from(2020,3,27));
        dates.add(CalendarDay.from(2020,3,28));
        dates.add(CalendarDay.from(2020,3,29));
        dates.add(CalendarDay.from(2020,3,30));
        dates.add(CalendarDay.from(2020,4,01));
        dates.add(CalendarDay.from(2020,4,02));

        dates.add(CalendarDay.from(2020,6,16));

        dates.add(CalendarDay.from(2020,7,14));
        dates.add(CalendarDay.from(2020,7,15));
        dates.add(CalendarDay.from(2020,7,16));
        dates.add(CalendarDay.from(2020,7,17));


        materialCalendar.addDecorators(new EventoCalendario(000000,dates));

    }
}
