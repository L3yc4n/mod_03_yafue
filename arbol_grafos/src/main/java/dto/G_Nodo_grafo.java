package dto;

public class G_Nodo_grafo {
    private int id;
    private String etiqueta;

    public G_Nodo_grafo(int id, String etiqueta) {
        this.id = id;
        this.etiqueta = etiqueta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    // Para depuración o mostrar nodos
    @Override
    public String toString() {
        return "\nNodo{id=" + id + ", etiqueta='" + etiqueta + "'}";
    }
}
