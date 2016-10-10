package com.example.mati.pasodeobjetos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editNombre =  (EditText)findViewById(R.id.nombre);
        final EditText editEdad =  (EditText)findViewById(R.id.Edad);
        final EditText editImagen =  (EditText)findViewById(R.id.Imagen);
        final Button botonValidar = (Button)findViewById(R.id.Validar);

        botonValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editNombre.getText().toString();
                String edad = editEdad.getText().toString();
                String info = "La persona es: "+ nom +" "+ "su edad es "+ edad+ " años";
                botonValidar.setText(info);

            }
        });
        Persona persona = new Persona();
    }
}
