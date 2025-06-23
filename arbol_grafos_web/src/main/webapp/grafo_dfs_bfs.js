class Nodo {
    constructor(id) {
        this.id = id;
    }
}

class Arista {
    constructor(origen, destino) {
        this.origen = origen;
        this.destino = destino;
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

    eliminarNodo(id) {
        this.nodos = this.nodos.filter(n => n.id !== id);
        this.aristas = this.aristas.filter(a => a.origen !== id && a.destino !== id);
        alert(`Nodo ${id} eliminado`);
    }

    agregarArista(origen, destino) {
        if (this.nodos.find(n => n.id === origen) && this.nodos.find(n => n.id === destino)) {
            this.aristas.push(new Arista(origen, destino));
            alert(`Arista ${origen} → ${destino} agregada`);
        } else {
            alert(`Uno o ambos nodos no existen`);
        }
    }

    eliminarArista(origen, destino) {
        this.aristas = this.aristas.filter(a => !(a.origen === origen && a.destino === destino));
        alert(`Arista ${origen} → ${destino} eliminada`);
    }

    obtenerVecinos(id) {
        return this.aristas
            .filter(a => a.origen === id)
            .map(a => a.destino);
    }

    bfs(inicio) {
        if (!this.nodos.find(n => n.id === inicio)) {
            alert(`Nodo ${inicio} no existe`);
            return;
        }
        let visitados = new Set();
        let cola = [inicio];
        let resultado = 'BFS recorrido:\n';

        while (cola.length > 0) {
            let actual = cola.shift();
            if (!visitados.has(actual)) {
                visitados.add(actual);
                resultado += actual + ' ';
                 let vecinos = this.obtenerVecinos(actual).filter(v => !visitados.has(v));
                resultado += `${actual} -> ${vecinos.length > 0 ? vecinos.join(', ') : '(sin vecinos)'}\n`;
                vecinos.forEach(v => cola.push(v));
            }
        }
        document.getElementById("resultado").innerText = resultado;
    }

    dfs(inicio) {
        if (!this.nodos.find(n => n.id === inicio)) {
            alert(`Nodo ${inicio} no existe`);
            return;
        }
        let visitados = new Set();
        let resultado = 'DFS recorrido:\n';

        const dfsRec = (nodo) => {
            visitados.add(nodo);
            resultado += nodo + ' ';
            let vecinos = this.obtenerVecinos(nodo).filter(v => !visitados.has(v));
            resultado += `${nodo} -> ${vecinos.length > 0 ? vecinos.join(', ') : '(sin vecinos)'}\n`;
            vecinos.forEach(v => dfsRec(v));
        };

        dfsRec(inicio);
        document.getElementById("resultado").innerText = resultado;
    }

    mostrar() {
        let texto = 'Nodos: ' + this.nodos.map(n => n.id).join(', ') + '\n';
        texto += 'Aristas: ' + this.aristas.map(a => `${a.origen}→${a.destino}`).join(', ');
        document.getElementById("resultado").innerText = texto;
    }
}

// --- Conexión con el HTML ---
const grafo = new Grafo();

document.getElementById("btnAgregarN").addEventListener("click", () => {
    const id = document.getElementById("numero").value;
    grafo.agregarNodo(id);
});

document.getElementById("btnEliminarN").addEventListener("click", () => {
    const id = document.getElementById("numero").value;
    grafo.eliminarNodo(id);
});

document.getElementById("btnAgregarA").addEventListener("click", () => {
    const valores = document.getElementById("numero").value.split(",");
    if (valores.length === 2) {
        grafo.agregarArista(valores[0].trim(), valores[1].trim());
    } else {
        alert("Formato: origen,destino");
    }
});

document.getElementById("btnEliminarA").addEventListener("click", () => {
    const valores = document.getElementById("numero").value.split(",");
    if (valores.length === 2) {
        grafo.eliminarArista(valores[0].trim(), valores[1].trim());
    } else {
        alert("Formato: origen,destino");
    }
});

document.getElementById("btnBFS").addEventListener("click", () => {
    const id = document.getElementById("numero").value;
    grafo.bfs(id);
});

document.getElementById("btnDFS").addEventListener("click", () => {
    const id = document.getElementById("numero").value;
    grafo.dfs(id);
});

document.getElementById("btnMostrar").addEventListener("click", () => {
    grafo.mostrar();
});
