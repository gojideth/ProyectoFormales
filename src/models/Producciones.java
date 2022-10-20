package models;

import java.util.ArrayList;
import java.util.Iterator;

public class Producciones {

	private String origen;
	private String destino;
	
	public Producciones() {
		origen = "";
		destino = "";
	}

	public Producciones(String origen, String destino) {
		this.origen = origen;
		this.destino = destino;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}

	public String obtenerSimboloNoTerminalDestino() {
		for (int i = 0; i < destino.length(); i++) {
			if (destino.charAt(i) >= 65 && destino.charAt(i) <= 90) {
				return Character.toString(destino.charAt(i));
			}
		}
		return destino;
	}
	
	@Override
	public String toString() {
		return origen + "->" +  destino;
	}

}
