/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import enums.Soluciones;
import interfaces.Listeners;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.xml.bind.Marshaller;

/**
 *
 * @author PACO
 */
public class PanelDatosEnt extends JPanel{
    
    private JPanel datos = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
    private JPanel tipoSolucion = new JPanel();
    public static final String [] ETIQUETAS = {
        "Condiciones iniciales",
        "x:",
        "y:",
        "h:",
        "Límite superior"
    };
    private JLabel etiquetas [] = new JLabel[ETIQUETAS.length];
    private JTextField camposDatos [] = new JTextField[4];
    private JRadioButton [] opcion = new JRadioButton[1];
    public static final String [] RADIOBUT = {
        "Método de euler"
    };
    private Listeners listener;

    public PanelDatosEnt() {
        super.setLayout(new GridLayout(2, 1));
        inicializarComponentes();
        super.add(datos);
        super.add(tipoSolucion);
    }
    
    private void inicializarComponentes(){
        tipoSolucion.setLayout(new BoxLayout(tipoSolucion, BoxLayout.Y_AXIS));
        for (int i = 0; i < ETIQUETAS.length; i++) {
            etiquetas[i] = new JLabel(ETIQUETAS[i]);
        }
        for (int i = 0; i < camposDatos.length; i++) {
            camposDatos[i] = new JTextField(10);
            camposDatos[i].setHorizontalAlignment(JTextField.RIGHT);
        }
        datos.add(etiquetas[0]);
        int j = 1;
        int k = 0;
        for (int i = 0; i < camposDatos.length * 2; i++) {
            if(i % 2 == 0){
                datos.add(etiquetas[j++]);
            }
            else{
                datos.add(camposDatos[k++]);
            }
        }
        ButtonGroup btnGroup = new ButtonGroup();
        for (int i = 0; i < opcion.length; i++) {
            opcion[i] = new JRadioButton(RADIOBUT[i]);
            btnGroup.add(opcion[i]);
            int aux = i;
            opcion[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	try {
                		listener.solucionarEcOD(Soluciones.values()[aux]);
					} catch (NumberFormatException e2) {
						// TODO: handle exception
					}
                    
                }
            });
            tipoSolucion.add(opcion[i]);
        }
    }

    public JTextField[] getCamposDatos() {
        return camposDatos;
    }
    
    public JTextField getCampoDato(int indice){
        return camposDatos[indice];
    }

    public void setListener(Listeners listener) {
        this.listener = listener;
    }
   
}
