package com.example.mati.examenpmm_danielromero;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pantalla2 extends Activity {
    private Coche coche;
    private String modelo,Tarifa;
    private Double Peso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView Modelo = (TextView)findViewById(R.id.modelo);
        final TextView PrecioHora = (TextView)findViewById(R.id.ph);
        final TextView tiempo = (TextView)findViewById(R.id.tiempo);
        final TextView costefinal = (TextView)findViewById(R.id.costefinal);
        final Button boton1 = (Button)findViewById(R.id.button);
        Intent intent = getIntent();

        coche=(Coche) intent.getSerializableExtra("informacion");

        modelo = intent.getStringExtra("modelo");
        if (modelo.compareTo("Megane")==0)
            modelo = "Megane Seat";
        if (modelo.compareTo("X-11")==0)
            modelo = "X-11 Ferrari";
        if (modelo.compareTo("Leon")==0)
            modelo = "Leon Seat";
        Modelo.setText("Modelo: "+modelo);

        Tarifa = intent.getStringExtra("tarifa");
        if (Tarifa.compareTo("Seguro Normal")==0)
            Tarifa="Con Seguro";
        if (Tarifa.compareTo("Seguro a todo riesgo")==0)
            Tarifa="Urgente";
        tarifa.setText("La tarifa es: "+Tarifa);

        Peso = intent.getDoubleExtra("peso",0);
        peso.setText("El peso es: "+Peso);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miintent =new Intent(Pantalla2.this,MainActivity.class);
                startActivity(miintent);

            }
        });
    }
}