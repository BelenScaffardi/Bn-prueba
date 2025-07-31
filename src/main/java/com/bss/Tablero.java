package main.java.com.bss;

import java.util.Scanner;

public class Tablero {
    private int[][] tablerodeDisparos;


    public Tablero(int[][] tablerodeDisparos) {
        this.tablerodeDisparos = new int[3][3];
    }


    // Atributos tablero
    private int filas;
    private int columnas;
    private int filaInicial;


    public int getFilaInicial() {
        return filaInicial;
    }


    public void setFilaInicial(int filaInicial) {
        this.filaInicial = filaInicial;
    }


    private int columnaInicial;


    public int getColumnaInicial() {
        return columnaInicial;
    }


    public void setColumnaInicial(int columnaInicial) {
        this.columnaInicial = columnaInicial;
    }


    private int[][] tablero;
    int size;


    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }


    Scanner input = new Scanner(System.in);
    public int length;


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


        do {
            System.out.println("Colocá tu barco.");
            System.out.print("Size del barco: ");
            size = input.nextInt();


            // Verificar que el barco tenga size como mínimo 1 y máximo 3
            if (size <= 0 || size > 4) {
                System.out.println("Tamaño incorrecto, debe ser mínimo 1 máximo 3.");
            }
        } while (size <= 0 || size >= 4);


        orientacion();


        do {


            // Dar coordenadas del barco horizontal
            System.out.print("Ingrese fila inicial (0 a: " + (size - 1) + ")");
            filaInicial = input.nextInt();
            while ((filaInicial + size) > 3) {
                System.out.println("El barco se sale del tablero. Intente nuevamente");
                System.out.print("Fila inicial (0 a 2): ");
                filaInicial = input.nextInt();
            }


            System.out.print("Ingrese columna inicial (0 a 2): ");
            columnaInicial = input.nextInt();


            while ((columnaInicial + size) > 3) {
                System.out.println("El barco se sale del tablero. Intente nuevamente");
                System.out.print("Columna inicial (0 a : " + (size - 1));
                columnaInicial = input.nextInt();
            }
            input.nextLine();


        } while ((filaInicial + size) > 3 || (columnaInicial + size) > 3);
        asignarBarco();


        System.out.println("Barco colocado correctamente.");
        return tablero;
    }


    // Da orientacion al tablero


    public int[][] orientacion() {


        boolean inputValido = false;
        orientacionBarco orientacionElegida = null;
        tablero = new int[3][3];
        String orientacionUsuario;


        do {
            System.out.println("Elige la orientación para el barco de tamaño " + size + ": (V) // (H)");
            input.nextLine();
            orientacionUsuario = input.nextLine().trim().toUpperCase(); // Leer la entrada y limpiar espacios
         
            if (orientacionUsuario.equals("V")) {
                orientacionElegida = orientacionBarco.VERTICAL;
                inputValido = true;


            } else if (orientacionUsuario.equals("H")) {
                orientacionElegida = orientacionBarco.HORIZONTAL;
                inputValido = true;


            } else {
                System.out.println("Orientación no válida. Por favor, ingresa 'V' para Vertical o 'H' para Horizontal.");
                input.nextLine();
                inputValido=true;
            }
        } while (!inputValido);


        return tablero;


    }


    // asigna barco
    public int[][] asignarBarco() {
        if (orientacionBarco.VERTICAL != null) {
            for (int i = 0; i < size; i++) {
                tablero[filaInicial + i][columnaInicial] = 1;


            }
        } else {
            for (int j = 0; j < size; j++) {


                tablero[filaInicial][columnaInicial + j] = 1;


            }
        }


        return tablero;


    }


    // Imprime el tablero por pantalla
    public void mostrarTablero() {
        System.out.println("Tablero actual:");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }


}



