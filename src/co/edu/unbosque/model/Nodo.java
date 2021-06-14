package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	private Nodo padre;
	private List<Nodo> hijos;
	private int posicionFila;
	private int posicionColumna;
	private boolean visitado;
	private int altura;
	
	public Nodo(int posicionFila, int posicionColumna) {
		this.posicionFila = posicionFila;
		this.posicionColumna = posicionColumna;
		hijos = new ArrayList<Nodo>();
	}


	public Nodo getPadre() {
		return padre;
	}


	public void setPadre(Nodo padre) {
		this.padre = padre;
	}


	public List<Nodo> getHijos() {
		return hijos;
	}


	public void setHijos(List<Nodo> hijos) {
		this.hijos = hijos;
	}


	


	public int getPosicionFila() {
		return posicionFila;
	}


	public void setPosicionFila(int posicionFila) {
		this.posicionFila = posicionFila;
	}


	public int getPosicionColumna() {
		return posicionColumna;
	}


	public void setPosicionColumna(int posicionColumna) {
		this.posicionColumna = posicionColumna;
	}


	public boolean isVisitado() {
		return visitado;
	}


	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	
	
	
	public int getAltura() {
		return altura;
	}


	public void setAltura(int altura) {
		this.altura = altura;
	}


	@Override
	public String toString() {
		return "Nodo posicionFila=" + posicionFila + ", posicionColumna="
				+ posicionColumna + ", visitado=" + visitado + "]";
	}
	
	
}
