package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import objetos.Metodo_Newton_Raphson;
import interfaces.Clickeable;

public class VentanaPrin1 extends JFrame{

	private PnlAbajo pnlAbajo = new PnlAbajo();
	private PnlInsercion pnlInsercion = new PnlInsercion();
	private PnlTabla tabla = new PnlTabla();
	private Metodo_Newton_Raphson controlador;
	
	public VentanaPrin1() {
		// TODO Auto-generated constructor stub
		super.setSize(550, 600);
		super.setBackground(Color.orange);
		super.setLayout(new BorderLayout(8, 1));
		super.setLocationRelativeTo(null);
                pnlInsercion.setFuego( new Clickeable() {
                    @Override
                    public void buscarRaiz() {
                        controlador = new Metodo_Newton_Raphson(Float.parseFloat(pnlInsercion.getTxtError().getText()), Float.parseFloat(pnlInsercion.getTxtError().getText()));
                        tabla.crearModelo(controlador.getFilas());
                        tabla.setVisible(true); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void limpiar() {
                        //To change body of generated methods, choose Tools | Templates.
                    }
                }
                    //To change body of generated methods, choose Tools | Templates
                        
                );
                pnlAbajo.setClick(new Clickeable() {
                    @Override
                    public void buscarRaiz() {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void limpiar() {
                        tabla.setVisible(false);
                        pnlInsercion.getTxtError().setText("");
                        pnlInsercion.getTxtPuntoI().setText("");
                    }
                });
		super.add(pnlInsercion, BorderLayout.NORTH);
		super.add(tabla, BorderLayout.CENTER);
		super.add(pnlAbajo, BorderLayout.SOUTH);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
