package objetos;

import java.util.ArrayList;

public class Metodo_Newton_Raphson {
	
	private ArrayList<ColumnasNewtonRaphson> filas;
	private Float puntoI;
	private ColumnasNewtonRaphson aux;

	public Metodo_Newton_Raphson() {
		// TODO Auto-generated constructor stub
	}
	
	public Metodo_Newton_Raphson(Float puntoI) {
		 aux = new ColumnasNewtonRaphson(puntoI);
		 this.puntoI = aux.getXi();
	}
	
	
	public void Metodo() {
		aux.setFxi(funcion());
		aux.setDiffxi(derivadaFuncion());
		aux.setXj(Xj());
		filas.add(aux);
		if(this.puntoI!=aux.getXj()) {
			float aux = this.aux.getXj();
			this.aux = new ColumnasNewtonRaphson(aux);
			this.puntoI = this.aux.getXi();
			Metodo();
		}
	}
	
	public Float funcion() {
		return (float) (Math.exp(this.puntoI)-this.puntoI);
	}
	
	public Float derivadaFuncion() {
		return (float) (-Math.exp(this.puntoI)-1);
	}
	
	public Float Xj() {
		if(aux.getDiffxi()!=0) {
			return (this.puntoI-(aux.getFxi()/aux.getDiffxi()));
		}
		else {
			return this.puntoI+0.0000001f;
		}
	}

}
