/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determinantematriz;

/**
 *
 * @author PACO
 */
public class DeterminanteMatriz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
    double [][] matriz = {
				{1,2,3, 4, 5},
				{-2,3,-9,0,2},
				{4,0,-1,-2,-10},
                                                                        {3,-5,0,5,4},
                                                                        {1,-1,1,-1,1}
		};
                
        System.out.println(determinante(matriz));
        new VentanaPrin();
    }
    
   public static double determinante(double[][] matriz)
{
    double det;
    if(matriz.length==2)
    {
        det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
        return det;
    }
    else{
         double suma=0;
    for(int i=0; i<matriz.length; i++){
    double[][] nm=new double[matriz.length-1][matriz.length-1];
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
