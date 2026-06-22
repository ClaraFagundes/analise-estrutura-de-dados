package datastructures.ArvoreAVL;

import common.Perfume;

public class TNodoLista {

    Perfume item;
    TNodoLista proximo;
    TNodo nodo;

    public TNodoLista(TNodo nodo){
        if(nodo != null){
            this.item = nodo.item;
            this.nodo = nodo;
            this.proximo = null;
        }
    }
}
