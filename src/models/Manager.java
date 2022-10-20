package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JTree;

public class Manager {

	private ArrayList<String> simbolosNoTerminales;
	private ArrayList<String> simbolosTerminales;
	private ArrayList<Producciones> producciones;
	private String simboloInicial;
	private String init;
	private BinaryTree arbol;
	private String simboloADerivar = "";
	private String concat = "";

	private Scanner sc;

	public Manager(String simbolosNT, String simbolosT, String produccion, String simboloInicial) {
		sc = new Scanner(System.in);
		simbolosNoTerminales = new ArrayList<>();
		simbolosTerminales = new ArrayList<>();
		producciones = new ArrayList<>();
		arbol = new BinaryTree();
		añadirSimbolosNoTerminales(simbolosNT);
		añadirSimbolosTerminales(simbolosT);
		añadirProducciones(produccion);
		añadirSimboloInicial(simboloInicial);
	}
	/*
	 * En este metodo se instancias los simbolos no terminales
	 */
	public void añadirSimbolosNoTerminales(String entrada) {
		String[] simbolos = entrada.split(",");
		for (int i = 0; i < simbolos.length; i++) {
			simbolosNoTerminales.add(simbolos[i]);
		}
	}

	/*
	 * En este metodo se instancias los simbolos terminales
	 */
	public void añadirSimbolosTerminales(String entrada) {
		String[] simbolos = entrada.split(",");
		for (int i = 0; i < simbolos.length; i++) {
			simbolosTerminales.add(simbolos[i]);
		}
		arbol.setSymbols(simbolosTerminales);
	}

	/*
	 * En este metodo se añaden las producciones correspondientes a la gramatica
	 */
	public void añadirProducciones(String entrada) {
		String[] produccion = entrada.split(",");
		for (int i = 0; i < produccion.length; i++) {
			String[] elementos = produccion[i].split("->");
			producciones.add(new Producciones(elementos[0], elementos[1]));
		}
	}

	/*
	 * En este metodo se establece el simbolo inicial
	 */
	public void añadirSimboloInicial(String simbolo) {
		if (simbolosNoTerminales.contains(simbolo)) {
			simboloInicial = simbolo;
		}
	}

	/*
	 * Metodo de prueba
	 */
	public void imprimir() {
		System.out.println("Simbolos no terminales -> "+  simbolosNoTerminales);
		System.out.println("Simbolos terminales -> " + simbolosTerminales);
		System.out.println("Producciones -> " + producciones);
		System.out.println("Simbolo incial -> "+ simboloInicial);
	}

	/*
	 * En este metodo se construye el arbol deacuerdo a los parametros iniciales.
	 */
	public void añadir() {
		ArrayList<String> der = buscar(simboloInicial);
	//	System.out.println("ELEMENTO A A�ADIR" + der);
		for (int i = 0; i < der.size(); i++) {
			arbol.addElement(concat + der.get(i));
		}
	}

	/*
	 * En este metodo se busca a donde para una respectiva produccion teniendo como base su origen
	 */
	public ArrayList<String> buscar(String origen) {
		if (!simboloADerivar.equals("")) {
			for (int j = 0; j < simboloADerivar.length(); j++) {
				if (simboloADerivar.charAt(j) >= 97 && simboloADerivar.charAt(j) <= 122) {
					concat += simboloADerivar.charAt(j);
				}
			}
		}
		ArrayList<Producciones> p = new ArrayList<>();
		ArrayList<String> derivaciones = new ArrayList<>();
		for (int i = 0; i < producciones.size(); i++) {
			if (producciones.get(i).getOrigen().equals(origen)) {
				p.add(producciones.get(i));
			}
		}
		for (int i = 0; i < p.size(); i++) {
			derivaciones.add(p.get(i).getDestino());
			if (p.get(i).getDestino().length() == 2) {
				simboloInicial = p.get(i).obtenerSimboloNoTerminalDestino();
				simboloADerivar = p.get(i).getDestino();
			}
		}
		return derivaciones;
	}

	/*
	 * En este metodo, se a�ade por completo el arbol con n iteraciones
	 */
	public void añadiduraCompleta() {
		arbol.addElement(simboloInicial);
		init = simboloInicial;
		int count = 0;
		while (count < 6) {
			añadir();
			count++;
		}
		System.out.println(arbol.print());
	}


	/*
	 * En este metodo, se obtiene la derivacion horizontal de la palabra
	 */
	public String derivacionHorizontal(String palabra){
		ArrayList<String> derivacionHorizontal = arbol.getPath(palabra);
		String derivacionFinal = "";
		for (int i = 0; i < derivacionHorizontal.size(); i++) {
			derivacionFinal += derivacionHorizontal.get(i) + ((i != derivacionHorizontal.size()-1) ? "->" : "");
		}
		return derivacionFinal;
	}
	
	/*
	 * En este metodo, se determina si un palabra dada pertenece o no a un lenguaje originado por la gramatica. 
	 */
	public boolean perteneceONo(String palabra) {
		ArrayList<String> derivacionHorizontal = arbol.getPath(palabra);
		return palabra.equals(derivacionHorizontal.get(derivacionHorizontal.size()-1));
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\"Ingrese los simbolos no terminales\"");
		String inputSimbolosNT = scanner.nextLine();
		System.out.println("\"Ingrese los simbolos terminales\"");
		String simbolosTerminales = scanner.nextLine();
		System.out.println("\"Ingrese las producciones\"");
		String producciones = scanner.nextLine();
		System.out.println("\"Ingrese el simbolo inicial\"");
		String simboloInicial = scanner.nextLine();
		System.out.println("--------------------------------------------");

		Manager m = new Manager(inputSimbolosNT ,simbolosTerminales, producciones, simboloInicial);
		//Manager m = new Manager("S,T,R", "a,b", "S->aT,T->bR,R->aT,T->b", "S");
		//Manager m = new Manager("S", "a", "S->a,S->aS", "S");
		m.imprimir();
		m.añadiduraCompleta();
		// System.out.println(m.perteneceONo("abab"));
		System.out.println("Derivación horizontal -> " + m.derivacionHorizontal("abab"));
		System.out.println(m.perteneceONo("abab")?"->W1 ∈ L<- La palabra w1 pertenece al lenguaje generado por la gramática":"No pertenece");

	}

}
