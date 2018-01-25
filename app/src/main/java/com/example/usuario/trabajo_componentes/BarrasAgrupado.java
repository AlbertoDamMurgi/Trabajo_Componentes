package com.example.usuario.trabajo_componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AndroidException;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by usuario on 22/01/18.
 */

public class BarrasAgrupado extends View{

    private Paint mPaintEjeX,mPaintEjeY,mPaintNumerosY,barra1,barra2,barra3;
    private int width,height;

    public BarrasAgrupado(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BarrasAgrupado,0,0
        );

        init();

        try {

        }finally {
            a.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        pintarEjes(canvas);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=MeasureSpec.getSize(widthMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    private void init(){

        mPaintEjeX = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintEjeX.setColor(Color.BLACK);
        mPaintEjeX.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintEjeX.setStrokeWidth(2F);

        mPaintEjeY = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintEjeY.setColor(getResources().getColor(android.R.color.darker_gray));
        mPaintEjeY.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintEjeY.setStrokeWidth(4F);

        mPaintNumerosY = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintNumerosY.setColor(Color.BLACK);
        mPaintNumerosY.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintNumerosY.setTextSize(50);

        barra1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        barra1.setColor(Color.GREEN);
        barra1.setStyle(Paint.Style.FILL_AND_STROKE);

        barra2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        barra2.setColor(Color.RED);
        barra2.setStyle(Paint.Style.FILL_AND_STROKE);

        barra3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        barra3.setColor(Color.BLUE);
        barra3.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private void pintarEjes(Canvas canvas) {

        ejeX(canvas);

        ejeY(canvas);

        //anios(canvas);

        barras(canvas);

    }

    private void barras(Canvas canvas) {
        int espacio = width/6;
        int cont = 0;
        for (int i = 0; i < width ; i=espacio+i) {

            if(cont<5) {
                canvas.drawRect(getPaddingLeft() + 150 + i,calcularTop(120), getPaddingLeft() + 150 + espacio / 3 + i, height - getPaddingBottom() - 60, barra1);
                canvas.drawRect(getPaddingLeft() + 150 + (espacio / 3) + i, getPaddingTop() + (height / 2), getPaddingLeft() + 150 + (espacio / 3) * 2 + i, height - getPaddingBottom() - 60, barra2);
                canvas.drawRect(getPaddingLeft() + 150 + (espacio / 3) * 2 + i, getPaddingTop() + (height / 2), getPaddingLeft() + 150 + (espacio) + i, height - getPaddingBottom() - 60, barra3);
            }
            cont++;
        }

       // top de la linea de la y getPaddingTop() + (width / 2)

        //bot de la y            height - getPaddingBottom() - 60
    }

    private void anios(Canvas canvas) {

        int total = width-150-getPaddingLeft();
        total = total/5;




    }

    private float calcularTop(float valor){

        float altura;

        altura=(valor*(getPaddingTop()+(width/2)))/100;


        return altura;
    }

    private void ejeX(Canvas canvas) {

        canvas.drawLine(getPaddingLeft()+150,height-getPaddingBottom()-60,width-getPaddingRight()-90,height-getPaddingBottom()-60,mPaintEjeX);

    }

    private void ejeY(Canvas canvas) {

        canvas.drawLine(getPaddingLeft()+150,getPaddingTop()+(width/2),getPaddingLeft()+150,height-getPaddingBottom()-60,mPaintEjeY);
        int espacio = width/6;
        int cont = 0;
        for (int i = 0; i < width ; i=espacio+i) {
            if(cont<=100) {
                canvas.drawText(String.valueOf(cont), getPaddingLeft() + 20, height - getPaddingBottom() - i - 60, mPaintNumerosY);
                canvas.drawLine(getPaddingLeft() + 150 + i, getPaddingTop() + (width / 2), getPaddingLeft() + 150 + i, height - getPaddingBottom() - 60, mPaintEjeY);
            }
            cont+=20;

        }

    }
}
