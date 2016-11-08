package com.example.mati.primerosdibujos2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new VistaAMedida(this));
    }
    public class VistaAMedida extends View {
        private ShapeDrawable miDrawable;
        public VistaAMedida(Context context){
            super(context);
            int x=10, y=100;
            int ancho= 300, alto =500;
            miDrawable = new ShapeDrawable(new OvalShape());
            miDrawable.getPaint().setColor(Color.BLACK);
            miDrawable.setBounds(x,y,x+ancho,y+alto);
        }
        protected void onDraw (Canvas canvas){
            miDrawable.draw(canvas);
        }
    }

}
