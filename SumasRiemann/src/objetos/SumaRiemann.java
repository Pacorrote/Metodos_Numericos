/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import enums.TipoSuma;
import java.text.DecimalFormat;

/**
 *
 * @author PACO
 */
public class SumaRiemann {
    
    protected Float liIn;
    protected Float liSu;
    protected Integer n;
    private TipoSuma tipo;
    protected Float longitud;

    public SumaRiemann(Float liIn, Float liSu, Integer n, TipoSuma tipo) {
        this.liIn = liIn;
        this.liSu = liSu;
        this.n = n;
        this.tipo = tipo;
        this.longitud = Math.abs(this.liIn-this.liSu);
    }

    public SumaRiemann() {
        this.liIn = 0f;
        this.liSu = 0f;
        this.n = 1;
    }

    public Float getLiIn() {
        return liIn;
    }

    public void setLiIn(Float liIn) {
        this.liIn = liIn;
    }

    public Float getLiSu() {
        return liSu;
    }

    public void setLiSu(Float liSu) {
        this.liSu = liSu;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public TipoSuma getTipo() {
        return tipo;
    }

    public void setTipo(TipoSuma tipo) {
        this.tipo = tipo;
    }
    
    public Float sumaRiemann(){
        float resultado = 0;
        if(this.longitud == null){
            this.longitud = Math.abs(liIn - liSu);
        }
        if(tipo == TipoSuma.SumaInf){
            resultado = sumaRiemannInf();
        }
        if(tipo == TipoSuma.SumaMedia){
            resultado = sumaRiemannpuntoMedio();
        }
        if(tipo == TipoSuma.SumaSup){
            resultado = sumaRiemannSup();
        }
        return resultado;
    }
    
    protected float sumaRiemannInf(){
        float resultado = 0;
        for (float i = liIn; i < liSu && i < liSu - 0.01; i = i + (longitud / n)){
           // resultado += exp(i) * (longitud / n);
            //resultado += cos(i) * (longitud / n);
            //resultado += estadistico(i, 10, 2) * (longitud / n);
            resultado += coscosx(i) * (longitud / n);
        }
        return resultado;
    }
    
    protected float sumaRiemannSup(){
        float resultado = 0;
        DecimalFormat formato = new DecimalFormat("#.##");
        for (float i = liIn + (longitud / n); (Float.parseFloat(formato.format(i)) - 0.01) <= liSu; i = i + (longitud / n)){
           // resultado += exp(i) * (longitud / n);
            //resultado += cos(i) * (longitud / n);
            //resultado += estadistico(i, 10, 2) * (longitud / n);
            resultado += coscosx(i) * (longitud / n);
        }
        return resultado;
    }
    
    protected float sumaRiemannpuntoMedio(){
        float resultado = 0;
        float punto1 = liIn;
        float punto2 = punto1 + (longitud / n);
        for (int i = 0; i < n; i++){
           // resultado += exp(puntoMedio(punto1, punto2)) * (longitud / n);
           // resultado += cos(puntoMedio(punto1, punto2)) * (longitud / n);
            //resultado += estadistico(puntoMedio(punto1, punto2), 10, 2) * (longitud / n);
            resultado += coscosx(puntoMedio(punto1, punto2)) * (longitud / n);
            punto1 += (longitud / n);
            punto2 += (longitud / n);
        }
        return resultado;
    }
    
    public static float exp(float x){
        return (float) Math.exp(x);
    }
    
    public static float puntoMedio(float x1, float x2){
        return (x1 + x2) / 2;
    }
    
    public static float cos(float x){
        return (float) Math.cos(x);
    }
    
    public static float estadistico(float x, float miu, float sigmaCuadrada){
        float coeficiente = (float) ((float) 1 / Math.sqrt(2 * sigmaCuadrada * Math.PI));
        float exponente = (float) Math.pow(x - miu, 2);
        exponente /= 2 * sigmaCuadrada;
        return (float) (coeficiente * Math.exp(-exponente));
    }
    
    public static float coscosx(float x){
        float sinx = (float) Math.sin(x);
        return (float) (Math.cos(x) * Math.cos(sinx));
    }
    
}
