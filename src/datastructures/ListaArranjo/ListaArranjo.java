package datastructures.ListaArranjo;

import common.Perfume;
import datastructures.ListaApontador.ListaApontador;
import entities.Cronometro;
import entities.EstruturaDeDados;
import entities.enums.TipoInsercao;

public class ListaArranjo implements EstruturaDeDados {
    private final int Tmax;
    private Perfume[] Item;
    private int Primeiro;
    private int Ultimo;
    private int comparacoes;
    private int comp;
    private TipoInsercao tipoInsercao;
    private Cronometro cronometro;

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
        cronometro = new Cronometro();
        cronometro.iniciar();

        if (!Vazia()) {
            for(int i = Primeiro; i < Ultimo; i++) {
                comparacoes++;
                if (Item[i].getChave() == item.getChave()) {
                    cronometro.finalizar();
                    return Item[i];
                }
            }
        }
        cronometro.finalizar();
        return null;
    }

    public Cronometro getCronometro() { return cronometro; }

    public int getComparacoes() {
        return comparacoes;
    }

    public int getComp() {
        return comp;
    }//comparações da binária

    public Perfume PesquisaBinaria(Perfume item) {

        comp = 0;
        cronometro.iniciar();
        if (Vazia()) return null;
        int inic = 0, fim = Ultimo - 1, meio = (inic + fim) / 2;
        while ((Item[meio].getChave() != item.getChave()) && (inic != fim)) {
            if (item.getChave() > Item[meio].getChave()) inic = meio + 1; else fim = meio;
            comp++; meio = (inic + fim) / 2;
        }
        comp++;
        if (Item[meio].getChave() == item.getChave()){
            cronometro.finalizar();
            return Item[meio];
        }
        cronometro.finalizar();
        return null;

    }

    public void Ordena() {
        for (int i = 0; i < Ultimo - 1; i++)
            for (int j = 0; j < Ultimo - 1 - i; j++)
                if (Item[j].getChave() > Item[j + 1].getChave()) { Perfume temp = Item[j]; Item[j] = Item[j+1]; Item[j+1] = temp; }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = PesquisaSequencial(item);

        return "Lista Arranjo (Sequencial) : "+ perfume + " | Quantidade de comparações: " + getComparacoes() +
                " | Tempo de execução: " + getCronometro() + imprimirPesquisaBinaria(item);
    }

    public String imprimirPesquisaBinaria(Perfume item){
        Perfume perfume = PesquisaBinaria(item);

        return "\nLista Arranjo (Binária) : "+ perfume + " | Quantidade de comparações: " + getComp() +
                " | Tempo de execução: " + getCronometro();
    }
}