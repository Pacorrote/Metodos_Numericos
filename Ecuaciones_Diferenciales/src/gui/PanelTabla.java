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
import objetos.ModeloTablaPuntoMed;
import objetos.ModeloTablaRalston;

/**
 *
 * @author PACO
 */
public class PanelTabla extends JPanel{
    
    private ModeloTablaEuler modelo = new ModeloTablaEuler(null, null);
    private ModeloTablaHeun modelo1 = new ModeloTablaHeun(null, null);
    private ModeloTablaPuntoMed modelo2 = new ModeloTablaPuntoMed(null, null);
    private ModeloTablaRalston modelo3 = new ModeloTablaRalston(null, null);
    private JTable tablaPuntos = new JTable();

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
        tablaPuntos.setModel(this.modelo);
    }

    public ModeloTablaHeun getModelo1() {
        return modelo1;
    }

    public void setModelo1(ModeloTablaHeun modelo1) {
        this.modelo1 = modelo1;
        tablaPuntos.setModel(this.modelo1);
    }

    public ModeloTablaPuntoMed getModelo2() {
        return modelo2;
    }

    public void setModelo2(ModeloTablaPuntoMed modelo2) {
        this.modelo2 = modelo2;
        tablaPuntos.setModel(this.modelo2);
    }

    public ModeloTablaRalston getModelo3() {
        return modelo3;
    }

    public void setModelo3(ModeloTablaRalston modelo3) {
        this.modelo3 = modelo3;
        tablaPuntos.setModel(this.modelo3);
    }
    
}
