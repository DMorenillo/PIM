package com.example.mati.proyectoboton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class boton2 extends AppCompatActivity {
    protected Button miBotonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton2);
        final Button miBotonVolver = (Button) findViewById(R.id.botonVolver);
        miBotonVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(boton2.this, MainActivity.class);
                startActivity(miIntent);
            }
        });
    }
}
