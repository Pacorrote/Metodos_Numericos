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

import enums.Modo;

/**
 *
 * @author PACO
 */
public class PanelEcuacion extends JPanel{
	
	private PanelCoefFracc coeficientesFrac [];
    private JTextField coeficientesDec [];
    private JLabel variables[];
    
    public PanelEcuacion(Integer variables, Modo modo) {
        inicializarComponentes(variables, modo);
        super.setVisible(false);
    }
    
    private void inicializarComponentes(Integer variables, Modo modo) {
    	if(modo == Modo.Decimal || modo == Modo.decimal) {
    		decimal(variables);
    	}
    	else {
    		fraccion(variables);
    	}
    }
    
    private void decimal(Integer variables) {
    	super.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        super.setLayout(new FlowLayout(FlowLayout.CENTER, 20,10));
        coeficientesDec = new JTextField[variables+1];
        this.variables = new JLabel[variables+1];
        for (int i = 0; i <= variables; i++) {
            coeficientesDec[i] = new JTextField(4);
            coeficientesDec[i].setHorizontalAlignment(JTextField.RIGHT);
            if(i==variables){
                this.variables [i] = new JLabel("=");
                super.add(this.variables[i]);
                super.add(coeficientesDec[i]);
            }
            else{
                String etiqueta = "x"+Integer.toString(i+1) + (i == variables-1 ? "" : " +");
                this.variables [i] = new JLabel(etiqueta);
                super.add(coeficientesDec[i]);
                super.add(this.variables[i]);
            }
            
        }
    }
    
    private void fraccion(Integer variables) {
    	super.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        super.setLayout(new FlowLayout(FlowLayout.CENTER, 20,10));
        coeficientesFrac = new PanelCoefFracc[variables+1];
        this.variables = new JLabel[variables+1];
        for (int i = 0; i < coeficientesFrac.length; i++) {
			coeficientesFrac[i] = new PanelCoefFracc();
			if(i==variables){
                this.variables [i] = new JLabel("=");
                super.add(this.variables[i]);
                super.add(coeficientesFrac[i]);
            }
            else{
                String etiqueta = "x"+Integer.toString(i+1) + (i == variables-1 ? "" : " +");
                this.variables [i] = new JLabel(etiqueta);
                super.add(coeficientesFrac[i]);
                super.add(this.variables[i]);
            }
		}
    }

    public JTextField[] getCoeficientesDec() {
        return coeficientesDec;
    }

	public PanelCoefFracc [] getCoeficientesFrac() {
		return coeficientesFrac;
	}
    
    
}
