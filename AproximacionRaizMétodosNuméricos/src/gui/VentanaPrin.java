package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import objetos.Controlador;
import objetos.NumerosPositivosException;

public class VentanaPrin extends JFrame{
	private JLabel[] etiquetas = new JLabel[3];
	private JTextField [] campos = new JTextField[3];
	private ButtonGroup rdbtns;
	private JRadioButton [] buttonsR = new JRadioButton [2];
	private Font fuente;
	private JLabel [] resultado = new JLabel[4];
	private JButton [] botones = new JButton[2]; 
	
	public VentanaPrin() {
		// TODO Auto-generated constructor stub
		super.setSize(550, 600);
		super.setLayout(new GridLayout(7, 2));
		super.setLocationRelativeTo(null);
		fuente = new Font("Futura", Font.BOLD, 20);
		etiquetas[0] = new JLabel("xl");
		etiquetas[0].setFont(fuente);
		etiquetas[1] = new JLabel("xu");
		etiquetas[1].setFont(fuente);
		etiquetas[2] = new JLabel("Er");
		etiquetas[2].setFont(fuente);
		for (int i = 0; i < campos.length; i++) {
			campos[i] = new JTextField(10);
			campos[i].setFont(fuente);
		}
		
		buttonsR[0] = new JRadioButton("Bisección");
		buttonsR[1] = new JRadioButton("Falsa posición");
		
		rdbtns = new ButtonGroup();
		
		for (int i = 0; i < buttonsR.length; i++) {
			rdbtns.add(buttonsR[i]);
		}
		
		for (int i = 0; i < campos.length; i++) {
			super.add(etiquetas[i]);
			super.add(campos[i]);
		}
		
		for (int i = 0; i < buttonsR.length; i++) {
			buttonsR[i].setFont(fuente);
			buttonsR[i].setHorizontalAlignment(JRadioButton.CENTER);
			//buttonsR[i].setHorizontalTextPosition(JRadioButton.CENTER);
			super.add(buttonsR[i]);
		}
		
		for (int i = 0; i < resultado.length; i++) {
			resultado[i] = new JLabel();
			resultado[i].setFont(fuente);
		}
		
		resultado[0].setText("El resultado es:");
		resultado[2].setText("Número de iteracciones: ");
		
		for (int i = 0; i < resultado.length; i++) {
			super.add(resultado[i]);
		}
		
		botones[0] = new JButton("Analizar");
		botones[1] = new JButton("jnjwn");
		
		for (int i = 0; i < botones.length; i++) {
			int aux = i;
			botones[i].setFont(fuente);
			botones[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				
					if(aux == 0) {
						try {
							Float xl, xu, error;
							xl = Float.parseFloat(campos[0].getText());
							xu = Float.parseFloat(campos[1].getText());
							error = Float.parseFloat(campos[2].getText());
							Controlador controlador = new Controlador(xl, xu, error);
							if (buttonsR[0].isSelected()) {
								if(controlador.isNegativo(xl, xu)) {
									controlador.biseccion();
									String resultadoPre = String.valueOf(controlador.getXr());
									resultado[1].setText(resultadoPre);
									resultado[3].setText(String.valueOf(controlador.getIteracciones()));
								}
								else {
									try {
										throw new NumerosPositivosException();
									} catch (NumerosPositivosException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(getContentPane(), 
												"Los números iniciales son positivos",
												"ERROR",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							}
							if(buttonsR[1].isSelected()) {
								
								if(controlador.isNegativo(xl, xu)) {
									controlador.falsa_posicion();
									String resultadoPre = String.valueOf(controlador.getXr());
									resultado[1].setText(resultadoPre);
									resultado[3].setText(String.valueOf(controlador.getIteracciones()));
								}
								else {
									try {
										throw new NumerosPositivosException();
									} catch (NumerosPositivosException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(getContentPane(), 
												"Los números iniciales son positivos",
												"ERROR",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						} catch (NumberFormatException e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(getContentPane(), 
									"Formato inválido",
									"ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						
					}
					else {
						
					}
				}
			});
			super.add(botones[i]);
		}
		
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
