package com.example.usuario.trabajo_componentes;

import android.animation.Animator;
import android.animation.ValueAnimator;
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
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by usuario on 22/01/18.
 */

public class BarrasAgrupado extends View implements ValueAnimator.AnimatorUpdateListener {

    private Paint mPaintEjeX,mPaintEjeY,mPaintNumerosY,barra1,barra2,barra3,mPaintEmpresas;
    private int width,height;
    private float mLeft,mBottom,mTop,mRigth;
    private String mValor="2";
    private ValueAnimator mAnimator;
    private float mAnimatingFraction;
    private float numanios=Bd.grupos.size();

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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width=w;
        height=h;
        mLeft = getPaddingLeft()+150;
        mBottom = h - getPaddingBottom()-40;
        mTop = getPaddingTop()+350;
        mRigth = w - getPaddingRight();

    }




    private void cambiarValores(final int finalI, final int s){

        int aux,anio;
        String nombre;

        aux = Bd.grupos.get(finalI).getBarras()[s].getValor();
        nombre = Bd.grupos.get(0).getBarras()[s].getNombre();
        anio = Bd.grupos.get(finalI).getAnio();

        Snackbar.make(getRootView(),"Compañia "+nombre+" en el año "+anio+" valor: "+aux, Snackbar.LENGTH_LONG)
                .setAction("Cambiar Valor", new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        pedirnuevoValor(finalI,s);


                        //invalidate();
                    }
                })

                .show();

    }


    private void cambiarNombre(int s){

            pedirNombre(s);
    }


    private void pedirNombre(final int s){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.peticion_de_datos);
        builder.setTitle("Cambiar Nombre.");
        builder.setMessage("Introduza un nuevo nombre para esta empresa");

        final EditText input = new EditText(getContext());
        input.setTextColor(getResources().getColor(R.color.blanco));
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);


        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mValor = input.getText().toString();


                Bd.grupos.get(0).getBarras()[s].setNombre(mValor);
                mAnimator.start();
                invalidate();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();



        invalidate();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View view = getRootView();
        int x= (int) event.getX();
        int y = (int) event.getY();

        int eventaction = event.getAction();

        switch (eventaction){

            case MotionEvent.ACTION_DOWN:
//numanios
                float espacioderecha = (mRigth-mLeft)/numanios;

                float columnWidth = ((mRigth-mLeft)/numanios)/3;

                float espacio = width/3;


                    if(x>180&&x<80+espacio&&y<150){
                        int s = 0;
                        cambiarNombre(s);

                    }else if(x>180+espacio&&x<80+espacio*2&&y<150){
                        int s = 1;
                        cambiarNombre(s);
                    }else if(x>180+espacio*2&&x<mRigth&&y<150){
                        int s = 2;
                        cambiarNombre(s);

                    }


                for (int i = 0; i < 5; i++) {

                    try {


                        if (x > mLeft + (i * espacioderecha) && x < mLeft + columnWidth + (i * espacioderecha) && y > mTop) {
                            int s = 0;
                            cambiarValores(i, s);
                            invalidate();
                        } else if (x > mLeft + (i * espacioderecha) + columnWidth && x < mLeft + columnWidth * 2 + (i * espacioderecha) && y > mTop) {
                            int s = 1;
                            cambiarValores(i, s);

                            invalidate();
                        } else if (x > mLeft + (i * espacioderecha) + columnWidth * 2 && x < mLeft + columnWidth * 3 + (i * espacioderecha) && y > mTop) {
                            int s = 2;

                            cambiarValores(i, s);
                            invalidate();
                        }

                    }catch (Exception e){

                        Toast.makeText(getContext(),"Para que el componente funcione correctamente debes rellenar el array con todos sus campos",Toast.LENGTH_LONG).show();

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

    private void pedirnuevoValor(final int i, final int s) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.peticion_de_datos);
        builder.setTitle("Introduzca un nuevo valor.");
        builder.setMessage("Introduza un valor entre 0 y 100");

        final EditText input = new EditText(getContext());
        input.setTextColor(getResources().getColor(R.color.blanco));
        input.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(input);


        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                mValor = input.getText().toString();
                if(!mValor.isEmpty()) {
                    int valor = Integer.parseInt(mValor);
                    if (valor < 0 || valor > 100) {
                        Toast.makeText(getContext(), "Debes introducir un valor entre 0 y 100", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Bd.grupos.get(i).getBarras()[s].setValor(Integer.parseInt(mValor));
                    mAnimator.start();
                    invalidate();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
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

        mAnimator = new ValueAnimator();
        mAnimator.setDuration(400);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addUpdateListener(this);
        mAnimator.setFloatValues(0f,1f);

        mAnimator.start();

    }




    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

        mAnimatingFraction = animation.getAnimatedFraction();

        invalidate();

    }

    private void pintarEjes(Canvas canvas) {



        ejeXY(canvas);

        pintarBarras(canvas);

        empresas(canvas);

    }

    private float calcularTop(float valor){

        float altura = mBottom;
        float espacio = (mBottom - mTop) / 10f;

        altura=altura-(espacio*valor*mAnimatingFraction)/(10f);



        return altura;

    }


    private void pintarBarras(Canvas canvas) {


        float espacioderecha = (mRigth-mLeft)/numanios;

        float columnWidth = (((mRigth-mLeft)/numanios)/3)-5;



        for (int i = 0; i < numanios; i++) {

                try {


                    canvas.drawRect(mLeft + (i * espacioderecha) + 5, calcularTop(Bd.grupos.get(i).getBarras()[0].getValor()), mLeft + columnWidth + (i * espacioderecha), mBottom - 4, barra1);
                    canvas.drawRect(mLeft + (i * espacioderecha) + 5 + columnWidth, calcularTop(Bd.grupos.get(i).getBarras()[1].getValor()), mLeft + columnWidth * 2 + (i * espacioderecha), mBottom - 4, barra2);
                    canvas.drawRect(mLeft + (i * espacioderecha) + 5 + columnWidth * 2, calcularTop(Bd.grupos.get(i).getBarras()[2].getValor()), mLeft + columnWidth * 3 + (i * espacioderecha), mBottom - 4, barra3);

                }catch (NullPointerException e){

                    Toast.makeText(getContext(),"Para que el componente funcione correctamente debes rellenar el array con todos sus campos",Toast.LENGTH_LONG).show();

                }

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

            canvas.drawText(String.valueOf(cont),mLeft-90,y+10,mPaintNumerosY);
            cont-=10;
        }

        float espacioderecha = (mRigth-mLeft)/numanios;

        for (int i = 0; i <= numanios; i++) {
            y = mLeft + i * espacioderecha;
            canvas.drawLine(y, mTop, y, mBottom, mPaintEjeY);
            if(i<5) {
                canvas.drawText(String.valueOf(Bd.grupos.get(i).getAnio()), y + 80, mTop, mPaintNumerosY);
            }

        }

    }


    private void empresas(Canvas canvas) {

        float espacio = width/3;

        for (int i = 0; i < 3; i++) {
            if(i==0){
                canvas.drawRect(150, 150, 80, 80,barra1);
                mPaintEmpresas.setColor(getResources().getColor(Bd.grupos.get(0).getBarras()[i].getColor()));
                canvas.drawText(Bd.grupos.get(0).getBarras()[i].getNombre(),180,130,mPaintEmpresas);
            }else if(i==1) {
                mPaintEmpresas.setColor(getResources().getColor(Bd.grupos.get(0).getBarras()[i].getColor()));
                canvas.drawRect(150+espacio, 150 , 80+espacio, 80, barra2);
                canvas.drawText(Bd.grupos.get(0).getBarras()[i].getNombre(),180+espacio,130,mPaintEmpresas);
            }else{
                mPaintEmpresas.setColor(getResources().getColor(Bd.grupos.get(0).getBarras()[i].getColor()));
                canvas.drawRect(150+espacio*2, 150 , 80+espacio*2, 80 , barra3);
                canvas.drawText(Bd.grupos.get(0).getBarras()[i].getNombre(),180+espacio*2,130,mPaintEmpresas);
            }

        }



    }




    }









