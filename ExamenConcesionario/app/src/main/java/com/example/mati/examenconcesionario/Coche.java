package com.example.mati.examenconcesionario;

/**
 * Created by mati on 21/11/16.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class Coche implements Serializable {

    private String modelo;
    private String marca;
    private double precio;
    //private int foto;



    public Coche(String modelo, String marca, double precio) {
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;
       // this.foto = foto;
    }
    //public int getFoto() {return foto;   }

    //public void setFoto(int foto) {this.foto = foto;}

    public String getModelo() {return modelo;}

    public String getMarca() {return marca;}

    public double getPrecio() {return precio;}

    public void setModelo(String modelo) {this.modelo = modelo;}

    public void setMarca(String marca) {this.marca = marca;}

    public void setPrecio(double precio) {this.precio = precio;}

}