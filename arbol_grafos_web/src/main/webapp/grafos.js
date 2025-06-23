class RedSocial {
      constructor() {
        this.grafo = {};
      }

      agregarUsuario(nombre) {
        if (!this.grafo[nombre]) {
          this.grafo[nombre] = [];
        }
      }

      agregarAmistad(u1, u2) {
        if (!this.grafo[u1].includes(u2)) {
          this.grafo[u1].push(u2);
          this.grafo[u2].push(u1);
        }
      }

      estanConectados(u1, u2) {
        let visitados = new Set();
        let cola = [[u1]];
        while (cola.length > 0) {
          let camino = cola.shift();
          let actual = camino[camino.length - 1];
          if (actual === u2) return camino;
          if (!visitados.has(actual)) {
            visitados.add(actual);
            for (let vecino of this.grafo[actual]) {
              if (!visitados.has(vecino)) {
                cola.push([...camino, vecino]);
              }
            }
          }
        }
        return null;
      }

      amigosEnComun(u1, u2) {
        const a1 = new Set(this.grafo[u1]);
        const a2 = new Set(this.grafo[u2]);
        return [...a1].filter(x => a2.has(x));
      }
    }

    const red = new RedSocial();
    let nodes = new vis.DataSet([]);
    let edges = new vis.DataSet([]);
    let network = null;

    function inicializarGrafo() {
      const container = document.getElementById('grafoRed');
      const data = { nodes: nodes, edges: edges };
      network = new vis.Network(container, data, {});
    }

    function actualizarGrafo() {
      nodes.clear();
      edges.clear();
      Object.keys(red.grafo).forEach(usuario => {
        nodes.add({ id: usuario, label: usuario });
      });
      const agregadas = new Set();
      Object.keys(red.grafo).forEach(u => {
        red.grafo[u].forEach(v => {
          const clave = [u, v].sort().join('-');
          if (!agregadas.has(clave)) {
            edges.add({ from: u, to: v });
            agregadas.add(clave);
          }
        });
      });
    }

    function actualizarSelects() {
      const usuarios = Object.keys(red.grafo);
      const ids = ["usuario1", "usuario2", "checkUsuario1", "checkUsuario2", "comunUsuario1", "comunUsuario2"];
      ids.forEach(id => {
        const select = document.getElementById(id);
        select.innerHTML = "";
        usuarios.forEach(u => {
          let opt = document.createElement("option");
          opt.value = opt.text = u;
          select.appendChild(opt);
        });
      });
    }

    function agregarUsuario() {
      const nombre = document.getElementById("nuevoUsuario").value.trim();
      if (nombre && !red.grafo[nombre]) {
        red.agregarUsuario(nombre);
        actualizarSelects();
        actualizarGrafo();
        document.getElementById("nuevoUsuario").value = "";
        alert("Usuario agregado.");
      } else {
        alert("Nombre inválido o ya existe.");
      }
    }

    function agregarAmistad() {
      const u1 = document.getElementById("usuario1").value;
      const u2 = document.getElementById("usuario2").value;
      if (u1 && u2 && u1 !== u2) {
        red.agregarAmistad(u1, u2);
        actualizarGrafo();
        alert("Amistad creada.");
      } else {
        alert("Selecciona usuarios distintos.");
      }
    }

    function verificarConexion() {
      const u1 = document.getElementById("checkUsuario1").value;
      const u2 = document.getElementById("checkUsuario2").value;
      const camino = red.estanConectados(u1, u2);
      const r = document.getElementById("resultadoConexion");
      if (camino) {
        r.innerText = `Conectados: ${camino.join(" → ")}`;
      } else {
        r.innerText = "No están conectados.";
      }
    }

    function buscarAmigosEnComun() {
      const u1 = document.getElementById("comunUsuario1").value;
      const u2 = document.getElementById("comunUsuario2").value;
      const comunes = red.amigosEnComun(u1, u2);
      document.getElementById("resultadoComun").innerText = comunes.length ? comunes.join(", ") : "No hay amigos en común.";
    }

    window.onload = inicializarGrafo;