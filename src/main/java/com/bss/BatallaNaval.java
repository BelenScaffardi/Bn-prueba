package main.java.com.bss;

public class BatallaNaval {
    // Colores para la consola
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

        public static void main(String[] args) throws Exception {
            int filas = 3;
            int columnas = 3;
            int size = 0;
            int filaInicial = 0;
            int columnaInicial = 0;

            Tablero tablero = new Tablero(filas, columnas, filaInicial, columnaInicial, size);

            Turnos adivinar = new Turnos(tablero, filas, columnas, size);

            iniciodeJuego();
            reglastablero();
            System.out.println("Player 1 arma tablero");
            tablero.colocarBarco();
            tablero.mostrarTablero();
            adivinar.adivinarBarco();

            System.out.println("¡Juego terminado!");
        }

        // Mensaje de bienvenida, inicio del juego
        public static void iniciodeJuego() {
            String mensajeBienvenida = "Bienvenido a la Batalla Naval";
            System.out.println(ANSI_GREEN + mensajeBienvenida + ANSI_RESET);

        }
        // Reglas de armado de tablero

        public static void reglastablero() {
            String reglas = "Arma el tablero primero: ingresá 1 para barco, Máximo de tamaño es 3";
            System.out.println(reglas);
        }
    }

