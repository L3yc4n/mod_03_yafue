package dao;

import dto.Nodo;

public class ArbolAVL {
    public Nodo raiz;
    
    public void ArbolAVL(){
        raiz = null;
    }
    
    public int altura(Nodo puntero){
        if(puntero == null){
            return 0;
        } else {
            return puntero.getAltura();
        }
    }
    
    public int FE(Nodo puntero){
        return altura(puntero.getDerecha()) - altura(puntero.getIzquierda());
    }
    
    public Nodo giroIzquierda(Nodo puntero){
        Nodo aux = puntero.getDerecha();
        puntero.setDerecha(aux.getIzquierda());
        aux.setIzquierda(puntero);
        
        puntero.setAltura(Math.max(altura(puntero.getIzquierda()),altura(puntero.getIzquierda()))+1);
        aux.setAltura(Math.max(altura(aux.getIzquierda()), altura(aux.getIzquierda()))+1);
        return aux;
    }
    
    public Nodo giroDerecha(Nodo puntero){
        Nodo aux = puntero.getIzquierda();
        puntero.setIzquierda(aux.getDerecha());
        aux.setDerecha(puntero);
        
        puntero.setAltura(Math.max(altura(puntero.getIzquierda()),altura(puntero.getIzquierda()))+1);
        aux.setAltura(Math.max(altura(aux.getIzquierda()), altura(aux.getIzquierda()))+1);
        return aux;
    }
    
    public Nodo equilibrar(Nodo puntero){
        if (FE(puntero) > 1) {
            if (FE(puntero.getDerecha()) < 0) {
                puntero.setDerecha(giroDerecha(puntero.getDerecha()));
            }
            puntero = giroIzquierda(puntero);
        } else if (FE(puntero) < -1) {
            if (FE(puntero.getIzquierda()) > 0) {
                puntero.setIzquierda(giroIzquierda(puntero.getIzquierda()));
            }
            puntero = giroDerecha(puntero);
        }
        return puntero;
    }
    
    public Nodo agregar(Nodo puntero, int valor){
        if(puntero == null){
            return new Nodo(valor);
        } else {
            if(valor < puntero.getValor()){
                puntero.setIzquierda(agregar(puntero.getIzquierda(),valor));
            } else if (valor > puntero.getValor()){
                puntero.setDerecha(agregar(puntero.getDerecha(),valor));
            } else {
                puntero.incrementarContador();
            }
        }
        puntero.setAltura(Math.max(altura(puntero.getIzquierda()),altura(puntero.getDerecha())) + 1);
        return equilibrar(puntero);
    }
    
    public void preorden(Nodo puntero, int nivel){
        if(puntero != null){
            System.out.println(puntero.getValor() + " - Altura: " + puntero.getAltura() + " - Nivel: " + nivel + " - Contador: " + puntero.getContador());
            preorden(puntero.getIzquierda(),nivel+1);
            preorden(puntero.getDerecha(),nivel+1);
        }
    }
    
    public void inorden(Nodo puntero, int nivel) {
        if (puntero != null) {
            inorden(puntero.getIzquierda(), nivel + 1);
            System.out.println(puntero.getValor() + " - Altura: " + puntero.getAltura() + " - Nivel: " + nivel + " - Contador: " + puntero.getContador());
            inorden(puntero.getDerecha(), nivel + 1);
        }
    }

    
    public void posorden(Nodo puntero, int nivel) {
        if (puntero != null) {
            posorden(puntero.getIzquierda(), nivel + 1);
            posorden(puntero.getDerecha(), nivel + 1);
            System.out.println(puntero.getValor() + " - Altura: " + puntero.getAltura() + " - Nivel: " + nivel + " - Contador: " + puntero.getContador());
        }
    }
}
