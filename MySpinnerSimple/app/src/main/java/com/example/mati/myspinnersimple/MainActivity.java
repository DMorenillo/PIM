package com.example.mati.myspinnersimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner miSpinner;
    final static String semana[]= {"Lunes", "Martes","Miercoles","Jueves","Viernes",
            "Sabado","Domingo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String mensaje;
        miSpinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,semana);
        miSpinner.setAdapter(miAdaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mensaje= "";
                mensaje = "Item clicked => " + semana[position];
                showToast(mensaje);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    } //del OnCreate
    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();}
}
