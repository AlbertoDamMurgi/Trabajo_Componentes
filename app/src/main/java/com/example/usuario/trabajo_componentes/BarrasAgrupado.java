package com.example.usuario.trabajo_componentes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.AndroidException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by usuario on 22/01/18.
 */

public class BarrasAgrupado extends View{

    private Paint mPaintEjeX,mPaintEjeY,mPaintNumerosY,barra1,barra2,barra3,mPaintEmpresas;
    private int width,height;
    private float mLeft,mBottom,mTop,mRigth;
    private String mValor="2";
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

        width=w;
        height=h;
        mLeft = getPaddingLeft()+150;
        mBottom = h - getPaddingBottom()-40;
        mTop = getPaddingTop()+350;
        mRigth = w - getPaddingRight();

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
        View view = getRootView();
        int x= (int) event.getX();
        int y = (int) event.getY();
        int aux,anio;
        String nombre;
        int eventaction = event.getAction();
         /*
         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
         * */
        switch (eventaction){

            case MotionEvent.ACTION_DOWN:

                float espacioderecha = (mRigth-mLeft)/5f;

                float columnWidth = ((mRigth-mLeft)/5f)/3;



                for (int i = 0; i < 5; i++) {

                    if(x>mLeft+(i*espacioderecha)&&x<mLeft+columnWidth+(i*espacioderecha)&&y>mTop){

                        aux = Bd.grupos.get(i).getBarras()[0].getValor();
                        nombre = Bd.grupos.get(0).getBarras()[0].getNombre();
                        anio = Bd.grupos.get(i).getAnio();

                        final int finalI = i;
                        Snackbar.make(view,"Compañia "+nombre+" en el año "+anio+" valor: "+aux, Snackbar.LENGTH_LONG)
                                .setAction("Cambiar Valor", new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        pedirnuevoValor();
                                        Bd.grupos.get(finalI).getBarras()[0].setValor(Integer.parseInt(mValor));
                                        Toast.makeText(getContext(),"asdasd",Toast.LENGTH_LONG).show();
                                        invalidate();
                                    }
                                }).show();

                        /* Bd.grupos.get(i).getBarras()[0].setValor(88);
                                        Toast.makeText(getContext(),"asdasd",Toast.LENGTH_LONG).show();
                                        invalidate();*/

                    }else if(x>mLeft+(i*espacioderecha)+columnWidth&&x<mLeft+columnWidth*2+(i*espacioderecha)&&y>mTop){

                        aux = Bd.grupos.get(i).getBarras()[1].getValor();
                        nombre = Bd.grupos.get(0).getBarras()[1].getNombre();
                        anio = Bd.grupos.get(i).getAnio();
                        Snackbar.make(getRootView(),"Compañia "+nombre+" en el año "+anio+" valor: "+aux, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();


                    }else if(x>mLeft+(i*espacioderecha)+columnWidth*2&&x<mLeft+columnWidth*3+(i*espacioderecha)&&y>mTop){

                        aux = Bd.grupos.get(i).getBarras()[2].getValor();
                        nombre = Bd.grupos.get(0).getBarras()[2].getNombre();
                        anio = Bd.grupos.get(i).getAnio();
                        Snackbar.make(getRootView(),"Compañia "+nombre+" en el año "+anio+" valor: "+aux, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }

                }

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;



        }


        return true;
    }

    private void pedirnuevoValor() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Title");

// Set up the input
        final EditText input = new EditText(getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mValor = input.getText().toString();
                invalidate();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

        invalidate();



    }

    private void init(){

        mPaintEjeX = new Paint();
        mPaintEjeX.setColor(Color.BLACK);
        mPaintEjeX.setStyle(Paint.Style.STROKE);
        mPaintEjeX.setStrokeWidth(10F);

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



        ejeXY(canvas);

        pintarBarras(canvas);


    }

    private float calcularTop(float valor){

        float altura = mBottom;
        float espacio = (mBottom - mTop) / 10f;

        altura=altura-(espacio*valor)/(10f);

        return altura;

    }


    private void pintarBarras(Canvas canvas) {


        float espacioderecha = (mRigth-mLeft)/5f;

        float columnWidth = ((mRigth-mLeft)/5f)/3;



        for (int i = 0; i < 5; i++) {

            canvas.drawRect(mLeft+(i*espacioderecha), calcularTop(Bd.grupos.get(i).getBarras()[0].getValor()), mLeft+columnWidth+(i*espacioderecha), mBottom, barra1);
            canvas.drawRect(mLeft+(i*espacioderecha)+columnWidth, calcularTop(Bd.grupos.get(i).getBarras()[1].getValor()), mLeft+columnWidth*2+(i*espacioderecha), mBottom, barra2);
            canvas.drawRect(mLeft+(i*espacioderecha)+columnWidth*2, calcularTop(Bd.grupos.get(i).getBarras()[2].getValor()), mLeft+columnWidth*3+(i*espacioderecha), mBottom, barra3);


        }


    }

    private void ejeXY(Canvas canvas) {

        canvas.drawLine(mLeft, mBottom, mLeft, mTop,
                mPaintEjeX);
        canvas.drawLine(mLeft, mBottom, mRigth, mBottom,
                mPaintEjeX);

        float espacio = (mBottom - mTop) / 10f;

        float y;
        int cont = 100;
        for (int i = 0; i <= 10; i++) {
            y = mTop + i * espacio;
            //canvas.drawLine(mLeft, y, mRigth, y, mPaintEjeY);
            canvas.drawText(String.valueOf(cont),mLeft-90,y+10,mPaintNumerosY);
            cont-=10;
        }

        float espacioderecha = (mRigth-mLeft)/5f;

        for (int i = 0; i <= 5; i++) {
            y = mLeft + i * espacioderecha;
            canvas.drawLine(y, mTop, y, mBottom, mPaintEjeY);
            if(i<5) {
                canvas.drawText(String.valueOf(Bd.grupos.get(i).getAnio()), y + 80, mTop, mPaintNumerosY);
            }

        }

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
