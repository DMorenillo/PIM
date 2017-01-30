package com.example.mati.solobici;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;

public class VistaJuego extends View implements SensorEventListener {
    //	COCHES	//
    private Vector<Grafico> Coches;	//Vector con los Coches
    private int numCoches = 5;		//Numero inicial de Coches
    private int numMotos = 3;		//Fragmentos/Motos en que se dividir un Coche

    // BICI //
    private Grafico bici;
    private int giroBici;	//Incremento direccion de la bici
    private float aceleracionBici;//Aumento de velocidad en la bici
    private static final int PASO_GIRO_BICI = 5;
    private static final float PASO_ACELERACION_BICI = 1.5f;

    // THREAD Y TIEMPO //
    //Hilo encargado de procesar el tiempo
    private HiloJuego hiloJuego;
    //Tiempo que debe transcurrir para procesar cambios (ms)
    private static int PERIODO_PROCESO = 50;
    //Momento en el que se realizó el último proceso
    private long ultimoProceso = 0;

    //PANTALLA TACTIL
    // Las variables mX y mY se utilizaran para recordar
    // las coordenadas del ultimo evento.
    private float mX=0, mY=0;
    private boolean disparo = false;

    //RUEDA
    private Grafico rueda;
    private static int VELOCIDAD_RUEDA =12;
    private boolean ruedaActiva;
    private int distanciaRueda;

    //VARIABLES GLOBALES
    //Controlar si la aplicacion esta en segundo plano
    private boolean corriendo = false;
    //Controlar si la aplicacion esta en pausa
    private boolean pausa;


    public VistaJuego(Context contexto, AttributeSet atributos) {
        super(contexto, atributos);
        Drawable graficoBici, graficoCoche, graficoRueda;
        //Obtenemos la imagen/recurso del coche
        graficoCoche = contexto.getResources().getDrawable(R.drawable.coche);

        //Creamos un vector para contener todos los coches que se ven en la pantalla
        //y lo rellenamos con graficos de coches
        // con valores aleatorios para su velocidad, direccion y rotacion.
        Coches = new Vector<Grafico>();
        for (int i=0; i<numCoches; i++) {
            Grafico coche = new Grafico(this, graficoCoche);
            coche.setIncX(Math.random() * 4 - 2);
            coche.setIncY(Math.random() * 4 - 2);
            coche.setAngulo((int) (Math.random() * 360));
            coche.setRotacion((int) (Math.random() * 8 - 4));
            Coches.add(coche);
        }

        //BICI
        graficoBici = contexto.getResources().getDrawable(R.drawable.bici);
        bici = new Grafico(this, graficoBici);
        // CONTROL DEL HILO DEL JUEGO
        corriendo = true;

        hiloJuego = new HiloJuego();
        hiloJuego.start();
        // REGISTRO DE SENSORES
        SensorManager miSensorManager= (SensorManager)getContext().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> listaSensores = miSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if(!listaSensores.isEmpty()){
            Sensor sensorOrientacion = listaSensores.get(0);
            miSensorManager.registerListener(this, sensorOrientacion, SensorManager.SENSOR_DELAY_UI);
        }
        //RUEDA
        graficoRueda = contexto.getResources().getDrawable(R.drawable.rueda);
        rueda = new Grafico(this, graficoRueda);
        ruedaActiva = false;

        bici.setIncX(Math.random() * 10 - 2);
        bici.setIncY(Math.random() * 10 - 2);
        bici.setAngulo((int) (Math.random() * 360));
        bici.setRotacion((int) (Math.random() * 8 - 4));
    }

    //Al comenzar y dibujar por primera vez la pantalla del juego
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //Dibujamos los coches en posiciones aleatorias
        for (Grafico coche: Coches) {
            do {
                coche.setPosX(Math.random()*(w-coche.getAncho()));
                coche.setPosY(Math.random()*(h-coche.getAlto()));
            } while (coche.distancia(bici) < (w+h)/5);
        }

        //Para la bici ponemos la posicion central de la pantalla
        bici.setPosX((w-bici.getAncho())/2);
        bici.setPosY((h-bici.getAlto())/2);

        //HILO QUE CONTROLA EL JUEGO
        hiloJuego = new HiloJuego();
        hiloJuego.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Dibujamos cada uno de los coches
        for (Grafico coche: Coches) {
            coche.dibujaGrafico(canvas);
        }

        //Dibujamos la bici
        bici.dibujaGrafico(canvas);
        //Dibujamos la rueda si lo indica la variable ruedaActiva
        if(ruedaActiva)
            rueda.dibujaGrafico(canvas);
    }
    /*
    private class HiloJuego extends Thread {
        @Override
        public void run() {
            while (true) {
                actualizaMovimiento();
            }
        }
    }
    */
    class HiloJuego extends Thread {
        private boolean pausa, corriendo;

        @Override
        public void run() {
            corriendo=true;
            while(corriendo) {
            actualizaMovimiento();
            synchronized(this){
                while(pausa) {
                try {
                    wait();
                    } catch (Exception e) {
                        }
                    }
                }
            } // del while
        } //del metodo run
        public synchronized void pausar() {
            pausa = true;
        }
        /*public synchronized void reanudar() {
            pausa = false;
            notify();
        }
        public void detener() {
            corriendo = false;
            if (pausa) reanudar();
        }*/
    } // de la clase HiloJuego

    protected synchronized void actualizaMovimiento() {
        long ahora = System.currentTimeMillis();
        // No hacemos nada si el período de proceso no se ha cumplido.
        if (ultimoProceso + PERIODO_PROCESO > ahora) {
            return;
        }
        // Para una ejecución en tiempo real calculamos retardo
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        // Actualizamos la posición de la bici
        bici.setAngulo((int) (bici.getAngulo() + giroBici * retardo));
        double nIncX = bici.getIncX() + aceleracionBici * Math.cos(Math.toRadians(bici.getAngulo())) * retardo;
        double nIncY = bici.getIncY() + aceleracionBici * Math.sin(Math.toRadians(bici.getAngulo())) * retardo;
        if (Grafico.distanciaE(0, 0, nIncX, nIncY) <= Grafico.getMaxVelocidad()) {
            bici.setIncX(nIncX);
            bici.setIncY(nIncY);
        }
        bici.incrementaPos();
        bici.setIncX(0);
        bici.setIncY(0);

        //Movemos los coches
        for (Grafico coche : Coches) {
            coche.incrementaPos();
        }
        ultimoProceso = ahora;

        if (ruedaActiva) {//Movemos rueda
            rueda.incrementaPos();
            distanciaRueda--;
            if (distanciaRueda < 0) {
                ruedaActiva = false;
            } else {
                for (int i = 0; i < Coches.size(); i++) {
                    if (rueda.verificaColision(Coches.elementAt(i))) {
                        destruyeCoche(i);
                        i = Coches.size();
                        ruedaActiva = false;
                    }
                }
            }
        }
    }
    @Override
    public boolean onKeyDown (int codigoTecla, KeyEvent evento){
        super.onKeyDown(codigoTecla, evento);
        //Procesamos la pulsacion
        boolean pulsacion =true;
        switch (codigoTecla)
        {
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionBici=+PASO_ACELERACION_BICI;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                giroBici=-PASO_GIRO_BICI;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroBici=+PASO_GIRO_BICI;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                lanzarRueda();
                break;
            default:
                //Si estamos aqui no hemos pulsado nada que nos interese
                pulsacion=false;
                break;
        }
        return pulsacion;
    }

    public boolean onKeyUp(int codigoTecla, KeyEvent evento){ // TECLA DEJAR DE SER PULSADA
        super.onKeyUp(codigoTecla, evento);
        //Procesamos la pulsacion
        boolean pulsacion=true;
        switch (codigoTecla){
            case KeyEvent.KEYCODE_DPAD_UP:
               //aceleracionBici=0;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroBici=0;
                break;
            default:
                //Si estamos aqui, no hemos pulsado nada que interese
                    pulsacion=false;
                    break;
        }
        return pulsacion;
    }
    @Override
   public  boolean onTouchEvent (MotionEvent evento){
        super.onTouchEvent(evento);
        //Obtenemos la posicion de la pulsacion
        float x= evento.getX();
        float y= evento.getY();
        switch (evento.getAction()){
            //Al comenzar pulsacion (ACTION_DOWN) se activa la variable disparo
            case MotionEvent.ACTION_DOWN:
                disparo=true;
                break;
            //Comprobar pulsacion continuada con desplazamiento hor/ver.
            //Si es asi, desactvamos disparo: se tratara de un movimiento
            //se trata de un moviemto en vez de un disparo
            case MotionEvent.ACTION_MOVE:
                float dx=Math.abs(x-mX);
                float dy=Math.abs(y-mY);
                //Un desplazamiento del dedo horizontal hace girar la bici.
                if (dy<6 && dx>6)
                {
                    giroBici=Math.round((x-mX)/2);
                    disparo = false;
                } else //Un desplazamiento vertical produce una aceleracion.
                if (dx<6 && dy>6){
                    aceleracionBici=Math.round((mY-y)/25);
                    disparo=false;
                }
                break;
            //Si se levanta el dedo (ACTION_UP) sin haberse producido desplazamiento horizontal o vertical
            //disparo estara activo y lo que hacemos es disparar
            case MotionEvent.ACTION_UP:
                giroBici=0;
                aceleracionBici=0;
                if(disparo){
                    lanzarRueda();
                }
                break;
        }
        mX=x;
        mY=y;
        return true;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
        // TODO Auto-generated methos stub
    }
    private boolean hayValorInicial = false;
    private float valorInicial;

    @Override
    public void onSensorChanged(SensorEvent evento){
        float valor = evento.values[1];
        if (!hayValorInicial){
            valorInicial=valor;
            hayValorInicial= true;
        }
        giroBici= (int) (valor-valorInicial)/3;
    }
    private void destruyeCoche(int i){
        Coches.remove(i);
        ruedaActiva=false;
        //Activamos el sonido de explosion
        MediaPlayer miMediaPlayer=MediaPlayer.create(getContext(),R.raw.explision);
        miMediaPlayer.start();
    }
    private void lanzarRueda(){
        rueda.setPosX(bici.getPosX()+bici.getAncho()/2-rueda.getAncho()/2);
        rueda.setPosY(bici.getPosY()+bici.getAlto()/2-rueda.getAlto()/2);
        rueda.setAngulo(bici.getAngulo());
        rueda.setIncX(Math.cos(Math.toRadians(rueda.getAngulo()))*VELOCIDAD_RUEDA);
        rueda.setIncY(Math.sin(Math.toRadians(rueda.getAngulo()))*VELOCIDAD_RUEDA);
        distanciaRueda=(int)Math.min(
                this.getWidth()/Math.abs(rueda.getIncX()),
                this.getHeight()/Math.abs(rueda.getIncY()))-2;
        ruedaActiva=true;

    }
    public HiloJuego getHilo(){
        return hiloJuego;
    }
    public void setCorriendo(boolean corriendo){
        this.corriendo= corriendo;
    }
    public void setPausa(boolean pausa){
        this.pausa= pausa;
    }
}