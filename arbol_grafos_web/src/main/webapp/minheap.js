class MinHeap {
      constructor() {
        this.heap = [];
      }

      insert(val) {
        this.heap.push(val);
        this.bubbleUp();
      }

      bubbleUp() {
        let index = this.heap.length - 1;
        while (index > 0) {
          let parent = Math.floor((index - 1) / 2);
          if (this.heap[parent] <= this.heap[index]) break;
          [this.heap[parent], this.heap[index]] = [this.heap[index], this.heap[parent]];
          index = parent;
        }
      }

      extractMin() {
        const min = this.heap[0];
        const end = this.heap.pop();
        if (this.heap.length > 0) {
          this.heap[0] = end;
          this.sinkDown(0);
        }
        return min;
      }

      sinkDown(index) {
        const length = this.heap.length;
        const element = this.heap[index];
        while (true) {
          let left = 2 * index + 1;
          let right = 2 * index + 2;
          let swap = null;

          if (left < length && this.heap[left] < element) swap = left;
          if (right < length && this.heap[right] < (swap === null ? element : this.heap[swap])) swap = right;
          if (swap === null) break;

          [this.heap[index], this.heap[swap]] = [this.heap[swap], this.heap[index]];
          index = swap;
        }
      }

      getHeap() {
        return this.heap;
      }
    }

    const heap = new MinHeap();

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
      const min = heap.extractMin();
      if (min !== undefined) {
        alert("Mínimo extraído: " + min);
        mostrarHeap();
      }
    }

   function mostrarHeap() {
      const div = document.getElementById("heapDisplay");
      div.innerHTML = heap.getHeap().join(" | ");
    }

//    function mostrarHeap() {
//      const contenedor = document.getElementById("heapDisplay");
//      const heapArray = heap.getHeap();
//      let salida = "";
//      let nivel = 0;
//      let elementosEnNivel = 1;
//      let i = 0;
//
//      while (i < heapArray.length) {
//        let linea = "Nivel " + nivel + ": ";
//        for (let j = 0; j < elementosEnNivel && i < heapArray.length; j++, i++) {
//          linea += heapArray[i] + " ";
//        }
//        salida += linea.trim() + "\n";
//        nivel++;
//        elementosEnNivel *= 2;
//      }
//
//      contenedor.textContent = salida.trim();
//    }


