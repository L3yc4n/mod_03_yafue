
class RedSocial {
  constructor() {
    this.grafo = {};
  }

  agregarUsuario(nombre) {
    if (!this.grafo[nombre]) {
      this.grafo[nombre] = [];
      return true;
    }
    return false;
  }

  agregarAmistad(u1, u2) {
    if (u1 === u2) return false;
    if (!this.grafo[u1] || !this.grafo[u2]) return false;
    if (!this.grafo[u1].includes(u2)) {
      this.grafo[u1].push(u2);
      this.grafo[u2].push(u1);
      return true;
    }
    return false;
  }

  obtenerAmigos(usuario) {
    return this.grafo[usuario] || [];
  }

  amigosEnComun(u1, u2) {
    if (!this.grafo[u1] || !this.grafo[u2]) return [];
    const amigos1 = new Set(this.grafo[u1]);
    const amigos2 = new Set(this.grafo[u2]);
    return [...amigos1].filter(amigo => amigos2.has(amigo));
  }

  estanConectados(u1, u2) {
    if (!this.grafo[u1] || !this.grafo[u2]) return false;
    
    let visitados = new Set();
    let cola = [u1];
    
    while (cola.length > 0) {
      let actual = cola.shift();
      if (actual === u2) return true;
      if (!visitados.has(actual)) {
        visitados.add(actual);
        this.grafo[actual].forEach(vecino => {
          if (!visitados.has(vecino)) cola.push(vecino);
        });
      }
    }
    return false;
  }

  mostrarRed() {
    let resultado = "Usuarios y sus conexiones:\n";
    for (const usuario in this.grafo) {
      resultado += `${usuario}: ${this.grafo[usuario].join(', ')}\n`;
    }
    return resultado;
  }
}

const red = new RedSocial();

function mostrarResultado(texto) {
  document.getElementById("resultado").innerText = texto;
}

function agregarUsuario() {
  const nombre = document.getElementById("usuarioInput").value.trim();
  if (nombre) {
    if (red.agregarUsuario(nombre)) {
      mostrarResultado(`Usuario "${nombre}" agregado exitosamente.`);
    } else {
      mostrarResultado(`Error: El usuario "${nombre}" ya existe.`);
    }
  } else {
    mostrarResultado("Error: Nombre de usuario no válido.");
  }
  document.getElementById("usuarioInput").value = "";
}

function agregarAmistad() {
  const u1 = document.getElementById("usuarioA").value.trim();
  const u2 = document.getElementById("usuarioB").value.trim();
  
  if (u1 && u2) {
    if (red.agregarAmistad(u1, u2)) {
      mostrarResultado(`Amistad entre "${u1}" y "${u2}" creada.`);
    } else {
      mostrarResultado(`Error: No se pudo crear la amistad (¿usuarios no existen o ya son amigos?).`);
    }
  } else {
    mostrarResultado("Error: Debe ingresar dos nombres de usuario.");
  }
}

function mostrarRed() {
  mostrarResultado(red.mostrarRed());
}

function buscarAmigos() {
  const usuario = document.getElementById("usuarioA").value.trim();
  if (usuario) {
    const amigos = red.obtenerAmigos(usuario);
    if (amigos.length > 0) {
      mostrarResultado(`Amigos de "${usuario}": ${amigos.join(', ')}`);
    } else {
      mostrarResultado(`"${usuario}" no tiene amigos o no existe.`);
    }
  } else {
    mostrarResultado("Error: Ingrese un nombre de usuario.");
  }
}

function amigosComunes() {
  const u1 = document.getElementById("usuarioA").value.trim();
  const u2 = document.getElementById("usuarioB").value.trim();
  
  if (u1 && u2) {
    const comunes = red.amigosEnComun(u1, u2);
    if (comunes.length > 0) {
      mostrarResultado(`Amigos comunes entre "${u1}" y "${u2}": ${comunes.join(', ')}`);
    } else {
      mostrarResultado(`No hay amigos comunes entre "${u1}" y "${u2}".`);
    }
  } else {
    mostrarResultado("Error: Ingrese dos nombres de usuario.");
  }
}

function verificarConexion() {
  const u1 = document.getElementById("usuarioA").value.trim();
  const u2 = document.getElementById("usuarioB").value.trim();
  
  if (u1 && u2) {
    if (red.estanConectados(u1, u2)) {
      mostrarResultado(`"${u1}" y "${u2}" están conectados.`);
    } else {
      mostrarResultado(`"${u1}" y "${u2}" NO están conectados.`);
    }
  } else {
    mostrarResultado("Error: Ingrese dos nombres de usuario.");
  }
}
