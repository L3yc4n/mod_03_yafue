
    class Nodo {
      constructor(valor){
        this.valor = valor;
        this.izq = null;
        this.der = null;
        this.altura = 1;
        this.contador = 1;
      }
    }

    class ArbolAVL {
      constructor() {
        this.raiz = null;
      }

      altura(nodo) {
        return nodo ? nodo.altura : 0;
      }

      FE(nodo) {
        return this.altura(nodo.der) - this.altura(nodo.izq);
      }

      giroIzquierda(nodo) {
        const aux = nodo.der;
        nodo.der = aux.izq;
        aux.izq = nodo;

        nodo.altura = 1 + Math.max(this.altura(nodo.izq), this.altura(nodo.der));
        aux.altura = 1 + Math.max(this.altura(aux.izq), this.altura(aux.der));

        return aux;
      }

      giroDerecha(nodo) {
        const aux = nodo.izq;
        nodo.izq = aux.der;
        aux.der = nodo;

        nodo.altura = 1 + Math.max(this.altura(nodo.izq), this.altura(nodo.der));
        aux.altura = 1 + Math.max(this.altura(aux.izq), this.altura(aux.der));

        return aux;
      }

      equilibrar(nodo) {
        if (this.FE(nodo) > 1) {
          if (this.FE(nodo.der) < 0) {
            nodo.der = this.giroDerecha(nodo.der);
          }
          nodo = this.giroIzquierda(nodo);
        } else if (this.FE(nodo) < -1) {
          if (this.FE(nodo.izq) > 0) {
            nodo.izq = this.giroIzquierda(nodo.izq);
          }
          nodo = this.giroDerecha(nodo);
        }
        return nodo;
      }

      agregar(nodo, valor) {
        if (!nodo) return new Nodo(valor);

        if (valor < nodo.valor) {
          nodo.izq = this.agregar(nodo.izq, valor);
        } else if (valor > nodo.valor) {
          nodo.der = this.agregar(nodo.der, valor);
        } else {
          nodo.contador++;
        }

        nodo.altura = 1 + Math.max(this.altura(nodo.izq), this.altura(nodo.der));
        return this.equilibrar(nodo);
      }

//      preorden(nodo, nivel = 0, salida = []) {
//        if (nodo) {
//          salida.push(`${'  '.repeat(nivel)}${nodo.valor} (Altura: ${nodo.altura}, Contador: ${nodo.contador})`);
//          this.preorden(nodo.izq, nivel + 1, salida);
//          this.preorden(nodo.der, nivel + 1, salida);
//        }
//        return salida;
//      }

inorden(nodo, nivel = 0, salida = []) {
  if (nodo) {
    this.inorden(nodo.izq, nivel + 1, salida);  // 1. Izquierda
    salida.push(`${'  '.repeat(nivel)}${nodo.valor} (Altura: ${nodo.altura}, Contador: ${nodo.contador})`);  // 2. Actual
    this.inorden(nodo.der, nivel + 1, salida);  // 3. Derecha
  }
  return salida;
}



    }

    const arbol = new ArbolAVL();

    function insertar() {
      const valor = parseInt(document.getElementById('valor').value);
      if (!isNaN(valor)) {
        arbol.raiz = arbol.agregar(arbol.raiz, valor);
//        const resultado = arbol.preorden(arbol.raiz).join('\n');
//        document.getElementById('salida').textContent = resultado;
const resultado = arbol.inorden(arbol.raiz).join('\n');
document.getElementById('salida').textContent = resultado;
      }
    }

