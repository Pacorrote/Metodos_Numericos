/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import enums.TipoSuma;
import java.text.DecimalFormat;
import java.util.ArrayList;
import static objetos.SumaRiemann.exp;

/**
 *
 * @author PACO
 */
public class SumaRiemannPoli extends SumaRiemann{

    protected Polinomios polinomio;
    
    public SumaRiemannPoli(Polinomios polinomio, Float liIn, Float liSu, Integer n, TipoSuma tipo) {
        super(liIn, liSu, n, tipo);
        this.polinomio = polinomio;
    }

    @Override
    protected float sumaRiemannInf() {
         //To change body of generated methods, choose Tools | Templates.
        float resultado = 0;
        for (float i = liIn; i < liSu && i < liSu - 0.01; i = i + (longitud / (float) n)){
            resultado += valorPx(polinomio, i) * (longitud / (float) n);
        }
        return resultado;
    }

    @Override
    protected float sumaRiemannSup() {
         //To change body of generated methods, choose Tools | Templates.
         float resultado = 0;
        DecimalFormat formato = new DecimalFormat("#.##");
        for (float i = liIn + (longitud / n); Float.parseFloat(formato.format(i)) <= liSu; i = i + (longitud / (float) n)){
            resultado += valorPx(polinomio, i) * (longitud / (float) n);
        }
        return resultado;
    }

    @Override
    protected float sumaRiemannpuntoMedio() {
         //To change body of generated methods, choose Tools | Templates.
         float resultado = 0;
        float punto1 = liIn;
        float punto2 = punto1 + (longitud / n);
        for (int i = 0; i < n; i++){
            resultado += valorPx(polinomio, puntoMedio(punto1, punto2)) * (longitud / (float) n);
            punto1 += (longitud / (float) n);
            punto2 += (longitud / (float) n);
        }
        return resultado;
    }
    
    public static Float valorPx(Polinomios polinomio, float x){
        float retorno = 0;
        for (Monomio monomio : polinomio.getMonomios()) {
            retorno += valorMx(monomio, x);
        }
        return retorno;    
    }

    public static Float valorMx(Monomio monomio, float x){
        return (float) (monomio.getCoeficienteDouble() * Math.pow(x, monomio.getPotencia()));
    }
    
    
    
}
