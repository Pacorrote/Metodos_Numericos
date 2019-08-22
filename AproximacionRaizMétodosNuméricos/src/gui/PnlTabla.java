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
	

	public PnlTabla(ArrayList<ColumnasNewtonRaphson> lista) {
		// TODO Auto-generated constructor stub
		this.modeloTabla = new ModelTabla1(lista);
		super.setPreferredSize(new Dimension(550, 400));
		tabla = new JTable(this.modeloTabla);
		tabla.setFont(new Font("Verdana", 0, 18));
		tabla.setShowGrid(true);
		tabla.setForeground(new Color(117, 78, 231));
		JScrollPane jS = new JScrollPane(tabla);
		jS.setPreferredSize(new Dimension(540, 467));
		super.add(jS);
		super.setBackground(Color.WHITE);
		super.setVisible(false);
		updateUI();
	}

}
