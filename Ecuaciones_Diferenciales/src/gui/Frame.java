/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import enums.Soluciones;
import interfaces.Listeners;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import objetos.Columnas_tabla;

/**
 *
 * @author PACO
 */
public class Frame extends JFrame{
    
    private PanelDatosEnt pnlEnt = new PanelDatosEnt();
    private PanelTabla pnlTabla = new PanelTabla();

    public Frame() {
        super.setSize(1000, 650);
        super.setBackground(new Color(128, 128, 128));
	super.setLayout(null);
        super.setLocationRelativeTo(null);
        pnlEnt.setBounds(0, 0, 1000, 162);
        pnlTabla.setBounds(0, 163, 1000, 488);
        pnlEnt.setListener(listener);
        super.add(pnlEnt);
        super.add(pnlTabla);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private Listeners listener = new Listeners() {
        @Override
        public void solucionarEcOD(Soluciones solucion) {
            
            double x = Double.parseDouble(pnlEnt.getCampoDato(0).getText());
            double yi = Double.parseDouble(pnlEnt.getCampoDato(1).getText());
            double h = Double.parseDouble(pnlEnt.getCampoDato(2).getText());
            double limSup = Double.parseDouble(pnlEnt.getCampoDato(3).getText());
           
            switch(solucion){
                case Euler:
                    ArrayList<Columnas_tabla> filas = new ArrayList<>();
                    pnlTabla.getModelo().setFilas(filas);
                    break;
            }
        }
    };
    
}
