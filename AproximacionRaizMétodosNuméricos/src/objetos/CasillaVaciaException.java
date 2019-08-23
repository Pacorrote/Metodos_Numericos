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
public class CasillaVaciaException extends Exception {

    /**
     * Creates a new instance of <code>CasillaVaciaException</code> without
     * detail message.
     */
    public CasillaVaciaException() {
        super("Una de las casillas estan vacias\nIntente de nuevo");
    }

    /**
     * Constructs an instance of <code>CasillaVaciaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CasillaVaciaException(String msg) {
        super(msg);
    }
}
