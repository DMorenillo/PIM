package com.example.mati.figurasaleatorias;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by mati on 7/11/16.
 */
public class RandomShapeView extends View {
    private Integer[] mBackgrounds = {Color.CYAN, Color.GRAY, Color.LTGRAY, Color.MAGENTA, Color.YELLOW, Color.WHITE};
    private Paint[] mForegrounds = {makePaint(Color.BLACK), makePaint(Color.BLUE), makePaint(Color.GREEN), makePaint(Color.RED)};
    private Bitmap[] mPics = {
            makeBitmap(R.drawable.astronauta), makeBitmap(R.drawable.fiesta),
            makeBitmap(R.drawable.diablo), makeBitmap(R.drawable.mascara), makeBitmap(R.drawable.teemin)};
    private String mMessage = "Android";

    public RandomShapeView(Context context) {
        super(context);
    }

    public RandomShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(RandomUtils.randomElement(mBackgrounds));
        int viewWidth = getWidth();
        int viewHeigth = getHeight();
        int avgShapeWidth = viewWidth / 5;
        for (int i = 0; i < 20; i++) {
            drawRandomCircle(canvas, viewWidth, viewHeigth, avgShapeWidth);
            drawRandomRect(canvas, viewWidth, viewHeigth, avgShapeWidth);
            drawRandomBitmap(canvas, viewWidth, viewHeigth);
            drawRandomText(canvas, viewWidth, viewHeigth, avgShapeWidth);
        }
    }
    private Paint makePaint(int color) {
        Paint p = new Paint();
        p.setColor(color);
        return (p);
    }

    private Bitmap makeBitmap(int bitmapId) {
        return (BitmapFactory.decodeResource(getResources(), bitmapId));
    }

    private void drawRandomCircle(Canvas canvas, int viewWidth, int viewHeigth, int avgShapeWidth) {
        float x = RandomUtils.randomFloat(viewWidth);
        float y = RandomUtils.randomFloat(viewHeigth);
        float radius = RandomUtils.randomFloat(avgShapeWidth / 2);
        Paint circleColor = RandomUtils.randomElement(mForegrounds);
        canvas.drawCircle(x, y, radius, circleColor);
    }

    private void drawRandomRect(Canvas canvas, int viewWidth, int viewHeigth, int avgShapeWidth) {
        float left = RandomUtils.randomFloat(viewWidth);
        float top = RandomUtils.randomFloat(viewHeigth);
        float width = RandomUtils.randomFloat(avgShapeWidth);
        float right = left + width;
        float bottom = top + width;
        Paint squareColor = RandomUtils.randomElement(mForegrounds);
        canvas.drawRect(left,top,right,bottom,squareColor);
    }
    private void drawRandomBitmap(Canvas canvas, int viewWidth, int viewHeigth){
        float left = RandomUtils.randomFloat(viewWidth);
        float top = RandomUtils.randomFloat(viewHeigth);
        Bitmap pic = RandomUtils.randomElement(mPics);
        canvas.drawBitmap(pic,left,top,null);
    }
    private void drawRandomText(Canvas canvas,int viewWidth, int viewHeigth, int avgShapeWidth){
        float x = RandomUtils.randomFloat(viewWidth);
        float y = RandomUtils.randomFloat(viewHeigth);
        float textSize = RandomUtils.randomFloat(avgShapeWidth);
        Paint textPaint = RandomUtils.randomElement(mForegrounds);
        textPaint.setTextSize(textSize);
        canvas.drawText(mMessage,x,y,textPaint);
    }
}