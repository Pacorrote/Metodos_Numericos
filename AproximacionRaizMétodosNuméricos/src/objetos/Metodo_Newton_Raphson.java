package objetos;

import java.util.ArrayList;

public class Metodo_Newton_Raphson {
	
	private ArrayList<ColumnasNewtonRaphson> filas;
	private Float puntoI;
	private ColumnasNewtonRaphson aux;
	private Float error;

	public Metodo_Newton_Raphson() {
		// TODO Auto-generated constructor stub
	}
	
	public Metodo_Newton_Raphson(Float puntoI, Float error) {
		this.filas = new ArrayList<>();
		this.error = error;
		aux = new ColumnasNewtonRaphson(puntoI);
		this.puntoI = aux.getXi();
	}
	
	
	public void Metodo() {
		aux.setFxi(funcion());
		aux.setDiffxi(derivadaFuncion());
		aux.setXj(Xj());
		aux.setErrorAprox(error()*100);
		filas.add(aux);
		if(aux.getErrorAprox()>error) {
			float aux = this.aux.getXj();
			this.aux = new ColumnasNewtonRaphson(aux);
			this.puntoI = this.aux.getXi();
			Metodo();
		}
	}
	
	private Float funcion() {
		//return (float) Math.sin(Math.pow(this.puntoI, 3)+8);
		return (float) (Math.exp(-this.puntoI)-this.puntoI);
	}
	
	private Float derivadaFuncion() {
		//return (float) (3*Math.pow(this.puntoI, 2)*Math.cos(Math.pow(this.puntoI, 3)+8));
		return (float) (-Math.exp(-this.puntoI)-1);
	}
	
	private Float Xj() {
		if(aux.getDiffxi()!=0) {
			return (this.puntoI-(aux.getFxi()/aux.getDiffxi()));
		}
		else {
			return this.puntoI+0.0000001f;
		}
	}

	public ArrayList<ColumnasNewtonRaphson> getFilas() {
		return filas;
	}

	public void setFilas(ArrayList<ColumnasNewtonRaphson> filas) {
		this.filas = filas;
	}

	public void imprimir() {
		System.out.println("   xi\t   f(xi)\t   diff(xi)\t   xi+1\t   error");
		for (ColumnasNewtonRaphson columnasNewtonRaphson : filas) {
			System.out.println(columnasNewtonRaphson.toString());
		}
	}
	
	private Float error() {
		return Math.abs(this.puntoI-aux.getXj())/aux.getXj();
	}

}
