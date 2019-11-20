package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.JToggleButton;
import objetos.ModeloTabla;
import objetos.Monomio;
import objetos.Polinomio;

/**
 *
 * @author PACO
 */
public class Frame1 extends JFrame{
    
    private Double mx1;
    private Double mx2;
    private Double mx3;
    private Double x1;
    private Double x2;
    private Double x3;
    private Double x;
    private Double h;
    private JTextField xi = new JTextField(10);
    private JTextField coeficiente = new JTextField(5);
    private JTextField potencia = new JTextField(5);
    private JLabel lblx = new JLabel("x^");
    private JButton agregarMonomio = new JButton("Agregar monomio");
    private JLabel lblxi = new JLabel("xi:");
    private JLabel lblh = new JLabel("Inserte datos al polinomio:");
    private JTable table;
    private JButton btnMostrar = new JButton("<html><center><p>Mostrar</p><p>resultados</p>");
    private JToggleButton primeraDev = new JToggleButton("f '");
    private JToggleButton segundaDev = new JToggleButton("f ''");
    private ArrayList<Double> valores;
    private ModeloTabla modelo = new ModeloTabla(valores);
    private Polinomio polinomio = new Polinomio();

    public Frame1() throws HeadlessException {
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
        JPanel panelMonomio = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        coeficiente.setHorizontalAlignment(JTextField.RIGHT);
        panelMonomio.add(coeficiente);
        panelMonomio.add(lblx);
        potencia.setHorizontalAlignment(JTextField.RIGHT);
        panelMonomio.add(potencia);
        agregarMonomio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int coeficiente = Integer.parseInt(Frame1.this.coeficiente.getText());
                    int potencia = Integer.parseInt(Frame1.this.potencia.getText());
                    Monomio monomio = new Monomio(coeficiente, potencia);
                    Frame1.this.coeficiente.setText("");
                    Frame1.this.potencia.setText("");
                    System.out.println(monomio);
                    polinomio.addMonomio(monomio);
                    System.out.println(polinomio.getMonomios().size());
                } catch (Exception e1) {
                    
                }
            }
        });
        panelMonomio.add(agregarMonomio);
        insertar_datos.add(panelMonomio);
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        panelBotones.add(primeraDev);
        panelBotones.add(segundaDev);
        insertar_datos.add(panelBotones);
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Polinomio derivada = Polinomio.derivada(polinomio);
                    Polinomio derivada2 = Polinomio.derivada(derivada);
                    x = Double.parseDouble(xi.getText());
                    h = (double) 1;
                    ArrayList<ArrayList<Double>> filas = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        mx1();
                        mx2();
                        mx3();
                        x1();
                        x2();
                        x3();
                        valores = new ArrayList<>();
                        valores.add(haciaAdelanteGrado1(polinomio, x, x1, h));
                        valores.add(Math.abs(haciaAdelanteGrado1(polinomio, x, x1, h) - derivada(derivada, x)) / derivada(derivada, x));
                        valores.add(haciaAdelanteGrado2(polinomio, x, x1, x2, h));
                        valores.add(Math.abs(haciaAdelanteGrado2(polinomio, x, x1, x2, h) - derivada(derivada, x)) / derivada(derivada, x));
                        valores.add(haciaAtrasGrado1(polinomio, x, mx1, h));
                        valores.add(Math.abs(haciaAtrasGrado1(polinomio, x, mx1, h) - derivada(derivada, x)) / derivada(derivada, x));
                        valores.add(haciaAtrasGrado2(polinomio, x, mx1, mx2, h));
                        valores.add(Math.abs(haciaAtrasGrado2(polinomio, x, mx1, mx2,h) - derivada(derivada, x)) / derivada(derivada, x));
                        valores.add(centralGrado1(polinomio, mx1, x1, h));
                        valores.add(Math.abs(centralGrado1(polinomio, mx1, x1, h) - derivada(derivada, x)) / derivada(derivada, x));
                        valores.add(centralGrado2(polinomio, mx1, x1, x2, mx2, h));
                        valores.add(Math.abs(centralGrado2(polinomio, mx1, x1, x2, mx2, h) - derivada(derivada, x)) / derivada(derivada, x));
                        valores.add(segundaHaciaAdelanteGrado1(polinomio, x, x1, x2, h));
                        valores.add(Math.abs(segundaHaciaAdelanteGrado1(polinomio, x, x1, x2, h) - segundaDerivada(derivada2, x)) / segundaDerivada(derivada2, x));
                        valores.add(segundaHaciaAdelanteGrado2(polinomio, x, x1, x2, x3, h));
                        valores.add(Math.abs(segundaHaciaAdelanteGrado2(polinomio, x, x1, x2, x3, h) - segundaDerivada(derivada2, x)) / segundaDerivada(derivada2, x));
                        valores.add(segundaHaciaAtrasGrado1(polinomio, x, mx1, mx2, h));
                        valores.add(Math.abs(segundaHaciaAtrasGrado1(polinomio, x, mx1, mx2, h) - segundaDerivada(derivada2, x)) / segundaDerivada(derivada2, x));
                        valores.add(segundaHaciaAtrasGrado2(polinomio, x, mx1, mx2, mx3, h));
                        valores.add(Math.abs(segundaHaciaAtrasGrado2(polinomio, x, mx1, mx2, mx3, h) - segundaDerivada(derivada2, x)) / segundaDerivada(derivada2, x));
                        valores.add(segundocentralGrado1(polinomio, x1, x, h));
                        valores.add(Math.abs(segundocentralGrado1(polinomio, x1, x, h) - segundaDerivada(derivada2, x)) / segundaDerivada(derivada2, x));
                        valores.add(segundocentralGrado2(polinomio, x1, x2, x, mx1, mx2, h));
                        valores.add(Math.abs(segundocentralGrado2(polinomio, x1, x2, x, mx1, mx2, h) - segundaDerivada(derivada2, x)) / segundaDerivada(derivada2, x));
                        valores.add(derivada(derivada, x));
                        valores.add(segundaDerivada(derivada2, x));
                        filas.add(valores);
                        h /= 10;
                    }
                    polinomio.getMonomios().clear();
                    System.out.println(polinomio.getMonomios().size());
                    modelo.setPrimeraDev(primeraDev.isSelected());
                    modelo.setSegundaDev(segundaDev.isSelected());
                    modelo.setFilas(filas);
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
    
    public static Double haciaAdelanteGrado1(Polinomio poli, double x, double x1, double h){
        double retorno = funcion(poli, x1);
        retorno -= funcion(poli, x);
        retorno /= h;
        return retorno;
    }
    
    public static Double haciaAdelanteGrado2(Polinomio poli, double x, double x1, double x2, double h){
        double retorno = -funcion(poli, x2);
        retorno += 4 * funcion(poli, x1);
        retorno -= 3 * funcion(poli, x);
        retorno /= 2 * h;
        return retorno;
    }
    
    public static Double segundaHaciaAdelanteGrado1(Polinomio poli, double x, double x1, double x2, double h){
        double retorno = funcion(poli, x2);
        retorno -= 2 * funcion(poli, x1);
        retorno += funcion(poli, x);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double segundaHaciaAdelanteGrado2(Polinomio poli, double x, double x1, double x2, double x3, double h){
        double retorno = -funcion(poli, x3);
        retorno += 4 * funcion(poli, x2);
        retorno -= 5 * funcion(poli, x1);
        retorno += 2 * funcion(poli, x);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double haciaAtrasGrado1(Polinomio poli, double x, double mx1, double h){
        double retorno = funcion(poli, x);
        retorno -= funcion(poli, mx1);
        retorno /= h;
        return retorno;
    }
    
    public static Double haciaAtrasGrado2(Polinomio poli, double x, double mx1, double mx2, double h){
        double retorno = funcion(poli, mx2);
        retorno -= 4 * funcion(poli, mx1);
        retorno += 3 * funcion(poli, x);
        retorno /= 2 * h;
        return retorno;
    }
    
    public static Double segundaHaciaAtrasGrado1(Polinomio poli, double x, double mx1, double mx2, double h){
        double retorno = funcion(poli, x);
        retorno -= 2 * funcion(poli, mx1);
        retorno += funcion(poli, mx2);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double segundaHaciaAtrasGrado2(Polinomio poli, double x, double mx1, double mx2, double mx3, double h){
        double retorno = 2 * funcion(poli, x);
        retorno -= 5 * funcion(poli, mx1);
        retorno += 4 * funcion(poli, mx2);
        retorno -= funcion(poli, mx3);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double centralGrado1(Polinomio poli, double mx1, double x1, double h){
        double retorno = funcion(poli, x1);
        retorno -=  funcion(poli, mx1);
        retorno /= 2 * h;
        return retorno;
    }
    
    public static Double centralGrado2(Polinomio poli, double mx1, double x1, double x2, double mx2, double h){
        double retorno = funcion(poli, mx2);
        retorno -= 8 * funcion(poli, mx1);
        retorno += 8 * funcion(poli, x1);
        retorno -= funcion(poli, x2);
        retorno /= 12 * h;
        return retorno;
    }
    
    public static Double segundocentralGrado1(Polinomio poli, double x1, double x, double h){
        double retorno = funcion(poli, x1);
        retorno -= 2 * funcion(poli, x);
        retorno += funcion(poli, x1);
        retorno /= Math.pow(h, 2);
        return retorno;
    }
    
    public static Double segundocentralGrado2(Polinomio poli, double x1, double x2, double x, double mx1, double mx2, double h){
        double retorno = -funcion(poli, mx2);
        retorno += 16 * funcion(poli, mx1);
        retorno -= 30 * funcion(poli, x);
        retorno += 16 * funcion(poli, x1);
        retorno -= funcion(poli, x2);
        retorno /= 12 * Math.pow(h, 2);
        return retorno;
    }
    
    public static double funcion(Polinomio poli, double x){
        double resultado = 0;
        for (Monomio monomio : poli.getMonomios()) {
            resultado += monomio.getCoeficiente() * Math.pow(x, monomio.getPotencia());
        }
        return resultado;
    }
    
    public static double derivada(Polinomio poli, double x){
        double resultado = 0;
        for (Monomio monomio : poli.getMonomios()) {
            resultado += monomio.getCoeficiente() * Math.pow(x, monomio.getPotencia());
        }
        return resultado;
    }
    
    public static double segundaDerivada(Polinomio poli, double x){
        double resultado = 0;
        for (Monomio monomio : poli.getMonomios()) {
            resultado += monomio.getCoeficiente() * Math.pow(x, monomio.getPotencia());
        }
        return resultado;
    }
}