package com.example.mati.alquiercoche.mati;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class Coche implements Serializable {

    private String modelo;
    private String marca;
    private double precio;

    public Coche(String modelo, String marca, double precio) {
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;
    }

    public String getModelo() {return modelo;}

    public String getMarca() {return marca;}

    public double getPrecio() {return precio;}

    public void setZona(String modelo) {this.modelo = modelo;}

    public void setContinente(String marca) {this.marca = marca;}

    public void setPrecio(double precio) {this.precio = precio;}

}