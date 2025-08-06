package main.java.com.bss;

import java.util.Scanner;

public class Tablero {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    // Atributos tablero
    private int filas;
    private int columnas;
    private int filaInicial;
    private int columnaInicial;
    private int size;

    private int[][] tablero;
    private int[][] tablerodeDisparos;

    // constructor tablero
   
    public Tablero(int filas, int columnas, int columnaInicial, int filaInicial, int size) {
        this.filas = filas;
        this.columnas = columnas;
        this.columnaInicial = columnaInicial;
        this.filaInicial = filaInicial;
        this.size = size;
        // Inicializa el tablero aquí para que siempre tenga un tamaño
        this.tablero = new int[3][3];
        this.tablerodeDisparos = new int[3][3];
    }

    // Getters
    public int[][] getTablero() {
        return tablero;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getFilaInicial() {
        return filaInicial;
    }

    public int getColumnaInicial() {
        return columnaInicial;
    }

    public int getSize() {
        return size;
    }

    public int[][] getTablerodeDisparos() {
        return tablerodeDisparos;
    }

    // Setters
    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setFilaInicial(int filaInicial) {
        this.filaInicial = filaInicial;
    }

    public void setColumnaInicial(int columnaInicial) {
        this.columnaInicial = columnaInicial;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTablerodeDisparos(int[][] tablerodeDisparos) {
        this.tablerodeDisparos = tablerodeDisparos;
    }

    Scanner input = new Scanner(System.in);

    // Coloca barco en el tablero 3×3
    public int[][] colocarBarco() {
        tablero = new int[3][3];

        do {
            System.out.println("Colocá tu barco.");
            System.out.print("Size del barco: ");
            size = input.nextInt();

            // Verificar que el barco tenga size como mínimo 1 y máximo 3
            if (size <= 0 || size > 4) {
                System.out.println("Tamaño incorrecto, debe ser mínimo 1 máximo 3.");
            }
        } while (size <= 0 || size >= 4);

        asignarBarco();
        System.out.println("Barco colocado correctamente.");
        return tablero;
    }

    // Da orientacion al barco

    public OrientacionBarco orientacion() {

        boolean inputValido = false;
        OrientacionBarco orientacionElegida = null;
        tablero = new int[3][3];
        String orientacionUsuario;

        do {
            System.out.println("Elige la orientación para el barco de tamaño " + size + ": (V) // (H)");
            input.nextLine();
            orientacionUsuario = input.nextLine().trim().toUpperCase(); // Leer la entrada y limpiar espacios

            if (orientacionUsuario.equals("V")) {
                orientacionElegida = OrientacionBarco.VERTICAL;
                inputValido = true;
                System.out.println("Elegiste orientación " + orientacionElegida);

            } else if (orientacionUsuario.equals("H")) {
                orientacionElegida = OrientacionBarco.HORIZONTAL;
                inputValido = true;
                System.out.println("Elegiste orientación " + orientacionElegida);

            } else {
                System.out
                        .println("Orientación no válida. Por favor, ingresa 'V' para Vertical o 'H' para Horizontal.");

            }
        } while (!inputValido);

        return orientacionElegida;

    }

    // asigna barco
    public int[][] asignarBarco() {

        switch (orientacion()) {
            case VERTICAL:
                asignarVertical();
                break;
            case HORIZONTAL:
                asignarHorizontal();
                break;
            default:
                break;
        }

        return tablero;

    }

    public int[][] asignarVertical() {
        try {
            do {
                // Dar coordenadas del barco
                System.out.print("Ingrese fila inicial:");
                filaInicial = input.nextInt();
                while ((filaInicial + size) > tablero.length) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Fila inicial: ");
                    filaInicial = input.nextInt();
                }

                System.out.print("Ingrese columna inicial: ");
                columnaInicial = input.nextInt();

                while (columnaInicial > tablero.length) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Columna inicial");
                    columnaInicial = input.nextInt();
                }
                input.nextLine();

            } while ((filaInicial + size) > 3 || (columnaInicial > tablero.length));

            for (int i = 0; i < size; i++) {
                tablero[filaInicial + i][columnaInicial] = 1;
            }

        } catch (Exception e) {
            System.out.println("Error al ingresar las coordenadas. Por favor, intente nuevamente.");
        }
        return tablero;
    }

    public int[][] asignarHorizontal() {

        try {

            do {

                // Dar coordenadas del barco
                System.out.print("Ingrese fila inicial:");
                filaInicial = input.nextInt();
                while (filaInicial > tablero.length) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Fila inicial: ");
                    filaInicial = input.nextInt();
                }

                System.out.print("Ingrese columna inicial: ");
                columnaInicial = input.nextInt();

                while ((columnaInicial + size) > tablero.length) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Columna inicial");
                    columnaInicial = input.nextInt();
                }
                input.nextLine();

            } while (filaInicial > tablero.length || (columnaInicial + size) > 3);

            System.out.println("llego case 2?");
            for (int j = 0; j < size; j++) {
                tablero[filaInicial][columnaInicial + j] = 1;

            }

        } catch (Exception e) {
            System.out.println("Error al ingresar las coordenadas. Por favor, intente nuevamente.");
        }
        return tablero;
    }

    // Imprime el tablero con barco colocado por pantalla
    public void mostrarTablero() {
        System.out.println("Tablero actual:");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(ANSI_PURPLE + tablero[i][j] + " " + ANSI_RESET);
            }
            System.out.println();
        }
    }

}
