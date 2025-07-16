package BN.Prueba1;

public class Turnos {

    public static final int[][] tablero = new int[3][3];

    public int[][] adivinarBarco() {
        int fila, columna, contador = 0;
        Scanner input = new Scanner(System.in);
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
            if (tablero[fila][columna] == 1) {
                contador++;
                tablero[fila][columna] = 2; // Marcamos como tocado/hundido
                System.out.println("Tocado!");

                if (contador == 2) {
                    System.out.println("Hundido! ¡Ganaste!");
                    break; // Salir del bucle: se hundió el barco completo
                }
            } else if (tablero[fila][columna] == 0) {
                tablero[fila][columna] = -1; // Marcamos agua
                System.out.println("Agua!");
            } else {
                // Ya habías elegido esa casilla (ya tocada o agua)
                System.out.println("Ya jugaste esa casilla, elegí otra.");
            }

        } while (true);

        input.close();
        return tablero;
    }
}
