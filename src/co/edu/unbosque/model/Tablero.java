package co.edu.unbosque.model;
/**
 *Modelamiento del tablero continental, se inicia la matriz con las posiciones dadas, 
 * y algunos atributos como el alto y el ancho del tablero, as� como una varible con valor "2"
 * que nos dir� si una casilla ya est� vac�a.
 * @author Sebastian Moncaleano
 *
 */
public class Tablero {

	private final static int FICHA = 1;
	private final static int ANCHO = 7;
	private final static int ALTO = 7;
	private final static int VACIO = 2;
	
	
	private String[] direcciones = {"ARRIBA","ABAJO","DERECHA","IZQUIERDA"};
	
	
	private int[][] matriz =  {
			{0, 0, 1, 1, 1, 0, 0},{0, 0, 1, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 2, 1, 1, 1}, 
			{1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 0, 0}};
	
	public Tablero() {
		 
	}
	

	
	public String[] getDirecciones() {
		return direcciones;
	}




	public void setDirecciones(String[] direcciones) {
		this.direcciones = direcciones;
	}




	public int[][] getMatriz() {
		return matriz;
	}




	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}




	public static int getAncho() {
		return ANCHO;
	}




	public static int getAlto() {
		return ALTO;
	}




	public void rellenarMatriz(Tablero tablero, int[][] matrizRecibida) {
		for(int i=0;i<ANCHO;i++) {
			for(int j=0;j<ALTO;j++) {
				matrizRecibida[i][j] = tablero.matriz[i][j];
			}
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
	

	public boolean move(int i, int j,String direccion) {
		int nuevaFila = posicionFila(i,direccion);
		int nuevaColumna = posicionColumna(j,direccion);
		
		if(sePuedeMover(i,j,nuevaFila,nuevaColumna)) {
			colocarFicha(nuevaFila,nuevaColumna);
			vaciarCelda(i, j);
			vaciarCelda((i+nuevaFila)/2,(j+nuevaColumna)/2);
			return true;
		}
		return false;
	}
	
	public void retroceder(int i,int j, String direccion) {
		int nuevaFila = posicionFila(i,direccion);
		int nuevaColumna = posicionColumna(j,direccion);
		
		vaciarCelda(nuevaFila,nuevaColumna);
		colocarFicha(i,j);
		colocarFicha((i+nuevaFila)/2,(j+nuevaColumna)/2);
	}
	

	private int posicionFila(int i, String direccion) {
		switch(direccion) {
		case "DERECHA":
			return i+2;
		case "IZQUIERDA":
			return i-2;
		}
		return i;
	}
	

	private int posicionColumna(int j, String direccion) {
		switch(direccion) {
		case "ARRIBA":
			return j-2;
		case "ABAJO":
			return j+2;
		}
		return j;
	}
	
	
	public boolean celdaLlena(int i, int j) {
		return matriz[i][j] == FICHA;
	}
	

	public boolean isSolucion(int[][] matriz) {
    	int contador = 0;
    	for(int i=0;i<matriz.length;i++) {
    		for(int j=0;j<matriz[0].length;j++) {
    			if(matriz[i][j] == 1)
    				contador ++;
    		}
    	}
    	return contador==1&&matriz[3][3]==1?true:false;
    }
	
	
	private void colocarFicha(int i, int j) {
		matriz[i][j] = FICHA;
	}
	
	private void vaciarCelda(int i, int j) {
		matriz[i][j] = VACIO;
	}
	
	
	public void imprimirMatriz() {
        for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                        System.out.print(matriz[i][j]);
                }
                System.out.println();
        }
        System.out.println();
}
	
	
	
	
	
	
	
	
	
}
