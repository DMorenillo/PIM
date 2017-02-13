package com.example.mati.proyecto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * Created by Morenillo on 10/02/2017.
 */

public class Login extends AppCompatActivity {
    private static Context myContext;

    Spinner spinner;
    static String usuarios[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Abrimos la base de datos en modo escritura
        JuegosSQLiteHelper partidas = new JuegosSQLiteHelper(this, "Insertar", null, 1);
        //Referencia de la base de datos para modificarla
        final SQLiteDatabase db = partidas.getWritableDatabase();

        final Button button = (Button) findViewById(R.id.validar);

        //Rellenamos la lista para el Spinner
        /*
        usuarios = new String[0];
        String[] dataFields = new String[]{"user"};
        Cursor cursor = db.query("usuarios", dataFields, null, null, null, null, null);
        String fetchUsers[];
        fetchUsers = new String[cursor.getCount()];
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                fetchUsers[i] = name;
                i++;

            } while (cursor.moveToNext());
        }
        usuarios = fetchUsers;
        cursor.close();
        //Fin relleno de lista
        */

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, usuarios);
        spinner.setAdapter(adapter);

        final Bundle miBundle = new Bundle();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Usuario: " + usuarios[i]);
                miBundle.putString("Texto1", usuarios[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Pasar con un Bundle el nombre del usuario que está accediendo a su cuenta

                Intent intent = new Intent(Login.this, Juego.class);
                intent.putExtras(miBundle);

                startActivity(intent);
            }
        });
    }

        //Menú para el Acerca de

        public boolean onCreateOptionsMenu(Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }


         public boolean onOptionItemSelected (MenuItem menuItem){
            switch (menuItem.getItemId()){
                case R.id.acerca:
                    mostrarAcerca();
                    break;
            }
            return true;
        }
        //@Override
        /*public boolean onOptionsItemSelected (MenuItem menuItem){
            switch (menuItem.getItemId()) {
                case R.id.acerca:
                    mostrarAcerca();
                    break;
            }
            return true;
        }
*/

        public void mostrarAcerca(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Acerca de")
                    .setMessage("Aplicación creada por Morenillo")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.create().show();
        }


    public static Context getMyContext(){
        return myContext;
    }
}