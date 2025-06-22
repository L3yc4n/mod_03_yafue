package gui;

import dto.Usuario;
import dao.arbol;

public class FraArbol extends arbol {
    public static void main(String[] args) {
        FraArbol arbol = new FraArbol();
        
        // Usamos los métodos heredados directamente
        arbol.agregar(new Usuario("Carlos", "123456789"));  
        arbol.agregar(new Usuario("Ana", "987654321"));
        arbol.agregar(new Usuario("Zoe", "555555555"));
        arbol.agregar(new Usuario("Beto", "111111111"));
        
        arbol.imprimirInOrden();
    }
}