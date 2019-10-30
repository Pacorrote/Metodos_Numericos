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
public class SumaTrapecio extends SumaRiemann{

    public SumaTrapecio(Float liIn, Float liSu, Integer n, TipoSuma tipo) {
        super(liIn, liSu, n, tipo);
    }

    public SumaTrapecio() {
        super();
    }
    
    public Float sumaTrapecio(){
        if(this.longitud == null){
            this.longitud = Math.abs(liIn - liSu);
        }
        float resultado = (exp(this.liIn) / 2) * (longitud / n);
        float inicio = this.liIn + (longitud / n);
        while(inicio < this.liSu){
            resultado += exp(inicio) * (longitud / n);
            inicio = inicio + (longitud / n);
        }
        resultado += (exp(this.liSu) / 2) * (longitud / n);
        return resultado;
    }
    
}
