package BN.Prueba1;

import java.util.Scanner;

public class Turnos {

    // Declara una referencia al objeto Tablero
    private Tablero tableroDelJuego;
    // Este tablero es para registrar los intentos del jugador (dónde ha disparado)
    private int[][] tableroDeDisparos;
    //private int filas, columnas; // Para saber las dimensiones del tablero

    // Constructor que recibe el tablero del juego y las dimensiones
    public Turnos(Tablero tableroDelJuego, int filas, int columnas) {
        this.tableroDelJuego = tableroDelJuego;
       // this.filas = filas;
       // this.columnas = columnas;
        // Inicializa el tablero de disparos
        // 0 = no disparado, 1 = acierto, -1 = fallo
        this.tableroDeDisparos = new int[3][3];
    }

    public void adivinarBarco() {
        int fila, columna;
        Scanner input = new Scanner(System.in);
        try {
            do {
                System.out.print("Ingresá un número de fila para encontrar el barco (0 al 2): ");
                fila = input.nextInt();

                System.out.print("Ingresá un número de columna para encontrar el barco (0 al 2): ");
                columna = input.nextInt();

                // Control de índices válidos
                if (fila < 0 || fila > 2 || columna < 0 || columna > 2) {
                    System.out.println("Coordenadas fuera del rango. Intentá de nuevo.");
                    continue;
                }
                // Verificar si ya se intentó esta posición en el tablero de disparos 0 significa que no se intento esa posicion
                if (tableroDeDisparos[fila][columna] != 0) { 
                    System.out.println("Ya has intentado esta posición. Elige otra.");
                    continue;
                }

                // Se compara el input con la ubicacion del barco
                if (tableroDelJuego.getTablero()[fila][columna] == 1) {
                    System.out.println("¡Acertaste! Has encontrado el barco.");
                    tableroDeDisparos[fila][columna] = 1; // Marca como acierto en el tablero de disparos
                    break; 
                } else {
                    System.out.println("Fallaste. No hay barco en esa posición.");
                    tableroDeDisparos[fila][columna] = -1; // Marca como fallo en el tablero de disparos
                }

            } while (true); 
                            
        } catch (Exception e) { 
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
       
            
        input.close();
    }
}
