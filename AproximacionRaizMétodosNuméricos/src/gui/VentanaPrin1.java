package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import objetos.Metodo_Newton_Raphson;

public class VentanaPrin1 extends JFrame{

	private PnlAbajo pnlAbajo = new PnlAbajo();
	private PnlInsercion pnlInsercion = new PnlInsercion();
	private PnlTabla tabla;
	private Metodo_Newton_Raphson controlador;
	
	public VentanaPrin1() {
		// TODO Auto-generated constructor stub
		super.setSize(550, 600);
		super.setBackground(Color.orange);
		super.setLayout(new BorderLayout(8, 1));
		super.setLocationRelativeTo(null);
		controlador = new Metodo_Newton_Raphson(0f, 0.01f);
		tabla = new PnlTabla(controlador.getFilas());
		super.add(pnlInsercion, BorderLayout.NORTH);
		super.add(tabla, BorderLayout.CENTER);
		super.add(pnlAbajo, BorderLayout.SOUTH);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
