package com.example.mati.pasodeobjetos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Pantalla2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        final TextView Mensaje = (TextView)findViewById(R.id.mensaje);
        final ImageView imagen = (ImageView)findViewById(R.id.Imagen);
        Bundle miBundle = getIntent().getExtras();
        Persona persona = (Persona)miBundle.getSerializable("InfoPersona");
        Mensaje.setText("Nombre: "+persona.getNombre()+" Edad: "+persona.getEdad());
        imagen.setImageResource(persona.getImagen());
    }
}
