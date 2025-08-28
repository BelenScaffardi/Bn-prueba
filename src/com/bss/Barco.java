package com.bss;

import java.util.ArrayList;

public class Barco {



    private int tamanio;
    private ArrayList<Coordenada> coordenadas;
    private int golpesRecibidos;


    public int getGolpesRecibidos() {
        return golpesRecibidos;
    }


    public void setGolpesRecibidos(int golpesRecibidos) {
        this.golpesRecibidos = golpesRecibidos;
    }


    public Barco(int tamanio) {
        this.tamanio = tamanio;
        this.coordenadas = new ArrayList<>();
        this.golpesRecibidos = 0;
    }


    public int getTamanio() {
        return tamanio;
    }


    public ArrayList<Coordenada> getCoordenadas() {
        return coordenadas;
    }


    public void agregarCoordenada(int fila, int columna) {
        this.coordenadas.add(new Coordenada(fila, columna));
    }


    public void registrarGolpe() {
        this.golpesRecibidos++;
    }


    public boolean estaHundido() {
        return golpesRecibidos == tamanio;
    }
}




