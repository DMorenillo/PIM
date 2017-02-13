package com.example.mati.proyecto;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Morenillo on 10/02/2017.
 */

public class Insertar extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertarl);

        //Abrimos Bases de Datos y sus referencias
        JuegosSQLiteHelper juegoBD = new JuegosSQLiteHelper(this,"juegos",null,1);
        final SQLiteDatabase db = juegoBD.getWritableDatabase();

        final EditText nombre = (EditText)findViewById(R.id.nombre);
        final EditText genero = (EditText)findViewById(R.id.genero);
        final EditText precio = (EditText)findViewById(R.id.precio);
        final Button insertar = (Button)findViewById(R.id.insertar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO juegos(nombre,genero,precio) VALUES (\'"+nombre+"\',\'"+genero+"\',\'"+precio+"\')");
                finish();
            }
        });
    }
}
