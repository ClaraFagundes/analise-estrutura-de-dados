package datastructures.ListaApontador;

import common.Perfume;
import datastructures.ArvoreBST.Nodo;
import entities.DataStructure;
import entities.enums.TipoInsercao;

public class ListaApontador implements DataStructure {

    private class Nodo {
        Perfume Item;
        Nodo Proximo;
    }

    private Nodo Primeiro;
    private Nodo Ultimo;
    private int comparacoes;
    private TipoInsercao tipoInsercao;

    public ListaApontador() {
        Primeiro = new Nodo();
        Ultimo = Primeiro;
        Primeiro.Proximo = null;
    }

    public ListaApontador(TipoInsercao tipoInsercao) {
        Primeiro = new Nodo();
        Ultimo = Primeiro;
        Primeiro.Proximo = null;
        this.tipoInsercao = tipoInsercao;
    }

    public boolean Vazia() { return Primeiro == Ultimo; }

    //Você pode escolher o tipo de inserção ao criar a lista, por exemplo: new ListaAPontador(TipoInsercao.INICIO);
    public void insere(Perfume item) {
        if (tipoInsercao == TipoInsercao.INICIO) InsereInicio(item);
        else if (tipoInsercao == TipoInsercao.FINAL) InsereFinal(item);
        else InsereOrdenado(item);
    }

    public void InsereInicio(Perfume item) {
        Nodo p = new Nodo(); p.Item = item;
        p.Proximo = Primeiro.Proximo; Primeiro.Proximo = p;
        if (p.Proximo == null) Ultimo = p;
    }

    public void InserePosicao(Perfume item, int p) {
        if (p == 1) InsereInicio(item);
        else {
            int i = 1; Nodo aux = Primeiro.Proximo;
            while ((i < p - 1) && (aux != null)) { i++; aux = aux.Proximo; }
            if (aux == null) System.out.println("Posição não existe");
            else {
                Nodo ptr = new Nodo(); ptr.Item = item;
                ptr.Proximo = aux.Proximo; aux.Proximo = ptr;
                if (ptr.Proximo == null) Ultimo = ptr;
            }
        }
    }

    public void InsereFinal(Perfume item) {
        Nodo p = new Nodo(); p.Item = item; p.Proximo = null;
        Ultimo.Proximo = p; Ultimo = p;
    }

    public void InsereOrdenado(Perfume item) {
        if (Vazia() || Primeiro.Proximo.Item.getChave() >= item.getChave()) InsereInicio(item);
        else {
            Nodo aux = Primeiro.Proximo;
            while (aux.Proximo != null && aux.Proximo.Item.getChave() < item.getChave()) aux = aux.Proximo;
            Nodo novo = new Nodo(); novo.Item = item;
            novo.Proximo = aux.Proximo; aux.Proximo = novo;
            if (novo.Proximo == null) Ultimo = novo;
        }
    }

    public void RemoveInicio() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            Nodo p = Primeiro.Proximo; System.out.println("Removido: " + p.Item.getChave());
            Primeiro.Proximo = p.Proximo; if (Primeiro.Proximo == null) Ultimo = Primeiro;
        }
    }

    public void RemoveFinal() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            Nodo p = Primeiro.Proximo;
            if (p.Proximo == null) { Primeiro.Proximo = null; Ultimo = Primeiro; }
            else { Nodo aux = null; while (p.Proximo != null) { aux = p; p = p.Proximo; } aux.Proximo = null; Ultimo = aux; }
        }
    }

    public Perfume pesquisa(Perfume item) {
        comparacoes = 0;
        Nodo aux = Primeiro.Proximo;

        while (aux != null) {
            comparacoes++;
            if (aux.Item.getChave() == item.getChave()) {
                return aux.Item;
            }
            aux = aux.Proximo;
        }

        return null;
    }

    public int getComparacoes() {
        return comparacoes;
    }

    public void Imprime() {
        if (Vazia()) System.out.println("Lista Vazia");
        else { Nodo aux = Primeiro.Proximo; while (aux != null) { System.out.print(aux.Item.getChave() + " "); aux = aux.Proximo; } System.out.println(); }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = pesquisa(item);
        return "Lista Apontador [Insere " + tipoInsercao.toString().toLowerCase() + "]: "+ perfume + " | Quantidade de comaparações: " + getComparacoes();
    }
}