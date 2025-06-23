
package gui;

import dto.AB_Usuario;
import dao.Arbol;

//public class FraArbol extends arbol {
//    public static void main(String[] args) {
//        FraArbol arbol = new FraArbol();
//        
//        // Usamos los métodos heredados directamente
//        arbol.agregar(new AB_Usuario("Carlos", "123456789"));  
//        arbol.agregar(new AB_Usuario("Ana", "987654321"));
//        arbol.agregar(new AB_Usuario("Zoe", "555555555"));
//        arbol.agregar(new AB_Usuario("Beto", "111111111"));
//        
//        arbol.imprimirInOrden();
//    }
//}


import java.util.Scanner;


public class FraArbol {
    private Arbol arbolUsuarios = new Arbol();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new FraArbol().iniciar();
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Buscar usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Mostrar todos");
            System.out.println("5. Salir");
            System.out.print("Seleccione: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch(opcion) {
                case 1:
                    agregarUsuario();
                    break;
                case 2:
                    buscarUsuario();
                    break;
                case 3:
                    eliminarUsuario();
                    break;
                case 4:
                    mostrarUsuarios();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while(opcion != 5);
    }

    private void agregarUsuario() {
        System.out.println("\n--- AGREGAR USUARIO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        arbolUsuarios.agregar(new AB_Usuario(nombre, telefono));
        System.out.println("Usuario agregado!");
    }

    private void buscarUsuario() {
        System.out.println("\n--- BUSCAR USUARIO ---");
        System.out.print("Ingrese nombre a buscar: ");
        String nombre = scanner.nextLine();
        
        AB_Usuario encontrado = arbolUsuarios.buscar(nombre);
        
        if(encontrado != null) {
            System.out.println("Usuario encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    private void eliminarUsuario() {
        System.out.println("\n--- ELIMINAR USUARIO ---");
        System.out.print("Ingrese nombre a eliminar: ");
        String nombre = scanner.nextLine();
        
        if(arbolUsuarios.eliminar(nombre)) {
            System.out.println("Usuario eliminado exitosamente");
        } else {
            System.out.println("No se pudo eliminar el usuario");
        }
    }

    private void mostrarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        arbolUsuarios.imprimirInOrden();
    }
}