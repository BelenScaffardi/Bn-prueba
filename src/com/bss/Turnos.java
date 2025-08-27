package com.bss;

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
    int cantidadBarcosP1;
    int cantidadBarcosP2;

    Scanner input = new Scanner(System.in);


    Tablero tableroActual;
    Tablero tableroPlayer1;
    Tablero tablerodeDisparosPlayer1;
    Tablero tableroPlayer2;
    Tablero tableroDeDisparosPlayer2;
    Tablero tableroOponente;
    Tablero tableroDeDisparosActual;


    public Turnos(Tablero tableroPlayer1, Tablero tablerodeDisparosPlayer1, Tablero tableroPlayer2,
            Tablero tablerodeDisparosPlayer2, int cantidadBarcosP1, int cantidadBarcosP2    ) {
        this.tableroPlayer1 = tableroPlayer1;
        this.tablerodeDisparosPlayer1 = tablerodeDisparosPlayer1;
        this.tableroPlayer2 = tableroPlayer2;
        this.tableroDeDisparosPlayer2 = tablerodeDisparosPlayer2;
        this.hundidoP1 = this.tableroPlayer2.getSize();
        this.hundidoP2 = this.tableroPlayer1.getSize();
        this.cantidadBarcosP1 = this.tableroPlayer1.getCantidadBarcos();
        this.cantidadBarcosP2 = this.tableroPlayer2.getCantidadBarcos();

    }


    public void jugar() {
        try {
            do {


                if (quienDispara() == QuienDispara.PLAYER_1) {
                    System.out.println(ANSI_GREEN + "\n--- Turno del Jugador 1 ---");
                    do {
                        validarDisparo(tableroOponente, tableroDeDisparosActual);
                        disparar(fila, columna, tableroOponente, tableroDeDisparosActual, null);
                        imprimirTableroDeDisparos();
                        esTurnoJugador1 = false;


                    } while (esTurnoJugador1 == true);


                } else {
                    System.out.println(ANSI_PURPLE + "\n--- Turno del Jugador 2 ---");
                    do {
                        validarDisparo(tableroOponente, tableroDeDisparosActual);
                        disparar(fila, columna, tableroOponente, tableroDeDisparosActual, null);
                        imprimirTableroDeDisparos();
                        esTurnoJugador1 = true;
                    } while (esTurnoJugador1 == false);
                }


            } while (ganaste == false);


        } catch (Exception e) { 
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


    private void disparar(int fila, int columna, Tablero tableroOponente, Tablero tableroDeDisparosActual,
            QuienDispara jugador) {
        if (tableroOponente.getCasilla(fila, columna) == EstadoCasilla.BARCO) {
            tableroDeDisparosActual.setCasilla(fila, columna, EstadoCasilla.TOCADO);

            if (quienDispara() == QuienDispara.PLAYER_1) {
                tocadoP2++;

                if (tocadoP2 == tableroPlayer2.getSize()) {
                    
                    System.out.println(ANSI_YELLOW + "hundiste el barco de tamaño " + tableroPlayer2.getSize() );
                    cantidadBarcosP2--;
                    System.out.println(ANSI_YELLOW + "Te falntan hundir " + cantidadBarcosP2 + " barcos.");

                    if (cantidadBarcosP2 <= 0) {
                        ganaste = true;
                        System.out.println(ANSI_YELLOW + "Ganaste!! :) " + ANSI_RESET);
                    }
                } else {
                    tableroDeDisparosActual.getTableroDelJuego()[fila][columna] = EstadoCasilla.TOCADO;
                    System.out.println("¡Tocado! Te falta encontrar " + (tableroPlayer2.getSize() - tocadoP2)
                            + " casillas.");
                }
            } else {
                tocadoP1++;

                if (tocadoP1 == tableroPlayer1.getSize()) {
                    cantidadBarcosP1--;
                    System.out.println(ANSI_YELLOW + "hundiste el barco de tamaño " + tableroPlayer1.getSize());
                    System.out.println(ANSI_YELLOW + "Te faltan hundir " + cantidadBarcosP1 + " barcos.");

                    if (cantidadBarcosP1 <= 0) {
                        ganaste = true;
                        System.out.println(ANSI_YELLOW + "Ganaste!! :) " + ANSI_RESET);
                        
                    }
                 
                } else {
                    System.out.println(
                            "¡Tocado! Te falta encontrar " + (tableroPlayer1.getSize() - tocadoP1) + " casillas");
                }


            }


        } else {
            tableroDeDisparosActual.setCasilla(fila, columna, EstadoCasilla.AGUA);
            System.out.println("¡Agua! Fallaste.");
        }
    

    }

    public void validarDisparo(Tablero tableroOponente, Tablero tableroDeDisparosActual) {
    int fila;
    int columna;
    boolean disparoValido = false;


    do {
        try {
            System.out.print("Ingresá un número de fila para encontrar el barco: ");
            fila = input.nextInt();
            if (fila < 0 || fila >= tableroOponente.getFilas() ) {
                System.out.println("Coordenada fuera del rango. Intentá de nuevo.");
                input.nextInt();
            }
           


            System.out.print("Ingresá un número de columna para encontrar el barco: ");
            columna = input.nextInt();

            if (columna < 0 || columna >= tableroOponente.getColumnas()) {
                System.out.println("Coordenada fuera del rango. Intentá de nuevo.");
                input.nextInt();
            }
           
      
            if (tableroDeDisparosActual.getCasilla(fila, columna) != EstadoCasilla.NO_DISPARADO) {
                System.out.println("Ya has intentado esta posición. Elige otra.");
            }
            // 3. Si las coordenadas son válidas, salir del bucle
            else {
                this.fila = fila; // Asigna las coordenadas validadas
                this.columna = columna;
                disparoValido = true;
            }
        } catch (Exception e) {
            System.out.println("Entrada no válida. Por favor, ingresa un número.");
            input.nextLine(); // Limpiar el buffer del scanner
        }
    } while (!disparoValido);
}


    // Método para imprimir el tablero de disparos del jugador
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
                        System.out.print( ANSI_BLUE + "0 " + ANSI_RESET ); // Fallo
                        break;
                    default:
                        System.out.println("Ocurrió un error");
                        break;


                }
            }
            System.out.println(); // Nueva línea para la siguiente fila
        }
        System.out.println("------------------------------\n");


    }
}





