package com.bss;

import java.util.ArrayList;
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


    private BatallaNaval leerEnteroValido;


    Scanner input = new Scanner(System.in);


    private ArrayList<Barco> barcosJugador;


    // constructor tablero
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.barcosJugador = new ArrayList<>();
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


    public ArrayList<Barco> getBarcosJugador() {
        return barcosJugador;
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


    public ArrayList<Barco> elegirCantidadBarcos() {


        int cantidadBarcos = BatallaNaval.leerEnteroValido();
        System.out.println("Elegiste colocar " + cantidadBarcos + "barcos");


        for (int i = 0; i < cantidadBarcos; i++) {
            System.out.println("Ingresá el tamaño del barco N°" + (i + 1) + ":");
            int sizeBarco = validaTamanioBarco();
            this.sizeBarco = sizeBarco;
            asignarBarco();
            mostrarTablero();


        }


        return barcosJugador;
    }


    public void agregarBarco(Barco barco) {
        this.barcosJugador.add(barco);
    }


    public int validaTamanioBarco() {
        do {
            sizeBarco = BatallaNaval.leerEnteroValido();
            // Verificar que el barco tenga size del tablero como maximo
            if (sizeBarco <= 0 || sizeBarco > tableroDelJuego.length) {
                System.out.println("Tamaño incorrecto. Intenta nuevamente");
                sizeBarco = input.nextInt();
            }


        } while (sizeBarco <= 0 || sizeBarco > tableroDelJuego.length);


        return sizeBarco;
    }


    // Da orientacion al barco


    public OrientacionBarco validarOrientacion() {


        boolean inputValido = false;
        OrientacionBarco orientacionElegida = null;
        String orientacionUsuario;


        do {
            System.out.println("Elige la orientación para el barco de tamaño " + sizeBarco + ": (V) // (H)");
             //input.nextLine();
            orientacionUsuario = input.nextLine().trim().toUpperCase(); 


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
        Barco nuevoBarco = new Barco(sizeBarco);
        switch (validarOrientacion()) {
            case VERTICAL:
                validarVertical();
                asignarVertical(nuevoBarco);
                break;
            case HORIZONTAL:
                validarHorizontal();
                asignarHorizontal(nuevoBarco);
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
                filaInicial = BatallaNaval.leerEnteroValido() - 1;
                while ((filaInicial + sizeBarco) > tableroDelJuego.length || filaInicial < 0) {


                    System.out.println("El barco se sale del tablero. Intente nuevamente.");
                    System.out.print("Fila inicial: ");
                    filaInicial = BatallaNaval.leerEnteroValido() - 1;


                }


                System.out.print("Ingrese columna inicial: ");
                columnaInicial = BatallaNaval.leerEnteroValido() - 1;


                while (columnaInicial > tableroDelJuego.length || columnaInicial < 0) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Columna inicial: ");
                    columnaInicial = BatallaNaval.leerEnteroValido() - 1;
                }


            } while ((filaInicial + sizeBarco) > tableroDelJuego.length
                    || (columnaInicial > tableroDelJuego.length));


        } catch (Exception e) {
            System.out.println("Error al ingresar las coordenadas. Por favor, intente nuevamente.");
        }
        return tableroDelJuego;
    }


    public EstadoCasilla[][] asignarVertical(Barco barco) {
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
                barco.agregarCoordenada(filaInicial + i, columnaInicial);
            }


            agregarBarco(barco);


        }
        return tableroDelJuego;
    }


    public EstadoCasilla[][] validarHorizontal() {


        try {
            do {


                // Dar coordenadas del barco
                System.out.print("Ingrese fila inicial:");
                filaInicial = BatallaNaval.leerEnteroValido() - 1;
                while (filaInicial > tableroDelJuego.length || filaInicial < 0) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Fila inicial: ");
                    filaInicial = BatallaNaval.leerEnteroValido() - 1;
                }


                System.out.print("Ingrese columna inicial: ");
                columnaInicial = BatallaNaval.leerEnteroValido() - 1;


                while ((columnaInicial + sizeBarco) > tableroDelJuego.length || columnaInicial < 0) {
                    System.out.println("El barco se sale del tablero. Intente nuevamente");
                    System.out.print("Columna inicial: ");
                    columnaInicial = BatallaNaval.leerEnteroValido() - 1;
                }


            } while (filaInicial > tableroDelJuego.length
                    || (columnaInicial + sizeBarco) > tableroDelJuego.length);


        } catch (Exception e) {
            System.out.println("Error al ingresar las coordenadas. Por favor, intente nuevamente.");
        }
        return tableroDelJuego;
    }


    public EstadoCasilla[][] asignarHorizontal(Barco barco) {
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
                barco.agregarCoordenada(filaInicial, columnaInicial + j);
            }
            agregarBarco(barco);


        }
        return tableroDelJuego;


    }


    public void mostrarTablero() {
        System.out.println("Tablero actual:");
        for (int i = 0; i < tableroDelJuego.length; i++) {
            for (int j = 0; j < tableroDelJuego[0].length; j++) {


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



