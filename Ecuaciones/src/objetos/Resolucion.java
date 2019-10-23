package objetos;

import java.util.ArrayList;

public abstract class Resolucion {

	public Resolucion() {
		// TODO Auto-generated constructor stub
	}
	
	public static Double [][] gauss(Double [][] matriz, ArrayList<String> cadenas) {
        Double [][] matrizGauss = copiarMatriz(matriz);
        for (int i = 0; i < matrizGauss.length; i++) {
            pivoteAUno(matrizGauss, i);
            for (int j = i+1; j < matrizGauss.length; j++) {
                Double multiplicador = matrizGauss[j][i];
                for (int k = 0; k < matrizGauss[i].length; k++) {
                    //System.out.println(multiplicador);
                    matrizGauss[j][k] = matrizGauss[j][k]-(multiplicador*matrizGauss[i][k]);
                    //System.out.print(matrizGauss[j][k]+"\t");
                }
                //System.out.println("");
            }
            obtenerMatrizCadena(matrizGauss, cadenas);
            imprimirMatriz(matrizGauss);
            
        }
//        pivoteAUno(matrizGauss, 0);
//        imprimirMatriz(matrizGauss);
        return matrizGauss;
    }
	
	public static Fraccion [][] gauss(Fraccion [][] matriz, ArrayList<String> cadenas){
		Fraccion [][] matrizGauss = copiarMatriz(matriz);
		for (int i = 0; i < matrizGauss.length; i++) {
            pivoteAUno(matrizGauss, i);
            for (int j = i+1; j < matrizGauss.length; j++) {
                Fraccion multiplicador = new Fraccion(matrizGauss[j][i]);
                //System.out.println(multiplicador);
                for (int k = 0; k < matrizGauss[i].length; k++) {
                    //System.out.println(multiplicador);
                	Fraccion f1 = new Fraccion(Fraccion.multiplicar(multiplicador, matrizGauss[i][k]));
                	matrizGauss[j][k] = Fraccion.restar(matrizGauss[j][k], f1);
                    //System.out.print(matrizGauss[j][k]+"\t");
                }
               // System.out.println("");
            }
            obtenerMatrizCadena(matrizGauss, cadenas);
            imprimirMatriz(matrizGauss);
        }
		return matrizGauss;
	}
	
	private static void pivoteAUno(Double [][] matriz, int fila){
        Double divisor = matriz[fila][fila];
        if(divisor != 0) {
        	for (int i = 0; i < matriz[fila].length; i++) {
                matriz[fila][i] = matriz[fila][i]/divisor;
            }
        }
        else {
        	Double filaVector [] = matriz[fila];
        	for (int i = fila+1; i < matriz.length; i++) {
				if(matriz[i][fila]!=0) {
					matriz[fila]=matriz[i];
					matriz[i]=filaVector;
					pivoteAUno(matriz, fila);
					break;
				}
			}
        }
    }
        
        private static void pivoteAUnoInvertido(Double [][] matriz, int fila){
        Double divisor = matriz[fila][fila];
        if(divisor != 0) {
        	for (int i = matriz[fila].length-1; i >= 0; i--) {
                matriz[fila][i] = matriz[fila][i]/divisor;
            }
        }
        else {
        	Double filaVector [] = matriz[fila];
        	for (int i = fila-2; i >= 0; i--) {
				if(matriz[i][fila]!=0) {
					matriz[fila]=matriz[i];
					matriz[i]=filaVector;
					pivoteAUno(matriz, fila);
					break;
				}
			}
        }
    }
	
	private static void pivoteAUno(Fraccion[][] matriz, int fila) {
		Fraccion divisor = new Fraccion(matriz[fila][fila].getNumerador(), matriz[fila][fila].getDenominador());
		if(divisor.getNumerador()==0) {
			Fraccion filaVector [] = matriz[fila];
			for (int i = fila+1; i < matriz.length; i++) {
				if(matriz[i][fila].getNumerador() != 0) {
					matriz[fila] = matriz[i];
					matriz[i] = filaVector;
					pivoteAUno(matriz, fila);
					break;
				}
			}
		}
		else {
			for (int i = 0; i < matriz[fila].length; i++) {
				matriz[fila][i] = Fraccion.dividir(matriz[fila][i], divisor);
			}
		}	
	}
        
        private static void pivoteAUnoInvertida(Fraccion[][] matriz, int fila) {
		Fraccion divisor = new Fraccion(matriz[fila][fila].getNumerador(), matriz[fila][fila].getDenominador());
		if(divisor.getNumerador()==0) {
			Fraccion filaVector [] = matriz[fila];
			for (int i = matriz[fila].length-1; i >= 0; i--) {
				if(matriz[i][fila].getNumerador() != 0) {
					matriz[fila] = matriz[i];
					matriz[i] = filaVector;
					pivoteAUno(matriz, fila);
					break;
				}
			}
		}
		else {
			for (int i = fila-2; i >= 0; i--) {
				matriz[fila][i] = Fraccion.dividir(matriz[fila][i], divisor);
			}
		}	
	}
	
	public static void obtenerMatrizCadena(Double [][] matriz, ArrayList<String> cadenas) {
		String cadena = "<html>";
		for (int i = 0; i < matriz.length; i++) {
			cadena += "[&emsp;";
            for (int j = 0; j < matriz[0].length; j++) {
                cadena += matriz[i][j] + "&emsp;&emsp;";
            }
            cadena += "]" + "<br>";
        }
		cadenas.add(cadena);
        System.out.println("\n");
	}
	
	public static void obtenerMatrizCadena(Fraccion [][] matriz, ArrayList<String> cadenas) {
		String cadena = "<html>";
		for (int i = 0; i < matriz.length; i++) {
			cadena += "[&emsp;&emsp;";
            for (int j = 0; j < matriz[0].length; j++) {
                cadena += matriz[i][j] + "&emsp;&emsp;";
            }
            cadena += "]" + "<br>";
        }
		cadenas.add(cadena);
        System.out.println("\n");
	}
	
	public static void imprimirMatriz(Double [][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]+"\t");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }
	
	public static void imprimirMatriz(Fraccion [][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]+"\t");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }
	
	private static Double [][] copiarMatriz(Double [][] matriz){
        Double copiaMatriz [][] = new Double[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                copiaMatriz[i][j] = matriz[i][j];
            }
        }
        return copiaMatriz;
    }
	
	private static Fraccion [][] copiarMatriz(Fraccion [][] matriz){
        Fraccion copiaMatriz [][] = new Fraccion[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                copiaMatriz[i][j] = new Fraccion(matriz[i][j].getNumerador(), matriz[i][j].getDenominador());
            }
        }
        return copiaMatriz;
    }
	
	public static Double [][] gaussJordan(Double matriz [][]){
        Double [][] matrizGaussJordan = gauss(matriz);
        int i1=2;
        for (int i = matrizGaussJordan.length-1; i >= 0; i--) {
            reduccion(matrizGaussJordan, i1, i);
            i1++;
        }
        imprimirMatriz(matrizGaussJordan);
        return matrizGaussJordan;
    }
        public static Double [][] gaussJordan(Double matriz [][], ArrayList<String> cadenas){
        Double [][] matrizGaussJordan = gauss(matriz, cadenas);
        int i1=2;
        for (int i = matrizGaussJordan.length-1; i >= 0; i--) {
            reduccion(matrizGaussJordan, i1, i);
            i1++;
            obtenerMatrizCadena(matrizGaussJordan, cadenas);
        }
        imprimirMatriz(matrizGaussJordan);
        return matrizGaussJordan;
    }
        
        public static Double [][] gaussSimpleInferior(Double matriz [][], ArrayList<String> cadenas){
        Double [][] matrizGaussSimple = copiarMatriz(matriz);
        int i1=2;
        for (int i = matrizGaussSimple.length-1; i >= 0; i--) {
            pivoteAUnoInvertido(matriz, i);
            reduccion(matrizGaussSimple, i1, i);
            i1++;
            obtenerMatrizCadena(matrizGaussSimple, cadenas);
        }
        imprimirMatriz(matrizGaussSimple);
        return matrizGaussSimple;
    }
	
	public static Fraccion [][] gaussJordan(Fraccion matriz [][]){
        Fraccion [][] matrizGaussJordan = gauss(matriz);
        int i1=2;
        for (int i = matrizGaussJordan.length-1; i >= 0; i--) {
            reduccion(matrizGaussJordan, i1, i);
            i1++;
        }
        imprimirMatriz(matrizGaussJordan);
        return matrizGaussJordan;
    }
        
        public static Fraccion [][] gaussJordan(Fraccion matriz [][], ArrayList<String> cadenas){
        Fraccion [][] matrizGaussJordan = gauss(matriz, cadenas);
        int i1=2;
        for (int i = matrizGaussJordan.length-1; i >= 0; i--) {
            reduccion(matrizGaussJordan, i1, i);
            i1++;
            obtenerMatrizCadena(matrizGaussJordan, cadenas);
        }
        imprimirMatriz(matrizGaussJordan);
        return matrizGaussJordan;
    }
        
        public static Fraccion [][] gaussSimpleInferior(Fraccion matriz [][], ArrayList<String> cadenas){
        Fraccion [][] matrizGaussSimple = copiarMatriz(matriz);
        int i1=2;
        for (int i = matrizGaussSimple.length-1; i >= 0; i--) {
            pivoteAUno(matriz, i);
            reduccion(matrizGaussSimple, i1, i);
            i1++;
            obtenerMatrizCadena(matrizGaussSimple, cadenas);
        }
        imprimirMatriz(matrizGaussSimple);
        return matrizGaussSimple;
    }
	
	private static Double [][] gauss(Double [][] matriz) {
        Double [][] matrizGauss = copiarMatriz(matriz);
        for (int i = 0; i < matrizGauss.length; i++) {
            pivoteAUno(matrizGauss, i);
            for (int j = i+1; j < matrizGauss.length; j++) {
                Double multiplicador = matrizGauss[j][i];
                for (int k = 0; k < matrizGauss[i].length; k++) {
                    //System.out.println(multiplicador);
                    matrizGauss[j][k] = matrizGauss[j][k]-(multiplicador*matrizGauss[i][k]);
                    //System.out.print(matrizGauss[j][k]+"\t");
                }
                //System.out.println("");
            }
            imprimirMatriz(matrizGauss);
            
        }
//        pivoteAUno(matrizGauss, 0);
//        imprimirMatriz(matrizGauss);
        return matrizGauss;
    }
	
	private static Fraccion [][] gauss(Fraccion [][] matriz) {
        Fraccion [][] matrizGauss = copiarMatriz(matriz);
        for (int i = 0; i < matrizGauss.length; i++) {
            pivoteAUno(matrizGauss, i);
            for (int j = i+1; j < matrizGauss.length; j++) {
                Fraccion multiplicador = new Fraccion(matrizGauss[j][i]);
                for (int k = 0; k < matrizGauss[i].length; k++) {
                    //System.out.println(multiplicador);
                	Fraccion f1 = new Fraccion(Fraccion.multiplicar(multiplicador, matrizGauss[i][k]));
                	matrizGauss[j][k] = Fraccion.restar(matrizGauss[j][k], f1);
                    //System.out.print(matrizGauss[j][k]+"\t");
                }
                //System.out.println("");
            }
            imprimirMatriz(matrizGauss);
            
        }
//        pivoteAUno(matrizGauss, 0);
//        imprimirMatriz(matrizGauss);
        return matrizGauss;
    }
    
    public static void reduccion(Double [][] matriz, int i1, int i){
        for (int j = matriz.length-i1; j >= 0; j--) {
            Double multiplicador = matriz[j][i];
            for (int k = matriz[j].length-1; k >= 0; k--) {
                matriz[j][k] = matriz[j][k]-(multiplicador*matriz[i][k]);
            }      
        }
    }
    
    public static void reduccion(Fraccion [][] matriz, int i1, int i){
        for (int j = matriz.length-i1; j >= 0; j--) {
            Fraccion multiplicador = new Fraccion(matriz[j][i]);
            for (int k = matriz[j].length-1; k >= 0; k--) {
            	Fraccion f1 = new Fraccion(Fraccion.multiplicar(multiplicador, matriz[i][k]));
            	matriz[j][k] = Fraccion.restar(matriz[j][k], f1);
            }      
        }
    }
    
    public static Double determinante(Double [][] matriz){
    	Double det;
    	if(matriz.length==2)
    	{
    		det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
    		return det;
    	}
    	else{
    		double suma=0;
    		for(int i=0; i<matriz.length; i++){
    			Double [][] nm=new Double[matriz.length-1][matriz.length-1];
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
    
    public static Float determinante(Float [][] matriz){
    	Float det;
    	if(matriz.length==2)
    	{
    		det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
    		return det;
    	}
    	else{
    		Float suma=0f;
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
    
     public static Fraccion determinante(Fraccion [][] matriz){
    	Fraccion det;
    	if(matriz.length==2)
    	{
    		det=Fraccion.restar((Fraccion.multiplicar(matriz[0][0], matriz[1][1])),(Fraccion.multiplicar(matriz[1][0], matriz[0][1])));
    		return det;
    	}
    	else{
    		Fraccion suma=new Fraccion(0, 1);
    		for(int i=0; i<matriz.length; i++){
    			Fraccion [][] nm = new Fraccion[matriz.length-1][matriz.length-1];
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
    						nm[indice][k-1]= new Fraccion(matriz[j][k]);
    					}
    				}
    			}
    			if(i%2==0){
    				suma = Fraccion.sumar(suma, Fraccion.multiplicar(matriz[i][0], determinante(nm)));
    			}
    			else{
    				suma = Fraccion.restar(suma, Fraccion.multiplicar(matriz[i][0], determinante(nm)));
    			}
    		}
    		return suma;
    	}
    }

}
