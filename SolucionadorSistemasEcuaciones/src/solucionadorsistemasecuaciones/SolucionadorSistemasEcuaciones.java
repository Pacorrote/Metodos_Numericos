/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucionadorsistemasecuaciones;

import gui.Frame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import objetos.MetodoGaussSeidel;
import objetos.MetodoJacobi;

/**
 *
 * @author PACO
 */
public class SolucionadorSistemasEcuaciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        // TODO code application logic here}
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        new Frame();
        Float matriz [][] = {
            {10f, -1f, 2f, 0f, 6f},
            {-1f, 11f, -1f, 3f, 25f},
            {2f, -1f, 10f, -1f, -11f},
            {-0f, 3f, -1f, 8f, 15f}
        };
        
        Float vectorInicial [] = {0f, 0f, 0f, 0f};
        
//        MetodoJacobi metodo = new MetodoJacobi(matriz, vectorInicial, 100, (float) 0.01);
//        metodo.imprimirQ();
//        metodo.imprimirR();
//        metodo.imprimirVectorResultado();
//        System.out.println("");
//        Float soluciones [] = metodo.metodoJacobi();

        MetodoGaussSeidel metodo = new MetodoGaussSeidel(matriz, vectorInicial, 100, (float) 0.01);
        metodo.imprimirQ();
        metodo.imprimirR();
        metodo.imprimirVectorResultado();
        System.out.println("");
        Float soluciones [] = metodo.metodoGaussSeidel();
    }
    
}
