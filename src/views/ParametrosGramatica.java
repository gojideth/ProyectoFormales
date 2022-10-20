package views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ParametrosGramatica extends JDialog {
	
	public ParametrosGramatica() {
		this.setSize(700,600);
		this.setLocationRelativeTo(null);
		initComponents();
		this.setVisible(true);
	}

	private void initComponents() {
		this.setLayout(new GridLayout(6,2));
		JLabel SNT = new JLabel("Por favor, introduzca los simbolos no terminales");
		JTextField simbolosNoTerminales = new JTextField();
		JLabel ST = new JLabel("Por favor, introduzca los simbolos terminales");
		JTextField simbolosTerminales = new JTextField();
		JLabel P = new JLabel("Por favor, introduzca las producciones");
		JTextField produccion = new JTextField();
		JLabel SI = new JLabel("Por favor, introduzca los simbolos iniciales");
		JTextField simboloInicial = new JTextField();
		JLabel w1 = new JLabel("Por favor, introduzca la palabra a comprobar");
		JTextField palabra = new JTextField();
		JButton boton = new JButton("Aceptar");
		add(SNT);
		add(simbolosNoTerminales);
		add(ST);
		add(simbolosTerminales);
		add(P);
		add(produccion);
		add(SI);
		add(simboloInicial);
		add(w1);
		add(palabra);
		add(boton);
	}
	
	public static void main(String[] args) {
		new ParametrosGramatica();
	}

	
}
