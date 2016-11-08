package com.example.mati.spinnerobjeto;

/**
 * Created by mati on 19/10/16.
 */
public class Titular {

    private String Titulo;
    private String Subtitulo;
    private int imagen;

    public int getImagen() {
        return imagen;
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
        imagen = imagen;
    }

}