package dto;

public class AVL_Nodo {
   private int valor;
   private  AVL_Nodo derecha;
   private AVL_Nodo izquierda;
   private int contador;
   private int altura;
   
    public AVL_Nodo(int valor){
        this.valor=valor;
        derecha=null;
        izquierda=null;
        contador=1;
        altura=1;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public AVL_Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(AVL_Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public AVL_Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(AVL_Nodo derecha) {
        this.derecha = derecha;
    }

    public int getContador() {
        return contador;
    }

    public void incrementarContador() {
        this.contador++;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
