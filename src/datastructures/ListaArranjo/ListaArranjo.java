package datastructures.ListaArranjo;

import common.Perfume;
import entities.Cronometro;
import entities.EstruturaDeDados;
import entities.enums.TipoInsercao;

public class ListaArranjo implements EstruturaDeDados {
    private final int Tmax;
    private Perfume[] Item;
    private int Primeiro;
    private int Ultimo;
    private int comparacoes;
    private TipoInsercao tipoInsercao;

    public ListaArranjo(TipoInsercao tipoInsercao, int Tmax) {
        this.Tmax = Tmax;
        Item = new Perfume[Tmax];
        Primeiro = 0;
        Ultimo = Primeiro;
        this.tipoInsercao = tipoInsercao;
    }

    public boolean Vazia() { return Primeiro == Ultimo; }
    public boolean Cheia() { return Ultimo >= Tmax; }

    //Você pode escolher o tipo de inserção ao criar a lista, por exemplo: new ListaAPontador(TipoInsercao.INICIO);
    public void insere(Perfume item) {
        if (tipoInsercao == TipoInsercao.INICIO) InsereInicio(item);
        else InsereFinal(item);
    }

    public void InsereInicio(Perfume item) {
        if (Cheia()) System.out.println("Erro: Lista cheia");
        else {
            for (int aux = Ultimo; aux >= Primeiro + 1; aux--) Item[aux] = Item[aux - 1];
            Item[Primeiro] = item;
            Ultimo++;
        }
    }

    public void InserePosicao(int p, Perfume item) {
        if ((Cheia()) || (p >= Ultimo)) System.out.println("Erro: Lista cheia ou posição inválida.");
        else {
            for (int aux = Ultimo; aux >= p + 1; aux--) Item[aux] = Item[aux - 1];
            Item[p] = item;
            Ultimo++;
        }
    }

    public void InsereFinal(Perfume item) {
        if (Cheia()) System.out.println("Erro: Lista cheia");
        else { Item[Ultimo] = item; Ultimo++; }
    }

    public void RemoveInicio() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            for (int aux = Primeiro; aux < Ultimo - 1; aux++) Item[aux] = Item[aux + 1];
            Item[Ultimo - 1] = null; Ultimo--;
        }
    }

    public void RemoveFinal() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else { Item[Ultimo - 1] = null; Ultimo--; }
    }

    private void RemovePosicao(int p) {
        if ((Vazia()) || (p >= Ultimo)) System.out.println("Erro: Lista vazia ou posição inválida.");
        else {
            for (int aux = p; aux < Ultimo - 1; aux++) Item[aux] = Item[aux + 1];
            Item[Ultimo - 1] = null; Ultimo--;
        }
    }

    public void PesquisaRemove(int chave) {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            int aux = Primeiro;
            while ((Item[aux].getChave() != chave) && (aux < Ultimo - 1)) aux++;
            if (Item[aux].getChave() == chave) { System.out.println("Removido: " + chave); RemovePosicao(aux); }
            else System.out.println("Não encontrado");
        }
    }

    public void Imprime() {
        if (Vazia()) System.out.println("Lista Vazia");
        else { for (int aux = Primeiro; aux < Ultimo; aux++) System.out.print(Item[aux].getChave() + " "); System.out.println(); }
    }

    // --- Buscas ---
    //Alterei a busca para que ela se encaixasse na função imprimirPesquisa(), mas ela está fazendo a mesma coisa
    public Perfume PesquisaSequencial(Perfume item) {
        comparacoes = 0;

        if (!Vazia()) {
            for(Perfume perfume : Item) {
                comparacoes++;
                if (perfume.getChave() == item.getChave()) return perfume;
            }
        }
        return null;
    }

    public int getComparacoes() {
        return comparacoes;
    }

    public int[] PesquisaBinaria(int chave) {
        int comp = 0;
        if (Vazia()) return new int[]{-1, 0};
        int inic = 0, fim = Ultimo - 1, meio = (inic + fim) / 2;
        while ((Item[meio].getChave() != chave) && (inic != fim)) {
            if (chave > Item[meio].getChave()) inic = meio + 1; else fim = meio;
            comp++; meio = (inic + fim) / 2;
        }
        comp++;
        if (Item[meio].getChave() == chave) return new int[]{meio, comp};
        return new int[]{-1, comp};
    }

    public void Ordena() {
        for (int i = 0; i < Ultimo - 1; i++)
            for (int j = 0; j < Ultimo - 1 - i; j++)
                if (Item[j].getChave() > Item[j + 1].getChave()) { Perfume temp = Item[j]; Item[j] = Item[j+1]; Item[j+1] = temp; }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = PesquisaSequencial(item);

        return "Lista Arranjo [Insere " + tipoInsercao.toString().toLowerCase() + "]: "+ perfume + " | Quantidade de comaparações: " + getComparacoes();
    }
}