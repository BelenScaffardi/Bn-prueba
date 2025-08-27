package com.bss;

import java.util.Scanner;

public class BatallaNaval {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    public class Main {
        public static void main(String[] args) throws Exception {

            int[] dimensiones = elegirTamanioTablero();
            int filas = dimensiones[0];
            int columnas = dimensiones[1];
            Scanner input = new Scanner(System.in);

            int cantidadBarcosP1 = 0;
            int cantidadBarcosP2 = 0;

            Tablero tableroPlayer1 = new Tablero(filas, columnas, cantidadBarcosP1);
            Tablero tablerodeDisparosPlayer1 = new Tablero(filas, columnas, cantidadBarcosP1);
            Tablero tableroPlayer2 = new Tablero(filas, columnas, cantidadBarcosP2);
            Tablero tableroDeDisparosPlayer2 = new Tablero(filas, columnas, cantidadBarcosP2);
            Turnos juego = new Turnos(tableroPlayer1, tablerodeDisparosPlayer1, tableroPlayer2,
                    tableroDeDisparosPlayer2, cantidadBarcosP1, cantidadBarcosP2);

            iniciodeJuego();
            reglastablero();

            System.out.println("Juega Player 1. Cuántos barcos deseas colocar?");

            cantidadBarcosP1 = input.nextInt();

            System.out.println("Player 1 quiere colocar " + cantidadBarcosP1 + " barcos.");
            do {
                tableroPlayer1.colocarBarco();
                tableroPlayer1.mostrarTablero();
                cantidadBarcosP1--;
                System.out.println("Faltan colocar " + cantidadBarcosP1 + "barcos.");

            } while (cantidadBarcosP1 > 0);
           

            System.out.println("Juega Player 2. Cuántos barcos deseas colocar?");

            cantidadBarcosP2 = input.nextInt();

            System.out.println(ANSI_PURPLE + "Player 2 quiere colocar " + cantidadBarcosP2 + " barcos.");
            do {
                tableroPlayer2.colocarBarco();
                tableroPlayer2.mostrarTablero();
                cantidadBarcosP2--;
                System.out.println("Faltan colocar " + cantidadBarcosP2 + "barcos.");
            } while (cantidadBarcosP2 > 0);
            

            int tocadoP1 = 0;
            int tocadoP2 = 0;

            do {

                juego.jugar();

            } while (tocadoP1 > tableroPlayer1.getSize() || tocadoP2 > tableroPlayer2.getSize());

            System.out.println(ANSI_YELLOW + "¡Juego terminado!" + ANSI_RESET);
        }

        // Mensaje de bienvenida, inicio del juego
        public static void iniciodeJuego() {
            String mensajeBienvenida = "Bienvenido a la Batalla Naval";
            System.out.println(ANSI_YELLOW + mensajeBienvenida);

        }
        // Reglas de armado de tablero

        public static void reglastablero() {
            String reglas = "Arma el tablero: ingresá 1 para colocar tu barco, que puede tener de tamaño máximo 3"
                    + ANSI_RESET;
            System.out.println(reglas);
        }

    }

    public static int[] elegirTamanioTablero() {
        Scanner input = new Scanner(System.in);
        System.out.println("Elige el tamaño que va a tener el tablero:");

        System.out.print("Cantidad de filas: ");
        int filas = input.nextInt();

        System.out.print("Cantidad de columnas: ");
        int columnas = input.nextInt();

        return new int[] { filas, columnas };

    }
}
