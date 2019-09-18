/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determinantematriz;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PACO
 */
public class PanelMatriz  extends JPanel{
    private JTextField numeros [][];

    public PanelMatriz() {
        super.setVisible(false);
    }

    public JTextField[][] getNumeros() {
        return numeros;
    }

    public void setNumeros(JTextField[][] numeros) {
        this.numeros = numeros;
    }

    @Override
    public void setVisible(boolean flag) {
        super.setVisible(flag);
        updateUI();
    }
    
    public JTextField getNumerosTextField(int i, int j){
        return numeros[i][j];
    }
    
    public void setNumerosTextField(int i, int j, JTextField text){
        this.numeros[i][j] = text;
    }
    
    public void inicializarComponentes(int tamano){
        this.removeAll();
        this.setLayout(new GridLayout(tamano, tamano));
        numeros = new JTextField[tamano][tamano];
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                numeros[i][j] = new JTextField(40);
                numeros[i][j].setHorizontalAlignment(JTextField.CENTER);
                this.add(numeros[i][j]);
            }
        }
    }
    
    
    
}
