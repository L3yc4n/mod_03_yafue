package gui;

import dto.Usuario;
import dao.arbol;

//public class FraArbol extends arbol {
//    public static void main(String[] args) {
//        FraArbol arbol = new FraArbol();
//        
//        // Usamos los métodos heredados directamente
//        arbol.agregar(new Usuario("Carlos", "123456789"));  
//        arbol.agregar(new Usuario("Ana", "987654321"));
//        arbol.agregar(new Usuario("Zoe", "555555555"));
//        arbol.agregar(new Usuario("Beto", "111111111"));
//        
//        arbol.imprimirInOrden();
//    }
//}


import java.util.Scanner;

public class FraArbol {
    private arbol arbolUsuarios = new arbol();

    public static void main(String[] args) {
        FraArbol programa = new FraArbol();
        programa.ejecutar();
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Mostrar usuarios");
            System.out.println("3. Salir");
            System.out.print("Seleccione: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            
            switch(opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    
                    arbolUsuarios.agregar(new Usuario(nombre, telefono));
                    System.out.println("Usuario agregado!");
                    break;
                    
                case 2:
                    System.out.println("\n--- USUARIOS ---");
                    arbolUsuarios.imprimirInOrden();
                    break;
                    
                case 3:
                    System.out.println("Saliendo...");
                    break;
                    
                default:
                    System.out.println("Opción inválida");
            }
        } while(opcion != 3);
        
      //  scanner.close();
    }
}