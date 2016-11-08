package com.example.mati.spinnerobjeto;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Titular[] datos = new Titular[]{
            new Titular("Titulo 1", "subtitulo largo 1", R.drawable.img1),
            new Titular("Titulo 2", "subtitulo largo 2", R.drawable.img2),
            new Titular("Titulo 3", "subtitulo largo 3", R.drawable.img3),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        spin.setAdapter(adaptador);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "Titulo:" + datos[position].getTitulo() + "Subtitulo:" + datos[position].getSubtitulo();
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public class AdaptadorTitulares extends ArrayAdapter {

        Activity context;

        AdaptadorTitulares(Activity context) {
            super(context, R.layout.activity_titular, datos);
            this.context = context;
        }
        public View getView(int i, View convertView, ViewGroup parent) {

            View item = convertView;
            if (item == null) {

                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.activity_titular, null);
            }
            TextView titulo = (TextView) item.findViewById(R.id.titulo);
            titulo.setText(datos[i].getTitulo());

            TextView subtitulo = (TextView) item.findViewById(R.id.subtitulo);
            subtitulo.setText(datos[i].getSubtitulo());

            ImageView imagen = (ImageView) item.findViewById(R.id.imagen);
            imagen.setBackground(getDrawable(datos[i].getImagen()));

            return item;
        }
        // NECESARIO PARA QUE EL SPINNER FUNCIONE, SINO FALLA

        public View getDropDownView(final int position, View convertview, ViewGroup parent) {
            View vistadesplegada = getView(position, convertview, parent);

            vistadesplegada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final TextView title = (TextView) findViewById(R.id.titulo);
                    title.setText(datos[position].getTitulo());
                    final TextView sub = (TextView) findViewById(R.id.subtitulo);
                    sub.setText(datos[position].getSubtitulo());
                    final ImageView img = (ImageView) findViewById(R.id.imagen);

                    String titulo = title.getText().toString();
                    String subtitulo = sub.getText().toString();

                    Titular persona = new Titular(titulo,subtitulo,R.id.imagen);
                    Intent miintent = new Intent(MainActivity.this, Pantalla2.class);
                    Bundle mibundle = new Bundle();

                    mibundle.putSerializable("informacion",persona);
                    miintent.putExtras(mibundle);

                    startActivity(miintent);

                }
            });

            return vistadesplegada;

        }
    }
}