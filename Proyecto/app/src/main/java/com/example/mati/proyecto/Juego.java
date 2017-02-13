package com.example.mati.proyecto;

/**
 * Created by mati on 28/11/16.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class Juego implements Serializable {

    private String nombre;
    private String genero;
    private int precio;


    public Juego(String nombre, String genero, int precio) {
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;

    }

    public String getNombre() {return nombre;}

    public String getGenero() {return genero;}

    public int getPrecio() {return precio;}

    public void setNombre(String modelo) {this.nombre = modelo;}

    public void setGenero(String marca) {this.genero = marca;}

    public void setPrecio(int precio) {this.precio = precio;}


}