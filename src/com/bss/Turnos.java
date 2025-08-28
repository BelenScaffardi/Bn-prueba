package com.bss;

import java.util.ArrayList;
import java.util.Scanner;

public class Turnos {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";


    int fila, columna, tocado = 0;
    int hundidoP1;
    int size;
    boolean esTurnoJugador1 = true;
    int hundidoP2;
    int player;
    boolean ganaste = false;
    int tocadoP2 = 0;
    int tocadoP1 = 0;


    Scanner input = new Scanner(System.in);


    Tablero tableroActual;
    Tablero tableroPlayer1;
    Tablero tablerodeDisparosPlayer1;
    Tablero tableroPlayer2;
    Tablero tableroDeDisparosPlayer2;
    Tablero tableroOponente;
    Tablero tableroDeDisparosActual;
    Tablero listaBarcos;


    public Turnos(Tablero tableroPlayer1, Tablero tablerodeDisparosPlayer1, Tablero tableroPlayer2,
            Tablero tablerodeDisparosPlayer2) {
        this.tableroPlayer1 = tableroPlayer1;
        this.tablerodeDisparosPlayer1 = tablerodeDisparosPlayer1;
        this.tableroPlayer2 = tableroPlayer2;
        this.tableroDeDisparosPlayer2 = tablerodeDisparosPlayer2;


        this.hundidoP1 = this.tableroPlayer2.getSize();
        this.hundidoP2 = this.tableroPlayer1.getSize();


    }


    public void jugar() {
        try {
            do {


                if (quienDispara() == QuienDispara.PLAYER_1) {
                    System.out.println(ANSI_GREEN + "\n--- Turno del Jugador 1 ---");
                    do {
                        validarDisparo(tableroOponente, tableroDeDisparosActual);
                        disparar(fila, columna, tableroOponente, tableroDeDisparosActual, null, columna);
                        imprimirTableroDeDisparos();
                        esTurnoJugador1 = false;


                    } while (esTurnoJugador1 == true);


                } else {
                    System.out.println(ANSI_PURPLE + "\n--- Turno del Jugador 2 ---");
                    do {
                        validarDisparo(tableroOponente, tableroDeDisparosActual);
                        disparar(fila, columna, tableroOponente, tableroDeDisparosActual, null, columna);
                        imprimirTableroDeDisparos();
                        esTurnoJugador1 = true;
                    } while (esTurnoJugador1 == false);
                }


            } while (ganaste == false);


        } catch (Exception e) { // Captura cualquier excepción para evitar que el programa se caiga
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());


        }
    }


    public QuienDispara quienDispara() {
        QuienDispara player;


        if (esTurnoJugador1) {
            player = QuienDispara.PLAYER_1;
            tableroOponente = tableroPlayer2;
            tableroDeDisparosActual = tablerodeDisparosPlayer1;


        } else {
            player = QuienDispara.PLAYER_2;
            tableroOponente = tableroPlayer1;
            tableroDeDisparosActual = tableroDeDisparosPlayer2;


        }
        return player;
    }


    //evalúa si se encontró barco, cantidad de golpes y si se hundió. Evalúa si se hundieron todos los barcos.
    private void disparar(int fila, int columna, Tablero tableroOponente, Tablero tableroDeDisparosActual,
            QuienDispara jugador, int barco) {


        if (tableroOponente.getCasilla(fila, columna) == EstadoCasilla.BARCO) {
            tableroDeDisparosActual.setCasilla(fila, columna, EstadoCasilla.TOCADO);


            if (quienDispara() == QuienDispara.PLAYER_1) {
                 Barco barcoTocadoP2 = buscarBarcoEnCoordenada(fila, columna, tableroOponente.getBarcosJugador());
                    if (barcoTocadoP2 != null) {
                        barcoTocadoP2.registrarGolpe();
                        System.out.println("Barco tocado!");
                    }




                if (barcoTocadoP2.estaHundido()) {
                    System.out.println(ANSI_YELLOW + "Hundiste el barco de tamaño " + barcoTocadoP2.getTamanio());
                    tableroOponente.getBarcosJugador().remove(barcoTocadoP2);
                    if (tableroOponente.getBarcosJugador().isEmpty()) {
                        ganaste = true;
                        System.out.println("Felicitaciones Player 1. Ganaste!");
                    }


                } else {
                    tableroDeDisparosActual.getTableroDelJuego()[fila][columna] = EstadoCasilla.TOCADO;
                   
                    System.out.println("Faltan " + (barcoTocadoP2.getTamanio() - barcoTocadoP2.getGolpesRecibidos()) + " golpes para hundirlo.");
                }


            } else {
                Barco barcoTocadoP1 = buscarBarcoEnCoordenada(fila, columna, tableroOponente.getBarcosJugador());


                if (barcoTocadoP1 != null) {
                        barcoTocadoP1.registrarGolpe();
                        System.out.println("Barco tocado!");
                    }




                if (barcoTocadoP1.estaHundido()) {
                    System.out.println(ANSI_YELLOW + "Hundiste el barco de tamaño " + barcoTocadoP1.getTamanio());
                    tableroOponente.getBarcosJugador().remove(barcoTocadoP1);
                    if (tableroOponente.getBarcosJugador().isEmpty()) {
                        ganaste = true;
                        System.out.println("Felicitaciones Player 2. Ganaste!");
                    }


                } else {
                    tableroDeDisparosActual.getTableroDelJuego()[fila][columna] = EstadoCasilla.TOCADO;
                   
                    System.out.println("Faltan " + (barcoTocadoP1.getTamanio() - barcoTocadoP1.getGolpesRecibidos()) + " golpes para hundirlo.");
                }
            }


        } else {
            tableroDeDisparosActual.setCasilla(fila, columna, EstadoCasilla.AGUA);
            System.out.println("¡Agua! Fallaste.");
        }
    }


    //valida que las coordenadas ingresadas esten dentro del rango, no se haya intentado antes y sean números
    public void validarDisparo(Tablero tableroOponente, Tablero tableroDeDisparosActual) {
        int fila;
        int columna;
        boolean disparoValido = false;


        do {
            try {
                System.out.print("Ingresá un número de fila para encontrar el barco: ");
                fila = BatallaNaval.leerEnteroValido() - 1;
                if (fila < 0 || fila >= tableroOponente.getFilas()) {
                    System.out.println("Coordenada fuera del rango. Intentá de nuevo.");
                    fila = BatallaNaval.leerEnteroValido();
                }


                System.out.print("Ingresá un número de columna para encontrar el barco: ");
                columna = BatallaNaval.leerEnteroValido() - 1;


                if (columna < 0 || columna >= tableroOponente.getColumnas()) {
                    System.out.println("Coordenada fuera del rango. Intentá de nuevo.");
                    columna = BatallaNaval.leerEnteroValido() - 1;
                }


                if (tableroDeDisparosActual.getCasilla(fila, columna) != EstadoCasilla.NO_DISPARADO) {
                    System.out.println("Ya has intentado esta posición. Elige otra.");
                }


                else {
                    this.fila = fila;
                    this.columna = columna;
                    disparoValido = true;
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                input.nextLine();
            }
        } while (!disparoValido);
    }


    // buscar el barco en las coordenadas
    private Barco buscarBarcoEnCoordenada(int fila, int columna, ArrayList<Barco> listaBarcos) {
        for (Barco barco : listaBarcos) {
            for (Coordenada coord : barco.getCoordenadas()) {
                if (coord.getFila() == fila && coord.getColumna() == columna) {
                    return barco;
                }
            }
           
        }
       return null;
    }


    // imprimir el tablero de disparos del jugador
    public void imprimirTableroDeDisparos() {


        System.out.println(ANSI_RESET + "\n--- Tu Tablero de Disparos ---");


        for (int i = 0; i < tableroDeDisparosActual.getTablerodeDisparos().length; i++) {
            for (int j = 0; j < tableroDeDisparosActual.getTablerodeDisparos().length; j++) {


                switch (tableroDeDisparosActual.getTableroDelJuego()[i][j]) {
                    case NO_DISPARADO:
                        System.out.print("~ "); // No disparado
                        break;
                    case TOCADO:
                        System.out.print(ANSI_RED + "X " + ANSI_RESET); // Acierto
                        break;
                    case AGUA:
                        System.out.print(ANSI_BLUE + "0 " + ANSI_RESET); // Fallo
                        break;
                    default:
                        System.out.println("Ocurrió un error");
                        break;


                }
            }
            System.out.println(); 
        }
        System.out.println("------------------------------\n");


    }
}




