package BN.Prueba1;

import java.util.Scanner;

public class Tablero {
    // Atributos tablero
    private int filas;
    private int columnas;
    private int[][] tablero;
    private int i, j;

    Scanner input = new Scanner(System.in);

    // Constructor tablero
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }

    // Getters
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int[][] getTablero() {
        return tablero;
    }

    // Setters
    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    // Crea el tablero 3×3
    public int[][] crearTablero() {
        tablero = new int[3][3];
        try {
            for (i = 0; i < 3; i++) {
                System.out.println("Para fila " + i);

                for (j = 0; j < 3; j++) {
                    System.out.println("Para columna " + j);
                    tablero[i][j] = input.nextInt();

                }
            }

        } catch (Exception e) {
            System.out.println("Caracter inválido, ingressá 0 para agua o 1 para barco");
        }
        return tablero;
    }

    // Imprime el tablero por pantalla
    public void mostrarTablero() {
        System.out.println("Tablero actual:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Verifica que ingrese 0 o 1
    public void verificarValores() {

        if (i < 0 || i > 2 || j < 0 || j > 2) {
            System.out.println("Fuera de rango, ingressá 0 para agua o 1 para barco");

        }
    }

}
