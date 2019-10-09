/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author PACO
 */
public class Solucionador {
    
    private Float matriz [][];

    public Solucionador(Float[][] matriz) {
        this.matriz = matriz;
    }

    public Float[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Float[][] matriz) {
        this.matriz = matriz;
    }
    
    public static float determinante(Float [][] matriz){
        float  det;
        if(matriz.length==2)
        {
            det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }
        else{
             float suma=0;
        for(int i=0; i<matriz.length; i++){
        Float [][] nm=new Float[matriz.length-1][matriz.length-1];
            for(int j=0; j<matriz.length; j++){
                if(j!=i){
                    for(int k=1; k<matriz.length; k++){
                    int indice=0;
                    if(j<i){
                        indice=j;
                    }
                    else if(j>i){
                        indice=j-1;
                    }
                    nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0){
                suma+=matriz[i][0] * determinante(nm);
            }
            else{
                 suma-=matriz[i][0] * determinante(nm);
             }
        }
        return suma;
        }
    }
}
