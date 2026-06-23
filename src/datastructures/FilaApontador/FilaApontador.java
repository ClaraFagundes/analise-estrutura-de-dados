package datastructures.FilaApontador;
import common.Perfume;
import entities.Cronometro;
import entities.EstruturaDeDados;
import entities.Util;

public class FilaApontador implements EstruturaDeDados {

    public class Nodo {
        Perfume Item;
        Nodo Proximo;
        Perfume chave;
    }

    private Cronometro cronometro;
    private int comparacoes;
    private Nodo Frente;
    private Nodo Tras;

    public FilaApontador() { Frente = new Nodo(); Tras = Frente; Frente.Proximo = null; }
    public boolean Vazia() { return Frente == Tras; }

    public void insere(Perfume item) { Nodo p = new Nodo(); p.Item = item; p.Proximo = null; Tras.Proximo = p; Tras = p; }

    public Perfume Desenfileira() {
        if (Vazia()) { System.out.println("Fila Vazia"); return null; }
        Nodo p = Frente.Proximo; Perfume item = p.Item; Frente.Proximo = p.Proximo;
        if (Frente.Proximo == null) Tras = Frente; return item;
    }

    public Perfume pesquisa(Perfume chave) {
        comparacoes = 0;
        cronometro = new Cronometro();
        cronometro.iniciar();
        if (Vazia()) {
            return null;
        }

        Nodo aux = Frente.Proximo;

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
        if (Vazia()) System.out.println("Fila Vazia");
        else { System.out.print("Frente-> "); Nodo aux = Frente.Proximo;

            while (aux != null) {
                System.out.print(aux.Item.getChave() + " ");
                aux = aux.Proximo;
            }
            System.out.println("<-Tras");

        }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = pesquisa(item);

        return "Fila Apontador: "+ perfume + " | Quantidade de comparações: "
                + getComparacoes() + " | Tempo de execução: " + getCronometro();
    }

}
