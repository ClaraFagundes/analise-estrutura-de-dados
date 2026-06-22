package datastructures.ListaDupla;

import common.Perfume;
import entities.Cronometro;
import entities.EstruturaDeDados;
import entities.enums.TipoInsercao;

public class ListaDupla implements EstruturaDeDados {

    private class Nodo {
        Perfume Item;
        Nodo Anterior;
        Nodo Proximo;
    }

    private Nodo Primeiro;
    private Nodo Ultimo;
    private int comparacoes;
    private TipoInsercao tipoInsercao;

    public ListaDupla() {
        Primeiro = null;
        Ultimo = null;
    }

    public ListaDupla(TipoInsercao tipoInsercao) {
        Primeiro = null;
        Ultimo = null;
        this.tipoInsercao = tipoInsercao;
    }

    public boolean Vazia() { return Primeiro == null; }

    public void insere(Perfume item) {
        if (tipoInsercao == TipoInsercao.INICIO) InsereInicio(item);
        else InsereFinal(item);
    }

    public void InsereInicio(Perfume item) {
        Nodo novo = new Nodo(); novo.Item = item; novo.Anterior = null; novo.Proximo = Primeiro;
        if (Vazia()) Ultimo = novo; else Primeiro.Anterior = novo;
        Primeiro = novo;
    }

    public void InsereFinal(Perfume item) {
        Nodo novo = new Nodo(); novo.Item = item; novo.Proximo = null; novo.Anterior = Ultimo;
        if (Vazia()) Primeiro = novo; else Ultimo.Proximo = novo;
        Ultimo = novo;
    }

    public void RemoveInicio() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else { System.out.println("Removido: " + Primeiro.Item.getChave()); Primeiro = Primeiro.Proximo; if (Primeiro != null) Primeiro.Anterior = null; else Ultimo = null; }
    }

    public void RemoveFinal() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else { System.out.println("Removido: " + Ultimo.Item.getChave()); Ultimo = Ultimo.Anterior; if (Ultimo != null) Ultimo.Proximo = null; else Primeiro = null; }
    }

    public Perfume pesquisa(Perfume item) {
        comparacoes = 0;
        Nodo aux = Primeiro;

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
        if (Vazia()) System.out.println("Lista Dupla Vazia");
        else { Nodo aux = Primeiro; while (aux != null) { System.out.print(aux.Item.getChave() + " "); aux = aux.Proximo; } System.out.println(); }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = pesquisa(item);

        return "Lista Dupla [Insere " + tipoInsercao.toString().toLowerCase() + "]: " + perfume + " | Quantidade de comparações: " + getComparacoes();
    }
}