/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PACO
 */
public class PanelEcuacion extends JPanel{
    private JTextField coeficientes [];
    private JLabel variables[];
    

    public PanelEcuacion(Integer variables) {
        super.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        super.setLayout(new FlowLayout(FlowLayout.CENTER, 20,10));
        coeficientes = new JTextField[variables+1];
        this.variables = new JLabel[variables+1];
        for (int i = 0; i <= variables; i++) {
            coeficientes[i] = new JTextField(4);
            coeficientes[i].setHorizontalAlignment(JTextField.RIGHT);
            if(i==variables){
                this.variables [i] = new JLabel("=");
                super.add(this.variables[i]);
                super.add(coeficientes[i]);
            }
            else{
                String etiqueta = "x"+Integer.toString(i+1) + (i == variables-1 ? "" : " +");
                this.variables [i] = new JLabel(etiqueta);
                super.add(coeficientes[i]);
                super.add(this.variables[i]);
            }
        }
        super.setVisible(false);
    }

    public JTextField[] getCoeficientes() {
        return coeficientes;
    }
    
}
