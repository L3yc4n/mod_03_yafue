package dao;
import dto.G_Arista;
import dto.G_Grafo;
import dto.G_Nodo_grafo;
import java.util.*;

public class grafo_dfs_bfs {
    private G_Grafo grafo;

    public grafo_dfs_bfs(G_Grafo grafo) {
        this.grafo = grafo;
    }

    // Agregar nodo
    public void agregarNodo(G_Nodo_grafo nodo) {
        grafo.getNodos().add(nodo);
    }

    // Agregar arista
    public void agregarArista(G_Nodo_grafo origen, G_Nodo_grafo destino, int peso) {
        grafo.getAristas().add(new G_Arista(origen, destino, peso));
    }

    // Sobrecarga para arista no ponderada (peso = 1)
    public void agregarArista(G_Nodo_grafo origen, G_Nodo_grafo destino) {
        grafo.getAristas().add(new G_Arista(origen, destino));
    }

    // Eliminar nodo
    public void eliminarNodo(G_Nodo_grafo nodo) {
        grafo.getNodos().remove(nodo);
        // Quitar aristas relacionadas
        grafo.getAristas().removeIf(a -> a.getOrigen().equals(nodo) || a.getDestino().equals(nodo));
    }

    // Eliminar arista
    public void eliminarArista(G_Nodo_grafo origen, G_Nodo_grafo destino) {
        grafo.getAristas().removeIf(a -> a.getOrigen().equals(origen) && a.getDestino().equals(destino));
    }

    // Recorrido BFS
    public void bfs(G_Nodo_grafo inicio) {
        Set<G_Nodo_grafo> visitados = new HashSet<>();
        Queue<G_Nodo_grafo> cola = new LinkedList<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            G_Nodo_grafo actual = cola.poll();
            System.out.println("Visitando: " + actual);

            for (G_Nodo_grafo vecino : obtenerVecinos(actual)) {
                if (!visitados.contains(vecino)) {
                    cola.add(vecino);
                    visitados.add(vecino);
                }
            }
        }
    }

    // Recorrido DFS
    public void dfs(G_Nodo_grafo inicio) {
        Set<G_Nodo_grafo> visitados = new HashSet<>();
        dfsRecursivo(inicio, visitados);
    }

    private void dfsRecursivo(G_Nodo_grafo nodo, Set<G_Nodo_grafo> visitados) {
        if (visitados.contains(nodo)) return;

        visitados.add(nodo);
        System.out.println("Visitando: " + nodo);

        for (G_Nodo_grafo vecino : obtenerVecinos(nodo)) {
            dfsRecursivo(vecino, visitados);
        }
    }

    // Obtener vecinos de un nodo
    private List<G_Nodo_grafo> obtenerVecinos(G_Nodo_grafo nodo) {
        List<G_Nodo_grafo> vecinos = new ArrayList<>();
        for (G_Arista arista : grafo.getAristas()) {
            if (arista.getOrigen().equals(nodo)) {
                vecinos.add(arista.getDestino());
            }
            // Si el grafo es no dirigido, agrega el inverso
            // vecinos.add(arista.getOrigen()); si el nodo es destino
        }
        return vecinos;
    }

    // Mostrar el grafo
    public void mostrarGrafo() {
        System.out.println(grafo);
    }
}
