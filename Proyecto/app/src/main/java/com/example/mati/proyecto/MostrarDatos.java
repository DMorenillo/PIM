package com.example.mati.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MostrarDatos extends AppCompatActivity {

    /*public Juego[] juegos = new Juego[]{
           new Juego("Final Fantasy 7","RPG",1.0),


    };
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostratdatos);

        //final ImageView imageView = (ImageView)findViewById(R.id.imgPersonaje);
        final Button volver = (Button)findViewById(R.id.volver);

        Bundle bundle = getIntent().getExtras();
        String usuario = bundle.getString("user");
        int seleccion = bundle.getInt("int");

        //Mensaje de usuario logeado correctamente
        /*Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Usuario: "+ usuario + "\nJuego: "+juegos[seleccion].getGenero(), Toast.LENGTH_SHORT);
        toast1.show();*/
        //Fin Toast

        //imageView.setImageResource(per[seleccion].getImagen());

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}