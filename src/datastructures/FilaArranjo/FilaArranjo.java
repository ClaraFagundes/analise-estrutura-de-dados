package datastructures.FilaArranjo;
import common.Perfume;
import datastructures.FilaApontador.FilaApontador;
import entities.Cronometro;
import entities.EstruturaDeDados;
import entities.Util;

public class FilaArranjo implements EstruturaDeDados {

    public class Nodo {
        Perfume Item;
        FilaApontador.Nodo Proximo;
        Perfume chave;
    }

    private final int Tmax = 91134;
    private Perfume[] Item;
    private int Frente, Tras;
    private Cronometro cronometro;
    private int comparacoes;

    public FilaArranjo() { Item = new Perfume[Tmax]; Frente = 0; Tras = 0; }

    public boolean Vazia() { return Frente == Tras; }

    public boolean Cheia() { return ((Tras + 1) % Tmax) == Frente; }

    public void insere(Perfume item) {

        if (Cheia()) System.out.println("Fila Cheia");
        else { Item[Tras] = item; Tras = (Tras + 1) % Tmax; }

    }

    public Perfume Desenfileira() {
        if (Vazia()) { System.out.println("Fila Vazia"); return null; }
        Perfume item = Item[Frente]; Item[Frente] = null; Frente = (Frente + 1) % Tmax; return item;
    }

    public Perfume pesquisa(Perfume chave) {
        comparacoes = 0;
        cronometro = new Cronometro();
        cronometro.iniciar();

        if (Vazia()) {
            return null;
        }

        int i = Frente;

        while (i != Tras) {
            comparacoes++;
            if (Item[i].getChave() == chave.getChave()){
                cronometro.finalizar();
                return Item[i];
            }
            i = (i + 1) % Tmax;
        }

        cronometro.finalizar();
        return null;
    }

    public Cronometro getCronometro() { return cronometro; }

    public int getComparacoes() {
        return comparacoes;
    }

    public void Imprime() {
        if (Vazia()) System.out.println("Fila Vazia");
        else { System.out.print("Frente-> "); int i = Frente;

            while (i != Tras) {
                System.out.print(Item[i].getChave() + " ");
                i = (i + 1) % Tmax;
            }

            System.out.println("<-Tras"); }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = pesquisa(item);

        return "Fila Arranjo: "+ perfume + " | Quantidade de comparações: "
                + getComparacoes() + " | Tempo de execução: " + getCronometro();
    }

}
