package com.example.mati.shapedrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mati on 7/11/16.
 */
public class VistaAMedida extends View {
    private ShapeDrawable miDrawable;
    public VistaAMedida(Context context){
        super(context);

    }
    public VistaAMedida(Context context, AttributeSet atts) {
        super(context, atts);
    }
    protected void onDraw (Canvas canvas){
        int x=10, y=100;
        int ancho= 300, alto =500;
        miDrawable = new ShapeDrawable(new OvalShape());
        miDrawable.getPaint().setColor(Color.BLACK);
        miDrawable.setBounds(x,y,x+ancho,y+alto);
        miDrawable.draw(canvas);
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
        canvas.drawArc(300,800,1100,2000,0,90,false,pincel);

        pincel.setColor(Color.GREEN);
        canvas.drawRect(400,400,1000,1000,pincel);
    }
}