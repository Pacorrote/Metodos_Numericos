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
public class MetodoJacobi {
    
    private Float matrizQ [][];
    private Float matriz [][];
    private Float matrizR [][];
    private Float vectorResultado [];
    private Float vectorX [];
    private Integer iteraciones;
    private Float error;
    private Float errorEstimado = 1f;

    public MetodoJacobi() {
        
    }
    
    public MetodoJacobi(Float matrizInicial[][], Float vectorInicial [], Integer iteraciones, Float error){
        this.matriz = matrizInicial;
        verificarMatriz(matrizInicial);
        inicializarMatrizQ(matrizInicial[0].length-1);
        rellenarMatrizQ(matrizInicial);
        inicializarMatrizR(matrizInicial[0].length-1);
        rellenarMatrizR(matrizInicial);
        rellenarVectorResultado(matrizInicial);
        this.vectorX = vectorInicial;
        this.iteraciones = iteraciones;
        this.error = error;
    }
    
    private void verificarMatriz(Float matriz [][]){
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][i] == 0){
                Float vectorAux [] = matriz[i];
                matriz[i] = matriz[i+1];
                matriz[i+1] = vectorAux;
            }
        }
    }
    
    public final void inicializarMatrizQ(Integer tamanno){
        matrizQ = new Float[tamanno][tamanno];
        for (int i = 0; i < tamanno; i++) {
            for (int j = 0; j < tamanno; j++) {
                matrizQ[i][j] = 0f;
            }
        }
    }
    
    public final void inicializarMatrizR(Integer tamanno){
        matrizR = new Float[tamanno][tamanno];
    }
    
    public final void rellenarMatrizQ(Float matrizInicial[][]){
        for (int i = 0; i < matrizInicial.length; i++) {
            matrizQ[i][i] = matrizInicial[i][i];
        }
    }
    
    public final void rellenarMatrizR(Float matrizInicial[][]){
        for (int i = 0; i < matrizQ.length; i++) {
            for (int j = 0; j < matrizQ.length; j++) {
                matrizR[i][j] = matrizQ[i][j] - matrizInicial[i][j];
            }
        }
    }
    
    private void inicializarVectorResultado(Integer tamanno){
        vectorResultado = new Float[tamanno];
    }
   
    public final void rellenarVectorResultado(Float matrizInicial[][]){
        inicializarVectorResultado(matrizInicial.length);
        for (int i = 0; i < vectorResultado.length; i++) {
            vectorResultado[i] = matrizInicial[i][matrizInicial.length];
        }
    }
    
    public void imprimirQ(){
        System.out.println("Matriz Q:");
        for (int i = 0; i < matrizQ.length; i++) {
            for (int j = 0; j < matrizQ[i].length; j++) {
                System.out.print(matrizQ[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public void imprimirR(){
        System.out.println("Matriz R");
        for (int i = 0; i < matrizR.length; i++) {
            for (int j = 0; j < matrizR[i].length; j++) {
                System.out.print(matrizR[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public void imprimirVectorResultado(){
        System.out.println("Vector de resultado");
        for (int i = 0; i < vectorResultado.length; i++) {
            System.out.println(vectorResultado[i]);
        }
    }
    
    public Float [] metodoJacobi(){
        int iteraciones = 0;
        do{
            if(iteraciones > 0){
                errorEstimado = errorAproximado(vectorDiferencia());
            }
            Float vectorMatriz [][] = {vectorX};
            Float multiplicacion [][] = multiplicacionMatriz(matrizR, vectorMatriz);
            Float matrizVector [] = null;
            if(multiplicacion[0].length == 1){
                matrizVector = new Float[multiplicacion.length];
                for (int j = 0; j < multiplicacion.length; j++) {
                    for (int k = 0; k < multiplicacion[0].length; k++) {
                       matrizVector[j] = multiplicacion[j][k];
                    }
                }
            }
            if(matrizVector != null){
                Float suma [] = new Float[vectorResultado.length];
                for (int j = 0; j < vectorResultado.length; j++) {
                    suma [j] = vectorResultado[j] + matrizVector[j];
                }
                System.out.println("Vector x:\n");
                for (int j = 0; j < vectorX.length; j++) {
                    vectorX[j] = suma[j]/matrizQ[j][j];
                    System.out.println(vectorX[j]);
                }
                System.out.println("");
            }
            iteraciones ++;
            System.out.println("Iteraciones: " + iteraciones +"\n");
            if(iteraciones == this.iteraciones){
                break;
            }
        }while(Math.abs(errorEstimado-errorAproximado(vectorDiferencia()))/errorAproximado(vectorDiferencia()) > error);
        return this.vectorX;
    }
    
    private Float[] vectorDiferencia(){
        Float vectorDiferencia [] = new Float[vectorX.length];
        //System.out.println("Vector diferencia:");
        for (int i = 0; i < vectorDiferencia.length; i++) {
            float resultado = 0;
            for (int j = 0; j < matriz[i].length-1; j++) {
                resultado  = resultado + (matriz[i][j]*vectorX[j]);
            }
            vectorDiferencia[i] = resultado;
           // System.out.println(vectorDiferencia[i]);
        }
        return vectorDiferencia;
    }
    
    public static Float [][] multiplicacionMatriz(Float m1 [][], Float m2 [][]){
        Float matrizResultado [][];
        if(m2.length != 1 ){
            if(m1[0].length == m2.length){
                matrizResultado = new Float[m1.length][m2[0].length];
                for (int i = 0; i < m1.length; i++) {
                    for (int j = 0; j < m2[0].length; j++) {
                        float suma = 0;
                        for (int k = 0; k < m2.length; k++) {
                            suma += m1[i][k] * m2[k][j];
                        }
                        matrizResultado[i][j] = suma;
                    }
                }
                return matrizResultado;
            }
        }
        else{
            if(m1[0].length == m2[0].length){
                matrizResultado = new Float[m1.length][m2.length];
                for (int i = 0; i < m1.length; i++) {
                    float suma = 0;
                    for (int j = 0; j < m2[0].length; j++) {
                        suma += m1[i][j] * m2[0][j];
                    }
                    matrizResultado[i][0] = suma;
                }
                return matrizResultado;
            }
        }
        return null;
    }
    
    private Float errorAproximado(Float [] vectorDiferencia){
        float norma = 0;
        Float vectorAux [] = new Float[vectorResultado.length];
        for (int i = 0; i < vectorAux.length; i++) {
            vectorAux[i] = Math.abs(vectorResultado[i]-vectorDiferencia[i]);
            norma += Math.pow(vectorAux[i], 2);
        }
        System.out.println("Norma: " + Math.sqrt(norma) +"\n");
        return (float) Math.sqrt(norma);
    }
}
