package com.example.mati.pasodeobjetos;


import java.io.Serializable;

public class Persona implements Serializable{
    private String nombre;
    private int edad;
    private  int Imagen;


    public Persona()  {
    }

    public Persona (String str, int age, int Imagen){
        this.nombre = str;
        this.edad = age;
        this.Imagen = Imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        this.Imagen = imagen;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "edad=" + edad +
                ", imagen=" + Imagen +
                '}';
    }
}
