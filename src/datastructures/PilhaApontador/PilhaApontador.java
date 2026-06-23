package datastructures.PilhaApontador;
import common.Perfume;
import entities.Cronometro;
import entities.EstruturaDeDados;
import entities.Util;

public class PilhaApontador implements EstruturaDeDados {

    private class Nodo {
        Perfume Item;
        Nodo Proximo;
    }
    private Cronometro cronometro;
    private int comparacoes;
    private Nodo Topo;

    public PilhaApontador() { Topo = null; }
    public boolean Vazia() { return Topo == null; }

    public void insere(Perfume item) { Nodo p = new Nodo(); p.Item = item; p.Proximo = Topo; Topo = p; }
    public Perfume Desempilha() {
        if (Vazia()) { System.out.println("Pilha Vazia"); return null; }
        Perfume item = Topo.Item; Topo = Topo.Proximo; return item;
    }

    public Perfume pesquisa(Perfume chave) {
        comparacoes = 0;
        cronometro = new Cronometro();
        cronometro.iniciar();

        Nodo aux = Topo;

        while (aux != null) {
            comparacoes++;

            if (aux.Item.getChave() == chave.getChave()) {
                cronometro.finalizar();
                return aux.Item;
            }

            aux = aux.Proximo;
        }

        cronometro.finalizar();
        return null;
    }

    public Cronometro getCronometro() { return cronometro; }

    public int getComparacoes() {
        return comparacoes;
    }

    public void Imprime() {
        if (Vazia()) System.out.println("Pilha Vazia");
        else { System.out.print("Topo-> "); Nodo aux = Topo;
            while (aux != null) {
                System.out.print(aux.Item.getChave() + " ");
                aux = aux.Proximo;
            }
            System.out.println("<-Base");
        }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = pesquisa(item);

        return "Pilha Apontador: " + perfume + " | Quantidade de comparações: " + getComparacoes()
                + " | Tempo de execução: " + getCronometro();
    }

}
