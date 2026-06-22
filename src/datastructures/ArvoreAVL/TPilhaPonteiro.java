package datastructures.ArvoreAVL;

import common.Perfume;

public class TPilhaPonteiro {

    private TCelulaCabeca celulaCabeca;
    private TNodoLista topo;

    public TPilhaPonteiro(){
        this.topo = null;
        this.celulaCabeca = new TCelulaCabeca();
    }

    public boolean vazia(){
        return (this.topo == null);
    }

    public void push(TNodo nodo){
        TNodoLista p = new TNodoLista(nodo);
        p.proximo = this.topo;
        this.topo = p;
        this.celulaCabeca.incItens();
    }

    public TNodo pop(){

        if(vazia()){
            System.out.println("Erro: A lista está vazia.");
            return null;
        }

        TNodoLista aux = this.topo;
        this.topo = this.topo.proximo;
        this.celulaCabeca.decItens();

        return aux.nodo;
    }

    public Perfume peek(){
        return this.topo.item;
    }

    public void print(){

        System.out.print("Itens na LISTA: ");

        TNodoLista aux = this.topo;

        while(aux != null){
            System.out.print(aux.item + " ");
            aux = aux.proximo;
        }

        System.out.println();
        System.out.println("Total de " + this.celulaCabeca.n_itens + " itens.");
    }
}