package objetos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaHeun extends AbstractTableModel{
    
    private ArrayList<Columnas_tabla> filas;
    private ArrayList<String> iteraciones;

    public ModeloTablaHeun() {
    }

    public ModeloTablaHeun(ArrayList<Columnas_tabla> filas, ArrayList<String> iteraciones) {
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
        return 6; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex){
            case 0:
                return iteraciones.get(rowIndex);
            case 1:
                return filas.get(rowIndex).getX();
            case 2:
                return filas.get(rowIndex).getPredictor();
            case 3:
                return filas.get(rowIndex).getCorrector();
            case 4:
                return filas.get(rowIndex).getY();
            case 5:
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
                return "Predictor";
            case 3:
                return "Corrector";
            case 4:
                return "y exacta";
            case 5:
                return "Error";
            default:
                return "";
        }
    }
    
    
    
}
