/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumasriemann;

import enums.TipoSuma;
import java.util.InputMismatchException;
import java.util.Scanner;
import objetos.IntegralesDefinidasPolinomica;
import objetos.Monomio;
import objetos.Polinomios;
import objetos.SumaRiemann;
import objetos.SumaRiemannPoli;
import objetos.SumaTrapecio;
import objetos.SumaTrapecioPoli;

/**
 *
 * @author PACO
 */
public class SumasRiemann {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Polinomios polinomios = new Polinomios();
//        System.out.println("Suma inf: " + new SumaRiemann(0f, 3f, 20, TipoSuma.SumaInf).sumaRiemann());
//        System.out.println("Suma sup: " + new SumaRiemann(0f, 3f, 20, TipoSuma.SumaSup).sumaRiemann());
//        System.out.println("Suma punto medio: " + new SumaRiemann(0f, 3f, 20, TipoSuma.SumaMedia).sumaRiemann());
//        System.out.println("Suma trapecio: " + new SumaTrapecio(0f, 3f, 20, null).sumaTrapecio());
        Scanner sc = new Scanner(System.in);
        Boolean salir = false;
        do {            
            try {
                System.out.println("Ingresa coeficiente y potencia del monomio:");
                polinomios.addMonomio(new Monomio(sc.nextInt(), sc.nextInt()));
            } catch (InputMismatchException e) {
                salir = true;
                sc.nextLine();
            }
        } while (!salir);
//        polinomios.addMonomio(new Monomio(1, 4));
//        polinomios.addMonomio(new Monomio(-3, 2));
//        polinomios.addMonomio(new Monomio(7, 0));
        
        salir = false;
        do {            
            try {
                System.out.println("Ingrese limite inferior, limite superior y el numero de particiones");
                int liInf  = sc.nextInt();
                int liSup = sc.nextInt();
                int n = sc.nextInt();
            //for (int n = 1; n <= 30; n++) {
                System.out.println("Suma inf: " + new SumaRiemannPoli(polinomios, (float)liInf, (float)liSup, n, TipoSuma.SumaInf).sumaRiemann());
                System.out.println("Suma sup: " + new SumaRiemannPoli(polinomios, (float)liInf, (float)liSup, n, TipoSuma.SumaSup).sumaRiemann());
                System.out.println("Suma puntos medios: " + new SumaRiemannPoli(polinomios, (float)liInf, (float)liSup, n, TipoSuma.SumaMedia).sumaRiemann());
                System.out.println("Suma trapecio: " + new SumaTrapecioPoli(polinomios, (float)liInf, (float)liSup, n, null).sumaTrapecio());
                System.out.println("Simpson 1/3: " + simpson13(polinomios, liInf, liSup, n));
                System.out.println("Simpson 3/8: " + simpson38(polinomios, liInf, liSup, n));
                System.out.println("Integral definida del polinomio: " + (IntegralesDefinidasPolinomica.integralDefinida(polinomios, liInf, liSup)));
                System.out.println("----------------------------------------------------------------------------------------------------------------");
//            System.out.print(new SumaRiemannPoli(polinomios, (float)liInf, (float)liSup, n, TipoSuma.SumaInf).sumaRiemann());
//            System.out.print("\t" + new SumaRiemannPoli(polinomios, (float)liInf, (float)liSup, n, TipoSuma.SumaSup).sumaRiemann());
//            System.out.print("\t" + new SumaRiemannPoli(polinomios, (float)liInf, (float)liSup, n, TipoSuma.SumaMedia).sumaRiemann());
//            System.out.print("\t" + new SumaTrapecioPoli(polinomios, (float)liInf, (float)liSup, n, null).sumaTrapecio());
//            System.out.print("\t" + simpson13(polinomios, liInf, liSup, n));
//            System.out.print("\t" + simpson38(polinomios, liInf, liSup, n));
//            System.out.print("\t" + (IntegralesDefinidasPolinomica.integralDefinida(polinomios, liInf, liSup)));
            //System.out.println("");
            //sc.nextLine();
        //}
            } catch (InputMismatchException e) {
                salir = true;
                
            }
        } while (!salir);
        
        
        
    }
    
    public static Double simpson13(double liInf, double liSup, int  n){
        double h;
        double a = 0;
        double paso = Math.abs(liInf - liSup) / n;
        for (double i = liInf; i < liSup; i+=paso) {
            h=((i+paso)-i)/2;
            if ((i+paso)<3) {
                a += h/3*(Math.exp(i)+4*Math.exp((i+(i+paso))/2)+Math.exp(i+paso));
            }else{
                break;
            }
        }
        return a;
    }
    


    public static Double simpson38(double liInf, double liSup, int n){
        double h1;
        double a1 = 0;
        double h2;
        double h3;
        double paso = Math.abs(liInf - liSup) / n;
        for (double i = liInf; i < liSup; i+=paso) {
            h1=((i+paso)-i)/3;
            h2=(2*i+(i+paso))/3;
            h3=(i+2*(i+paso))/3;
            if ((i+paso)<3) {
                a1+=((0.375)*h1)*(Math.exp(i)+3*Math.exp(h2)+3*Math.exp(h3)+Math.exp(i+paso));
            }else{
                break;
            }
        }
        return a1;   
    }
    
    public static Double simpson13(Polinomios polinomio, double liInf, double liSup, int  n){
        double h;
        double a = 0;
        double paso = Math.abs(liInf - liSup) / n;
        for (double i = liInf; i < liSup; i+=paso) {
            h=((i+paso)-i)/2;
            if ((i+paso)<3) {
                a += h/3*(SumaRiemannPoli.valorPx(polinomio, (float) i)+4*SumaRiemannPoli.valorPx(polinomio, (float) (i+(i+paso))/2)+SumaRiemannPoli.valorPx(polinomio,(float) i + (float) paso));
            }else{
                break;
            }
        }
        return a;
    }
    


    public static Double simpson38(Polinomios polinomio, double liInf, double liSup, int n){
        double h1;
        double a1 = 0;
        double h2;
        double h3;
        double paso = Math.abs(liInf - liSup) / n;
        for (double i = liInf; i < liSup; i+=paso) {
            h1=((i+paso)-i)/3;
            h2=(2*i+(i+paso))/3;
            h3=(i+2*(i+paso))/3;
            if ((i+paso)<3) {
                a1+=((0.375)*h1)*(SumaTrapecioPoli.valorPx(polinomio, (float) i)+
                    3*SumaRiemannPoli.valorPx(polinomio,(float) h2)+
                    3*SumaRiemannPoli.valorPx(polinomio, (float) h3)+
                    SumaRiemannPoli.valorPx(polinomio, (float) i+ (float) paso));
            }else{
                break;
            }
        }
        return a1;   
    }
}
