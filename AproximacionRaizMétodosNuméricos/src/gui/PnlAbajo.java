package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PnlAbajo extends JPanel{
	
	private JButton limpiar;
	
	public PnlAbajo() {
		// TODO Auto-generated constructor stub
		super.setBackground(new Color(23, 144, 15));
		super.setLayout(null);
		limpiar = new JButton("Limpiar");
		super.setPreferredSize(new Dimension(550, 50));
		limpiar.setBounds(445, 15, 80, 21);
		super.add(limpiar);
		super.setVisible(true);
	}

}
