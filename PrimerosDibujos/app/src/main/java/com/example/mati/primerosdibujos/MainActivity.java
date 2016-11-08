package com.example.mati.primerosdibujos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EjemploView(this));
    }

    public class EjemploView extends View {
        public EjemploView(Context contexto) {
            super(contexto);
        }
        protected void onDraw(Canvas canvas) {
            Paint pincel = new Paint();
            Paint punto = new Paint();
            Paint punto2 = new Paint();
            Paint linea = new Paint();
            //Estilo para el circulo
            pincel.setColor(Color.GRAY);
            pincel.setStrokeWidth(30);
            pincel.setStyle(Paint.Style.STROKE);

            //Estilo para punto

            punto.setColor(Color.RED);
            punto.setStrokeWidth(50);
            punto.setStyle(Paint.Style.STROKE);

            //Estilo para punto2

            punto2.setColor(Color.BLUE);
            punto2.setStrokeWidth(100);
            punto2.setStyle(Paint.Style.STROKE);

            //Estilo para linea

            linea.setColor(Color.MAGENTA);
            linea.setStrokeWidth(50);
            linea.setStyle(Paint.Style.STROKE);

            canvas.drawCircle(700, 700, 100, pincel);
            canvas.drawPoint(300, 300, punto);
            canvas.drawPoint(1100,300, punto2);
            canvas.drawLine(400,1100,1100,1100,linea);

            pincel.setColor(Color.YELLOW);
            canvas.drawArc(300,1200,1100,2000,0,90,false,pincel);

            pincel.setColor(Color.GREEN);
            canvas.drawRect(400,400,1000,1000,pincel);
        }


    }
}
