package dao;
import dto.NodoBinario;
import dto.Usuario;
import java.util.ArrayList;
import java.util.List;

//class Usuario { 
//    private String nombre;
//    private String telefono;
//
//    public Usuario(String nombre, String telefono) {
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

//class NodoBinario {
//    Usuario dato;  
//    NodoBinario izquierda;
//    NodoBinario derecha;
//
//    public NodoBinario(Usuario usuario) {  
//        this.dato = usuario;
//        this.izquierda = null;
//        this.derecha = null;
//    }
//}

public class arbol {
    private NodoBinario raiz;

    public arbol() {
        this.raiz = null;
    }

    public void agregar(Usuario u) { 
        if (u == null || u.getNombre().trim().isEmpty() || 
            u.getTelefono().trim().isEmpty()) {
            System.out.println("No se puede agregar un usuario con nombre o teléfono vacío.");
            return;
        }

        NodoBinario nuevo = new NodoBinario(u);

        if (raiz == null) {
            raiz = nuevo;
            System.out.println("El usuario \"" + u.getNombre() + "\" agregado correctamente (raíz).");
            return;
        }

        NodoBinario auxiliar = raiz;
        NodoBinario padre;

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

    private void inOrden(NodoBinario r, List<String> resultado) {
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
}

