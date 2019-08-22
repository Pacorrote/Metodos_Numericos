package main;

import gui.VentanaPrin1;
import objetos.Metodo_Newton_Raphson;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VentanaPrin1();
		Metodo_Newton_Raphson metodo = new Metodo_Newton_Raphson(0f, 0.01f);
		metodo.Metodo();
		metodo.imprimir();
		
	}

}
