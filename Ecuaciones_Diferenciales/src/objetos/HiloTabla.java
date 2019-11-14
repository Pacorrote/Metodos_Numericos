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
    private ModeloTablaEuler modelo;
    private ModeloTablaHeun modelo1;

    public HiloTabla(double x, double yi, double h, double limSup, ModeloTablaEuler modelo) {
        this.x = x;
        this.yi = yi;
        this.h = h;
        this.limSup = limSup;
        this.modelo = modelo;
        Columnas_tabla col = new Columnas_tabla();
        this.modelo.getIteraciones().add("x0");
        col.setX(this.x);
        col.setYi(this.yi);
        col.setY(funcion(this.x));
        this.modelo.getFilas().add(col);
    }
    
    

    public HiloTabla(double x, double yi, double h, double limSup, ModeloTablaHeun modelo1) {
		this.x = x;
		this.yi = yi;
		this.h = h;
		this.limSup = limSup;
		this.modelo1 = modelo1;
		Columnas_tabla col = new Columnas_tabla();
        this.modelo1.getIteraciones().add("x0");
        col.setX(this.x);
        col.setYi(this.yi);
        col.setCorrector(this.yi);
        col.setPredictor(this.yi);
        col.setY(funcion(this.x));
        this.modelo1.getFilas().add(col);
	}



	@Override
    public void run() {
        int i = 0;
        try {
        	if(modelo != null) {
        		modelo.fireTableDataChanged();
        	}
        	else {
        		modelo1.fireTableDataChanged();
        	}
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (double x = this.x; x < this.limSup; x += h) {
            Columnas_tabla aux = new Columnas_tabla();
            aux.setX(x + this.h);
            aux.setYi(y(i, x));
            aux.setPredictor(aux.getYi());
            aux.setCorrector(corrector(i, x));
            aux.setY(funcion(x + this.h));
            if(modelo != null) {
            	aux.setError(Math.abs(aux.getY() - aux.getYi()) / Math.abs(aux.getY()));
            }
            else {
            	aux.setError(Math.abs(aux.getY() - aux.getCorrector()) / Math.abs(aux.getY()));
            }
            if(modelo != null) {
            	modelo.getIteraciones().add("x" + (++i));
                modelo.getFilas().add(aux);
                modelo.fireTableDataChanged();
            }
            else {
            	modelo1.getIteraciones().add("x" + (++i));
                modelo1.getFilas().add(aux);
                modelo1.fireTableDataChanged();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public double derivada(double x){
        return -2 * Math.pow(x, 3) + 12 * Math.pow(x, 2) - 20 * x + 8.5;
    }
    
    public double funcion(double x){
        return -0.5 * Math.pow(x, 4) + 4 * Math.pow(x, 3) - 10 * Math.pow(x, 2) + 8.5 * x + 1;
    }
    
    public double y(int posicion, double x){
        return modelo1.getFilas().get(posicion).getYi() + derivada(x) * this.h;
    }
    
    public double corrector(int posicion, double x) {
    	return modelo1.getFilas().get(posicion).getYi() + ((derivada(x) + derivada(x + this.h)) / 2) * this.h;
    }
}
