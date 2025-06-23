
package dto;
import dto.AB_Usuario;

public class AB_NodoBinario {
    public AB_Usuario dato;          
    public AB_NodoBinario izquierda; 
    public AB_NodoBinario derecha;   
    
    public AB_NodoBinario(AB_Usuario usuario) {
        this.dato = usuario;
        this.izquierda = null;
        this.derecha = null;
    }

   
    public AB_Usuario getDato() {
        return dato;
    }

    public String getNombreUsuario() {
        return dato.getNombre();
    }

    public String getTelefonoUsuario() {
        return dato.getTelefono();
    }

    public AB_NodoBinario getIzquierda() {
        return izquierda;
    }

    public AB_NodoBinario getDerecha() {
        return derecha;
    }

    public void setDato(AB_Usuario dato) {
        this.dato = dato;
    }

    public void setIzquierda(AB_NodoBinario izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(AB_NodoBinario derecha) {
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