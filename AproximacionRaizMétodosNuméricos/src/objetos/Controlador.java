package objetos;

import java.util.ArrayList;

public class Controlador {
	
	private Float xl;
	private Float xu;
	private Float xr;
	private ArrayList<Float> erroresPorcentuales;
	private Float errorParo;
	private Integer iteracciones=0;
	
	public Controlador() {
		// TODO Auto-generated constructor stub
		this.erroresPorcentuales = new ArrayList<>();
	}

	public Controlador(Float xl, Float xu, Float erroParo) {
		this.xl = xl;
		this.xu = xu;
		this.errorParo = erroParo;
		this.erroresPorcentuales = new ArrayList<>();
	}

	public Float getXr() {
		return xr;
	}

	public void setXr(Float xr) {
		this.xr = xr;
	}

	public Float getXl() {
		return xl;
	}

	public void setXl(Float xl) {
		this.xl = xl;
	}

	public Float getXu() {
		return xu;
	}

	public void setXu(Float xu) {
		this.xu = xu;
	}
	
	public void biseccion() {
		promedio();
		System.out.println(this.xr);
		if(!isNegativo(xl)) {
			this.xl=this.xr;
		}
		System.out.println("xl: "+this.xl);
		if(!isNegativo(xu)){
			this.xu=this.xr;
		}
		System.out.println("xu: "+this.xu);
		System.out.println(errorPorcentual());
		if(this.errorParo<errorPorcentual()) {
			this.iteracciones+=1;
			this.erroresPorcentuales.add(this.xr);
			biseccion();
		}
	}
	
	public void falsa_posicion() {
		xrPosicion();
		if(!isNegativo(xl)) {
			this.xl=this.xr;
		}
		System.out.println("xl: "+this.xl);
		if(!isNegativo(xu)){
			this.xu=this.xr;
		}
		System.out.println("xu: "+this.xu);
		System.out.println(errorPorcentual());
		if(this.errorParo<errorPorcentual()) {
			
			this.erroresPorcentuales.add(this.xr);
			falsa_posicion();
		}
	}
	
	private void promedio() {
		this.xr = (this.xl+this.xu)/2;
	}
	

	private Float function(float x) {
		return (float) ((667.78/x)*(1-Math.exp(-1*(x/68.1)*10))-40);
	}
	
	private void xrPosicion() {
		this.xr = -1*function(this.xl)*(this.xu-this.xl);
		this.xr /= (function(this.xu)-function(this.xl));
		this.xr += this.xl;
	}
	
	public Boolean isNegativo(Float x) {
		return function(x)*function(this.xr)<0;
	}
	
	public Boolean isNegativo(Float x1, Float x2) {
		return function(x1)*function(x2)<0;
	}
	
	private Float errorPorcentual() {
		if(erroresPorcentuales.size()==0) {
			return (float) 1;
		}
		else {
			return Math.abs(this.xr-erroresPorcentuales.get(this.erroresPorcentuales.size()-1))/this.xr;
		}
	}

	public Integer getIteracciones() {
		// TODO Auto-generated method stub
		return this.iteracciones;
	}
}
