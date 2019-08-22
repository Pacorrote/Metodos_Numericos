package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import interfaces.Clickeable;

public class PnlInsercion extends JPanel{
    
        private JLabel lblPuntoI;
	private JLabel lblError;
	private JButton btnCalcular;
	private JTextField txtPuntoI;
	private JTextField txtError;
        private Clickeable fuego;

    public Clickeable getFuego() {
        return fuego;
    }

    public void setFuego(Clickeable fuego) {
        this.fuego = fuego;
    }

    public JTextField getTxtPuntoI() {
        return txtPuntoI;
    }

    public void setTxtPuntoI(JTextField txtPuntoI) {
        this.txtPuntoI = txtPuntoI;
    }

    public JTextField getTxtError() {
        return txtError;
    }

    public void setTxtError(JTextField txtError) {
        this.txtError = txtError;
    }
    
    
    
	public PnlInsercion() {
		// TODO Auto-generated constructor stub
                this.lblPuntoI = new JLabel("Punto inicial");
		this.lblError = new JLabel("Margen de error");
		this.btnCalcular = new JButton("Calcular");
                this.btnCalcular.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         //To change body of generated methods, choose Tools | Templates.
                         fuego.buscarRaiz();
                    }
                });
		this.txtPuntoI = new JTextField(8);
		this.txtError = new JTextField(8);
		Font fuente = new Font("Verdana", 0, 14);
                this.lblPuntoI.setFont(fuente);
		this.lblError.setFont(fuente);
		this.btnCalcular.setFont(fuente);
		this.txtPuntoI.setFont(fuente);
		this.txtError.setFont(fuente);
                super.add(this.lblPuntoI);
		super.add(this.txtPuntoI);
		super.add(this.lblError);
		super.add(this.txtError);
		super.add(this.btnCalcular);
		super.setBackground(new Color(70, 168, 194));
		super.setLayout(new FlowLayout(FlowLayout.CENTER));
		super.setPreferredSize(new Dimension(550, 50));
		super.setVisible(true);
	}

}
