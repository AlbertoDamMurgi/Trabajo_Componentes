package com.example.usuario.trabajo_componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AndroidException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by usuario on 22/01/18.
 */

public class BarrasAgrupado extends View{

    private Paint mPaintEjeX,mPaintEjeY,mPaintNumerosY,barra1,barra2,barra3,mPaintEmpresas;
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

        //barras(canvas);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w-(getPaddingLeft()+getPaddingRight());
        height=h-(getPaddingTop()+getPaddingBottom());

        invalidate();
    }
/*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=MeasureSpec.getSize(widthMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }
*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x= (int) event.getX();
        int y = (int) event.getY();

        int eventaction = event.getAction();

        switch (eventaction){

            case MotionEvent.ACTION_DOWN:
                int espacio = width/6;
                int cont = 0;

                for (int i = 0; i < width ; i=espacio+i) {

                    if(cont<5) {
                        //fila a
                        if(x>getPaddingLeft()+150+i&&x<getPaddingLeft()+150+espacio/3+i&&y>getPaddingTop()+(width/2)){
                            Toast.makeText(getContext(),String.valueOf(Bd.grupos.get(cont).getBarras()[0].getValor()),Toast.LENGTH_SHORT).show();


                           // invalidate();
                            //fila b
                        }else if(x>getPaddingLeft()+150+(espacio/3)+i&&x<getPaddingLeft()+150+(espacio/3)*2+i&&y>getPaddingTop()+(width/2)){
                            Toast.makeText(getContext(),String.valueOf(Bd.grupos.get(cont).getBarras()[1].getValor()),Toast.LENGTH_SHORT).show();
                        }else if(x>getPaddingLeft()+150+(espacio/3)*2+i&&x<getPaddingLeft()+150+espacio+i&&y>getPaddingTop()+(width/2)){
                            //fila c
                            Toast.makeText(getContext(),String.valueOf(Bd.grupos.get(cont).getBarras()[2].getValor()),Toast.LENGTH_SHORT).show();
                        }


                    }
                    cont++;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;



        }


        return true;
    }

    private void init(){

        mPaintEjeX = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintEjeX.setColor(Color.BLACK);
        mPaintEjeX.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintEjeX.setStrokeWidth(2F);

        mPaintEjeY = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintEjeY.setColor(getResources().getColor(R.color.negro));
        mPaintEjeY.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintEjeY.setStrokeWidth(4F);

        mPaintNumerosY = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintNumerosY.setColor(Color.BLACK);
        mPaintNumerosY.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintNumerosY.setTextSize(50);

        mPaintEmpresas = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaintEmpresas.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintEmpresas.setTextSize(50);

        barra1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        barra1.setColor(getResources().getColor(Bd.grupos.get(0).getBarras()[0].getColor()));
        barra1.setStyle(Paint.Style.FILL_AND_STROKE);
        //barra1.setMaskFilter(new EmbossMaskFilter(new float[] {1, 1, 1}, .4f, 6f, 3.5f));


        barra2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        barra2.setColor(getResources().getColor(Bd.grupos.get(0).getBarras()[1].getColor()));

        barra2.setStyle(Paint.Style.FILL_AND_STROKE);


        barra3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        barra3.setColor(getResources().getColor(Bd.grupos.get(0).getBarras()[2].getColor()));
        barra3.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    private void pintarEjes(Canvas canvas) {

        ejeX(canvas);

        ejeY(canvas);

        anios(canvas);

        barras(canvas);

        empresas(canvas);

    }

    private void empresas(Canvas canvas) {
        int cont=0;

        for (int i = 0; i < 3; i++) {
            if(i==0){
                canvas.drawRect(150, 150+cont, 80, 80+cont,barra1);
                mPaintEmpresas.setColor(getResources().getColor(Bd.grupos.get(0).getBarras()[i].getColor()));
                canvas.drawText(Bd.grupos.get(0).getBarras()[i].getNombre(),180,130+cont,mPaintEmpresas);
            }else if(i==1) {
                mPaintEmpresas.setColor(getResources().getColor(Bd.grupos.get(0).getBarras()[i].getColor()));
                canvas.drawRect(150, 150 + cont, 80, 80 + cont, barra2);
                canvas.drawText(Bd.grupos.get(0).getBarras()[i].getNombre(),180,130+cont,mPaintEmpresas);
            }else{
                mPaintEmpresas.setColor(getResources().getColor(Bd.grupos.get(0).getBarras()[i].getColor()));
                canvas.drawRect(150, 150 + cont, 80, 80 + cont, barra3);
                canvas.drawText(Bd.grupos.get(0).getBarras()[i].getNombre(),180,130+cont,mPaintEmpresas);
            }
            cont+=150;
        }



    }

    private void barras(Canvas canvas) {
        int espacio = width/6;
        int cont = 0;
        Log.e("pintando barras","pintando barras");
        for (int i = 0; i < width ; i=espacio+i) {

            if(cont<5) {
                canvas.drawRect(getPaddingLeft() + 150 + i,calcularTop(Bd.grupos.get(cont).getBarras()[0].getValor()), getPaddingLeft() + 150 + espacio / 3 + i, height - getPaddingBottom() - 60, barra1);
                canvas.drawRect(getPaddingLeft() + 150 + (espacio / 3) + i, calcularTop(Bd.grupos.get(cont).getBarras()[1].getValor()), getPaddingLeft() + 150 + (espacio / 3) * 2 + i, height - getPaddingBottom() - 60, barra2);
                canvas.drawRect(getPaddingLeft() + 150 + (espacio / 3) * 2 + i, calcularTop(Bd.grupos.get(cont).getBarras()[2].getValor()), getPaddingLeft() + 150 + (espacio) + i, height - getPaddingBottom() - 60, barra3);
            }
            cont++;
        }

       // top de la linea de la y getPaddingTop() + (width / 2)

        //bot de la y            height - getPaddingBottom() - 60
    }

    private void anios(Canvas canvas) {
        int cont = 0;

        int total = width/6;
        int nuevotop = getPaddingTop() + (width / 2);

        Log.e("total",String.valueOf(total));



        for (int i = 0; i < width ; i=total+i) {
            if(cont<5) {
                canvas.drawText(String.valueOf(Bd.grupos.get(cont).getAnio()), total + i-20, nuevotop, mPaintNumerosY);
                cont++;
            }
                //canvas.drawLine(getPaddingLeft() + 150 + i, getPaddingTop() + (width / 2), getPaddingLeft() + 150 + i, height - getPaddingBottom() - 60, mPaintEjeY);
            }





    }

    private float calcularTop(float valor){

       float altura= height - getPaddingBottom() - 60;


        altura=altura-((width/6)*valor)/(20);


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
