package com.example.mati.ejemploexamen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mati on 14/11/16.
 */
public class pantalla2 extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView texto = (TextView)findViewById(R.id.infor);
        final Button boton1 = (Button) findViewById(R.id.button);

        Bundle mibundle = getIntent().getExtras();
        Destino destino=(Destino) mibundle.getSerializable("informacion");
        texto.setText("Zona: "+destino.getZona()+" Continente: "+destino.getContinente()+"Precio: "+destino.getPrecio()+".");

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miintent =new Intent(pantalla2.this,MainActivity.class);
                startActivity(miintent);

            }
        });
    }
}
