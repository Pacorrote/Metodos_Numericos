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
import objetos.ModeloTablaEuler;
import objetos.ModeloTablaHeun;

/**
 *
 * @author PACO
 */
public class PanelTabla extends JPanel{
    
    private ModeloTablaEuler modelo = new ModeloTablaEuler(null, null);
    private ModeloTablaHeun modelo1 = new ModeloTablaHeun(null, null);
    private JTable tablaPuntos = new JTable(modelo1);

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

    public ModeloTablaEuler getModelo() {
        return modelo;
    }

    public void setModelo(ModeloTablaEuler modelo) {
        this.modelo = modelo;
    }

	public ModeloTablaHeun getModelo1() {
		return modelo1;
	}

	public void setModelo1(ModeloTablaHeun modelo1) {
		this.modelo1 = modelo1;
	}
    
    
}
