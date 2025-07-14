package BN.Prueba1;



public class BatallaNaval {
   public static void main(String args[]) {


        iniciodejuego(null);
        reglastablero(null);
        Turnos.player1(null);
        Turnos.player2(null);
    }


    // Mensaje de bienvenida, inicio del juego
    public static void iniciodejuego(String mensajeBienvenida) {
        mensajeBienvenida = "Bienvenido a la Batalla Naval";
        System.out.println(mensajeBienvenida);
    }
    // Reglas de armado de tablero


    public static void reglastablero(String reglas) {
        reglas = "Arma el tablero primero: ingresá 1 para barco y 0 para agua ";
        System.out.println(reglas);
    }
}
