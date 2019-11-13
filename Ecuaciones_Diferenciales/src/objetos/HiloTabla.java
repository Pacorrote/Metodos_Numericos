/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PACO
 */
public class HiloTabla extends Thread{
    
    private double x;
    private double yi;
    private double h;
    private double limSup;
    private ModeloTabla modelo;

    public HiloTabla(double x, double yi, double h, double limSup, ModeloTabla modelo) {
        this.x = x;
        this.yi = yi;
        this.h = h;
        this.limSup = limSup;
        this.modelo = modelo;
        Columnas_tabla col = new Columnas_tabla();
        this.modelo.getIteraciones().add("x1");
        col.setX(this.x);
        col.setYi(this.yi);
        col.setY(funcion(this.x));
        this.modelo.getFilas().add(col);
    }

    @Override
    public void run() {
        int i = 0;
        double y = this.yi;
        for (double x = this.x + h; x < this.limSup; x += h) {
            Columnas_tabla aux = new Columnas_tabla();
            y += this.h * derivada(, y)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public double derivada(double x, double y){
        return 6 * x + 4;
    }
    
    public double funcion(double x){
        return 3 * Math.pow(x, 2) + 4 * x + 1;
    }
    
    public double y(int posicion, double x, double y){
        return modelo.getFilas().get(posicion).getYi() + derivada(x, modelo.getFilas().get(posicion).getYi()) * this.h;
    }
    
    
}
