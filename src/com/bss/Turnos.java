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


    int fila, columna, tocado = 0;
    int hundidoP1;
    int size;
    boolean esTurnoJugador1 = true;
    int hundidoP2;
    int player;
    boolean ganaste = false;
     int tocadoP2 = 0;
     int tocadoP1 = 0;


    Scanner input = new Scanner(System.in);


    Tablero tableroActual;
    Tablero tableroPlayer1;
    Tablero tablerodeDisparosPlayer1;
    Tablero tableroPlayer2;
    Tablero tableroDeDisparosPlayer2;
    Tablero tableroOponente;
    Tablero tableroDeDisparosActual;


    public Turnos(Tablero tableroPlayer1, Tablero tablerodeDisparosPlayer1, Tablero tableroPlayer2,
            Tablero tablerodeDisparosPlayer2) {
        this.tableroPlayer1 = tableroPlayer1;
        this.tablerodeDisparosPlayer1 = tablerodeDisparosPlayer1;
        this.tableroPlayer2 = tableroPlayer2;
        this.tableroDeDisparosPlayer2 = tablerodeDisparosPlayer2;
        this.hundidoP1 = this.tableroPlayer2.getSize();
        this.hundidoP2 = this.tableroPlayer1.getSize();
    }


    public void disparos() {
        try {
            do {
   
               if (quienDispara() == QuienDispara.PLAYER_1){
                    System.out.println(ANSI_GREEN + "\n--- Turno del Jugador 1 ---" );
                    do {
                        validarDisparo();
                        disparaPlayer1();
                        imprimirTableroDeDisparos();
                         esTurnoJugador1 = false;


                    } while (esTurnoJugador1 == true);
                   
                   
                } else {
                    System.out.println(ANSI_PURPLE + "\n--- Turno del Jugador 2 ---" );
                   do {
                    validarDisparo();
                    disparaPlayer2();
                    imprimirTableroDeDisparos();
                    esTurnoJugador1 = true;
                   }while (esTurnoJugador1 == false);
                }




            } while (ganaste == false);    


        } catch (Exception e) { // Captura cualquier excepción para evitar que el programa se caiga
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());


        }
    }


    public QuienDispara quienDispara() {
        QuienDispara player;


        if (esTurnoJugador1) {
            player = QuienDispara.PLAYER_1;
            tableroOponente = tableroPlayer2;
            tableroDeDisparosActual = tablerodeDisparosPlayer1;


        } else {
            player = QuienDispara.PLAYER_2;
            tableroOponente = tableroPlayer1;
            tableroDeDisparosActual = tableroDeDisparosPlayer2;


        }
        return player;
    }




public Tablero disparaPlayer1(){
// Verificar si ya se intentó esta posición en el tablero de disparos
     if (tableroDeDisparosActual.getTableroDelJuego()[fila][columna] != 0) { // 0 significa que no se ha disparado allí
        System.out.println("Ya has intentado esta posición. Elige otra.");
    }


     // Accedo al tablero del juego para ver si encuentra el barco
    if (tableroOponente.getTableroDelJuego()[fila][columna] == 1) {
        tableroDeDisparosActual.getTableroDelJuego()[fila][columna] = 1; // Marca como acierto en el tablero de disparos
        tocadoP2++;
       
       
       
        if (tocadoP2 == tableroPlayer2.getSize()){
            ganaste = true;
            System.out.println(ANSI_YELLOW + "hundiste el barco de tamaño" + tableroPlayer2.getSize());
            System.out.println("Ganaste!");
        } else  System.out.println("¡Tocado! Te falta encontrar " + (tableroPlayer2.getSize() - tocadoP2) + " casillas");
       
       


    } else {
            System.out.println("Fallaste. No hay barco en esa posición.");
            tableroDeDisparosActual.getTableroDelJuego()[fila][columna] = -1; // Marca como fallo en el tablero de disparos
    }
    return tableroDeDisparosActual;
}


public Tablero disparaPlayer2(){


// Verificar si ya se intentó esta posición en el tablero de disparos
     if (tableroDeDisparosActual.getTableroDelJuego()[fila][columna] != 0) { // 0 significa que no se ha disparado allí
        System.out.println("Ya has intentado esta posición. Elige otra.");
    }


     // Accedo al tablero del juego para ver si encuentra el barco
    if (tableroOponente.getTableroDelJuego()[fila][columna] == 1) {
        tableroDeDisparosActual.getTableroDelJuego()[fila][columna] = 1; // Marca como acierto en el tablero de disparos
     
      tocadoP1++;


        if (tocadoP1 == tableroPlayer1.getSize()){
            ganaste=true;
            System.out.println(ANSI_YELLOW + "hundiste el barco de tamaño " + tableroPlayer1.getSize());
            System.out.println( "Ganaste!");
        } else  System.out.println("¡Tocado! Te falta encontrar " + (tableroPlayer1.getSize() - tocadoP1) + " casillas");
       
       


    } else {
            System.out.println("Fallaste. No hay barco en esa posición.");
            tableroDeDisparosActual.getTableroDelJuego()[fila][columna] = -1; // Marca como fallo en el tablero de disparos
    }




    return tableroDeDisparosActual;
}


public void validarDisparo(){
    do{
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
               
}while (fila > 2 || columna > 2);
}
    // Método para imprimir el tablero de disparos del jugador
    public void imprimirTableroDeDisparos() {


        System.out.println(ANSI_RESET + "\n--- Tu Tablero de Disparos ---");
       
                for (int i = 0; i < tableroDeDisparosActual.getTablerodeDisparos().length; i++) {
                    for (int j = 0; j < tableroDeDisparosActual.getTablerodeDisparos().length; j++) {


                        switch (tableroDeDisparosActual.getTableroDelJuego()[i][j]) {
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


