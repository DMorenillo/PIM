package com.example.mati.pruebasql;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Spinner miSpinner;
    String nombre,telefono;
    public ArrayList<Clientes> clientes = new ArrayList<Clientes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miSpinner = (Spinner) findViewById(R.id.Spinner);

        //Abrimos la base de datos en modo escritura
        ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "DBClientes", null, 1);

        //Obtenemos referencia a la base de datos para poder modificarla.
        SQLiteDatabase bd = cliBDh.getWritableDatabase();

        //En caso de abrir de forma correcta la base de datos
        if (bd!=null) {
            //Introducimos 3 clientes de ejemplo
           /* for (int cont=1; cont<=3; cont++) {
                //Creamos los datos
                int codigo = cont;
                String nombre = "cli" + cont;
                String telefono = cont + "XXXXXXX";

                //Introducimos los datos en la tabla Clientes
                bd.execSQL("INSERT INTO Clientes (codigo, nombre, telefono) " +
                        "VALUES (" + codigo + ", '" + nombre + "', '" + telefono + "')");
            }*/
            String[] campos = new String[] {"nombre", "telefono"};
            Cursor c = bd.query("Clientes", campos, null, null, null, null, null);
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    String nombreCli = c.getString(0);
                    String telefonoCli = c.getString(1);
                    clientes.add(new Clientes(nombreCli,telefonoCli));

                } while (c.moveToNext());
            }
            AdaptadorClientes adaptador = new AdaptadorClientes(this);
            miSpinner.setAdapter(adaptador);
            miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    nombre = clientes.get(position).getNombre();
                    telefono = clientes.get(position).getTelefono();
                    Toast.makeText(getApplicationContext(),nombre+" "+telefono,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
/*
        		//Insertar un registro
        		bd.execSQL("INSERT INTO Clientes (nombre, telefono) VALUES ('cli1','11111') ");
        		//Actualizar un registro
        		bd.execSQL("UPDATE Clientes SET telefono='00000' WHERE nombre='cli1' ");
        		//Eliminar un registro
        		bd.execSQL("DELETE FROM Clientes WHERE nombre='cli1' ");

        		//Ejemplo de utilización del método insert()
        		//Creamos el registro que queremos insertar utilizando un objeto ContentValues
        		ContentValues nuevoRegistro = new ContentValues();
        		nuevoRegistro.put("nombre","cli10");
        		nuevoRegistro.put("telefono", "101010");
        		//Insertamos el registro en la base de datos
        		//El primer parámetro es el nombre de la tabla donde insertaremos.
        		//El segundo parámetro solo sirve en caso de introducir un registro vacio
        		//El tercer paráemtro es el objeto ContentValues que contiene el registro a insertar
        		bd.insert("Clientes", null, nuevoRegistro);

        		//Ejemplo de utilización del método update()
        		//Establecemos los campos-valores que vamos a actualizar
        		ContentValues valores = new ContentValues();
        		valores.put("telefono", "101010XX");
        		//Actualizamos el registro en la base de datos
        		//El tercer argumento es la condición del UPDATE tal como lo haríamos en la cláusula
        		//WHERE en una sentencia SQL normal.
        		//El cuarto argumento son partes variables de la sentencia SQL que aportamos en un
        		//vector de valores aparte
        		String[] args = new String[]{"cli1", "cli2"};
        		bd.update("Clientes", valores, "nombre=? OR nombre=?", args);

        		//Ejemplo de utilización del método delete()
        		//Eliminamos el registro del cliente 'cli2'
        		String[] args2 = new String[]{"cli2"};
        		bd.delete("Clientes", "nombre=?", args2);

        		//Ejemplo Select
        		String[] args3 = new String[]{"cli1"};
        		Cursor c = bd.rawQuery("SELECT nombre,telefono FROM Clientes WHERE nombre=? ", args3);

        		//Ejemplo Select2
        		String[] campos = new String[] {"nombre", "telefono"};
        		String[] args4 = new String[] {"cli1"};
        		Cursor c = bd.query("Clientes", campos, "nombre=?", args4, null, null, null);
        		//Nos aseguramos de que exista al menos un registro
        		if (c.moveToFirst()) {
        			//Recorremos el cursor hasta que no haya más registros
        			do {
        				String nombreCli = c.getString(0);
        				String telefonoCli = c.getString(1);
        			} while (c.moveToNext());
        		}
*/

            //Cerramos la base de datos
            bd.close();
        } //del if
    }
    public class AdaptadorClientes extends ArrayAdapter {
        Activity context;

        AdaptadorClientes(Activity context) {
            super(context, R.layout.activity_clientes, clientes);
            this.context = context;

        }

        public View getView(int i, View convertView, ViewGroup parent) {

            View item = convertView;
            if (item == null) {

                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.activity_clientes, null);
            }
            TextView Nombre = (TextView) item.findViewById(R.id.nombre);
            Nombre.setText(clientes.get(i).getNombre());

            TextView Telefono = (TextView) item.findViewById(R.id.telefono);
            Telefono.setText(clientes.get(i).getTelefono());

            return item;
        }
        public View getDropDownView(final int position, View convertview, ViewGroup parent) {
            View vistadesplegada = getView(position, convertview, parent);
            return vistadesplegada;
        }
    }

}