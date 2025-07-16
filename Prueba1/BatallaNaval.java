package BN.Prueba1;


public class BatallaNaval {
  public static void main(String[] args) throws Exception {
       
        Tablero tablero = new Tablero(0, 0);
        Turnos adivinar = new Turnos();


       
        iniciodeJuego();
        reglastablero();
        System.out.println("Player 1 arma tablero");
        tablero.crearTablero();
        tablero.mostrarTablero();
       // System.out.println("Player 2 arma tablero");
       // tablero.crearTablero();
       // tablero.mostrarTablero();
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
        String reglas = "Arma el tablero primero: ingresá 1 para barco y 0 para agua ";
        System.out.println(reglas);
    }
}


