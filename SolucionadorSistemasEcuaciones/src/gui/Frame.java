/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import objetos.Matrices;
import objetos.Solucionador;

/**
 *
 * @author PACO
 */
public class Frame extends JFrame{
    private JPanel  datos = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
    private JLabel lblNumVar = new JLabel("Numero de variables:");
    private JTextField txtNumVar = new JTextField(2);
    private JLabel lblIteraciones = new JLabel("Numero de iteraciones:");
    private JButton aceptar = new JButton("Aceptar");
    private JButton cancelar = new JButton("Cancelar");
    private JPanel padre = new JPanel(new BorderLayout());
    private JPanel ecuaciones1 = new JPanel();
    private PanelEcuacion ecuaciones[];
    private JPanel pnlbtnSolucionar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JButton solucionar = new JButton("Soluciones");
    private Integer variables;
    private PanelSoluciones soluciones;
    private ArrayList<Matrices> matrices;
    private Float sol [];
    private JPanel pnlAbajo;
    private JPanel pnlbtnMostrarComprobacion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JButton btnMostrarComprobacion = new JButton("Mostrar comprobacion");
   

    public Frame() throws HeadlessException {
        txtNumVar.setHorizontalAlignment(JTextField.RIGHT);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(450, 500);
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout(10, 10));
        datos.add(lblNumVar);
        datos.add(txtNumVar);
        datos.add(aceptar);
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    variables = Integer.parseInt(txtNumVar.getText());
                    ecuaciones1.setLayout(new GridLayout(variables, 1));
                    ecuaciones = new PanelEcuacion[variables];
                    for (int i = 0; i < ecuaciones.length; i++) {
                        ecuaciones[i] = new PanelEcuacion(variables);
                        ecuaciones[i].setVisible(true);
                        ecuaciones1.add(ecuaciones[i]);
                        ecuaciones1.updateUI();
                    }
                    repaint();
                } catch (Exception e1) {
                    System.out.println("Error :v");
                }
            }
        });
        datos.add(cancelar);
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ecuaciones1.removeAll();
                try {
                    remove(pnlAbajo);
                } catch (NullPointerException e1) {
                    
                }
                pnlAbajo = null;
                ecuaciones1.updateUI();
                repaint();
            }
        });
        super.add(datos, BorderLayout.NORTH);
        padre.add(ecuaciones1, BorderLayout.CENTER);
        pnlbtnMostrarComprobacion.setBackground(Color.CYAN);
        pnlbtnMostrarComprobacion.add(btnMostrarComprobacion);
        pnlbtnSolucionar.add(solucionar);
        solucionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(pnlAbajo!=null){
                    remove(pnlAbajo);
                }
                if(variables != null){
                    Float delta[][] = new Float[variables][variables];
                    matrices = new ArrayList<>();
                    sol = new  Float[variables];
                    try {
                        for (int i = 0; i < variables+1; i++) {
                            if(i==0){
                                rellenarDelta(delta);
                            }
                            else{
                                Frame.this.matrices.add(new Matrices(matrices(i)));
                                Solucionador delta1 =  new Solucionador(Frame.this.matrices.get(0).getMatrices());
                                Solucionador xi = new Solucionador(Frame.this.matrices.get(i).getMatrices());
                                if(Solucionador.determinante(delta1.getMatriz())!=0){
                                    sol[i-1] = Solucionador.determinante(xi.getMatriz()) / Solucionador.determinante(delta1.getMatriz());
                                }
                                else{
                                    throw new ArithmeticException("Division cero");
                                }
                            }
                        }
                        soluciones = new PanelSoluciones(variables, sol);
                        pnlAbajo = new JPanel(new GridLayout(2, 1));
                        pnlAbajo.add(soluciones);
                        pnlAbajo.add(pnlbtnMostrarComprobacion);
                        add(pnlAbajo, BorderLayout.SOUTH);
                        soluciones.updateUI();
                        pnlAbajo.updateUI();
                        repaint();
                    } catch (ArithmeticException e1) {
                        JOptionPane.showMessageDialog(getContentPane(), 
                                                            e1.getMessage(),
                                                            "ERROR",
                                                            JOptionPane.ERROR_MESSAGE);
                    } catch(NumberFormatException e1){
                        JOptionPane.showMessageDialog(getContentPane(), 
                                                            "Datos invalidos",
                                                            "ERROR",
                                                            JOptionPane.ERROR_MESSAGE);
                    }
                }
      
            }
            
        });
        btnMostrarComprobacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Frame.this, concatenarEcuaciones(ecuaciones()));
            }
        });
        pnlbtnSolucionar.setBackground(new Color(147, 5, 5));
        padre.add(pnlbtnSolucionar, BorderLayout.SOUTH);
        JScrollPane jSP = new JScrollPane(padre, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        super.add(jSP, BorderLayout.CENTER);
        super.setVisible(true);
    }
    
    private void rellenarDelta(Float delta[][]){
        for (int j = 0; j < delta.length; j++) {
            for (int k = 0; k < delta.length; k++) {
                delta[j][k] = Float.parseFloat(ecuaciones[j].getCoeficientes()[k].getText());
            }
        }
        matrices.add(new Matrices(delta));
        System.out.println(Solucionador.determinante(delta));
    }
    
    private Float [][] matrices (int i){
        Float  matrices1 [][] = new Float[variables][variables];
        for (int j = 0; j < variables; j++) {
            for (int k = 0; k < variables; k++) {
                if(i==k+1){
                    matrices1[j][k] = Float.parseFloat(ecuaciones[j].getCoeficientes()[variables].getText());
                }
                else{
                    matrices1[j][k] = Float.parseFloat(ecuaciones[j].getCoeficientes()[k].getText());
                }
            }
        }
        return matrices1;
    }
    
    private String concatenarEcuaciones(String [] ecuaciones){
        String cadena = "";
        for (int i = 0; i < ecuaciones.length; i++) {
           cadena = cadena + ecuaciones[i] + "\n";
        }
        return cadena;
    }
    
    private String [] ecuaciones(){
        String ecuaciones [] = new String[sol.length];
        for (int i = 0; i < ecuaciones.length; i++) {
            ecuaciones[i] = "";
            for (int j = 0; j < this.ecuaciones[i].getCoeficientes().length; j++) {
                if(j<this.ecuaciones[i].getCoeficientes().length-1){
                    float coeficiente = Float.parseFloat(this.ecuaciones[i].getCoeficientes()[j].getText());
                    ecuaciones[i] += (coeficiente < 0 ? coeficiente: j % 2 == 0 ? coeficiente: "+" + coeficiente);
                    ecuaciones[i] += "*" + sol[j];
                }
                else{
                    ecuaciones[i] += "=" + resultado(i).toString();
                }
            }
            System.out.println(ecuaciones[i]);   
        }
        return ecuaciones;
    }
    
    private Float resultado(int posicion){
        float resultadoComprobacion = 0;
        for (int i = 0; i < sol.length; i++) {
            resultadoComprobacion += sol[i] * Float.parseFloat(this.ecuaciones[posicion].getCoeficientes()[i].getText());
            System.out.println(resultadoComprobacion);
        }
        return resultadoComprobacion;
    }
    
}
