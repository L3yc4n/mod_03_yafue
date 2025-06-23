package dao;

import dto.G_Arista;
import dto.G_Grafo;
import dto.G_Nodo_grafo;
import java.util.*;

public class grafo_dk {
    private G_Grafo grafo;

    public grafo_dk(G_Grafo grafo) {
        this.grafo = grafo;
    }

    // Algoritmo de Dijkstra
    public void dijkstra(G_Nodo_grafo origen) {
        Map<G_Nodo_grafo, Integer> distancias = new HashMap<>();
        Map<G_Nodo_grafo, G_Nodo_grafo> predecesores = new HashMap<>();
        PriorityQueue<G_Nodo_grafo> cola = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        // Inicialización
        for (G_Nodo_grafo nodo : grafo.getNodos()) {
            distancias.put(nodo, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            G_Nodo_grafo actual = cola.poll();

            for (G_Arista arista : grafo.getAristas()) {
                if (arista.getOrigen().equals(actual)) {
                    G_Nodo_grafo vecino = arista.getDestino();
                    int nuevoDist = distancias.get(actual) + arista.getPeso();

                    if (nuevoDist < distancias.get(vecino)) {
                        distancias.put(vecino, nuevoDist);
                        predecesores.put(vecino, actual);
                        cola.add(vecino);
                    }
                }
            }
        }

        // Mostrar resultados
        System.out.println("Distancias desde " + origen.getEtiqueta() + ":");
        for (G_Nodo_grafo nodo : grafo.getNodos()) {
            System.out.println(nodo.getEtiqueta() + " -> " + distancias.get(nodo));
        }
    }

    // Algoritmo de Kruskal
    public void kruskal() {
        List<G_Arista> aristasOrdenadas = new ArrayList<>(grafo.getAristas());
        aristasOrdenadas.sort(Comparator.comparingInt(G_Arista::getPeso));

        Map<G_Nodo_grafo, G_Nodo_grafo> padre = new HashMap<>();
        for (G_Nodo_grafo nodo : grafo.getNodos()) {
            padre.put(nodo, nodo);
        }

        List<G_Arista> mst = new ArrayList<>();

        for (G_Arista arista : aristasOrdenadas) {
            G_Nodo_grafo u = arista.getOrigen();
            G_Nodo_grafo v = arista.getDestino();

            if (!enMismoConjunto(u, v, padre)) {
                mst.add(arista);
                union(u, v, padre);
            }
        }

        // Mostrar MST
        System.out.println("Árbol de expansión mínima (Kruskal):");
        for (G_Arista arista : mst) {
            System.out.println(arista.getOrigen().getEtiqueta() + " - " +
                               arista.getDestino().getEtiqueta() +
                               " (peso: " + arista.getPeso() + ")");
        }
    }

    // Métodos auxiliares para Kruskal
    private G_Nodo_grafo encontrar(G_Nodo_grafo nodo, Map<G_Nodo_grafo, G_Nodo_grafo> padre) {
        if (!padre.get(nodo).equals(nodo)) {
            padre.put(nodo, encontrar(padre.get(nodo), padre)); // compresión de caminos
        }
        return padre.get(nodo);
    }

    private boolean enMismoConjunto(G_Nodo_grafo u, G_Nodo_grafo v, Map<G_Nodo_grafo, G_Nodo_grafo> padre) {
        return encontrar(u, padre).equals(encontrar(v, padre));
    }

    private void union(G_Nodo_grafo u, G_Nodo_grafo v, Map<G_Nodo_grafo, G_Nodo_grafo> padre) {
        G_Nodo_grafo raizU = encontrar(u, padre);
        G_Nodo_grafo raizV = encontrar(v, padre);
        padre.put(raizU, raizV);
    }
}