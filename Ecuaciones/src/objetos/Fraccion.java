package objetos;

public class Fraccion {
	
	private Integer numerador;
	private Integer denominador;
	public static final int [] PRIMOS = {2, 
			3, 5, 7, 11, 13, 
			17, 19, 23, 29, 
			31, 37, 41, 43, 
			47, 53, 59, 61, 
			67, 71, 73, 79, 
			83, 89, 97};
	
	public Fraccion(Integer numerador, Integer denominador) throws ArithmeticException{
		// TODO Auto-generated constructor stub
		this.numerador = numerador < 0 && denominador < 0 ? -numerador: numerador;
		if(denominador != 0) {
			this.denominador = denominador < 0 && numerador < 0 ? -denominador: denominador;
		}
		else {
			throw new ArithmeticException("Denominador igual a cero");
		}
		this.simplificar();
	}
	
	public Fraccion(Fraccion fraccion) {
		this.numerador = fraccion.numerador;
		if(fraccion.denominador != 0) {
			this.denominador=fraccion.denominador;
		}
		else {
			throw new ArithmeticException("Denominador igual a cero");
		}
		this.simplificar();
	}
	
	public void setNumerador(Integer numerador) {
		this.numerador = numerador;
	}
	
	public void setDenominador(Integer denominador) {
		this.denominador = denominador;
	}
	
	public Integer getNumerador() {
		return numerador;
	}
	
	public Integer getDenominador() {
		return denominador;
	}
	
	@Override
	public String toString() {
		return denominador == 1 ? String.format("%d", numerador) :
			denominador == numerador ? Integer.toString(1) : 
			numerador == 0 ? Integer.toString(0) :
			String.format("%d/%d", this.denominador < 0? - this.numerador : 
				this.numerador, this.denominador < 0 ? - this.denominador : this.denominador);
	}
	
	public void simplificar() {
		int a = 0;
		do {
			if(getNumerador()%PRIMOS[a]==0 && getDenominador()%PRIMOS[a]==0) {
				setNumerador(getNumerador() / PRIMOS[a]);
				setDenominador(getDenominador() / PRIMOS[a]);
			}
			else {
				a++;
			}
		} while (a < PRIMOS.length);
	}
	
//	public Double toDecimal() {
//		return  (double)(this.numerador) / (double) (this.denominador);
//	}
        
//        public static Double toDecimal(Fraccion fraccion) {
//		return  (double)(fraccion.numerador) / (double) (fraccion.denominador);
//	}
//	
	public Float toDecimal() {
		return  (float)(this.numerador) / (float) (this.denominador);
	}
        
//        public static Float toDecimal(Fraccion fraccion) {
//		return  (float)(fraccion.numerador) / (float) (fraccion.denominador);
//	}
	
	public void agregar(Fraccion f1) {
		this.setNumerador(this.getNumerador()*f1.getDenominador()+this.denominador*f1.getNumerador());
		this.setDenominador(this.getDenominador()*f1.getDenominador());
		this.simplificar();
	}
	
	public static Fraccion sumar(Fraccion f1, Fraccion f2) {
		Fraccion r = new Fraccion(
					f1.getNumerador()*f2.getDenominador()+f1.getDenominador()*f2.getNumerador(),
					f1.getDenominador()*f2.getDenominador()
				);
		r.simplificar();
		return r;
	}
	
	public static Fraccion restar(Fraccion f1, Fraccion f2) {
		Fraccion r = new Fraccion(
				f1.getNumerador()*f2.getDenominador()-f1.getDenominador()*f2.getNumerador(),
				f1.getDenominador()*f2.getDenominador()
			);
		r.simplificar();
		return r;
	}
	
	public static Fraccion multiplicar(Fraccion f1, Fraccion f2) {
		Fraccion r = new Fraccion(
				f1.getNumerador()*f2.getNumerador(),
				f1.getDenominador()*f2.getDenominador()
			);
		r.simplificar();
		return r;
	}
	
	public static Fraccion dividir(Fraccion f1, Fraccion f2) {
		Fraccion r = new Fraccion(
				f1.getNumerador()*f2.getDenominador(),
				f1.getDenominador()*f2.getNumerador()
			);
		r.simplificar();
		return r;
	}
        
        public static Fraccion potencia(Fraccion fraccion, int potencia){
            Fraccion r = new Fraccion(
                                (int) Math.pow(fraccion.getNumerador(), potencia),
                               (int) Math.pow(fraccion.getDenominador(), potencia)
                        );
            r.simplificar();
            return r;
        }
        
        public static Fraccion raizCuad(Fraccion fraccion){
            Fraccion r = new Fraccion(
                                (int) Math.sqrt(fraccion.getNumerador()),
                                (int) Math.sqrt(fraccion.getDenominador())
                        );
            return r;
        }
        
        public static Fraccion raizCubica(Fraccion fraccion){
            Fraccion r = new Fraccion(
                                (int) Math.cbrt(fraccion.getNumerador()),
                                (int) Math.cbrt(fraccion.getDenominador())
                        );
            return r;
        }
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Fraccion) {
			Fraccion tmp = (Fraccion) obj;
			return this.getNumerador()*tmp.getDenominador() == this.getDenominador()*tmp.getNumerador();
		}
		else {
			return false;
		}
	}
	
	
}
