package objetos;

public class Monomio {
	
	private Integer coeficiente;
	private Integer potencia;
	
	public Monomio() {
		// TODO Auto-generated constructor stub
	}

	public Monomio(Integer coeficiente, Integer potencia) {
		this.coeficiente = coeficiente;
		this.potencia = potencia;
	}

	public Integer getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Integer coeficiente) {
		this.coeficiente = coeficiente;
	}

	public Integer getPotencia() {
		return potencia;
	}

	public void setPotencia(Integer potencia) {
		this.potencia = potencia;
	}
	
	public static boolean mayorIgualPotenciasMonomios(Monomio m1, Monomio m2) {
		return m1.getPotencia()>=m2.getPotencia();
	}

	@Override
	public String toString() {
		return coeficiente>0? String.format("+%dx^%d", this.coeficiente, this.potencia): String.format("%dx^%d", this.coeficiente, this.potencia);
	}

}
