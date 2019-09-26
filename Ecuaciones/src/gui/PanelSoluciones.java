/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PACO
 */
public class PanelSoluciones extends JPanel{
    
    private JLabel lblsoluciones [];
   
    public PanelSoluciones(Integer numSoluciones, Float soluciones [] ) {
        super.setLayout(new GridLayout(1, numSoluciones));
        lblsoluciones = new JLabel[numSoluciones];
        for (int i = 0; i < numSoluciones; i++) {
            lblsoluciones[i] = new JLabel("x"+Integer.toString(i+1)+" = "+soluciones[i]);
            lblsoluciones[i].setHorizontalAlignment(JLabel.CENTER);
            lblsoluciones[i].setForeground(Color.red);
            lblsoluciones[i].setFont(new Font("Arial", 0, 15));
            super.add(lblsoluciones[i]);
        }
        super.setBackground(Color.BLACK);
        super.setAutoscrolls(true);
    }

    public JLabel[] getLblsoluciones() {
        return lblsoluciones;
    }

    public void setLblsoluciones(JLabel[] lblsoluciones) {
        this.lblsoluciones = lblsoluciones;
    }
    
    
    
}
