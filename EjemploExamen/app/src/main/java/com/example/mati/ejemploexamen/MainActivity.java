package com.example.mati.ejemploexamen;

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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner miSpinner;
    private Destino [] zona = new Destino[]{
            new Destino("Zona A","Asia y Oceania",30),
            new Destino("Zona B","America y Africa",20),
            new Destino("Zona C", "Europa",10)
    };
    int precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miSpinner = (Spinner)findViewById(R.id.spinner1);
        final TextView miTexto = (TextView)findViewById(R.id.txt1);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.rg);
        AdaptadorDestino adaptador = new AdaptadorDestino(this);
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        spin.setAdapter(adaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected (AdapterView<?> arg0,View view, int position, long id){
               precio = zona[position].getPrecio();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public class AdaptadorDestino extends ArrayAdapter{

        Activity  context;

        AdaptadorDestino (Activity context){
            super(context, R.layout.activity_destino, zona);
            this.context = context;
        }
        public View getView (int i, View convertView, ViewGroup parent){
            View item = convertView;
            if (item == null) {

                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.activity_destino, null);
            }
            TextView zone = (TextView) item.findViewById(R.id.zona);
            zone.setText(zona[i].getZona());

            TextView continente = (TextView) item.findViewById(R.id.continente);
            continente.setText(zona[i].getContinente());

            TextView precio = (TextView) item.findViewById(R.id.precio);
            precio.setText(String.valueOf(zona[i].getPrecio()));

            return item;
        }
        public View getDropDownView(final int position, View convertview, ViewGroup parent) {
            View vistadesplegada = getView(position, convertview, parent);
            return vistadesplegada;
        }

    }


    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();}
    public void Calculos (View clickedButton){
        Intent activityIntent = new Intent(this, pantalla2.class);
        startActivity(activityIntent);
    }
}
