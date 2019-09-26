package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelProcedimiento extends JPanel{
	
	private ArrayList <String> matrices = new ArrayList<>();
	private JLabel procesos [];
	public static final GridLayout DISENNO = new GridLayout();
	public static final Font FUENTE = new Font("Verdana", 0, 20);

	public PanelProcedimiento(ArrayList<String> matrices) {
		
		this.matrices = matrices;
		procesos = new JLabel[this.matrices.size()];
		DISENNO.setRows(this.matrices.size());
		DISENNO.setColumns(this.matrices.size());
		DISENNO.setHgap(12);
		DISENNO.setVgap(22);
		super.setLayout(DISENNO);
		super.setBackground(Color.WHITE);
		//super.setPreferredSize(new Dimension(540, 400));
		for (int i = 0; i < procesos.length; i++) {
			procesos[i] = new JLabel(this.matrices.get(i));
			procesos[i].setFont(FUENTE);
			procesos[i].setForeground(new Color(19, 231, 19));
			super.add(procesos[i]);
		}
		
	}

	public PanelProcedimiento() {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.WHITE);
		//super.setPreferredSize(new Dimension(540, 350));
		
	}

	@Override
	public void setVisible(boolean aFlag) {
		// TODO Auto-generated method stub
		super.setVisible(aFlag);
		if (aFlag) {
			if(this.matrices != null) {
				procesos = new JLabel[this.matrices.size()];
				DISENNO.setRows(this.matrices.size());
				DISENNO.setColumns(this.matrices.size());
				DISENNO.setHgap(12);
				DISENNO.setVgap(22);
				super.setLayout(DISENNO);
				for (int i = 0; i < procesos.length; i++) {
					procesos[i] = new JLabel(this.matrices.get(i));
					procesos[i].setFont(FUENTE);
					procesos[i].setForeground(new Color(19, 231, 19));
					procesos[i].setHorizontalTextPosition(JLabel.CENTER);
					procesos[i].setHorizontalAlignment(JLabel.CENTER);
					super.add(procesos[i]);
				}
			}
			updateUI();
		}
	}

	public void setMatrices(ArrayList<String> matrices) {
		this.matrices = matrices;
	}

}
