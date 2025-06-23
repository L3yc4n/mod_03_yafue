package dto;

public class G_Arista {
    private G_Nodo_grafo origen;
    private G_Nodo_grafo destino;
    private int peso;

    public G_Arista(G_Nodo_grafo origen, G_Nodo_grafo destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public G_Arista(G_Nodo_grafo origen, G_Nodo_grafo destino) {
        this(origen, destino, 1);
    }

    public G_Nodo_grafo getOrigen() {
        return origen;
    }

    public void setOrigen(G_Nodo_grafo origen) {
        this.origen = origen;
    }

    public G_Nodo_grafo getDestino() {
        return destino;
    }

    public void setDestino(G_Nodo_grafo destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    // Para depuración o mostrar aristas
    @Override
    public String toString() {
        return "\nArista{Origen=" + origen.getEtiqueta() +
               ", Dest.=" + destino.getEtiqueta() +
               ", Peso=" + peso + "}";
    }
}
