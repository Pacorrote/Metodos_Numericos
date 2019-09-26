package gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCoefFracc extends JPanel{
	
	public static final GridLayout DISENNO = new GridLayout(3, 1, 4, 4);
	private JTextField txtNumerador = new JTextField(4);
	private JLabel rayita = new JLabel("/");
	private JTextField txtDenominador = new JTextField(4);

	public PanelCoefFracc() {
		// TODO Auto-generated constructor stub
		rayita.setFont(new Font("Futura", Font.BOLD, 20));
		txtNumerador.setHorizontalAlignment(JTextField.RIGHT);
		txtDenominador.setHorizontalAlignment(JTextField.RIGHT);
		super.add(txtNumerador);
		super.add(rayita);
		super.add(txtDenominador);
	}

	public JTextField getTxtNumerador() {
		return txtNumerador;
	}

	public void setTxtNumerador(JTextField txtNumerador) {
		this.txtNumerador = txtNumerador;
	}

	public JTextField getTxtDenominador() {
		return txtDenominador;
	}

	public void setTxtDenominador(JTextField txtDenominador) {
		this.txtDenominador = txtDenominador;
	}
	
	public void setTxtNumeradorTex(String text) {
		this.txtNumerador.setText(text);
	}
	
	public void setTxtDenominadorTex(String text) {
		this.txtDenominador.setText(text);
	}
	
	public String getTxtNumeradorText() {
		return txtNumerador.getText();
	}
	
	public String getTxtDenominadorText() {
		return txtDenominador.getText();
	}

}
