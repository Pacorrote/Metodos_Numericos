package ecuaciones;

import gui.InterfazPrin;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Ecuaciones {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new InterfazPrin();
	}

}
