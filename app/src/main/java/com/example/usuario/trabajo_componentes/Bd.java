package com.example.usuario.trabajo_componentes;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by usuario on 22/01/18.
 */

public class Bd {

    public static ArrayList<Grupo> grupos = new ArrayList<>();

    public static void rellenarDatos(){

        Barra[] barras = new Barra[3];
        Barra[] barras1 = new Barra[3];
        Barra[] barras2 = new Barra[3];
        Barra[] barras3 = new Barra[3];
        Barra[] barras4 = new Barra[3];

        barras[0] = new Barra(R.color.rojo,40);
        barras[1] = new Barra(R.color.azul,70);
        barras[2] = new Barra(R.color.verde,30);

        barras1[0] = new Barra(R.color.rojo,17);
        barras1[1] = new Barra(R.color.azul,89);
        barras1[2] = new Barra(R.color.verde,44);

        barras2[0] = new Barra(R.color.rojo,87);
        barras2[1] = new Barra(R.color.azul,35);
        barras2[2] = new Barra(R.color.verde,48);

        barras3[0] = new Barra(R.color.rojo,68);
        barras3[1] = new Barra(R.color.azul,87);
        barras3[2] = new Barra(R.color.verde,16);

        barras4[0] = new Barra(R.color.rojo,27);
        barras4[1] = new Barra(R.color.azul,45);
        barras4[2] = new Barra(R.color.verde,78);



        grupos.add(new Grupo(barras,1990));
        grupos.add(new Grupo(barras1,1991));
        grupos.add(new Grupo(barras2,1992));
        grupos.add(new Grupo(barras3,1993));
        grupos.add(new Grupo(barras4,1994));



    }



}
