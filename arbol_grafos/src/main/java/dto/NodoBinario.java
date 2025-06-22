
package dto;
import dto.Usuario;

public class NodoBinario {
    public Usuario dato;          
    public NodoBinario izquierda; 
    public NodoBinario derecha;   
    
    public NodoBinario(Usuario usuario) {
        this.dato = usuario;
        this.izquierda = null;
        this.derecha = null;
    }

   
    public Usuario getDato() {
        return dato;
    }

    public String getNombreUsuario() {
        return dato.getNombre();
    }

    public String getTelefonoUsuario() {
        return dato.getTelefono();
    }

    public NodoBinario getIzquierda() {
        return izquierda;
    }

    public NodoBinario getDerecha() {
        return derecha;
    }

    public void setDato(Usuario dato) {
        this.dato = dato;
    }

    public void setIzquierda(NodoBinario izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(NodoBinario derecha) {
        this.derecha = derecha;
    }

    public boolean esHoja() {
        return (izquierda == null && derecha == null);
    }

    public boolean tieneIzquierda() {
        return izquierda != null;
    }

    public boolean tieneDerecha() {
        return derecha != null;
    }

    @Override
    public String toString() {
        return dato.toString();
    }
}