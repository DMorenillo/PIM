package com.example.mati.ejemploexamen;

import java.io.Serializable;

/**
 * Created by mati on 14/11/16.
 */
public class Destino implements Serializable {
    private String zona;
    private String continente;
    private int precio;

    public Destino(String zona, String continente, int precio) {
        this.zona = zona;
        this.continente = continente;
        this.precio = precio;
    }

    public String getZona() {
        return zona;
    }

    public String getContinente() {
        return continente;
    }

    public int getPrecio() {
        return precio;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
