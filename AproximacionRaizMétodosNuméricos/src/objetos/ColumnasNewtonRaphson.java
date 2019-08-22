package objetos;

public class ColumnasNewtonRaphson {
	
	private Float xi;
	private Float fxi;
	private Float diffxi;
	private Float xj;
	private Float errorAprox;
	
	public ColumnasNewtonRaphson() {
		// TODO Auto-generated constructor stub
	}

	public ColumnasNewtonRaphson(Float xi) {
		this.xi = xi;
	}

	public Float getFxi() {
		return fxi;
	}

	public void setFxi(Float fxi) {
		this.fxi = fxi;
	}

	public Float getDiffxi() {
		return diffxi;
	}

	public void setDiffxi(Float diffxi) {
		this.diffxi = diffxi;
	}

	public Float getXj() {
		return xj;
	}

	public void setXj(Float xj) {
		this.xj = xj;
	}

	public Float getXi() {
		return xi;
	}
	

	public Float getErrorAprox() {
		return errorAprox;
	}

	public void setErrorAprox(Float errorAprox) {
		this.errorAprox = errorAprox;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%f\t%f\t%f\t%f\t%f por", this.xi, this.fxi, this.diffxi, this.xj, this.errorAprox);
	}
	
	

}
