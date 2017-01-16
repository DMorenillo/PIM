package com.example.mati.pruebasql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Clientes  {

   String nombre;
   String telefono;

    public Clientes(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
}
