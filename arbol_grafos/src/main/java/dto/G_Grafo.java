package dto;

import java.util.List;

public class G_Grafo {
    private List<G_Nodo_grafo> nodos;
    private List<G_Arista> aristas;

    public G_Grafo(List<G_Nodo_grafo> nodos, List<G_Arista> aristas) {
        this.nodos = nodos;
        this.aristas = aristas;
    }

    public List<G_Nodo_grafo> getNodos() {
        return nodos;
    }

    public void setNodos(List<G_Nodo_grafo> nodos) {
        this.nodos = nodos;
    }

    public List<G_Arista> getAristas() {
        return aristas;
    }

    public void setAristas(List<G_Arista> aristas) {
        this.aristas = aristas;
    }
    
    @Override
    public String toString() {
        return "Grafo{\nNodos=" + nodos + ", \nAristas=" + aristas + '}';
    }
}
