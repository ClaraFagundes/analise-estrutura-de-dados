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
    private TipoInsercao tipoInsercao = TipoInsercao.FINAL;
    private Cronometro cronometro;

    public ListaArranjo(int Tmax) {
        this.Tmax = Tmax;
        Item = new Perfume[Tmax];
        Primeiro = 0;
        Ultimo = Primeiro;
    }

    public ListaArranjo(TipoInsercao tipoInsercao, int Tmax) {
        this.Tmax = Tmax;
        Item = new Perfume[Tmax];
        Primeiro = 0;
        Ultimo = Primeiro;
        this.tipoInsercao = tipoInsercao;
    }

    public boolean Vazia() {
        return Primeiro == Ultimo;
    }

    public boolean Cheia() {
        return Ultimo >= Tmax;
    }

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
        else {
            Item[Ultimo] = item;
            Ultimo++;
        }
    }

    public void RemoveInicio() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            for (int aux = Primeiro; aux < Ultimo - 1; aux++) Item[aux] = Item[aux + 1];
            Item[Ultimo - 1] = null;
            Ultimo--;
        }
    }

    public void RemoveFinal() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            Item[Ultimo - 1] = null;
            Ultimo--;
        }
    }

    private void RemovePosicao(int p) {
        if ((Vazia()) || (p >= Ultimo)) System.out.println("Erro: Lista vazia ou posição inválida.");
        else {
            for (int aux = p; aux < Ultimo - 1; aux++) Item[aux] = Item[aux + 1];
            Item[Ultimo - 1] = null;
            Ultimo--;
        }
    }

    public void PesquisaRemove(int chave) {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            int aux = Primeiro;
            while ((Item[aux].getChave() != chave) && (aux < Ultimo - 1)) aux++;
            if (Item[aux].getChave() == chave) {
                System.out.println("Removido: " + chave);
                RemovePosicao(aux);
            } else System.out.println("Não encontrado");
        }
    }

    public void Imprime() {
        if (Vazia()) {
            System.out.println("Lista Vazia");
            return;
        }
        System.out.println("ID     | NOME                              | MARCA                      | AVALIACAO");
        System.out.println("-------+-----------------------------------+----------------------------+----------");
        for (int aux = Primeiro; aux < Ultimo; aux++) {
            Perfume p = Item[aux];
            System.out.printf("%-6d | %-33s | %-26s | %.2f%n",
                    p.getChave(),
                    truncar(p.getNome(), 33),
                    truncar(p.getBrand(), 26),
                    p.getRatingValue());
        }
    }

    private String truncar(String s, int max) {
        if (s == null) return "";
        if (s.length() <= max) return s;
        return s.substring(0, max - 3) + "...";
    }

    // --- Buscas ---
    public Perfume PesquisaSequencial(Perfume item) {
        comparacoes = 0;
        cronometro = new Cronometro();
        cronometro.iniciar();

        if (!Vazia()) {
            for (int i = Primeiro; i < Ultimo; i++) {
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

    public Cronometro getCronometro() {
        return cronometro;
    }

    public int getTotalRegistros() {
        return Ultimo;
    }

    public int getComparacoes() {
        return comparacoes;
    }

    public Perfume PesquisaBinaria(Perfume item) {
        comparacoes = 0;

        if (Vazia()) return null;
        int inic = 0, fim = Ultimo - 1, meio = (inic + fim) / 2;
        while ((Item[meio].getChave() != item.getChave()) && (inic != fim)) {
            if (item.getChave() > Item[meio].getChave()) inic = meio + 1;
            else fim = meio;
            comparacoes++;
            meio = (inic + fim) / 2;
        }
        comparacoes++;
        if (Item[meio].getChave() == item.getChave()) {
            return Item[meio];
        }

        return null;
    }

    public void Ordena() {
        for (int i = 0; i < Ultimo - 1; i++)
            for (int j = 0; j < Ultimo - 1 - i; j++)
                if (Item[j].getChave() > Item[j + 1].getChave()) {
                    Perfume temp = Item[j];
                    Item[j] = Item[j + 1];
                    Item[j + 1] = temp;
                }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = PesquisaSequencial(item);

        return "Lista Arranjo: " + perfume + " | Quantidade de comparações: " + getComparacoes() +
                " | Tempo de execução: " + getCronometro();
    }

    String imprimirPesquisaBinaria(Perfume item) {
        Perfume perfume = PesquisaBinaria(item);

        return "Lista Arranjo: " + perfume + " | Quantidade de comparações: " + getComparacoes() +
                " | Tempo de execução: " + getCronometro();
    }

    // ===================== SUPORTE PARA ORDENAÇÃO =====================

    private void swap(int one, int two) {
        Perfume temp = Item[one];
        Item[one] = Item[two];
        Item[two] = temp;
    }

    // ===================== ALGORITMOS DE ORDENAÇÃO =====================

    public void bubbleSort() {
        int in, out;

        for (out = Ultimo - 1; out >= Primeiro + 1; out--)
            for (in = Primeiro; in < out; in++)
                if (Item[in].getChave() > Item[in + 1].getChave())
                    swap(in, in + 1);
    }

    public void selectionSort() {
        int out, in, min;

        for (out = Primeiro; out < Ultimo - 1; out++) {
            min = out;
            for (in = out + 1; in < Ultimo - 1; in++)
                if (Item[in].getChave() < Item[min].getChave())
                    min = in;
            swap(out, min);
        }
    }

    public void insertionSort() {
        int in, out;

        for (out = Primeiro + 1; out < Ultimo; out++) {
            Perfume temp = Item[out];
            in = out;
            while (in > Primeiro && Item[in - 1].getChave() >= temp.getChave()) {
                Item[in] = Item[in - 1];
                --in;
            }
            Item[in] = temp;
        }
    }

    public void shellSort() {
        int inner, outer;
        Perfume temp;
        int nElems = Ultimo - Primeiro;

        int h = 1;
        while (h <= nElems / 3)
            h = h * 3 + 1;

        while (h > 0) {
            for (outer = Primeiro + h; outer < Ultimo; outer++) {
                temp = Item[outer];
                inner = outer;

                while (inner > Primeiro + h - 1 && Item[inner - h].getChave() >= temp.getChave()) {
                    Item[inner] = Item[inner - h];
                    inner -= h;
                }

                Item[inner] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    public void mergeSort() {
        Perfume[] workSpace = new Perfume[Tmax];
        recMergeSort(workSpace, Primeiro, Ultimo - 1);
    }

    public void recMergeSort(Perfume[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound)
            return;
        else {
            int mid = (lowerBound + upperBound) / 2;
            recMergeSort(workSpace, lowerBound, mid);
            recMergeSort(workSpace, mid + 1, upperBound);
            merge(workSpace, lowerBound, mid + 1, upperBound);
        }
    }

    public void merge(Perfume[] workSpace, int lowPtr, int highPtr, int upperBound) {
        int j = lowPtr;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;

        while (lowPtr <= mid && highPtr <= upperBound) {
            if (Item[lowPtr].getChave() < Item[highPtr].getChave())
                workSpace[j++] = Item[lowPtr++];
            else
                workSpace[j++] = Item[highPtr++];
        }

        while (lowPtr <= mid)
            workSpace[j++] = Item[lowPtr++];

        while (highPtr <= upperBound)
            workSpace[j++] = Item[highPtr++];

        for (j = lowerBound; j < lowerBound + n; j++)
            Item[j] = workSpace[j];
    }

    public void quickSort() {
        recQuickSort(Primeiro, Ultimo - 1);
    }

    public void recQuickSort(int left, int right) {
        if (right - left <= 0)
            return;
        else {
            long pivot = Item[right].getChave();

            int partition = partitionIt(left, right, pivot);

            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;

        while (true) {

            while (Item[++leftPtr].getChave() < pivot)
                ;

            while (rightPtr > Primeiro && Item[--rightPtr].getChave() > pivot)
                ;

            if (leftPtr >= rightPtr)
                break;
            else
                swap(leftPtr, rightPtr);
        }

        swap(leftPtr, right);

        return leftPtr;
    }

    public void heapSort() {
        int nElems = Ultimo - Primeiro;

        for (int i = nElems / 2 - 1; i >= 0; i--)
            heapify(nElems, i);

        for (int i = nElems - 1; i > 0; i--) {
            swap(Primeiro, Primeiro + i);
            heapify(i, 0);
        }
    }

    public void heapify(int size, int root) {

        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < size && Item[Primeiro + left].getChave() > Item[Primeiro + largest].getChave())
            largest = left;

        if (right < size && Item[Primeiro + right].getChave() > Item[Primeiro + largest].getChave())
            largest = right;

        if (largest != root) {
            swap(Primeiro + root, Primeiro + largest);
            heapify(size, largest);
        }
    }
}