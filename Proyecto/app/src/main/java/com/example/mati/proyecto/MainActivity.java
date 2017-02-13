package com.example.mati.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;


public class MainActivity extends AppCompatActivity {

    Spinner miSpinner;
    Button miBotton;
    TextView total;
    RadioGroup miRadio;
    EditText miTexto;
    RadioButton radioButton1,radioButton2;
    CheckBox Regalo,Envio,Dlc;
    public int precio = 0, Extras =0;
    public String Decoracion="", Seguro="";
    public Juego[] juegos = new Juego[]{
            new Juego("Final fantasy 7", "JRPG", 1),
            new Juego("Final fantasy 10", "JRPG", 2),
            new Juego("Metal Gear Solid 4", "Accion", 3),
            new Juego("The last of us", "Aventura", 3),
    };

    Juego juego;


    private static final int REQUEST_PANTALLA2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Abrimos Bases de Datos y sus referencias

        JuegosSQLiteHelper juegoBD = new JuegosSQLiteHelper(this,"juegos",null,1);
        final SQLiteDatabase db = juegoBD.getWritableDatabase();

        //INICIAMOS ELEMENTOS

        miSpinner = (Spinner) findViewById(R.id.spinner);
        miBotton = (Button)findViewById(R.id.Calculos);
        miTexto = (EditText) findViewById(R.id.txt1);
        miRadio = (RadioGroup) findViewById(R.id.rg);
        radioButton1 = (RadioButton) findViewById(R.id.radiobutton1);
        radioButton2 = (RadioButton) findViewById(R.id.radiobutton2);
        Regalo = (CheckBox)findViewById(R.id.Regalo);
        Envio = (CheckBox)findViewById(R.id.envio);
        Dlc = (CheckBox)findViewById(R.id.dlc);
        total = (TextView)findViewById(R.id.preciofinal);

        //Rellenar Lista de Juegos

        juegos= new Juego[0];
        String[] dataFields = new String[] {"nombre","genero","precio"};
        Cursor cursor = db.query("juegos",dataFields,null,null,null,null,null);
        Juego fetchjuego[];
        fetchjuego= new Juego[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                String nombre = cursor.getString(0);
                String genero = cursor.getString(1);
                int precio = parseInt(cursor.getString(2));

                fetchjuego[i]= new Juego(nombre,genero,precio);

                i++;

            }while(cursor.moveToNext());
        }
        juegos=fetchjuego;
        cursor.close();

        //SPINNER

        AdaptadorDestino adaptador = new AdaptadorDestino(this);
        miSpinner.setAdapter(adaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                precio = juegos[position].getPrecio();
                juego = juegos[position];
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
        Regalo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    precio = precio + 50;
                    Extras = Extras + 50;
                    if (Decoracion.isEmpty()){
                        Decoracion = "Regalo";

                    }
                    else {
                        Decoracion = Decoracion +" y Regalo";
                    }
                }
                else {
                    precio = precio - 50;
                    Extras = Extras - 50;
                }
            }
        });
        Envio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    precio = precio + 50;
                    Extras = Extras + 50;
                    if (Decoracion.isEmpty()){
                        Decoracion = "Envio Urgente";
                    }
                    else {
                        Decoracion = Decoracion +" y Envio urgente";

                    }
                }
                else {
                    precio = precio - 50;
                    Extras = Extras - 50;
                }
            }
        });
        Dlc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    precio = precio + 50;
                    Extras = Extras + 50;
                    if (Decoracion.isEmpty()){
                        Decoracion = "DLC";
                    }
                    else {
                        Decoracion = Decoracion +" y DLC";

                    }
                }
                else {
                    precio = precio - 50;
                    Extras = Extras - 50;
                }
            }
        });
        //RADIOGROUPS
        miRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.radiobutton1:
                        Seguro = "ED. Normal";
                        break;
                    case R.id.radiobutton2:
                        Seguro = "ED. Coleccionista";
                        precio = precio + (precio *2);
                        break;
                }
            }
        });

        miBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!miTexto.getText().toString().isEmpty()){
                    precio = precio * Integer.parseInt(miTexto.getText().toString());
                }
                total.setText("Precio: "+precio);
                Intent activityIntent = new Intent(MainActivity.this, Pantalla2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("informacion", juego);
                bundle.putDouble("extras",Extras);
                bundle.putDouble("tiempo",Double.parseDouble(miTexto.getText().toString()));
                bundle.putString("seguro",Seguro);
                bundle.putDouble("precio",precio);
                activityIntent.putExtras(bundle);
                startActivityForResult(activityIntent,REQUEST_PANTALLA2);
            }
        });
    }
    //ADAPTADOR SPINNER

    public class AdaptadorDestino extends ArrayAdapter {
        Activity context;

        AdaptadorDestino(Activity context) {
            super(context, R.layout.activity_juego, juegos);
            this.context = context;

        }

        public View getView(int i, View convertView, ViewGroup parent) {

            View item = convertView;
            if (item == null) {

                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.activity_juego, null);
            }
            TextView Marca = (TextView) item.findViewById(R.id.marca);
            Marca.setText(juegos[i].getNombre());

            TextView Modelo = (TextView) item.findViewById(R.id.modelo);
            Modelo.setText(juegos[i].getGenero());

            TextView Precio = (TextView) item.findViewById(R.id.precio);
            Precio.setText(String.valueOf(juegos[i].getPrecio()));


            return item;
        }
        public View getDropDownView(final int position, View convertview, ViewGroup parent) {
            View vistadesplegada = getView(position, convertview, parent);
            return vistadesplegada;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==REQUEST_PANTALLA2){
                Bundle bundle1 = data.getExtras();
                String resultado = bundle1.getString("Reloj");
                Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
            }
        }

    }
}
