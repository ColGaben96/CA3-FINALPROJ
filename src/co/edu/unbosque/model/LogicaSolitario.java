package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;


public class LogicaSolitario {
	
	private Tablero tablero;
	private List<int[][]> solucion;
	private String[] direcciones;
	
	private int[][] matrizInicial =  {
			{0, 0, 1, 1, 1, 0, 0},{0, 0, 1, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 2, 1, 1, 1}, 
			{1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 0, 0}};
	
	public LogicaSolitario() {
		tablero = new Tablero();
		solucion = new ArrayList<int[][]>();
		direcciones = tablero.getDirecciones();
		solucion.add(matrizInicial);
		
		boolean solucionado = iterarMatrices(1);
		if(solucionado) {
			System.out.println("¡Se encontró la solución!");
			imprimirSolucion();
		}else {
			System.out.println("****No hay Solución**********");
			System.out.println("A continuación se ven las decisiones de la solución");
		}
	}
	
	public boolean iterarMatrices(int posicion) {
		for(int i=0;i<tablero.getAncho();i++) {
			for(int j=0;j<tablero.getAlto();j++) {
				
				for(int k = 0;k<direcciones.length;k++) {
					
					if(tablero.move(i, j, direcciones[k])) {
						System.out.println("Se hace Movimiento desde la posición "+i+" - "+j);
						imprimirMatriz(tablero.getMatriz());
						if(solucion.size()<=posicion) {
							int[][] matriz = new int[7][7];
							solucion.add(matriz);
						}
						tablero.rellenarMatriz(tablero, solucion.get(posicion));
							if(!tablero.isSolucion(tablero.getMatriz())) {
							if(iterarMatrices(posicion +1)) {
								return true;
							}else {
								tablero.retroceder(i, j,direcciones[k]);
								System.out.println("No hay solución, Se hace backingtrack a la posición "+i+" - "+j);
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
		
		for(int i=0;i<solucion.size();i++) {
			System.out.println("Paso Número: "+(i+1)+"\n");
		for(int j=0;j<7;j++) {
			for(int k=0;k<7;k++) {
				System.out.print(solucion.get(i)[j][k]+" ");
			}
			System.out.println();
		}
		System.out.println();
		}
	}
	
	
	public void imprimirMatriz(int[][] matriz) {
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[0].length;j++) {
				System.out.print(matriz[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
}
