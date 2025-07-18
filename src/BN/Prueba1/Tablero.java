package BN.Prueba1;

import java.util.Scanner;

public class Tablero {
    // Atributos tablero
    private int filas;
    private int columnas;
    private int[][] tablero;
    // private int i, j;

    Scanner input = new Scanner(System.in);

    // Constructor tablero
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        // Inicializa el tablero aquí para que siempre tenga un tamaño
        this.tablero = new int[3][3];
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

    // Coloca barco en el tablero 3×3
    public int[][] colocarBarco() {
        tablero = new int[3][3];

        System.out.println("Colocá tu barco (tamaño 1 a 3).");
        System.out.print("Size del barco: ");
        int size = input.nextInt();

        if (size < 1 || size > 3) {
            System.out.println("Tamaño inválido.");
            return tablero;
        }

        System.out.print("Fila inicial (0–2): ");
        int fila = input.nextInt();
        System.out.print("Columna inicial (0–2): ");
        int columna = input.nextInt();

        // Verifica que el barco no se salga del tablero
        if (fila + size > 3 || columna + size > 3) {
            System.out.println("El barco se sale del tablero.");
            return tablero;
        }
        // Coloca el barco en el tablero
        for (int i = 0; i < size; i++) {
            tablero[fila][columna + i] = 1; // Asigno celdas ocupadas por el barco
        }

        System.out.println("Barco colocado correctamente.");
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

}
