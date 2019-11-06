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
public class SumaTrapecioPoli extends SumaRiemannPoli{
    
    public SumaTrapecioPoli(Polinomios polinomio, Float liIn, Float liSu, Integer n, TipoSuma tipo) {
        super(polinomio, liIn, liSu, n, tipo);
    }
    
    public Float sumaTrapecio(){
        if(this.longitud == null){
            this.longitud = Math.abs(liIn - liSu);
        }
        float resultado = (valorPx(polinomio, this.liIn) / 2) * (longitud / (float) n);
        float inicio = this.liIn + (longitud / (float) n);
        while(inicio < this.liSu){
            resultado += valorPx(polinomio, inicio) * (longitud / (float) n);
            inicio = inicio + (longitud / (float) n);
        }
        resultado += (valorPx(polinomio, this.liSu) / 2) * (longitud / (float) n);
        return resultado;
    }
}
