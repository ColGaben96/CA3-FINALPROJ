package co.edu.unbosque.model;

/**
 * Modelamiento del tablero continental, se inicia la matriz con las posiciones dadas,
 * y algunos atributos como el alto y el ancho del tablero, así como una varible con valor "2"
 * que nos dirá si una casilla ya está vacía.
 *
 * @author Sebastian Moncaleano
 */
public class Tablero {

    private final static int FICHA = 1;
    private final static int ANCHO = 7;
    private final static int ALTO = 7;
    private final static int VACIO = 2;


    private final String[] direcciones = {"ARRIBA", "ABAJO", "DERECHA", "IZQUIERDA"};


    private final int[][] matriz = {
            {0, 0, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 2, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 0, 0}};

    public Tablero() {

    }


    public String[] getDirecciones() {
        return direcciones;
    }


    public int[][] getMatriz() {
        return matriz;
    }


    public static int getAncho() {
        return ANCHO;
    }


    public static int getAlto() {
        return ALTO;
    }


    public void rellenarMatriz(Tablero tablero, int[][] matrizRecibida) {
        for (int i = 0; i < ANCHO; i++) {
            System.arraycopy(tablero.matriz[i], 0, matrizRecibida[i], 0, ALTO);
        }
    }


    private boolean sePuedeMover(int i, int j, int nuevaFila, int nuevaColumna) {
        return 0 <= i && i < matriz.length
                && 0 <= j && j < matriz[i].length
                && 0 <= nuevaFila && nuevaFila < matriz.length
                && 0 <= nuevaColumna && nuevaColumna < matriz[nuevaFila].length
                && matriz[nuevaFila][nuevaColumna] == VACIO
                && matriz[(i + nuevaFila) / 2][(j + nuevaColumna) / 2] == FICHA
                && matriz[i][j] == FICHA;
    }


    public boolean move(int i, int j, String direccion) {
        int nuevaFila = posicionFila(i, direccion);
        int nuevaColumna = posicionColumna(j, direccion);

        if (sePuedeMover(i, j, nuevaFila, nuevaColumna)) {
            colocarFicha(nuevaFila, nuevaColumna);
            vaciarCelda(i, j);
            vaciarCelda((i + nuevaFila) / 2, (j + nuevaColumna) / 2);
            return true;
        }
        return false;
    }

    public void retroceder(int i, int j, String direccion) {
        int nuevaFila = posicionFila(i, direccion);
        int nuevaColumna = posicionColumna(j, direccion);

        vaciarCelda(nuevaFila, nuevaColumna);
        colocarFicha(i, j);
        colocarFicha((i + nuevaFila) / 2, (j + nuevaColumna) / 2);
    }


    private int posicionFila(int i, String direccion) {
        return switch (direccion) {
            case "DERECHA" -> i + 2;
            case "IZQUIERDA" -> i - 2;
            default -> i;
        };
    }


    private int posicionColumna(int j, String direccion) {
        return switch (direccion) {
            case "ARRIBA" -> j - 2;
            case "ABAJO" -> j + 2;
            default -> j;
        };
    }

    public boolean isSolucion(int[][] matriz) {
        int contador = 0;
        for (int[] ints : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (ints[j] == 1)
                    contador++;
            }
        }
        return contador == 1 && matriz[3][3] == 1;
    }


    private void colocarFicha(int i, int j) {
        matriz[i][j] = FICHA;
    }

    private void vaciarCelda(int i, int j) {
        matriz[i][j] = VACIO;
    }
}
