package gui;

import dao.grafo_dk;
import dto.G_Arista;
import dto.G_Grafo;
import dto.G_Nodo_grafo;
import java.util.*;

public class FraGrafo_dk {
    private Scanner scanner;
    private G_Grafo grafoDTO;
    private grafo_dk grafoDK;

    public FraGrafo_dk() {
        this.grafoDTO = new G_Grafo(new ArrayList<>(), new ArrayList<>());
        this.grafoDK = new grafo_dk(grafoDTO);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Elige una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    agregarNodo();
                    break;
                case 2:
                    agregarArista();
                    break;
                case 3:
                    ejecutarDijkstra();
                    break;
                case 4:
                    ejecutarKruskal();
                    break;
                case 5:
                    mostrarGrafo();
                    break;
                case 0:
                    System.out.println("Saliendo de GrafoDK...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menú GrafoDK ---");
        System.out.println("1. Agregar nodo");
        System.out.println("2. Agregar arista");
        System.out.println("3. Ejecutar Dijkstra");
        System.out.println("4. Ejecutar Kruskal");
        System.out.println("5. Mostrar grafo");
        System.out.println("0. Salir");
    }

    private void agregarNodo() {
        System.out.print("ID del nodo: ");
        int id = leerEntero();
        System.out.print("Etiqueta del nodo: ");
        String etiqueta = scanner.next();
        G_Nodo_grafo nodo = new G_Nodo_grafo(id, etiqueta);
        grafoDTO.getNodos().add(nodo);
        System.out.println("Nodo agregado.");
    }

    private void agregarArista() {
        System.out.print("ID del nodo origen: ");
        int idOrigen = leerEntero();
        System.out.print("ID del nodo destino: ");
        int idDestino = leerEntero();
        System.out.print("Peso de la arista: ");
        int peso = leerEntero();

        G_Nodo_grafo origen = buscarNodoPorId(idOrigen);
        G_Nodo_grafo destino = buscarNodoPorId(idDestino);

        if (origen != null && destino != null) {
            grafoDTO.getAristas().add(new G_Arista(origen, destino, peso));
            System.out.println("Arista agregada.");
        } else {
            System.out.println("Uno o ambos nodos no existen.");
        }
    }

    private void ejecutarDijkstra() {
        System.out.print("ID del nodo origen: ");
        int id = leerEntero();
        G_Nodo_grafo origen = buscarNodoPorId(id);
        if (origen != null) {
            grafoDK.dijkstra(origen);
        } else {
            System.out.println("Nodo no encontrado.");
        }
    }

    private void ejecutarKruskal() {
        grafoDK.kruskal();
    }

    private void mostrarGrafo() {
        System.out.println(grafoDTO);
    }

    private G_Nodo_grafo buscarNodoPorId(int id) {
        for (G_Nodo_grafo nodo : grafoDTO.getNodos()) {
            if (nodo.getId() == id) {
                return nodo;
            }
        }
        return null;
    }

    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingresa un número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        FraGrafo_dk app = new FraGrafo_dk();
        app.iniciar();
    }
}