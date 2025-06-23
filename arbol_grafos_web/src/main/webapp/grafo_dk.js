class Nodo {
    constructor(id) {
        this.id = id;
    }
}

class Arista {
    constructor(origen, destino, peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
}

class Grafo {
    constructor() {
        this.nodos = [];
        this.aristas = [];
    }

    agregarNodo(id) {
        if (!this.nodos.find(n => n.id === id)) {
            this.nodos.push(new Nodo(id));
            alert(`Nodo ${id} agregado`);
        } else {
            alert(`Nodo ${id} ya existe`);
        }
    }

    agregarArista(origen, destino, peso) {
        if (this.nodos.find(n => n.id === origen) && this.nodos.find(n => n.id === destino)) {
            this.aristas.push(new Arista(origen, destino, peso));
            alert(`Arista ${origen} → ${destino} con peso ${peso} agregada`);
        } else {
            alert(`Uno o ambos nodos no existen`);
        }
    }

    mostrar() {
        let texto = 'Nodos:\n';
        texto += this.nodos.map(n => `- ${n.id}`).join('\n') + '\n';
        texto += '\nAristas:\n';
        texto += this.aristas
                    .map(a => `${a.origen} → ${a.destino} (peso ${a.peso})`)
                    .join('\n');
        document.getElementById("resultado").innerText = texto;
    }

    dijkstra(inicio) {
        if (!this.nodos.find(n => n.id === inicio)) {
            alert(`Nodo ${inicio} no existe`);
            return;
        }

        let dist = {};
        let visitados = new Set();
        this.nodos.forEach(n => dist[n.id] = Infinity);
        dist[inicio] = 0;

        let resultado = `Dijkstra desde ${inicio}:\n`;

        while (visitados.size < this.nodos.length) {
            let noVisitados = this.nodos
                .filter(n => !visitados.has(n.id));
            let actual = noVisitados.reduce((a, b) => dist[a.id] < dist[b.id] ? a : b);
            
            visitados.add(actual.id);
            resultado += `Visitando ${actual.id} (distancia: ${dist[actual.id]})\n`;

            this.aristas
                .filter(a => a.origen === actual.id)
                .forEach(a => {
                    if (dist[a.destino] > dist[actual.id] + a.peso) {
                        dist[a.destino] = dist[actual.id] + a.peso;
                        resultado += `  Actualizando ${a.destino}: nueva distancia ${dist[a.destino]}\n`;
                    }
                });
        }

        resultado += '\nDistancias finales:\n';
        Object.keys(dist).forEach(k => {
            resultado += `- ${inicio} → ${k}: ${dist[k]}\n`;
        });

        document.getElementById("resultado").innerText = resultado;
    }

    kruskal() {
        let resultado = 'Kruskal (MST):\n';
        let parent = {};
        this.nodos.forEach(n => parent[n.id] = n.id);

        const find = (u) => {
            if (parent[u] === u) return u;
            parent[u] = find(parent[u]); // compresión de camino
            return parent[u];
        };

        const union = (u, v) => {
            parent[find(u)] = find(v);
        };

        let aristasOrdenadas = [...this.aristas].sort((a, b) => a.peso - b.peso);
        let mst = [];

        aristasOrdenadas.forEach(a => {
            let u = find(a.origen);
            let v = find(a.destino);
            if (u !== v) {
                mst.push(a);
                union(u, v);
                resultado += `- ${a.origen} → ${a.destino} (peso ${a.peso})\n`;
            }
        });

        document.getElementById("resultado").innerText = resultado;
    }
}

// --- Conexión con el HTML ---
const grafo = new Grafo();

document.getElementById("btnAgregarN").addEventListener("click", () => {
    const id = document.getElementById("numero").value.trim();
    grafo.agregarNodo(id);
});

document.getElementById("btnAgregarA").addEventListener("click", () => {
    const valores = document.getElementById("numero").value.split(",");
    if (valores.length === 3) {
        const origen = valores[0].trim();
        const destino = valores[1].trim();
        const peso = parseInt(valores[2].trim());
        if (!isNaN(peso)) {
            grafo.agregarArista(origen, destino, peso);
        } else {
            alert("El peso debe ser un número");
        }
    } else {
        alert("Formato: origen,destino,peso");
    }
});

document.getElementById("btnDijkstra").addEventListener("click", () => {
    const id = document.getElementById("numero").value.trim();
    grafo.dijkstra(id);
});

document.getElementById("btnKruskal").addEventListener("click", () => {
    grafo.kruskal();
});

document.getElementById("btnMostrar").addEventListener("click", () => {
    grafo.mostrar();
});
