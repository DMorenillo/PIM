package com.example.mati.solobici;

import android.app.Activity;
import android.os.Bundle;

public class Juego extends Activity {

    private VistaJuego vistajuego;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);

        vistajuego = (VistaJuego)findViewById(R.id.VistaJuego);
    }
    /*@Override protected void onDestroy(){
        vistajuego.getHilo().detener();
        super.onDestroy();
    }*/
    @Override
    protected void onPause(){
        super.onPause();
        vistajuego.getHilo().pausar();
    }
    /*@Override
    protected void onResume(){
        super.onResume();
        vistajuego.getHilo().reanudar();
    }*/

}