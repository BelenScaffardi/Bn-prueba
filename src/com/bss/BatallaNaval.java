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


            Tablero tableroPlayer1 = new Tablero(filas, columnas);
            Tablero tablerodeDisparosPlayer1 = new Tablero(filas, columnas);
            Tablero tableroPlayer2 = new Tablero(filas, columnas);
            Tablero tableroDeDisparosPlayer2 = new Tablero(filas, columnas);
            Turnos juego = new Turnos(tableroPlayer1, tablerodeDisparosPlayer1, tableroPlayer2,
                    tableroDeDisparosPlayer2);
         


            iniciodeJuego();
           


            int cantidadBarcos = 0;


            System.out.println("Juega Player 1. Cuántos barcos deseas colocar?");
            do {
                tableroPlayer1.elegirCantidadBarcos();
                cantidadBarcos--;


            } while (cantidadBarcos >= 0);


            System.out.println("Juega Player 2. Cuántos barcos deseas colocar?");


            do {
                tableroPlayer2.elegirCantidadBarcos();
                cantidadBarcos--;
            } while (cantidadBarcos >= 0);


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


       


    }


    public static int[] elegirTamanioTablero() {
       
        System.out.println("Elige el tamaño que va a tener el tablero:");
     


        System.out.print("Cantidad de filas: ");
        int filas = leerEnteroValido();


        System.out.print("Cantidad de columnas: ");
        int columnas = leerEnteroValido();


        return new int[] { filas, columnas };


    }


    static int leerEnteroValido() {
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingresa un número.");
            input.next(); // Descarta la entrada no válida
        }
        int valor = input.nextInt();
       
     
        return valor;
       
    }


}
