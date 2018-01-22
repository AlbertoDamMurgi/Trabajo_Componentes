package com.example.usuario.trabajo_componentes;

/**
 * Created by usuario on 22/01/18.
 */

public class Grupo {

    private Barra[] barras;
    private int anio;

    public Grupo(Barra[] barras, int anio) {
        this.barras = barras;
        this.anio = anio;
    }

    public Barra[] getBarras() {
        return barras;
    }

    public void setBarras(Barra[] barras) {
        this.barras = barras;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
