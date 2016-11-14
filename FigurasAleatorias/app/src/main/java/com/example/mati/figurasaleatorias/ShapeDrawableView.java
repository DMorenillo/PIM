package com.example.mati.figurasaleatorias;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mati on 9/11/16.
 */
public class ShapeDrawableView extends View {
    private List<ShapeDrawable> shapes = new ArrayList<ShapeDrawable>();
    private Integer[] mColors= {Color.BLACK,Color.BLUE,Color.GREEN,Color.RED};

    public ShapeDrawableView(Context context){
        super(context);
    }
    public ShapeDrawableView (Context context, AttributeSet attrs){
        super (context,attrs);
    }
    @Override
    protected void onDraw (Canvas canvas){
        super.onDraw(canvas);
        for(ShapeDrawable shape: shapes){shape.draw(canvas);}
    }
    @Override
    public  boolean onTouchEvent (MotionEvent event){
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            int x = (int)event.getX();
            int y = (int)event.getY();
            if (!isDeletingExistingShape(x,y)){
                shapes.add(makeShapeDrawable(x,y));
            }
            invalidate();
            return (true);
        } else {
            return (false);
        }
    }
    private boolean isDeletingExistingShape(int x, int y){
        for (ShapeDrawable shape: shapes){
            Rect bounds = shape.getBounds();
            if ( bounds.contains(x,y)){
                shapes.remove(shape);
                return(true);
            }
        }
        return(false);
    }
    private ShapeDrawable makeShapeDrawable(int x, int y){
        int maxWidth = getWidth()/10;
        int maxHeigth = getHeight()/10;
        Shape shape;
        if (Math.random()<0.5){
            shape = new OvalShape();
        } else {
            shape = new RectShape();
        }
        ShapeDrawable shapeD = new ShapeDrawable(shape);
        int width = RandomUtils.randomInt(maxWidth);
        int heigth = RandomUtils.randomInt(maxHeigth);
        shapeD.setBounds(x-width/2,y-heigth/2,x+width/2,y+heigth/2);
        shapeD.getPaint().setColor(RandomUtils.randomElement(mColors));
        return (shapeD);
    }
}
