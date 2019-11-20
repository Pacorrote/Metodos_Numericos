package objetos;

import java.util.ArrayList;

public class Polinomio {
	
	private ArrayList<Monomio> monomios;
	
	public Polinomio() {
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
        
        public static Polinomio derivada(Polinomio poli){
            Polinomio polinomio = new Polinomio();
            for (Monomio monomio : poli.getMonomios()) {
                int potencia = monomio.getPotencia() - 1;
                int coeficiente = monomio.getCoeficiente() * monomio.getPotencia();
                polinomio.addMonomio(new Monomio(coeficiente, potencia));
            }
            return polinomio;
        }

}
