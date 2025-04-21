package gfutria.view;

import java.util.Scanner;

public class Consola {
    private Scanner sc = new Scanner(System.in);

    public static void mostrarMensaje (String msj){
        System.out.println(msj);
    }

    public static int pedirInt (){
        Consola c = new Consola();
        int numero = c.sc.nextInt();
        c.sc.nextLine(); // Limpiar el buffer
        return numero;
    }

    public static String pedirString (){
        Consola c = new Consola();
        String texto = c.sc.nextLine();
        return texto;
    }

}