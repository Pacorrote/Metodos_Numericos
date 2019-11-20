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
public class ModeloTabla extends AbstractTableModel{
    
    private ArrayList<Double> valores;
    private ArrayList<ArrayList<Double>> filas;
    private Boolean primeraDev = false;
    private Boolean segundaDev = false;

    public ModeloTabla(ArrayList<Double> valores) {
        this.valores = valores;
    }


    public ArrayList<Double> getValores() {
        return valores;
    }

    public void setValores(ArrayList<Double> valores) {
        this.valores = valores;
    }

    public ArrayList<ArrayList<Double>> getFilas() {
        return filas;
    }

    public void setFilas(ArrayList<ArrayList<Double>> filas) {
        this.filas = filas;
    }

    public Boolean getPrimeraDev() {
        return primeraDev;
    }

    public void setPrimeraDev(Boolean primeraDev) {
        this.primeraDev = primeraDev;
    }

    public Boolean getSegundaDev() {
        return segundaDev;
    }

    public void setSegundaDev(Boolean segundaDev) {
        this.segundaDev = segundaDev;
    }
    
    @Override
    public int getRowCount() {
        return valores != null ? 1: 4; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return 26; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(valores == null && filas == null){
            return "";
        }
        else{
            if(valores == null){
                if(primeraDev){
                    if(columnIndex < 11 || columnIndex > 23){
                        return filas.get(rowIndex).get(columnIndex);
                    }
                    else{
                        return "";
                    }
                }
                else if(segundaDev){
                    if(columnIndex > 11 && columnIndex < 24){
                        return filas.get(rowIndex).get(columnIndex);
                    }
                    else{
                        return "";
                    }
                }
                else{
                    return "";
                }
            }
            else{
                return valores.get(columnIndex);  
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "<html><center>Hacia adelante<br>grado 1";
            case 1:
                return "Error";
            case 2:
                return "<html><center>Hacia adelante<br>grado 2";
            case 3:
                return "Error";
            case 4:
                return "<html><center>Hacia atras<br>grado 1";
            case 5:
                return "Error";
            case 6:
                return "<html><center>Hacia atras<br>grado 2";
            case 7:
                return "Error";
            case 8:
                return "Centrado grado 1";
            case 9:
                return "Error";
            case 10:
                return "Centrado grado 2";
            case 11:
                return "Error";
            case 12:
                return "<html><center>Segunda derivada Hacia adelante<br>grado 1";
            case 13:
                return "Error";
            case 14:
                return "<html><center>Segunda derivada Hacia adelante<br>grado 2";
            case 15:
                return "Error";
            case 16:
                return "<html><center>Segunda derivada Hacia atras<br>grado 1";
            case 17:
                return "Error";
            case 18:
                return "<html><center>Segunda derivada Hacia atras<br>grado 2";
            case 19:
                return "Error";
            case 20:
                return "<html><center>Segunda derivada <br> Centrado grado 1";
            case 21:
                return "Error";
            case 22:
                return "<html><center>Segunda derivada <br> Centrado grado 2";
            case 23:
                return "Error";
            case 24:
                return "Derivada";
            case 25:
                return "Segunda derivada";
            default:
                return "";
        }
    }
    
    
}
