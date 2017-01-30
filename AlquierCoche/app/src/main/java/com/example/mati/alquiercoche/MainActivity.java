package com.example.mati.examenpmm_danielromero;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner miSpinner;
    TextView miTexto;
    RadioGroup miRadio;
    RadioButton radioButton1, radioButton2;
    private double precio;
    private String Seguro,Modelo,Extras;
    CheckBox Aire,Gps,RadioDVD;
    private String Decoracion= "";
    public Coche[] coche = new Coche[]{
            new Coche("Megane", "Seat", 20),
            new Coche("X-11", "Ferrari", 100),
            new Coche("Leon", "Seat", 30),
            new Coche("Fiesta", "Ford", 40),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INICIAMOS ELEMENTOS

        miSpinner = (Spinner) findViewById(R.id.spinner);
        miTexto = (TextView) findViewById(R.id.txt1);
        miRadio = (RadioGroup) findViewById(R.id.rg);
        radioButton1 = (RadioButton) findViewById(R.id.radiobutton1);
        radioButton2 = (RadioButton) findViewById(R.id.radiobutton2);
        Aire = (CheckBox)findViewById(R.id.AireAcondicionado);
        Gps = (CheckBox)findViewById(R.id.gps);
        RadioDVD = (CheckBox)findViewById(R.id.RadioDVD);

        //SPINNER

        AdaptadorDestino adaptador = new AdaptadorDestino(this);
        miSpinner.setAdapter(adaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                precio = coche[position].getPrecio();
                Modelo = coche[position].getModelo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //TEXTVIEW
        miTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miTexto.setText("");
            }
        });
        miRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        //DATOS CHECKBOXS
        Aire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (Decoracion.isEmpty()){
                        Decoracion = "Aire Acondicionado";
                    }
                    else {
                        Decoracion = Decoracion +" y Aire Acondicionado";
                        precio = precio + 50;
                        Extras = Extras + 50;
                    }
                }
            }
        });
        Gps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (Decoracion.isEmpty()){
                        Decoracion = "GPS";
                    }
                    else {
                        Decoracion = Decoracion +" y GPS";
                        precio = precio + 50;
                        Extras = Extras + 50;
                    }
                }
            }
        });
        RadioDVD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (Decoracion.isEmpty()){
                        Decoracion = "Radio/DVD";
                    }
                    else {
                        Decoracion = Decoracion +" y Radio/DVD";
                        precio = precio + 50;
                        Extras = Extras + 50;
                    }
                }
            }
        });

        //RADIOGROUPS
        miRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.radiobutton1:
                        Seguro = "Seguro Normal";
                        break;
                    case R.id.radiobutton2:
                        Seguro = "Seguro a todo riesgo";
                        precio = precio + (precio*0.3);
                }
            }
        });
        //CALCULAR ALQUILER
        if(!miTexto.getText().toString().isEmpty()){
            if(Double.parseDouble(miTexto.getText().toString())>=6 && Double.parseDouble(miTexto.getText().toString())<=10){
                precio = precio + (Double.parseDouble(miTexto.getText().toString())*1);
            }
            if(Double.parseDouble(miTexto.getText().toString())>=6 && Double.parseDouble(miTexto.getText().toString())<=10){
                precio = precio + (Double.parseDouble(miTexto.getText().toString())*1.5);
            }
            if(Double.parseDouble(miTexto.getText().toString())>10){
                precio = precio + (Double.parseDouble(miTexto.getText().toString())*2);
            }
        }

    }

    //ADAPTADOR SPINNER

    public class AdaptadorDestino extends ArrayAdapter {
        Activity context;

        AdaptadorDestino(Activity context) {
            super(context, R.layout.activity_coche, coche);
            this.context = context;

        }

        public View getView(int i, View convertView, ViewGroup parent) {

            View item = convertView;
            if (item == null) {

                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.activity_coche, null);
            }
            TextView Marca = (TextView) item.findViewById(R.id.marca);
            Marca.setText(coche[i].getMarca());

            TextView Modelo = (TextView) item.findViewById(R.id.modelo);
            Modelo.setText(coche[i].getModelo());

            TextView Precio = (TextView) item.findViewById(R.id.precio);
            Precio.setText(String.valueOf(coche[i].getPrecio()));

            return item;
        }

        public View getDropDownView(final int position, View convertview, ViewGroup parent) {
            View vistadesplegada = getView(position, convertview, parent);
            return vistadesplegada;
        }

        //PASO A SEGUNDA PANTALLA

        public void Calculos(View clickedButton) {
            Intent activityIntent = new Intent(MainActivity.this, Pantalla2.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("informacion",coche);
            bundle.putString("modelo",Modelo);
            bundle.putString("Seguro",Seguro);
            bundle.putString("extras",Extras);
            bundle.putDouble("horas",Double.parseDouble(miTexto.getText().toString()));
            activityIntent.putExtras(bundle);
            startActivity(activityIntent);

        }
    }
}
