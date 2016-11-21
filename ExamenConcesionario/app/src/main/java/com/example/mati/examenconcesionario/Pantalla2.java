package com.example.mati.examenconcesionario;

/**
 * Created by mati on 21/11/16.
 */
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pantalla2 extends Activity {
    private Coche coche;
    private String modelo,Tarifa,seguro,Final;
    private Double Peso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView Modelo = (TextView)findViewById(R.id.modelo);
        final TextView PrecioHora = (TextView)findViewById(R.id.ph);
        final TextView Seguro = (TextView)findViewById(R.id.seguro);
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
        if (modelo.compareTo("Fiesta")==0)
            modelo = "Fiesta Ford";
        Modelo.setText("Modelo: "+modelo);

        seguro = intent.getStringExtra("Seguro");
        if (seguro.compareTo("Seguro Normal")==0)
            seguro="Con Seguro";
        if (seguro.compareTo("Seguro a todo riesgo")==0)
            seguro="Urgente";
        Seguro.setText("El Seguro es: "+seguro);

        costefinal.setText(String.valueOf(coche.getPrecio()));




        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miintent =new Intent(Pantalla2.this,MainActivity.class);
                startActivity(miintent);

            }
        });
    }
}