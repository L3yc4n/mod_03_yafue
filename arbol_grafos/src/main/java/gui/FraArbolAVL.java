package gui;

import dao.ArbolAVL;
import java.util.Scanner;

public class FraArbolAVL {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        
        int n, a;
        System.out.println("Imprime la cantidad de numeros del arbol:");
        n = leer.nextInt();
        
        ArbolAVL ar = new ArbolAVL();
        
        for(int i=0;i<n;i++){
            System.out.println((i+1)+".- ");
            a = leer.nextInt();
            ar.raiz = ar.agregar(ar.raiz, a);
        }
        System.out.println("ARBOL-AVL: \n");
        System.out.println("---------------------------------\n");
        System.out.println("Preorden: \n");
        ar.preorden(ar.raiz,0);
        System.out.println("Inorden: \n");
        ar.inorden(ar.raiz,0);
        System.out.println("Posorden: \n");
        ar.posorden(ar.raiz,0);
    }
}
