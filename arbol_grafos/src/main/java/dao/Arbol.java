package dao;
import dto.AB_NodoBinario;
import dto.AB_Usuario;
import java.util.ArrayList;
import java.util.List;

//class AB_Usuario { 
//    private String nombre;
//    private String telefono;
//
//    public AB_Usuario(String nombre, String telefono) {
//        this.nombre = nombre;
//        this.telefono = telefono;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public String getTelefono() {
//        return telefono;
//    }
//
//   // @Override
//    public String toString() {
//        return "Nombre: " + nombre + ", su teléfono es: " + telefono;
//    }
//}


//class AB_NodoBinario {
//    AB_Usuario dato;  
//    AB_NodoBinario izquierda;
//    AB_NodoBinario derecha;
//
//    public AB_NodoBinario(AB_Usuario usuario) {  
//        this.dato = usuario;
//        this.izquierda = null;
//        this.derecha = null;
//    }
//}

public class Arbol {
    private AB_NodoBinario raiz;

    public Arbol() {
        this.raiz = null;
    }

    public void agregar(AB_Usuario u) { 
        if (u == null || u.getNombre().trim().isEmpty() || 
            u.getTelefono().trim().isEmpty()) {
            System.out.println("No se puede agregar un usuario con nombre o teléfono vacío.");
            return;
        }

        AB_NodoBinario nuevo = new AB_NodoBinario(u);

        if (raiz == null) {
            raiz = nuevo;
            System.out.println("El usuario \"" + u.getNombre() + "\" agregado correctamente (raíz).");
            return;
        }

        AB_NodoBinario auxiliar = raiz;
        AB_NodoBinario padre;

        while (true) {
            padre = auxiliar;

            int comparacion = u.getNombre().compareTo(auxiliar.dato.getNombre());

            if (comparacion < 0) {
                auxiliar = auxiliar.izquierda;
                if (auxiliar == null) {
                    padre.izquierda = nuevo;
                    System.out.println("El usuario \"" + u.getNombre() + "\" agregado correctamente a la izquierda.");
                    return;
                }
            } else if (comparacion > 0) {
                auxiliar = auxiliar.derecha;
                if (auxiliar == null) {
                    padre.derecha = nuevo;
                    System.out.println("El usuario \"" + u.getNombre() + "\" agregado correctamente a la derecha.");
                    return;
                }
            } else {
                System.out.println("El usuario con nombre \"" + u.getNombre() + "\" ya existe.");
                return;
            }
        }
    }

    public List<String> inOrden() {
        List<String> resultado = new ArrayList<>();
        inOrden(raiz, resultado);
        return resultado;
    }

    private void inOrden(AB_NodoBinario r, List<String> resultado) {
        if (r != null) {
            inOrden(r.izquierda, resultado);
            resultado.add(r.dato.toString());
            inOrden(r.derecha, resultado);
        }
    }

    public void imprimirInOrden() {
        System.out.println("---Recorrido en InOrden---");
        if (estaVacio()) {
            System.out.println("La agenda está vacía");
        } else {
            List<String> usuarios = inOrden();
            for (String usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    public boolean estaVacio() {
        return raiz == null;
    }
    
 public AB_Usuario buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private AB_Usuario buscarRecursivo(AB_NodoBinario nodo, String nombre) {
        if (nodo == null) {
            return null;
        }

        int comparacion = nombre.compareTo(nodo.dato.getNombre());
        
        if (comparacion < 0) {
            return buscarRecursivo(nodo.izquierda, nombre);
        } else if (comparacion > 0) {
            return buscarRecursivo(nodo.derecha, nombre);
        } else {
            return nodo.dato; // AB_Usuario encontrado
        }
    }
    
    
    public boolean eliminar(String nombre) {
        raiz = eliminarRecursivo(raiz, nombre);
        return raiz != null; // Retorna true si se eliminó
    }

    private AB_NodoBinario eliminarRecursivo(AB_NodoBinario nodo, String nombre) {
        if (nodo == null) {
            return null;
        }

        int comparacion = nombre.compareTo(nodo.dato.getNombre());
        
        if (comparacion < 0) {
            nodo.izquierda = eliminarRecursivo(nodo.izquierda, nombre);
        } else if (comparacion > 0) {
            nodo.derecha = eliminarRecursivo(nodo.derecha, nombre);
        } else {
            // Caso 1: Nodo sin hijos o con un solo hijo
            if (nodo.izquierda == null) {
                return nodo.derecha;
            } else if (nodo.derecha == null) {
                return nodo.izquierda;
            }
            
            // Caso 2: Nodo con dos hijos
            nodo.dato = encontrarMinimo(nodo.derecha);
            nodo.derecha = eliminarRecursivo(nodo.derecha, nodo.dato.getNombre());
        }
        return nodo;
    }

    private AB_Usuario encontrarMinimo(AB_NodoBinario nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo.dato;
    }

    
}

//public class Main {
//    public static void main(String[] args) {
//        ArbolBinario arbol = new ArbolBinario();
//        
//        arbol.agregar(new AB_Usuario("Carlos", "123456789"));
//        arbol.agregar(new AB_Usuario("Ana", "987654321"));
//        arbol.agregar(new AB_Usuario("Zoe", "555555555"));
//        arbol.agregar(new AB_Usuario("Beto", "111111111"));
//        
//        arbol.imprimirInOrden();
//    }
//}
// esto iria dentro de la llave