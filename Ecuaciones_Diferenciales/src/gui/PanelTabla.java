/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import enums.Soluciones;
import interfaces.Listeners;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import objetos.ModeloTabla;

/**
 *
 * @author PACO
 */
public class PanelTabla extends JPanel{
    
    private ModeloTabla modelo = new ModeloTabla(null, null);
    private JTable tablaPuntos = new JTable(modelo);

    public PanelTabla() {
        super.setLayout(null);
        tablaPuntos.setFont(new Font("Verdana", 0, 18));
        tablaPuntos.setShowGrid(true);
        tablaPuntos.setForeground(Color.ORANGE);
        JScrollPane scroll = new JScrollPane(tablaPuntos);
        scroll.setBounds(5, 5, 974, 438);
        super.add(scroll);
        super.setBackground(Color.BLACK);
    }

    public ModeloTabla getModelo() {
        return modelo;
    }

    public void setModelo(ModeloTabla modelo) {
        this.modelo = modelo;
    }


    
    
    
}
