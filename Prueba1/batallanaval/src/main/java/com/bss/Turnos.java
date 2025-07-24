package com.bss;

import java.util.Scanner;

public class Turnos {

   // Declara una referencia al objeto Tablero
    private Tablero tableroDelJuego;
    // Este tablero es para registrar los intentos del jugador (dónde ha disparado)
    private int[][] tableroDeDisparos;
   
    // Constructor que recibe el tablero del juego, las dimensiones y el size del barcp
    public Turnos(Tablero tableroDelJuego, int filas, int columnas, int size) {
        this.tableroDelJuego = tableroDelJuego;
        /* Inicializa el tablero de disparos con las mismas dimensiones que el tablero
         del juego
        0 = no disparado, 1 = acierto, -1 = fallo*/
        this.tableroDeDisparos = new int[filas][columnas];
    }


    public void adivinarBarco() {
        int fila, columna, tocado=0;
        int hundido = tableroDelJuego.getSize();
        Scanner input = new Scanner(System.in);
        try {
            while (tocado < tableroDelJuego.getSize()) {
                System.out.print("Ingresá un número de fila para encontrar el barco (0 al 2): ");
                fila = input.nextInt();


                System.out.print("Ingresá un número de columna para encontrar el barco (0 al 2): ");
                columna = input.nextInt();


                // Control de índices válidos
                if (fila < 0 || fila > 2 || columna < 0 || columna > 2) {
                    System.out.println("Coordenadas fuera del rango. Intentá de nuevo.");
                    continue;
                }
                // Verificar si ya se intentó esta posición en el tablero de disparos
                if (tableroDeDisparos[fila][columna] != 0) { // 0 significa que no se ha disparado allí
                    System.out.println("Ya has intentado esta posición. Elige otra.");
                    continue;
                }


                // Accedo al tablero del juego para ver si encuentra el barco
                if (tableroDelJuego.getTablero()[fila][columna] == 1) {
                   
                    tableroDeDisparos[fila][columna] = 1; // Marca como acierto en el tablero de disparos
                    tocado++;
                    hundido--;
                     
                     if (hundido == 0) {
                        System.out.println("Ganaste!");
                     } else System.out.println("¡Tocado! Te falta encontrar " + hundido + " casillas");
                    continue;
             
                } else {
                    System.out.println("Fallaste. No hay barco en esa posición.");
                    tableroDeDisparos[fila][columna] = -1; // Marca como fallo en el tablero de disparos
                }






            }
                           
        } catch (Exception e) { // Captura cualquier excepción para evitar que el programa se caiga
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
           
        } finally {
            input.close();
        }
       
    }
}