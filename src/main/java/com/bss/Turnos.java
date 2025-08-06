package main.java.com.bss;

import java.util.Scanner;

public class Turnos {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    private int[][] tablerodeDisparos;
    private Tablero TableroDelJuego;

    public Turnos(Tablero tableroDelJuego, int filas, int columnas, int size) {
        TableroDelJuego = tableroDelJuego;
        this.tablerodeDisparos = new int[3][3];
        this.tablerodeDisparos = new int[3][3];
    }

    public void adivinarBarco() {
        int fila, columna, tocado = 0;
        int hundido = TableroDelJuego.getSize();
        Scanner input = new Scanner(System.in);
        try {
            while (tocado < TableroDelJuego.getSize()) {
                System.out.print("Ingresá un número de fila para encontrar el barco (0 al 2): ");
                fila = input.nextInt();
                // Control de índices válidos
                if (fila < 0 || fila > 2) {
                    System.out.println("Coordenada fuera del rango. Intentá de nuevo.");
                    continue;
                }

                System.out.print("Ingresá un número de columna para encontrar el barco (0 al 2): ");
                columna = input.nextInt();

                // Control de índices válidos
                if (columna < 0 || columna > 2) {
                    System.out.println("Coordenada fuera del rango. Intentá de nuevo.");
                    continue;
                }
                // Verificar si ya se intentó esta posición en el tablero de disparos
                if (tablerodeDisparos[fila][columna] != 0) { // 0 significa que no se ha disparado allí
                    System.out.println("Ya has intentado esta posición. Elige otra.");
                    imprimirTableroDeDisparos();
                    continue;
                }

                // Accedo al tablero del juego para ver si encuentra el barco
                if (TableroDelJuego.getTablero()[fila][columna] == 1) {

                    tablerodeDisparos[fila][columna] = 1; // Marca como acierto en el tablero de disparos

                    tocado++;
                    hundido--;

                    if (hundido == 0) {
                        System.out.println("Ganaste!");
                    } else
                        System.out.println("¡Tocado! Te falta encontrar " + hundido + " casillas");
                    imprimirTableroDeDisparos();
                    continue;

                } else {
                    System.out.println("Fallaste. No hay barco en esa posición.");
                    tablerodeDisparos[fila][columna] = -1; // Marca como fallo en el tablero de disparos
                    imprimirTableroDeDisparos();
                }

            }

        } catch (Exception e) { // Captura cualquier excepción para evitar que el programa se caiga
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());

        } finally {
            input.close();
        }

    }

    // Método para imprimir el tablero de disparos del jugador
    private void imprimirTableroDeDisparos() {
        System.out.println("\n--- Tu Tablero de Disparos ---");
        for (int i = 0; i < tablerodeDisparos.length; i++) {
            for (int j = 0; j < tablerodeDisparos[0].length; j++) {
                // Puedes usar símbolos para representar los estados:
                // 0: ~ (agua no disparada)
                // 1: X (acierto)
                // -1: O (fallo)
                switch (tablerodeDisparos[i][j]) {
                    case 0:
                        System.out.print("~ "); // No disparado
                        break;
                    case 1:
                        System.out.print(ANSI_RED + "X " + ANSI_RESET); // Acierto
                        break;
                    case -1:
                        System.out.print(ANSI_BLUE + "O " + ANSI_RESET); // Fallo
                        break;
                }
            }
            System.out.println(); // Nueva línea para la siguiente fila
        }
        System.out.println("------------------------------\n");
    }
}
