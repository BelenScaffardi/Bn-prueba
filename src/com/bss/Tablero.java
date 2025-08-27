package com.bss;

import java.util.Scanner;

public class Tablero {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    // Atributos tablero
    private int filas;
    private int columnas;
    private int filaInicial;
    private int columnaInicial;
    private int sizeBarco;
    private int cantidadBarcos;


    private EstadoCasilla[][] tableroDelJuego;
    private EstadoCasilla[][] tablerodeDisparos;


    Scanner input = new Scanner(System.in);


    // constructor tablero
    public Tablero(int filas, int columnas, int cantidadBarcos) {
        this.filas = filas;
        this.columnas = columnas;
        this.cantidadBarcos = cantidadBarcos;
        // Inicializa el tablero
        this.tableroDelJuego = new EstadoCasilla[filas][columnas];
        this.tablerodeDisparos = new EstadoCasilla[filas][columnas];


        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.tableroDelJuego[i][j] = EstadoCasilla.NO_DISPARADO;
                this.tablerodeDisparos[i][j] = EstadoCasilla.NO_DISPARADO;
            }
        }


    }


    // Getters
    public EstadoCasilla[][] getTableroDelJuego() {
        return tableroDelJuego;
    }


    public int getFilas() {
        return filas;
    }
    public int getCantidadBarcos() {
        return cantidadBarcos;
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
        return sizeBarco;
    }


    public EstadoCasilla[][] getTablerodeDisparos() {
        return tablerodeDisparos;
    }


    public EstadoCasilla getCasilla(int fila, int columna) {
        return tableroDelJuego[fila][columna];
    }


    // Setters
    public void setTableroDelJuego(EstadoCasilla[][] tableroDelJuego) {
        this.tableroDelJuego = tableroDelJuego;
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


    public void setSize(int sizeBarco) {
        this.sizeBarco = sizeBarco;
    }


    public void setTablerodeDisparos(EstadoCasilla[][] tablerodeDisparos) {
        this.tablerodeDisparos = tablerodeDisparos;
    }


    public void setCasilla(int fila, int columna, EstadoCasilla estado) {
        tableroDelJuego[fila][columna] = estado;
    }


    // Coloca barco en el tablero
    public EstadoCasilla[][] colocarBarco() {


        do {
            System.out.print("Elije el tamaño de tu barco: ");
            sizeBarco = input.nextInt();
            // Verificar que el barco tenga size del tablero como maximo
            if (sizeBarco <= 0 || sizeBarco > tableroDelJuego.length) {
                System.out.println("Tamaño incorrecto. Intenta nuevamente");
            }


        } while (sizeBarco <= 0 || sizeBarco > tableroDelJuego.length - 1);


        asignarBarco();


        return tableroDelJuego;
    }


    // Da orientacion al barco


    public OrientacionBarco validarOrientacion() {


        boolean inputValido = false;
        OrientacionBarco orientacionElegida = null;
        String orientacionUsuario;


        do {
            System.out.println("Elige la orientación para el barco de tamaño " + sizeBarco + ": (V) // (H)");
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
    public EstadoCasilla[][] asignarBarco() {


        switch (validarOrientacion()) {
            case VERTICAL:
                validarVertical();
                asignarVertical();
                break;
            case HORIZONTAL:
                validarHorizontal();
                asignarHorizontal();
                break;
            default:
                break;
        }


        System.out.println("Barco colocado correctamente.");


        return tableroDelJuego;


    }


    public EstadoCasilla[][] validarVertical() {
        try {
            do {
                // Dar coordenadas del barco
                System.out.print("Ingrese fila inicial:");
                filaInicial = input.nextInt();
                while ((filaInicial + sizeBarco) > tableroDelJuego.length - 1) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Fila inicial: ");
                    filaInicial = input.nextInt();
                }


                System.out.print("Ingrese columna inicial: ");
                columnaInicial = input.nextInt();


                while (columnaInicial > tableroDelJuego.length - 1) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Columna inicial: ");
                    columnaInicial = input.nextInt();
                }
                input.nextLine();


            } while ((filaInicial + sizeBarco) > tableroDelJuego.length - 1
                    || (columnaInicial > tableroDelJuego.length - 1));


        } catch (Exception e) {
            System.out.println("Error al ingresar las coordenadas. Por favor, intente nuevamente.");
        }
        return tableroDelJuego;
    }


    public EstadoCasilla[][] asignarVertical() {
        boolean hayBarco = false;
        // chequeo si ya hay un barco en esa posicion
        for (int i = 0; i < getSize(); i++) {
            if (tableroDelJuego[filaInicial + i][columnaInicial] == EstadoCasilla.BARCO) {
                System.out.println("Ya hay un barco en esa posición. Elije otra.");
                hayBarco = true;
                validarVertical();
            } else
                hayBarco = false;
        }
        // si no hay barco, asigno nuevo barco
        if (hayBarco == false) {
            for (int i = 0; i < sizeBarco; i++) {
                tableroDelJuego[filaInicial + i][columnaInicial] = EstadoCasilla.BARCO;
            }
        }
        return tableroDelJuego;
    }


    public EstadoCasilla[][] validarHorizontal() {


        try {
            do {


                // Dar coordenadas del barco
                System.out.print("Ingrese fila inicial:");
                filaInicial = input.nextInt();
                while (filaInicial > tableroDelJuego.length) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Fila inicial: ");
                    filaInicial = input.nextInt();
                }


                System.out.print("Ingrese columna inicial: ");
                columnaInicial = input.nextInt();


                while ((columnaInicial + sizeBarco) > tableroDelJuego.length - 1) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Columna inicial: ");
                    columnaInicial = input.nextInt();
                }
                input.nextLine();


            } while (filaInicial > tableroDelJuego.length - 1
                    || (columnaInicial + sizeBarco) > tableroDelJuego.length - 1);


        } catch (Exception e) {
            System.out.println("Error al ingresar las coordenadas. Por favor, intente nuevamente.");
        }
        return tableroDelJuego;
    }


    public EstadoCasilla[][] asignarHorizontal() {
        boolean hayBarco = false;
        // chequeo si ya hay un barco en esa posicion
        for (int j = 0; j < getSize(); j++) {
            if (tableroDelJuego[filaInicial][columnaInicial + j] == EstadoCasilla.BARCO) {
                System.out.println("Ya hay un barco en esa posición. Elije otra.");
                hayBarco = true;
                validarHorizontal();
            } else
                hayBarco = false;
        }
        // si no hay barco, asigno barco nuevo
        if (hayBarco == false) {
            for (int j = 0; j < sizeBarco; j++) {
                tableroDelJuego[filaInicial][columnaInicial + j] = EstadoCasilla.BARCO;
            }
        }
        return tableroDelJuego;


    }


    // Imprime el tablero con barco colocado por pantalla
    public void mostrarTablero() {
        System.out.println("Tablero actual:");
        for (int i = 0; i < tableroDelJuego.length; i++) {
            for (int j = 0; j < tableroDelJuego[0].length; j++) {
                // System.out.print(tableroDelJuego[i][j] + " ");
                switch (tableroDelJuego[i][j]) {
                    case NO_DISPARADO:
                        System.out.print(ANSI_BLUE + "~ " + ANSI_RESET);
                        break;
                    case BARCO:
                        System.out.print(ANSI_RED + "B " + ANSI_RESET);
                        break;
                    default:
                        System.out.print("? ");
                        break;
                }


            }
            System.out.println();
        }
    }


}



