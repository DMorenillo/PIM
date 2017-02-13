package com.example.mati.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;


public class Pantalla2 extends AppCompatActivity {

    private Juego juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView Modelo = (TextView)findViewById(R.id.modelo);
        final TextView PrecioHora = (TextView)findViewById(R.id.ph);
        final TextView Extras = (TextView)findViewById(R.id.extras);
        final TextView Seguro = (TextView)findViewById(R.id.seguro);
        final TextView tiempo = (TextView)findViewById(R.id.tiempo);
        final TextView costefinal = (TextView)findViewById(R.id.costefinal);
        final ImageView imagen = (ImageView)findViewById(R.id.imagen);
        final Button boton1 = (Button)findViewById(R.id.button);
        final Button boton2 = (Button)findViewById(R.id.historial);
        final CheckBox chkReloj = (CheckBox)findViewById(R.id.relojchk);
        final TimePicker reloj = (TimePicker)findViewById(R.id.reloj);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        juego=(Juego) intent.getSerializableExtra("informacion");
        String genero=juego.getGenero();
        if (genero.compareTo("Megane")==0)
            genero = "Megane Seat";
        if (genero.compareTo("X-11")==0)
            genero = "X-11 Ferrari";
        if (genero.compareTo("Leon")==0)
            genero = "Leon Seat";
        if (genero.compareTo("Fiesta")==0)
            genero = "Fiesta Ford";
        Modelo.setText("Nombre: "+genero);

        PrecioHora.setText("El precio por hora es: "+String.valueOf(juego.getPrecio())+"€");
        Extras.setText("Extras: "+String.valueOf(bundle.getDouble("extras"))+"€");
        tiempo.setText("Tiempo: "+String.valueOf(bundle.getDouble("tiempo")));
        Seguro.setText("Seguro: "+bundle.getString("seguro"));
        costefinal.setText("Coste Total: "+String.valueOf(bundle.getDouble("precio"))+"€");
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "Adios!";
                bundle.putString("Reloj",mensaje);
                Intent miintent =new Intent(Pantalla2.this,MainActivity.class);
                miintent.putExtras(bundle);
                setResult(RESULT_OK,miintent);
                finish();

            }
        });
        chkReloj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    reloj.setVisibility(reloj.VISIBLE);
                }
                else {
                    reloj.setVisibility(reloj.INVISIBLE);
                }
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla2.this, MostrarDatos.class);
            }
        });

    }
}
