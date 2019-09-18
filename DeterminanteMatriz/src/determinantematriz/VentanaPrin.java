/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determinantematriz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author PACO
 */
public class VentanaPrin extends JFrame{
    private JPanel panelArriba = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 12));
    private JTextField txtColumnasYFilas = new JTextField(2);
    private JButton aceptar = new JButton("Aceptar");
    private JPanel pnlMedio = new JPanel(new BorderLayout(10, 10));
    private JPanel pnlCalcular = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JButton calcular = new JButton("Calcular determinante");
    private PanelMatriz pnlmatriz = new PanelMatriz();
    private JScrollPane jSP = new JScrollPane(pnlmatriz,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public VentanaPrin() {
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(new Dimension(400,400));
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout(10, 10));
        panelArriba.setBackground(Color.CYAN);
        panelArriba.add(txtColumnasYFilas);
        txtColumnasYFilas.setHorizontalAlignment(JTextField.RIGHT);
        panelArriba.add(aceptar);
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int colfil = Integer.parseInt(txtColumnasYFilas.getText());
               pnlmatriz.inicializarComponentes(colfil);
               pnlmatriz.setVisible(true);
            }
        });
        panelArriba.setPreferredSize(new Dimension(400, 50));
        pnlCalcular.add(calcular);
        pnlMedio.add(pnlCalcular, BorderLayout.SOUTH);
        pnlMedio.add(jSP, BorderLayout.CENTER);
        super.add(panelArriba, BorderLayout.NORTH);
        super.add(pnlMedio, BorderLayout.CENTER);
        super.setVisible(true);
    }
    
    
    
}
