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
public class MetodoGaussSeidel {
    private Float matrizQ [][];
    private Float matriz [][];
    private Float matrizR [][];
    private Float vectorResultado [];
    private Float vectorX [];
    private Integer iteraciones;
    private Float error;
    private Float errorEstimado = 1f;

    public MetodoGaussSeidel() {
        
    }
    
    public MetodoGaussSeidel(Float matrizInicial[][], Float vectorInicial [], Integer iteraciones, Float error){
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
    
    public Float [] metodoGaussSeidel(){
        int iteraciones = 0;
        if(verificarCondicion()) {
        	System.out.println("¡VA A CONVERGER!\n");
        }
        else {
        	System.out.println("¡NO VA A CONVERGER!\n");
        }
        do{
            if(iteraciones > 0){
                errorEstimado = errorAproximado(vectorDiferencia());
            }
            vectorX = newVector();
            System.out.println("");
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
    
    public Float [] newVector(){
        for (int i = 0; i < matrizR.length; i++) {
            float celda = 0;
            for (int j = 0; j < vectorX.length; j++) {
                celda += vectorX[j]*matrizR[i][j];
            }
            celda += vectorResultado[i];
            vectorX[i] = celda/matrizQ[i][i];
            System.out.println("x" + (i + 1) + ": " + vectorX[i]);
        }
        return vectorX;
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
    
    private Boolean verificarCondicion() {
    	Boolean verificado = true;
    	for (int i = 0; i < matrizQ.length && verificado; i++) {
			verificado = Math.abs(matrizQ[i][i]) > sumaFila(matrizR[i]);
		}
    	return verificado;
    }
    
    public static Float sumaFila(Float vector[]) {
    	Float suma = (float) 0;
    	for (int i = 0; i < vector.length; i++) {
			suma += Math.abs(vector[i]);
		}
    	return suma;
    }
}
