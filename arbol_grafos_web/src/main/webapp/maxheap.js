
 class MaxHeap {
      constructor() {
        this.heap = [];
      }

      insert(valor) {
        this.heap.push(valor);
        this.subir();
      }

      subir() {
        let i = this.heap.length - 1;
        while (i > 0) {
          let padre = Math.floor((i - 1) / 2);
          if (this.heap[i] <= this.heap[padre]) break;
          [this.heap[i], this.heap[padre]] = [this.heap[padre], this.heap[i]];
          i = padre;
        }
      }

      extractMax() {
        if (this.heap.length === 0) return null;
        const max = this.heap[0];
        const fin = this.heap.pop();
        if (this.heap.length > 0) {
          this.heap[0] = fin;
          this.bajar(0);
        }
        return max;
      }

      bajar(i) {
        const n = this.heap.length;
        const elem = this.heap[i];
        while (true) {
          let izq = 2 * i + 1;
          let der = 2 * i + 2;
          let mayor = i;

          if (izq < n && this.heap[izq] > this.heap[mayor]) mayor = izq;
          if (der < n && this.heap[der] > this.heap[mayor]) mayor = der;

          if (mayor === i) break;
          [this.heap[i], this.heap[mayor]] = [this.heap[mayor], this.heap[i]];
          i = mayor;
        }
      }

      getHeap() {
        return this.heap;
      }
    }

    const heap = new MaxHeap();

    function insertar() {
      const input = document.getElementById("valor");
      const valor = parseInt(input.value);
      if (!isNaN(valor)) {
        heap.insert(valor);
        input.value = "";
        mostrarHeap();
      }
    }

    function extraer() {
      const max = heap.extractMax();
      if (max !== null) {
        alert("Máximo extraído: " + max);
        mostrarHeap();
      }
    }

    function mostrarHeap() {
      const div = document.getElementById("heapDisplay");
      div.innerHTML = heap.getHeap().join(" | ");
    }

