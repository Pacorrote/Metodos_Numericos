package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import enums.Modo;
import javax.crypto.Mac;
import objetos.Fraccion;
import objetos.Resolucion;

public class InterfazPrin extends JFrame{
	
	private JPanel datos = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
	private JLabel lblNumvar = new JLabel("Numero de variables");
	private JTextField txtNumvar = new JTextField(2);
	private JButton aceptar = new JButton("Aceptar");
	private JButton cancelar = new JButton("Cancelar");
	private JPanel pnlRdoBtn = new JPanel(new GridLayout(2, 1));
	private ButtonGroup btngp = new ButtonGroup();
	private JRadioButton decimal = new JRadioButton("Decimal");
	private JRadioButton fraccion = new JRadioButton("Fraccion");
	private JPanel padre = new JPanel(new BorderLayout());
	private JPanel ecuaciones1 = new JPanel();
	private PanelEcuacion ecuaciones[];
	private JPanel pnlbtnSolucionar = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JButton solucionar = new JButton("Soluciones");
	private Integer variables;
	private PanelProcedimiento pnlProc = new PanelProcedimiento();
	private Double [][] matrizDec;
	private Fraccion [][] matrizFrac;
	private Double [][] reMatrizDec;
	private Fraccion [][] reMatrizFrac;
	private Fraccion solFrac [];
	private Double solDec [];
        private Float matrizDecDelta [][];
        private Fraccion matrizFracDelta [][];
        public static final Float CRITERIOSINGULARIDAD = (float) 0.2;
	

	public InterfazPrin() {
		// TODO Auto-generated constructor stub
		super.setSize(540, 650);
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout(10, 10));
        datos.add(lblNumvar);
        txtNumvar.setHorizontalAlignment(JTextField.RIGHT);
        datos.add(txtNumvar);
        decimal.setHorizontalAlignment(JRadioButton.CENTER);
        fraccion.setHorizontalAlignment(JRadioButton.CENTER);
        btngp.add(decimal);
        btngp.add(fraccion);
        pnlRdoBtn.add(decimal);
        pnlRdoBtn.add(fraccion);
        datos.add(pnlRdoBtn);
        datos.add(aceptar);
        aceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ecuaciones1.removeAll();
					variables = Integer.parseInt(txtNumvar.getText());
					ecuaciones1.setLayout(new GridLayout(variables, 1));
					ecuaciones = new PanelEcuacion[variables];
					for (int i = 0; i < ecuaciones.length; i++) {
						if(decimal.isSelected()) {
							ecuaciones[i] = new PanelEcuacion(variables, Modo.decimal);
						}
						else if(fraccion.isSelected()) {
							ecuaciones[i] = new PanelEcuacion(variables, Modo.Fraccion);
						}
						ecuaciones[i].setVisible(true);
						ecuaciones1.add(ecuaciones[i]);
						ecuaciones1.updateUI();
					}
					repaint();
				}catch (Exception e1) {
					// TODO: handle exception
				}
			}
			
		});
        datos.add(cancelar);
        cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ecuaciones1.removeAll();
				ecuaciones1.updateUI();
				pnlProc.removeAll();
				pnlProc.updateUI();
				txtNumvar.setText("");
				btngp.clearSelection();
				repaint();
				
			}
			
		});
        super.add(datos, BorderLayout.NORTH);
        padre.add(ecuaciones1, BorderLayout.CENTER);
        pnlbtnSolucionar.add(solucionar);
        solucionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnlProc.removeAll();
				if(variables!=null) {
					inicializarMatrices();
					try {
						rellenarMatrices();
						ArrayList<String> procesos = new ArrayList<>();
						gauss(procesos);
						soluciones(procesos);
                                                sistemaSingular(procesos);
						pnlProc.setMatrices(procesos);
						pnlProc.setVisible(true);
					} catch (NumberFormatException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(getContentPane(), 
								"Formato invalido",
								"ERROR",
								JOptionPane.ERROR_MESSAGE);
					}catch (ArithmeticException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(getContentPane(), 
								e2.getMessage(),
								"ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
        pnlbtnSolucionar.setBackground(new Color(147, 5, 5));
        padre.add(pnlbtnSolucionar, BorderLayout.SOUTH);
        JScrollPane jSP1 = new JScrollPane(padre, 
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
        		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane jSP2 =  new JScrollPane(pnlProc, 
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
        		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jSP2.setPreferredSize(new Dimension(540, 350));
        super.add(jSP1, BorderLayout.CENTER);
        super.add(jSP2, BorderLayout.SOUTH);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
	}
	
	private void inicializarMatrices() {
		if(fraccion.isSelected()) {
			matrizFrac = new Fraccion[variables][ecuaciones[0].getCoeficientesFrac().length];
		}
		else if(decimal.isSelected()){
			matrizDec = new Double[variables][ecuaciones[0].getCoeficientesDec().length];
		}
	}
	
	private void rellenarMatrices() {
		if(decimal.isSelected()) {
			rellenarMatrizDec();
		}
		else if(fraccion.isSelected()){
			rellenarMatrizFrac();
		}
	}
	
	private void rellenarMatrizDec() throws NumberFormatException{
		for (int i = 0; i < matrizDec.length; i++) {
			for (int j = 0; j < matrizDec[i].length; j++) {
				matrizDec[i][j]=Double.parseDouble(ecuaciones[i].getCoeficientesDec()[j].getText());
			}
		}
	}
	
	private void rellenarMatrizFrac() throws NumberFormatException{
		for (int i = 0; i < matrizFrac.length; i++) {
			for (int j = 0; j < matrizFrac[i].length; j++) {
				Integer numerador = Integer.parseInt(ecuaciones[i].getCoeficientesFrac()[j].getTxtNumeradorText());
				Integer denominador = Integer.parseInt(ecuaciones[i].getCoeficientesFrac()[j].getTxtDenominadorText());
				matrizFrac[i][j] = new Fraccion(numerador, denominador);
			}
		}
	}
	
	public void gauss(ArrayList <String> cadenas) {
		if(decimal.isSelected()) {
			reMatrizDec = Resolucion.gaussSimpleInferior(matrizDec, cadenas);
			Resolucion.imprimirMatriz(reMatrizDec);
		}
		else if(fraccion.isSelected()) {
			Resolucion.imprimirMatriz(matrizFrac);
			reMatrizFrac = Resolucion.gaussSimpleInferior(matrizFrac, cadenas);
			Resolucion.imprimirMatriz(reMatrizFrac);
		}
	}
        
        public void gaussJordan(ArrayList <String> cadenas) {
		if(decimal.isSelected()) {
			reMatrizDec = Resolucion.gaussJordan(matrizDec, cadenas);
			Resolucion.imprimirMatriz(reMatrizDec);
		}
		else if(fraccion.isSelected()) {
			Resolucion.imprimirMatriz(matrizFrac);
			reMatrizFrac = Resolucion.gaussJordan(matrizFrac, cadenas);
			Resolucion.imprimirMatriz(reMatrizFrac);
		}
	}
	
	private void soluciones(ArrayList <String> cadenas) {
		if(decimal.isSelected()) {
			solDec = new Double[variables];
			Double matrizRes [][] = Resolucion.gaussJordan(matrizDec);
			for (int i = 0; i < solDec.length; i++) {
				solDec[i] = matrizRes[i][matrizRes[i].length-1];
				cadenas.add("x" + (i+1) + ": " +solDec[i].toString());
			}
			
		}
		else if(fraccion.isSelected()) {
			solFrac = new Fraccion[variables];
			Fraccion matrizRes [][] = Resolucion.gaussJordan(matrizFrac);
			for (int i = 0; i < solFrac.length; i++) {
				solFrac[i] = new Fraccion(matrizRes[i][matrizRes[i].length-1]);
				cadenas.add("x" + (i+1) + ": " + solFrac[i].toString());
			}
		}
	}
        
//        private void soluciones(ArrayList <String> cadenas) {
//		if(decimal.isSelected()) {
//			solDec = new Double[variables];
//			for (int i = 0; i < solDec.length; i++) {
//				solDec[i] = reMatrizDec[i][reMatrizDec[i].length-1];
//				cadenas.add("x" + (i+1) + ": " +solDec[i].toString());
//			}
//			
//		}
//		else if(fraccion.isSelected()) {
//			solFrac = new Fraccion[variables];
//			for (int i = 0; i < solFrac.length; i++) {
//				solFrac[i] = new Fraccion(reMatrizFrac[i][reMatrizFrac[i].length-1]);
//				cadenas.add("x" + (i+1) + ": " + solFrac[i].toString());
//			}
//		}
//	}
        
        public void sistemaSingular(ArrayList<String> cadenas){
            cadenas.add(sistemaSingular() ? "<html><center>Sistema singular<br>Es muy probable"
                    + "<br>a que tenga multiples soluciones" : "<html><center>Sistema no singular"
                            + "<br>Poco probable a que tenga multiples soluciones");
        }
        
        public Boolean sistemaSingular(){
            if(decimal.isSelected()) {
                matrizDecDelta = new Float[variables][variables];
                rellenarMatrizDecDelta();
                Float determinante = Resolucion.determinante(matrizDecDelta);
                System.out.println(determinante);
                determinante = escalarDeterminante(determinante);
                System.out.println(determinante);
                return Math.abs(determinante) < CRITERIOSINGULARIDAD;
            }
       	    else if(fraccion.isSelected()) {
		matrizFracDelta = new Fraccion[variables][variables];
                rellenarMatrizFracDelta();
                Fraccion determinante = Resolucion.determinante(matrizFracDelta);
                System.out.println(determinante);
                determinante = escalarDeterminante(determinante);
                System.out.println(determinante);
                return Math.abs(determinante.toDecimal()) < CRITERIOSINGULARIDAD;
            }
            return false;
        }
        
        private void rellenarMatrizDecDelta(){
            for (int i = 0; i < variables; i++) {
                for (int j = 0; j < variables; j++) {
                    matrizDecDelta[i][j] = matrizDec[i][j].floatValue();
                }
            }
        }
        
        private void rellenarMatrizFracDelta(){
            for (int i = 0; i < variables; i++) {
                for (int j = 0; j < variables; j++) {
                    matrizFracDelta[i][j] = new Fraccion(matrizFrac[i][j]);
                }
            }
        }
        
        private Float escalarDeterminante(Float determinate){
           ArrayList <Float> arregloMaxValores = new ArrayList<>();
            for (int i = 0; i < matrizDecDelta.length; i++) {
                arregloMaxValores.add(valorMaximo(matrizDecDelta[i]));
            }
            for (Float arregloMaxValore : arregloMaxValores) {
                determinate /= arregloMaxValore;
            }
            return determinate;
        }
        
        private Fraccion escalarDeterminante(Fraccion determinante){
            ArrayList<Fraccion> arregloMaxValores = new ArrayList<>();
            for (int i = 0; i < matrizFracDelta.length; i++) {
                arregloMaxValores.add(valorMaximo(matrizFracDelta[i]));
            }
            for (Fraccion arregloMaxValore : arregloMaxValores) {
                determinante = Fraccion.dividir(determinante, arregloMaxValore);
            }
            return determinante;
        }
        
        public static Float valorMaximo(Float [] arreglo){
            Float numMayor = arreglo[0];
            for (int i = 1; i < arreglo.length; i++) {
                if(numMayor < arreglo[i]){
                    numMayor = arreglo[i];
                }
            }
            return numMayor;
        }
        
        public static Fraccion valorMaximo(Fraccion [] arreglo){
            Fraccion numMayor = arreglo[0];
            for (int i = 1; i < arreglo.length; i++) {
                if(numMayor.toDecimal() < arreglo[i].toDecimal()){
                    numMayor = arreglo[i];
                }
            }
            return numMayor;
        }
	
        
}
