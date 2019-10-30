/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumasriemann;

import enums.TipoSuma;
import objetos.SumaRiemann;
import objetos.SumaTrapecio;

/**
 *
 * @author PACO
 */
public class SumasRiemann {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Suma inf: " + new SumaRiemann(0f, 3f, 20, TipoSuma.SumaInf).sumaRiemann());
        System.out.println("Suma sup: " + new SumaRiemann(0f, 3f, 20, TipoSuma.SumaSup).sumaRiemann());
        System.out.println("Suma punto medio: " + new SumaRiemann(0f, 3f, 20, TipoSuma.SumaMedia).sumaRiemann());
        System.out.println("Suma trapecio: " + new SumaTrapecio(0f, 3f, 20, null).sumaTrapecio());
        System.out.println("Simpson 1/3: " + simpson13(0f, 3f, 20));
        System.out.println("Simpson 3/8: " + simpson38(0f, 3f, 20));
        System.out.println("Integral de exp: " + (SumaRiemann.exp(3)-SumaRiemann.exp(0)));
        
    }
    
    public static Float simpson13(float liInf, float liSup, int n){
        float retorno = Math.abs(liSup - liInf) / (6);
        retorno *= (Math.exp(liInf) + 4 * Math.exp((liInf + liSup) / 2) + Math.exp(liSup));
        return retorno;
    }
    
    public static Float simpson38(float liInf, float liSup, int n){
        float retorno = 3 * Math.abs(liSup - liInf) / (24);
        retorno *= (Math.exp(liInf) + 3 * Math.exp(( 2 * liInf + liSup) / 3) + 3 * Math.exp((liInf + (2 * liSup)) / 3) + Math.exp(liSup));
        return retorno;
    }
    
}
