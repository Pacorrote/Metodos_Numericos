package gui;

import interfaces.Clickeable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PnlAbajo extends JPanel{
	
	private JButton limpiar;
        private Clickeable click;
	
	public PnlAbajo() {
		// TODO Auto-generated constructor stub
		super.setBackground(new Color(23, 144, 15));
		super.setLayout(null);
		limpiar = new JButton("Limpiar");
                limpiar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        click.limpiar(); //To change body of generated methods, choose Tools | Templates.
                    }
                });
		super.setPreferredSize(new Dimension(550, 50));
		limpiar.setBounds(445, 15, 80, 21);
		super.add(limpiar);
		super.setVisible(true);
	}

    public Clickeable getClick() {
        return click;
    }

    public void setClick(Clickeable click) {
        this.click = click;
    }
        
        

}
