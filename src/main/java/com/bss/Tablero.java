package main.java.com.bss;

import java.util.Scanner;

public class Tablero {
    // Atributos tablero
    private int filas;
    private int columnas;
    private int[][] tablero;
    private int size;


    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }


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
        int fila = 0;
        int columna = 0;


       do{
        System.out.println("Colocá tu barco.");
        System.out.print("Size del barco: " );
        size = input.nextInt();
       
        //Verificar que el barco tenga size como mínimo 1 y máximo 3
        if(size <= 0 || size > 4){
            System.out.println("Tamaño incorrecto, debe ser mínimo 1 máximo 3.");
        }
       }while (size <= 0 || size >= 4);
       
       do {
   
       //Dar coordenadas del barco
        System.out.print("Fila inicial (0–2): ");
        fila = input.nextInt();
        System.out.print("Columna inicial (0–2): ");
        columna = input.nextInt();


        // Verifica que el barco no se salga del tablero
        if (fila + size > 3 || columna + size > 3) {
            System.out.println("El barco se sale del tablero.");
           
        }
    } while (fila + size > 3 || columna + size > 3);
       
       
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


