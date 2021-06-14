package co.edu.unbosque.model;

public class RamaYPoda {

	private final int posDestinoFilas;
	private final int posDestinoColumnas;
	private final int ANCHO;
	private final int ALTO;
	private final int saltoP;
	private final int saltoQ;
	private Nodo nodoSolucionado;
	private int alturaNodoSolucionado = Integer.MAX_VALUE;
	private final String[] movimientos = { "ARRIBA_IZQUIERDA", "ARRIBA_DERECHA", "ABAJO_IZQUIERDA", "ABAJO_DERECHA",
			"IZQUIERDA_ARRIBA", "IZQUIERDA_ABAJO", "DERECHA_ARRIBA", "DERECHA_ABAJO" };

	private int contador = 0;
	public RamaYPoda(int posDestinoFilas, int posDestinoColumnas, Nodo nodoRaiz, int ancho, int alto, int saltoP,
			int saltoQ) {
		this.posDestinoFilas = posDestinoFilas;
		this.posDestinoColumnas = posDestinoColumnas;
		this.ANCHO = ancho;
		this.ALTO = alto;
		this.saltoP = saltoP;
		this.saltoQ = saltoQ;
		try {
			saltoLiebre(nodoRaiz);
			if(nodoSolucionado == null) {
				System.out.println("No hay solución nodo nulo");
			}else {
				imprimirMatriz(nodoSolucionado);
				System.out.println("altura: "+nodoSolucionado.getAltura());
				System.out.println("llamados Recursivos: "+contador);
			}
		}catch(StackOverflowError e) {
			System.out.println("Se desborda la pila, contador: "+contador);
		}
		
	}

	
	public Nodo saltoLiebre(Nodo nodo) throws StackOverflowError {
		contador++;
		nodo.setVisitado(true);
		if(nodo.getAltura() < alturaNodoSolucionado) {
			System.out.println("Entra, la altura es menor, altura: "+nodo.getAltura());
			for (String movimiento : movimientos) {
				Nodo nodoHijo = generarMovimiento(nodo, movimiento);
				if (nodoHijo != null) {
					if (nodo.getPadre() == null) {
						if (!existe(nodo, nodoHijo)) {
							nodoHijo.setAltura(calcularAltura(nodoHijo));
							boolean solucion = solucionado(nodoHijo);
							if (solucion) {
								System.out.println("Solucionado! altura: " + nodoHijo.getAltura());

							}
							nodo.getHijos().add(nodoHijo);
						}

					} else if (!(nodo.getPadre().getPosicionFila() == nodoHijo.getPosicionFila() && nodo.getPadre().getPosicionColumna() == nodoHijo.getPosicionColumna())) {
						if (!existe(nodo, nodoHijo)) {
							nodoHijo.setAltura(calcularAltura(nodoHijo));
							boolean solucion = solucionado(nodoHijo);
							if (solucion) {
								System.out.println("Solucionado! altura: " + nodoHijo.getAltura() + " Padre: " + nodoHijo.getPadre().getPosicionFila() + " " + nodoHijo.getPadre().getPosicionColumna() + " Padre del padre: " + nodoHijo.getPadre().getPadre().getPosicionFila() + " " + nodoHijo.getPadre().getPadre().getPosicionColumna());

							}
							nodo.getHijos().add(nodoHijo);
						}

					}

				}
			}
		
		Nodo aux = nodoEscogido(nodo);
		
		if(aux!=null) {
			System.out.println("Escogido: "+aux);
			System.out.println("Altura del escogido: "+aux.getAltura());
			saltoLiebre(aux);
		}
		
		}
		else {
			System.out.println("No entra porque la altura del nodo no es menor a la altura de la solución ");
				saltoLiebre(nodo.getPadre());
		}
		return nodo;
	}

	private Nodo nodoEscogido(Nodo nodo) {
		contador++;
		Nodo aux = null;
		int masCercano = Integer.MAX_VALUE;
		for(int i = 0;i<nodo.getHijos().size();i++) {
			if(!nodo.getHijos().get(i).isVisitado()) {
				int sumaPosiciones = nodo.getHijos().get(i).getPosicionColumna() + nodo.getHijos().get(i).getPosicionFila();
				int sumaPosicionesDestino = posDestinoFilas+posDestinoColumnas;
				int suma = Math.abs(sumaPosicionesDestino-sumaPosiciones);
				if(suma < masCercano) {
					System.out.println("Menor: "+suma);
					aux = nodo.getHijos().get(i);
					masCercano = suma;
				}
				
			}
		}
		if(aux == null && nodo.getPadre() !=null) {
			return nodoEscogido(nodo.getPadre());
		}
		return aux;
		
	}
	
	
	private boolean existe(Nodo nodo,Nodo nodoHijo) {
		if(nodo.getHijos().size() == 0) return false;
		for(int i=0;i<nodo.getHijos().size();i++) {
			if(nodo.getHijos().get(i).getPosicionFila() == nodoHijo.getPosicionFila() 
			 && nodo.getHijos().get(i).getPosicionColumna() == nodoHijo.getPosicionColumna()) {
				return true;
			}
		}
		return false;
	}

	public void imprimirMatriz(Nodo posicion) {
		try {
		while(true) {	
		for (int i = 0; i < ANCHO; i++) {
			for (int j = 0; j < ALTO; j++) {
				if (i == posDestinoFilas && j == posDestinoColumnas) {
					System.out.print("X" + " ");
				} else if (i == posicion.getPosicionFila() && j == posicion.getPosicionColumna()) {
					System.out.print("L" + " ");
				} else {
					System.out.print("0" + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
		posicion = posicion.getPadre();
		}
		}catch(NullPointerException ignored) {
			
		}
	}

	public int calcularAltura(Nodo nodo) {
		int contador = 1;
		Nodo aux = nodo;
		while(aux.getPadre() != null) {
			aux = aux.getPadre();
			contador ++;
		}
		return contador;
	}
	
	public boolean solucionado(Nodo nodo) {
		if (nodo.getPosicionFila() == posDestinoFilas && nodo.getPosicionColumna() == posDestinoColumnas) {
				nodoSolucionado = nodo;
				alturaNodoSolucionado = nodo.getAltura();
			return true;
		}
		return false;
	}

	public Nodo generarMovimiento(Nodo nodo, String direccion) {
		int[] posicionesNuevas = validar(nodo, direccion);
		if (posicionesNuevas != null) {
			Nodo nuevoHijo = new Nodo(posicionesNuevas[0], posicionesNuevas[1]);
			nuevoHijo.setPadre(nodo);
			return nuevoHijo;
		}
		return null;
	}

	public int[] validar(Nodo nodo, String direccion) {
		int posicionFilas = nodo.getPosicionFila();
		int posicionColumnas = nodo.getPosicionColumna();
		int[] posiciones = new int[2];
		switch (direccion) {
			case "ARRIBA_IZQUIERDA" -> {
				if (posicionFilas - saltoP < 0)
					return null;
				if (posicionColumnas - saltoQ < 0)
					return null;
				posiciones[0] = posicionFilas - saltoP;
				posiciones[1] = posicionColumnas - saltoQ;
				return posiciones;
			}
			case "ARRIBA_DERECHA" -> {
				if (posicionFilas - saltoP < 0)
					return null;
				if (posicionColumnas + saltoQ > ANCHO - 1)
					return null;
				posiciones[0] = posicionFilas - saltoP;
				posiciones[1] = posicionColumnas + saltoQ;
				return posiciones;
			}
			case "ABAJO_IZQUIERDA" -> {
				if (posicionFilas + saltoP > ALTO - 1)
					return null;
				if (posicionColumnas - saltoQ < 0)
					return null;
				posiciones[0] = posicionFilas + saltoP;
				posiciones[1] = posicionColumnas - saltoQ;
				return posiciones;
			}
			case "ABAJO_DERECHA" -> {
				if (posicionFilas + saltoP > ALTO - 1)
					return null;
				if (posicionColumnas + saltoQ > ANCHO - 1)
					return null;
				posiciones[0] = posicionFilas + saltoP;
				posiciones[1] = posicionColumnas + saltoQ;
				return posiciones;
			}
			case "IZQUIERDA_ARRIBA" -> {
				if (posicionColumnas - saltoP < 0)
					return null;
				if (posicionFilas - saltoQ < 0)
					return null;
				posiciones[0] = posicionFilas - saltoQ;
				posiciones[1] = posicionColumnas - saltoP;
				return posiciones;
			}
			case "IZQUIERDA_ABAJO" -> {
				if (posicionColumnas - saltoP < 0)
					return null;
				if (posicionFilas + saltoQ > ALTO - 1)
					return null;
				posiciones[0] = posicionFilas + saltoQ;
				posiciones[1] = posicionColumnas - saltoP;
				return posiciones;
			}
			case "DERECHA_ARRIBA" -> {
				if (posicionColumnas + saltoP > ANCHO - 1)
					return null;
				if (posicionFilas - saltoQ < 0)
					return null;
				posiciones[0] = posicionFilas - saltoQ;
				posiciones[1] = posicionColumnas + saltoP;
				return posiciones;
			}
			case "DERECHA_ABAJO" -> {
				if (posicionColumnas + saltoP > ANCHO - 1)
					return null;
				if (posicionFilas + saltoQ > ALTO - 1)
					return null;
				posiciones[0] = posicionFilas + saltoQ;
				posiciones[1] = posicionColumnas + saltoP;
				return posiciones;
			}
		}

		return null;

	}

}
