/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import enums.TipoSuma;

/**
 *
 * @author PACO
 */
public abstract class IntegralesDefinidasPolinomica{

    public static Double integralDefinida(Polinomios polinomio, double liInf, double liSup){
        Polinomios integral = integral(polinomio);
        return valorPx(integral, liSup) - valorPx(integral, liInf);
    }
    
    public static Polinomios integral(Polinomios polinomio){
        Polinomios polinomiore = new Polinomios();
        for (Monomio monomio : polinomio.getMonomios()) {
            int potencia  = monomio.getPotencia() + 1;
            double coef = monomio.getCoeficiente() / (double) potencia;
            Monomio monomio1 = new Monomio();
            monomio1.setCoeficiente(coef);
            monomio1.setPotencia(potencia);
            polinomiore.addMonomio(monomio1);
        }
        return polinomiore;
    }
    
    public static Double valorPx(Polinomios polinomio, double x){
        Double retorno = (double) 0;
        for (Monomio monomio : polinomio.getMonomios()) {
            retorno += valorMx(monomio, x);
        }
        return retorno;    
    }

    public static Double valorMx(Monomio monomio, double x){
        return monomio.getCoeficienteDouble()* Math.pow(x, monomio.getPotencia());
    }
}
