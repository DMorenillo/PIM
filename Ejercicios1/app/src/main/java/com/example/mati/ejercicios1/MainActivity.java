package com.example.mati.ejercicios1;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView fondo = (TextView) findViewById(R.id.fondo);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radios);
        final Button botonSet = (Button)findViewById(R.id.button);
        final Button botonBorrar = (Button) findViewById(R.id.button2);
        final Context context = this;
        rg.clearCheck();
        botonSet.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                //En este metodo cambiaremos los colores dandole al boton PonerColor
                if (rg.getCheckedRadioButtonId()==R.id.radioButton)
                    fondo.setBackgroundColor(Color.RED);
                if (rg.getCheckedRadioButtonId()==R.id.radioButton2)
                    fondo.setBackgroundColor(Color.GREEN);
                if (rg.getCheckedRadioButtonId()==R.id.radioButton3)
                    fondo.setBackgroundColor(Color.BLUE);

            }
        });
        botonBorrar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                rg.clearCheck();
                //fondo.setBackgroundColor(Color.WHITE);ç
                // En la Linea de arriba hacemos un trampilla para ponerlo blanco
                fondo.setBackgroundColor(ContextCompat.getColor(context, R.color.background));
                //En esta linea lo hacemos de la manera correcta
            }
        });
    }
}
