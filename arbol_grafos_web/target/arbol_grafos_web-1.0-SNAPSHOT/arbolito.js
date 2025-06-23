// Clase Nodo
class Nodo {
    constructor(valor) {
        this.valor = valor;
        this.izquierda = null;
        this.derecha = null;
    }
}

// Clase Árbol Binario
class ArbolBinario {
    constructor() {
        this.raiz = null;
    }

    agregar(valor) {
        this.raiz = this._agregarRecursivo(this.raiz, valor);
    }

    _agregarRecursivo(nodo, valor) {
        if (nodo === null) {
            return new Nodo(valor);
        }
        if (valor < nodo.valor) {
            nodo.izquierda = this._agregarRecursivo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = this._agregarRecursivo(nodo.derecha, valor);
        } else {
            alert("El valor ya existe en el árbol");
        }
        return nodo;
    }

    buscar(valor) {
        return this._buscarRecursivo(this.raiz, valor);
    }

    _buscarRecursivo(nodo, valor) {
        if (nodo === null) {
            return false;
        }
        if (valor === nodo.valor) {
            return true;
        }
        if (valor < nodo.valor) {
            return this._buscarRecursivo(nodo.izquierda, valor);
        } else {
            return this._buscarRecursivo(nodo.derecha, valor);
        }
    }

    eliminar(valor) {
        this.raiz = this._eliminarRecursivo(this.raiz, valor);
    }

    _eliminarRecursivo(nodo, valor) {
        if (nodo === null) {
            return null;
        }
        if (valor < nodo.valor) {
            nodo.izquierda = this._eliminarRecursivo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = this._eliminarRecursivo(nodo.derecha, valor);
        } else {
            // Nodo encontrado
            if (nodo.izquierda === null && nodo.derecha === null) {
                return null;
            }
            if (nodo.izquierda === null) {
                return nodo.derecha;
            }
            if (nodo.derecha === null) {
                return nodo.izquierda;
            }
            // Dos hijos: reemplazar con el menor del subárbol derecho
            let sucesor = this._minValor(nodo.derecha);
            nodo.valor = sucesor.valor;
            nodo.derecha = this._eliminarRecursivo(nodo.derecha, sucesor.valor);
        }
        return nodo;
    }

    _minValor(nodo) {
        while (nodo.izquierda !== null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }

    inorden() {
        let resultado = [];
        this._inordenRecursivo(this.raiz, resultado);
        return resultado.join(", ");
    }

    _inordenRecursivo(nodo, resultado) {
        if (nodo !== null) {
            this._inordenRecursivo(nodo.izquierda, resultado);
            resultado.push(nodo.valor);
            this._inordenRecursivo(nodo.derecha, resultado);
        }
    }
}

// Instancia del árbol
const arbol = new ArbolBinario();

// Conexión con el DOM
document.getElementById('btnAgregar').addEventListener('click', () => {
    const numero = parseInt(document.getElementById('numero').value);
    if (isNaN(numero)) {
        alert("Ingrese un número válido");
    } else {
        arbol.agregar(numero);
        alert(`Número ${numero} agregado al árbol`);
    }
});

document.getElementById('btnBuscar').addEventListener('click', () => {
    const numero = parseInt(document.getElementById('numero').value);
    if (isNaN(numero)) {
        alert("Ingrese un número válido");
    } else {
        const encontrado = arbol.buscar(numero);
        alert(encontrado ? `Número ${numero} encontrado` : `Número ${numero} no está en el árbol`);
    }
});

document.getElementById('btnEliminar').addEventListener('click', () => {
    const numero = parseInt(document.getElementById('numero').value);
    if (isNaN(numero)) {
        alert("Ingrese un número válido");
    } else {
        arbol.eliminar(numero);
        alert(`Número ${numero} eliminado (si existía)`);
    }
});

document.getElementById('btnMostrar').addEventListener('click', () => {
    const resultado = arbol.inorden();
    alert(`Árbol inorden: ${resultado}`);
});