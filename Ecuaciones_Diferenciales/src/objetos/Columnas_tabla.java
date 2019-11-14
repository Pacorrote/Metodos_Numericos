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
public class Columnas_tabla {
    
    private double x;
    private double yi;
    private double y;
    private double error;
    private double predictor;
    private double corrector;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getYi() {
        return yi;
    }

    public void setYi(double yi) {
        this.yi = yi;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

	public double getPredictor() {
		return predictor;
	}

	public void setPredictor(double predictor) {
		this.predictor = predictor;
	}

	public double getCorrector() {
		return corrector;
	}

	public void setCorrector(double corrector) {
		this.corrector = corrector;
	}
    
}
