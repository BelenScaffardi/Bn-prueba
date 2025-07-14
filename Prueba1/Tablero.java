package BN.Prueba1;

import java.util.Scanner;

public class Tablero {
     static final String player1 = null;
    static final String player2 = null;


    public static int[][] armarTablero() {


        int[][] tablero = new int [3][3];


        int fila=0 , columna = 0;
        Scanner input = new Scanner(System.in);


       if (fila > 1 || fila < 0)  {
        System.out.println("Error. Ingrese 0 para agua o 1 para barco");
        } else  for (fila = 0; fila < tablero.length; fila++) {
                System.out.println("Para fila " + fila);
                input.nextInt();


                // Inner loop
                if (columna >1 || columna <0){
                     System.out.println("Error. Ingrese 0 para agua o 1 para barco");
                }else for (columna = 0; columna < tablero.length; columna++) {
                    System.out.println("Para columna " + columna);
                    input.nextInt();


                }


            }


        return tablero;


    }


    }


