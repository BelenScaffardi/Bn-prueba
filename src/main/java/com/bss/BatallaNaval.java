package main.java.com.bss;


public class BatallaNaval {
  public static void main(String[] args) throws Exception {
       
       
 int filas = 3;
        int columnas = 3;
        int size =0;

        Tablero tablero = new Tablero(filas, columnas);
       
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
        System.out.println(mensajeBienvenida);


    }
    // Reglas de armado de tablero


    public static void reglastablero() {
        String reglas = "Arma el tablero primero: ingresá 1 para barco, Máximo de tamaño es 3";
        System.out.println(reglas);
    }
}





