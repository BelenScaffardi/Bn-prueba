package main.java.com.bss;

public class BatallaNaval {
    
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

            Tablero tableroPlayer1 = new Tablero(filas, columnas, filaInicial, columnaInicial, size);
            Tablero tablerodeDisparosPlayer1 = new Tablero(filas, columnas, columnaInicial, filaInicial, size);
            Tablero tableroPlayer2 = new Tablero(filas, columnas, columnaInicial, filaInicial, size);
            Tablero tableroDeDisparosPlayer2 = new Tablero(filas, columnas, columnaInicial, filaInicial, size);
            Turnos juego = new Turnos(tableroPlayer1, tablerodeDisparosPlayer1, tableroPlayer2,
                    tableroDeDisparosPlayer2);

            iniciodeJuego();
            reglastablero();
            System.out.println(ANSI_GREEN + "Player 1 coloca barco");
            tableroPlayer1.colocarBarco();
            tableroPlayer1.mostrarTablero();

            System.out.println(ANSI_PURPLE + "Player 2 coloca barco");
            tableroPlayer2.colocarBarco();
            tableroPlayer2.mostrarTablero();

            // int hundidoP1 = tableroPlayer2.getSize();
            // int hundidoP2 = tableroPlayer1.getSize();
            int tocadoP1 = 0;
            int tocadoP2 = 0;

            do {

                juego.disparos();

            } while (tocadoP1 > tableroPlayer1.getSize() || tocadoP2 > tableroPlayer2.getSize());

            System.out.println(ANSI_YELLOW + "¡Juego terminado!" + ANSI_RESET);
        }

        // Mensaje de bienvenida, inicio del juego
        public static void iniciodeJuego() {
            String mensajeBienvenida = "Bienvenido a la Batalla Naval";
            System.out.println(ANSI_YELLOW + mensajeBienvenida);

        }
        // Reglas de armado de tablero

        public static void reglastablero() {
            String reglas = "Arma el tablero: ingresá 1 para colocar tu barco, que puede tener de tamaño máximo 3"
                    + ANSI_RESET;
            System.out.println(reglas);
        }
    }

