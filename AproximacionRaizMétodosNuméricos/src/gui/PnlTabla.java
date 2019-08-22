package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import objetos.ColumnasNewtonRaphson;
import objetos.ModelTabla1;

public class PnlTabla extends JPanel{
	
	private JTable tabla;
	private ModelTabla1 modeloTabla;
	

	public PnlTabla() {
		// TODO Auto-generated constructor stub
	}
        
        public void crearModelo(ArrayList<ColumnasNewtonRaphson> lista){
            this.modeloTabla = new ModelTabla1(lista);
        }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if(aFlag){
            updateUI();
            setPreferredSize(new Dimension(550, 400));
            tabla = new JTable(this.modeloTabla);
            tabla.setFont(new Font("Verdana", 0, 18));
            tabla.setShowGrid(true);
            tabla.setForeground(Color.ORANGE);
            
            JScrollPane jS = new JScrollPane(tabla);
            jS.setPreferredSize(new Dimension(540, 467));
            add(jS);
            setBackground(Color.WHITE);
            updateUI();
        }
        else{
            removeAll();
            updateUI();
        }
        //To change body of generated methods, choose Tools | Templates.
    }

    public ModelTabla1 getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(ModelTabla1 modeloTabla) {
        this.modeloTabla = modeloTabla;
    }
    
    
        
        

}
