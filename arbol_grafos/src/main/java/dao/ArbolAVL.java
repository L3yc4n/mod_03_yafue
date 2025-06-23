package dao;

import dto.AVL_Nodo;

public class ArbolAVL {
    public AVL_Nodo raiz;
    
    public void ArbolAVL(){
        raiz = null;
    }
    
    public int altura(AVL_Nodo puntero){
        if(puntero == null){
            return 0;
        } else {
            return puntero.getAltura();
        }
    }
    
    public int FE(AVL_Nodo puntero){
        return altura(puntero.getDerecha()) - altura(puntero.getIzquierda());
    }
    
    public AVL_Nodo giroIzquierda(AVL_Nodo puntero){
        AVL_Nodo aux = puntero.getDerecha();
        puntero.setDerecha(aux.getIzquierda());
        aux.setIzquierda(puntero);
        
        puntero.setAltura(Math.max(altura(puntero.getIzquierda()),altura(puntero.getIzquierda()))+1);
        aux.setAltura(Math.max(altura(aux.getIzquierda()), altura(aux.getIzquierda()))+1);
        return aux;
    }
    
    public AVL_Nodo giroDerecha(AVL_Nodo puntero){
        AVL_Nodo aux = puntero.getIzquierda();
        puntero.setIzquierda(aux.getDerecha());
        aux.setDerecha(puntero);
        
        puntero.setAltura(Math.max(altura(puntero.getIzquierda()),altura(puntero.getIzquierda()))+1);
        aux.setAltura(Math.max(altura(aux.getIzquierda()), altura(aux.getIzquierda()))+1);
        return aux;
    }
    
    public AVL_Nodo equilibrar(AVL_Nodo puntero){
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
    
    public AVL_Nodo agregar(AVL_Nodo puntero, int valor){
        if(puntero == null){
            return new AVL_Nodo(valor);
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
    
    public void preorden(AVL_Nodo puntero, int nivel){
        if(puntero != null){
            System.out.println(puntero.getValor() + " - Altura: " + puntero.getAltura() + " - Nivel: " + nivel + " - Contador: " + puntero.getContador());
            preorden(puntero.getIzquierda(),nivel+1);
            preorden(puntero.getDerecha(),nivel+1);
        }
    }
    
    public void inorden(AVL_Nodo puntero, int nivel) {
        if (puntero != null) {
            inorden(puntero.getIzquierda(), nivel + 1);
            System.out.println(puntero.getValor() + " - Altura: " + puntero.getAltura() + " - Nivel: " + nivel + " - Contador: " + puntero.getContador());
            inorden(puntero.getDerecha(), nivel + 1);
        }
    }

    
    public void posorden(AVL_Nodo puntero, int nivel) {
        if (puntero != null) {
            posorden(puntero.getIzquierda(), nivel + 1);
            posorden(puntero.getDerecha(), nivel + 1);
            System.out.println(puntero.getValor() + " - Altura: " + puntero.getAltura() + " - Nivel: " + nivel + " - Contador: " + puntero.getContador());
        }
    }
}
