package com.example.mati.pasodeobjetos;


public class Persona {
    private String nombre;
    private int edad;
    private  int imagen;

    public Persona() {
    }

    public Persona (String str, int age){
        this.nombre = str;
        this.edad = age;
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
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "edad=" + edad +
                ", imagen=" + imagen +
                '}';
    }
}
