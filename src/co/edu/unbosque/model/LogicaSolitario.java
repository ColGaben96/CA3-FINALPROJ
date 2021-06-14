package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian Moncaleano
 * @version 1.0
 */
public class LogicaSolitario {

    private final Tablero tablero;
    private final List<int[][]> solucion;
    private final String[] direcciones;

    public LogicaSolitario() {
        tablero = new Tablero();
        solucion = new ArrayList<>();
        direcciones = tablero.getDirecciones();
        int[][] matrizInicial = {
                {0, 0, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 2, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 0, 0}};
        solucion.add(matrizInicial);

        boolean solucionado = iterarMatrices(1);
        if (solucionado) {
            System.out.println("¡Se encontró la solución!");
            imprimirSolucion();
        } else {
            System.out.println("****No hay Solución**********");
            System.out.println("A continuación se ven las decisiones de la solución");
        }
    }

    public boolean iterarMatrices(int posicion) {
        for (int i = 0; i < Tablero.getAncho(); i++) {
            for (int j = 0; j < Tablero.getAlto(); j++) {

                for (String direccione : direcciones) {

                    if (tablero.move(i, j, direccione)) {
                        System.out.println("Se hace Movimiento desde la posición " + i + " - " + j);
                        imprimirMatriz(tablero.getMatriz());
                        if (solucion.size() <= posicion) {
                            int[][] matriz = new int[7][7];
                            solucion.add(matriz);
                        }
                        tablero.rellenarMatriz(tablero, solucion.get(posicion));
                        if (!tablero.isSolucion(tablero.getMatriz())) {
                            if (iterarMatrices(posicion + 1)) {
                                return true;
                            } else {
                                tablero.retroceder(i, j, direccione);
                                System.out.println("No hay solución, Se hace backingtrack a la posición " + i + " - " + j);
                                imprimirMatriz(tablero.getMatriz());
                            }
                        } else {
                            return true;
                        }


                    }

                }
            }


        }
        return false;
    }


    public void imprimirSolucion() {

        for (int i = 0; i < solucion.size(); i++) {
            System.out.println("Paso Número: " + (i + 1) + "\n");
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    System.out.print(solucion.get(i)[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }


    public void imprimirMatriz(int[][] matriz) {
        for (int[] ints : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
