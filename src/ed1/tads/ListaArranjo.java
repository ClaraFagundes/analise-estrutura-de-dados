package ed1.tads;

public class ListaArranjo {
    private final int Tmax = 100;
    private TInfo[] Item;
    private int Primeiro;
    private int Ultimo;

    public ListaArranjo() {
        Item = new TInfo[Tmax];
        Primeiro = 0;
        Ultimo = Primeiro;
    }

    public boolean Vazia() { return Primeiro == Ultimo; }
    public boolean Cheia() { return Ultimo >= Tmax; }

    public void InsereInicio(TInfo item) {
        if (Cheia()) System.out.println("Erro: Lista cheia");
        else {
            for (int aux = Ultimo; aux >= Primeiro + 1; aux--) Item[aux] = Item[aux - 1];
            Item[Primeiro] = item;
            Ultimo++;
        }
    }

    public void InserePosicao(int p, TInfo item) {
        if ((Cheia()) || (p >= Ultimo)) System.out.println("Erro: Lista cheia ou posição inválida.");
        else {
            for (int aux = Ultimo; aux >= p + 1; aux--) Item[aux] = Item[aux - 1];
            Item[p] = item;
            Ultimo++;
        }
    }

    public void InsereFinal(TInfo item) {
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
            while ((Item[aux].Chave != chave) && (aux < Ultimo - 1)) aux++;
            if (Item[aux].Chave == chave) { System.out.println("Removido: " + chave); RemovePosicao(aux); }
            else System.out.println("Não encontrado");
        }
    }

    public void Imprime() {
        if (Vazia()) System.out.println("Lista Vazia");
        else { for (int aux = Primeiro; aux < Ultimo; aux++) System.out.print(Item[aux].Chave + " "); System.out.println(); }
    }

    // --- Buscas ---
    public int[] PesquisaSequencial(int chave) {
        int comp = 0, achou = -1;
        if (!Vazia()) {
            int i = 0;
            while ((achou == -1) && (i < Ultimo)) {
                if (Item[i].Chave == chave) achou = i;
                comp++; i++;
            }
        }
        return new int[]{achou, comp};
    }

    public int[] PesquisaBinaria(int chave) {
        int comp = 0;
        if (Vazia()) return new int[]{-1, 0};
        int inic = 0, fim = Ultimo - 1, meio = (inic + fim) / 2;
        while ((Item[meio].Chave != chave) && (inic != fim)) {
            if (chave > Item[meio].Chave) inic = meio + 1; else fim = meio;
            comp++; meio = (inic + fim) / 2;
        }
        comp++;
        if (Item[meio].Chave == chave) return new int[]{meio, comp};
        return new int[]{-1, comp};
    }

    public void Ordena() {
        for (int i = 0; i < Ultimo - 1; i++)
            for (int j = 0; j < Ultimo - 1 - i; j++)
                if (Item[j].Chave > Item[j + 1].Chave) { TInfo temp = Item[j]; Item[j] = Item[j+1]; Item[j+1] = temp; }
    }
}
