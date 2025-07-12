package BN.Prueba1;

import java.util.Scanner;

public class Prueba {
    enum resultado {
        AGUA, HUNDIDO, TOCADO
    }

    public static void main(String args[]) {

        resultado[][] tablero = {
                { resultado.TOCADO, resultado.AGUA, resultado.AGUA },
                { resultado.TOCADO, resultado.AGUA, resultado.AGUA },
                { resultado.AGUA, resultado.AGUA, resultado.AGUA },
                { resultado.AGUA, resultado.AGUA, resultado.AGUA } };

        int fila, columna, contador = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Bienvenido a la Batalla Naval");

        do {
            System.out.println("Ingresá un número de fila para encontrar el barco (del 0 al 2)");
            fila = input.nextInt();
            System.out.println("Ingresá un número de columna para encontrar el barco (del 0 al 3)");
            columna = input.nextInt();

            if (tablero[fila][columna] == tablero[1][01]) {

                System.out.println(resultado.TOCADO);
                contador++;

                if (contador == 2) {
                    tablero[fila][columna] = resultado.HUNDIDO;
                    System.out.println(resultado.HUNDIDO);
                }

            } else {

                System.out.println(resultado.AGUA);
            }

        } while (tablero[fila][columna] == resultado.AGUA || contador < 2);

        input.close();

    }
}
