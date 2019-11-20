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
    private ModeloTablaPuntoMed modelo2;
    private ModeloTablaRalston modelo3;

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

    public HiloTabla(double x, double yi, double h, double limSup, ModeloTablaPuntoMed modelo2) {
        this.x = x;
        this.yi = yi;
        this.h = h;
        this.limSup = limSup;
        this.modelo2 = modelo2;
        Columnas_tabla col = new Columnas_tabla();
        this.modelo2.getIteraciones().add("x0");
        col.setX(this.x);
        col.setYi(this.yi);
        col.setCorrector(this.yi);
        col.setPredictor(this.yi);
        col.setY(funcion(this.x));
        this.modelo2.getFilas().add(col);
    }

    public HiloTabla(double x, double yi, double h, double limSup, ModeloTablaRalston modelo3) {
        this.x = x;
        this.yi = yi;
        this.h = h;
        this.limSup = limSup;
        this.modelo3 = modelo3;
        Columnas_tabla col = new Columnas_tabla();
        this.modelo3.getIteraciones().add("x0");
        col.setX(this.x);
        col.setYi(this.yi);
        col.setCorrector(this.yi);
        col.setPredictor(this.yi);
        col.setY(funcion(this.x));
        this.modelo3.getFilas().add(col);
    }

	@Override
    public void run() {
        int i = 0;
        try {
        	if(modelo != null) {
        		modelo.fireTableDataChanged();
        	}
        	else if(modelo1 != null){
        		modelo1.fireTableDataChanged();
        	}
                else if(modelo2 != null){
        		modelo2.fireTableDataChanged();
        	}
                else if(modelo3 != null){
        		modelo3.fireTableDataChanged();
        	}
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (double x = this.x; x < this.limSup; x += h) {
            Columnas_tabla aux = new Columnas_tabla();
            aux.setX(x + this.h);
            if(modelo != null){
                aux.setYi(y(i, x));
            }
            else if(modelo1 != null){
                aux.setYi(y(i, x));
                aux.setPredictor(aux.getYi());
                aux.setCorrector(corrector(i, x));
            }
            else if(modelo2 != null){
                aux.setYi(puntoMedio(i, x));
            }
            else if(modelo3 != null){
                aux.setYi(ralston(i, x));
            }
            aux.setY(funcion(x + this.h));
            if(modelo1 == null) {
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
            else if(modelo1 != null){
            	modelo1.getIteraciones().add("x" + (++i));
                modelo1.getFilas().add(aux);
                modelo1.fireTableDataChanged();
            }
            else if(modelo2 != null){
                modelo2.getIteraciones().add("x" + (++i));
                modelo2.getFilas().add(aux);
                modelo2.fireTableDataChanged();
            }
            else if(modelo3 != null){
                modelo3.getIteraciones().add("x" + (++i));
                modelo3.getFilas().add(aux);
                modelo3.fireTableDataChanged();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public double derivada(double x){
        //return -2 * Math.pow(x, 3) + 12 * Math.pow(x, 2) - 20 * x + 8.5;
        return -0.006 * Math.sqrt(x);
    }
    
    public double funcion(double x){
        //return -0.5 * Math.pow(x, 4) + 4 * Math.pow(x, 3) - 10 * Math.pow(x, 2) + 8.5 * x + 1;
        return Math.sqrt(x) / (0.5 * -0.006);
    }
    
    public double y(int posicion, double x){
        return modelo.getFilas().get(posicion).getYi() + derivada(x) * this.h;
    }
    
    public double corrector(int posicion, double x) {
    	return modelo1.getFilas().get(posicion).getYi() + ((derivada(x) + derivada(x + this.h)) / 2) * this.h;
    }
    
    public double ralston(int posicion, double x){
        return modelo3.getFilas().get(posicion).getYi() + (((double) 1 / (double) 3) * derivada(x) + ((double) 2 / (double) 3) * derivada(x + ((double) 3 / (double) 4) * h))
                * h;
    }
    
    public double puntoMedio(int posicion, double x){
        return modelo2.getFilas().get(posicion).getYi() + derivada(x + (this.h / 2)) * h;
    }
}
