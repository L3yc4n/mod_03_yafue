package gui;
import dao.grafo_dfs_bfs;
import dto.G_Grafo;
import dto.G_Nodo_grafo;
import java.util.*;

public class FraGrafo_dfs_bfs {
    private Scanner scanner;
    private grafo_dfs_bfs grafoDAO;
    private G_Grafo grafoDTO;

    public FraGrafo_dfs_bfs() {
        scanner = new Scanner(System.in);
        grafoDTO = new G_Grafo (new ArrayList<>(), new ArrayList<>());
        grafoDAO = new grafo_dfs_bfs (grafoDTO);
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
                    eliminarNodo();
                    break;
                case 4:
                    eliminarArista();
                    break;
                case 5:
                    recorrerBFS();
                    break;
                case 6:
                    recorrerDFS();
                    break;
                case 7:
                    grafoDAO.mostrarGrafo();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menú Grafo ---");
        System.out.println("1. Agregar nodo");
        System.out.println("2. Agregar arista");
        System.out.println("3. Eliminar nodo");
        System.out.println("4. Eliminar arista");
        System.out.println("5. Recorrido BFS");
        System.out.println("6. Recorrido DFS");
        System.out.println("7. Mostrar grafo");
        System.out.println("0. Salir");
    }

    private void agregarNodo() {
        System.out.print("ID del nodo: ");
        int id = leerEntero();
        System.out.print("Etiqueta: ");
        String etiqueta = scanner.next();
        G_Nodo_grafo nodo = new G_Nodo_grafo (id, etiqueta);
        grafoDAO.agregarNodo(nodo);
        System.out.println("Nodo agregado.");
    }

    private void agregarArista() {
        System.out.print("ID del nodo origen: ");
        int idOrigen = leerEntero();
        System.out.print("ID del nodo destino: ");
        int idDestino = leerEntero();
        System.out.print("Peso (opcional, ingresa 1 si no hay peso): ");
        int peso = leerEntero();

        G_Nodo_grafo origen = buscarNodoPorId(idOrigen);
        G_Nodo_grafo destino = buscarNodoPorId(idDestino);

        if (origen != null && destino != null) {
            grafoDAO.agregarArista(origen, destino, peso);
            System.out.println("Arista agregada.");
        } else {
            System.out.println("Uno o ambos nodos no existen.");
        }
    }

    private void eliminarNodo() {
        System.out.print("ID del nodo a eliminar: ");
        int id = leerEntero();
        G_Nodo_grafo nodo = buscarNodoPorId(id);
        if (nodo != null) {
            grafoDAO.eliminarNodo(nodo);
            System.out.println("Nodo eliminado.");
        } else {
            System.out.println("Nodo no encontrado.");
        }
    }

    private void eliminarArista() {
        System.out.print("ID del nodo origen: ");
        int idOrigen = leerEntero();
        System.out.print("ID del nodo destino: ");
        int idDestino = leerEntero();

        G_Nodo_grafo origen = buscarNodoPorId(idOrigen);
        G_Nodo_grafo destino = buscarNodoPorId(idDestino);

        if (origen != null && destino != null) {
            grafoDAO.eliminarArista(origen, destino);
            System.out.println("Arista eliminada.");
        } else {
            System.out.println("Uno o ambos nodos no existen.");
        }
    }

    private void recorrerBFS() {
        System.out.print("ID del nodo inicio: ");
        int id = leerEntero();
        G_Nodo_grafo inicio = buscarNodoPorId(id);
        if (inicio != null) {
            grafoDAO.bfs(inicio);
        } else {
            System.out.println("Nodo no encontrado.");
        }
    }

    private void recorrerDFS() {
        System.out.print("ID del nodo inicio: ");
        int id = leerEntero();
        G_Nodo_grafo inicio = buscarNodoPorId(id);
        if (inicio != null) {
            grafoDAO.dfs(inicio);
        } else {
            System.out.println("Nodo no encontrado.");
        }
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
            System.out.print("Por favor, ingresa un número: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        FraGrafo_dfs_bfs app = new FraGrafo_dfs_bfs();
        app.iniciar();
    }
}

