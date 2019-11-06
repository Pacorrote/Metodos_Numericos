package objetos;

import java.util.ArrayList;


public class Polinomios {
	
	private ArrayList<Monomio> monomios;
	
	public Polinomios() {
		// TODO Auto-generated constructor stub
		monomios = new ArrayList<>();
	}

	public ArrayList<Monomio> getMonomios() {
		return monomios;
	}

	public void setMonomios(ArrayList<Monomio> monomios) {
		this.monomios = monomios;
	}
	
	public void addMonomio(Monomio monomio) {
		this.monomios.add(monomio);
	}
	
	public void addMonomio(Integer posicion, Monomio monomio) {
		this.monomios.add(posicion, monomio);
	}
	
	public Monomio getMonomio(Integer index) {
		return this.monomios.get(index);
	}
	
	public void imprimirPolinomio() {
		for (Monomio monomio : monomios) {
                    System.out.print(monomio);
		}
	}

}
