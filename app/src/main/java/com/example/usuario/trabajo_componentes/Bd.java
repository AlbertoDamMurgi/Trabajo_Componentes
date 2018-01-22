package com.example.usuario.trabajo_componentes;

import java.util.ArrayList;

/**
 * Created by usuario on 22/01/18.
 */

public class Bd {

    public static ArrayList<Grupo> grupos = new ArrayList<>();

    public static void rellenarDatos(){
        Barra[] barras = new Barra[3];

        barras[0] = new Barra(android.R.color.darker_gray,40);
        barras[1] = new Barra(android.R.color.holo_red_dark,70);
        barras[2] = new Barra(android.R.color.holo_green_light,30);


        grupos.add(new Grupo(barras,1990));
        grupos.add(new Grupo(barras,1991));
        grupos.add(new Grupo(barras,1992));

    }



}
