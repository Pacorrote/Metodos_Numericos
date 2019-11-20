/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import objetos.ModeloTabla;

/**
 *
 * @author PACO
 */
public class Frame extends JFrame{
    
    private Double mx1;
    private Double mx2;
    private Double mx3;
    private Double x1;
    private Double x2;
    private Double x3;
    private Double x;
    private Double h;
    private JTextField xi = new JTextField(10);
    private JTextField htex = new JTextField(100);
    private JLabel lblxi = new JLabel("xi:");
    private JLabel lblh = new JLabel("h:");
    private JTable table;
    private JButton btnMostrar = new JButton("<html><center><p>Mostrar</p><p>resultados</p>");
    private ArrayList<Double> valores;
    private ModeloTabla modelo = new ModeloTabla(valores);

    public Frame() throws HeadlessException {
        super.setSize(1100, 600);
        super.setBackground(new Color(21, 243, 243));
	super.setLayout(new BorderLayout(8, 1));
        super.setLocationRelativeTo(null);
        JPanel insertar_datos = new JPanel();
        insertar_datos.setLayout(new BoxLayout(insertar_datos, BoxLayout.Y_AXIS));
        lblxi.setHorizontalTextPosition(JLabel.CENTER);
        insertar_datos.add(lblxi);
        insertar_datos.add(xi);
        lblh.setHorizontalTextPosition(JLabel.CENTER);
        insertar_datos.add(lblh);
        insertar_datos.add(htex);
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    x = Double.parseDouble(xi.getText());
                    h = Double.parseDouble(htex.getText());
                    mx1();
                    mx2();
                    mx3();
                    x1();
                    x2();
                    x3();
                    valores = new ArrayList<>();
                    valores.add(haciaAdelanteGrado1(x, x1, h));
                    valores.add(Math.abs(haciaAdelanteGrado1(x, x1, h) - derivada(x)) / derivada(x));
                    valores.add(haciaAdelanteGrado2(x, x1, x2, h));
                    valores.add(Math.abs(haciaAdelanteGrado2(x, x1, x2, h) - derivada(x)) / derivada(x));
                    valores.add(haciaAtrasGrado1(x, mx1, h));
                    valores.add(Math.abs(haciaAtrasGrado1(x, mx1, h) - derivada(x)) / derivada(x));
                    valores.add(haciaAtrasGrado2(x, mx1, mx2, h));
                    valores.add(Math.abs(haciaAtrasGrado2(x, mx1, mx2,h) - derivada(x)) / derivada(x));
                    valores.add(centralGrado1(mx1, x1, h));
                    valores.add(Math.abs(centralGrado1(mx1, x1, h) - derivada(x)) / derivada(x));
                    valores.add(centralGrado2(mx1, x1, x2, mx2, h));
                    valores.add(Math.abs(centralGrado2(mx1, x1, x2, mx2, h) - derivada(x)) / derivada(x));
                    valores.add(segundaHaciaAdelanteGrado1(x, x1, x2, h));
                    valores.add(Math.abs(segundaHaciaAdelanteGrado1(x, x1, x2, h) - segundaDerivada(x)) / segundaDerivada(x));
                    valores.add(segundaHaciaAdelanteGrado2(x, x1, x2, x3, h));
                    valores.add(Math.abs(segundaHaciaAdelanteGrado2(x, x1, x2, x3, h) - segundaDerivada(x)) / segundaDerivada(x));
                    valores.add(segundaHaciaAtrasGrado1(x, mx1, mx2, h));
                    valores.add(Math.abs(segundaHaciaAtrasGrado1(x, mx1, mx2, h) - segundaDerivada(x)) / segundaDerivada(x));
                    valores.add(segundaHaciaAtrasGrado2(x, mx1, mx2, mx3, h));
                    valores.add(Math.abs(segundaHaciaAtrasGrado2(x, mx1, mx2, mx3, h) - segundaDerivada(x)) / segundaDerivada(x));
                    valores.add(segundocentralGrado1(x1, x, h));
                    valores.add(Math.abs(segundocentralGrado1(x1, x, h) - segundaDerivada(x)) / segundaDerivada(x));
                    valores.add(segundocentralGrado2(x1, x2, x, mx1, mx2, h));
                    valores.add(Math.abs(segundocentralGrado2(x1, x2, x, mx1, mx2, h) - segundaDerivada(x)) / segundaDerivada(x));
                    valores.add(derivada(x));
                    valores.add(segundaDerivada(x));
                    modelo.setValores(valores);
                    modelo.fireTableDataChanged();
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(getContentPane(), 
                    "Cadena inválida\nIntente de nuevo",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(getContentPane(), 
                    "Algo malo pasó en procedimiento",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        insertar_datos.add(btnMostrar);
        super.add(insertar_datos, BorderLayout.NORTH);
        table = new JTable(modelo);
        JScrollPane scroll1 = new JScrollPane(table);
        scroll1.setPreferredSize(new Dimension(2400, 400));
        JPanel panelTabla = new JPanel();
        panelTabla.add(scroll1, BorderLayout.CENTER);
        panelTabla.setPreferredSize(new Dimension(2400, 400));
        JScrollPane scroll2 = new JScrollPane(panelTabla);
        super.add(scroll2, BorderLayout.SOUTH);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void mx1(){
        if(x != null){
            mx1 = x - h;
        }
    }
    
    private void mx2(){
        if(x != null){
            mx2 = x - 2 * h;
        }
    }
    
    private void mx3(){
        if(x != null){
            mx3 = x - 3 * h;
        }
    }
    
    private void x1(){
        if(x != null){
            x1 = x + h;
        }
    }
    
    private void x2(){
        if(x != null){
            x2 = x + 2 * h;
        }
    }
    
    private void x3(){
        if(x != null){
            x3 = x + 3 * h;
        }
    }
    
    public static Double haciaAdelanteGrado1(double x, double x1, double h){
        double retorno = funcion(x1);
        retorno -= funcion(x);
        retorno /= h;
        return retorno;
    }
    
    public static Double haciaAdelanteGrado2(double x, double x1, double x2, double h){
        double retorno = -funcion(x2);
        retorno += 4 * funcion(x1);
        retorno -= 3 * funcion(x);
        retorno /= 2 * h;
        return retorno;
    }
    
    public static Double segundaHaciaAdelanteGrado1(double x, double x1, double x2, double h){
        double retorno = funcion(x2);
        retorno -= 2 * funcion(x1);
        retorno += funcion(x);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double segundaHaciaAdelanteGrado2(double x, double x1, double x2, double x3, double h){
        double retorno = -funcion(x3);
        retorno += 4 * funcion(x2);
        retorno -= 5 * funcion(x1);
        retorno += 2 * funcion(x);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double haciaAtrasGrado1(double x, double mx1, double h){
        double retorno = funcion(x);
        retorno -= funcion(mx1);
        retorno /= h;
        return retorno;
    }
    
    public static Double haciaAtrasGrado2(double x, double mx1, double mx2, double h){
        double retorno = funcion(mx2);
        retorno -= 4 * funcion(mx1);
        retorno += 3 * funcion(x);
        retorno /= 2 * h;
        return retorno;
    }
    
    public static Double segundaHaciaAtrasGrado1(double x, double mx1, double mx2, double h){
        double retorno = funcion(x);
        retorno -= 2 * funcion(mx1);
        retorno += funcion(mx2);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double segundaHaciaAtrasGrado2(double x, double mx1, double mx2, double mx3, double h){
        double retorno = 2 * funcion(x);
        retorno -= 5 * funcion(mx1);
        retorno += 4 * funcion(mx2);
        retorno -= funcion(mx3);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double centralGrado1(double mx1, double x1, double h){
        double retorno = funcion(x1);
        retorno -=  funcion(mx1);
        retorno /= 2 * h;
        return retorno;
    }
    
    public static Double centralGrado2(double mx1, double x1, double x2, double mx2, double h){
        double retorno = -funcion(mx2);
        retorno += 8 * funcion(mx1);
        retorno -= 8 * funcion(x1);
        retorno += funcion(x2);
        retorno /= 12 * h;
        return retorno;
    }
    
    public static Double segundocentralGrado1(double x1, double x, double h){
        double retorno = funcion(x1);
        retorno -= 2 * funcion(x);
        retorno += funcion(x1);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double segundocentralGrado2(double x1, double x2, double x, double mx1, double mx2, double h){
        double retorno = -funcion(mx2);
        retorno += 16 * funcion(mx1);
        retorno -= 30 * funcion(x);
        retorno += 16 * funcion(x1);
        retorno -= funcion(x2);
        retorno /= 12 * Math.pow(h, 2);
        return retorno;
    }
    
    public static double funcion(double x){
        //return Math.pow(x, 3) + 5 * Math.exp(-x);
        //return 1 - Math.exp(-Math.pow(x / 30, 1.44));
        //return Math.exp(-2 * x) - x;
        double potencia = 2 * x;
        return Math.pow(3, potencia) - x;
    }
    
    public static double derivada(double x){
        //return 3 * Math.pow(x, 2) - 5 * Math.exp(-x);
        //return (1.44 / 30) * Math.pow(x / 30, 0.44) * Math.exp(-Math.pow(x / 30, 1.44));
        //return -2 * Math.exp(-2 * x) - 1;
        double potencia = 2 * x;
        return Math.pow(3, potencia) * 2 * Math.log(3) - 1;
    }
    
    public static double segundaDerivada(double x){
        //return 6 * x + 5 * Math.exp(-x);
//        double resultado = ((0.09851 * Math.exp(-Math.pow(x / 30, 1.44))) / Math.pow(x, 0.56)) - 
//                0.0024 * Math.exp(-Math.pow(x / 30, 1.44)) * Math.pow(x, 0.88);
//        resultado = 0.048 * resultado;
//        return resultado;
       // return 4 * Math.exp(-2 * x);
       double potencia = 2 * x;
       return Math.pow(3, potencia) * 4 * Math.pow(Math.log(3), 2);
    }
}
