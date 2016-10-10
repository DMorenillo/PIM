package com.example.mati.ejercicios2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button botonRojo = (Button) findViewById(R.id.boton1);
        final Button botonAmarillo = (Button) findViewById(R.id.boton2);
        final Button botonAzul = (Button) findViewById(R.id.boton3);
        final Button botonClear = (Button) findViewById(R.id.boton4);
        final TableRow fondo = (TableRow) findViewById(R.id.fondo);

        botonRojo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                fondo.setBackgroundColor(Color.RED);
            }
        });
        botonAmarillo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                fondo.setBackgroundColor(Color.YELLOW);
            }
        });
        botonAzul.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                fondo.setBackgroundColor(Color.CYAN);
            }
        });
        botonClear.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                //En la linea siguiente usamos el truco de ponelo blanco
                //ya que en el ejercicio anterios hemos usado el context
                fondo.setBackgroundColor(Color.WHITE);
            }
        });

    }
}
