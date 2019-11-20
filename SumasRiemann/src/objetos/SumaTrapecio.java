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
        float resultado = (coscosx(this.liIn)) * (longitud / n.floatValue());
        float inicio = this.liIn + (longitud / n.floatValue());
        while(inicio < this.liSu){
            resultado += coscosx(inicio) * (longitud / n.floatValue());
            inicio = inicio + (longitud / n.floatValue());
        }
        resultado += (coscosx(this.liSu) / (float) 2) * (longitud / n.floatValue());
        return resultado;
    }
    
}
