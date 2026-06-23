package datastructures.PilhaArranjo;
import common.Perfume;
import entities.Cronometro;
import entities.EstruturaDeDados;
import entities.Util;

public class PilhaArranjo implements EstruturaDeDados {
    private final int Tmax = 91134;
    private Perfume[] Item;
    private int Topo;
    private Cronometro cronometro;
    private int comparacoes;

    public PilhaArranjo() { Item = new Perfume[Tmax]; Topo = -1; }
    public boolean Vazia() { return Topo == -1; }
    public boolean Cheia() { return Topo >= Tmax - 1; }

    public void insere(Perfume item) {

        if (Cheia()) System.out.println("Pilha Cheia");
        else Item[++Topo] = item;

    }
    public Perfume Desempilha() {

        if (Vazia()) { System.out.println("Pilha Vazia"); return null; }
        return Item[Topo--];

    }

    public void Imprime() {
        if (Vazia()) System.out.println("Pilha Vazia");
        else {
            System.out.print("Topo-> ");
            for (int i = Topo; i >= 0; i--)
                System.out.print(Item[i].getChave() + " ");
                System.out.println("<-Base");
        }
    }

    public Perfume pesquisa(Perfume item) {
        comparacoes = 0;
        cronometro = new Cronometro();
        cronometro.iniciar();

        for (int i = Topo; i >= 0; i--) {
            comparacoes++;

            if (Item[i].getChave() == item.getChave()) {
                cronometro.finalizar();
                return Item[i];
            }
        }

        cronometro.finalizar();
        return null;
    }

    public Cronometro getCronometro() { return cronometro; }

    public int getComparacoes() {
        return comparacoes;
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = pesquisa(item);

        return "Pilha Arranjo: " + perfume + " | Quantidade de comparações: " + getComparacoes()
                + " | Tempo de execução: " + getCronometro();
    }

}
