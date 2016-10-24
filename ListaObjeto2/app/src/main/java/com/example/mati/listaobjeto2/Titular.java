package com.example.mati.listaobjeto2;

/**
 * Created by mati on 24/10/16.
 */
public class Titular {

    private String Titulo;
    private String Subtitulo;
    private int Imagen;

    public int getImagen() {
        return Imagen;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getSubtitulo() {
        return Subtitulo;
    }

    public Titular(String subtitulo, String titulo, int imagen) {
        Subtitulo = subtitulo;
        Titulo = titulo;
        Imagen = imagen;
    }

}
