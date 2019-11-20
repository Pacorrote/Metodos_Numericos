/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PACO
 */
public class ModeloTablaPuntoMed extends AbstractTableModel{
    private ArrayList<Columnas_tabla> filas;
    private ArrayList<String> iteraciones;

    public ModeloTablaPuntoMed() {
        
    }

    public ModeloTablaPuntoMed(ArrayList<Columnas_tabla> filas, ArrayList<String> iteraciones) {
        this.filas = filas;
        this.iteraciones = iteraciones;
    }

    public ArrayList<Columnas_tabla> getFilas() {
        return filas;
    }

    public void setFilas(ArrayList<Columnas_tabla> filas) {
        this.filas = filas;
    }

    public ArrayList<String> getIteraciones() {
        return iteraciones;
    }

    public void setIteraciones(ArrayList<String> iteraciones) {
        this.iteraciones = iteraciones;
    }
    
        @Override
    public int getRowCount() {
        return filas == null ? 0 : filas.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return 5; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex){
            case 0:
                return iteraciones.get(rowIndex);
            case 1:
                return filas.get(rowIndex).getX();
            case 2:
                return filas.get(rowIndex).getYi();
            case 3:
                return filas.get(rowIndex).getY();
            case 4:
                return filas.get(rowIndex).getError();
            default:
                return "";
         }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "Iteración";
            case 1:
                return "x";
            case 2:
                return "ya";
            case 3:
                return "y";
            case 4:
                return "Error";
            default:
                return "";
        }
    }
}
