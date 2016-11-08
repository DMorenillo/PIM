package com.example.mati.spinnerobjeto;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Pantalla2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView texto = (TextView)findViewById(R.id.infor);
        final ImageView log =(ImageView)findViewById(R.id.logo);
        final Button boton1 = (Button) findViewById(R.id.button);

        Bundle mibundle = getIntent().getExtras();
        Titular titular=(Titular) mibundle.getSerializable("informacion");
        texto.setText("Titulo: "+titular.getTitulo()+" subtitulo,"+titular.getSubtitulo());

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miintent =new Intent(Pantalla2.this,MainActivity.class);
                startActivity(miintent);

            }
        });

    }


}