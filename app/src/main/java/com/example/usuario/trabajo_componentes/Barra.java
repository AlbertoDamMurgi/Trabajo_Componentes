package com.example.usuario.trabajo_componentes;

/**
 * Created by usuario on 22/01/18.
 */

public class Barra {

    private int color;
    private int valor;
    private String nombre;

    public Barra(int color, int valor,String nombre) {
        this.color = color;
        this.valor = valor;
        this.nombre=nombre;
    }

    public Barra(int color,int valor){

        this.color=color;
        this.valor=valor;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
