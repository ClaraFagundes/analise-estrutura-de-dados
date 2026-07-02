package datastructures.ListaDupla;

import common.Perfume;
import entities.Cronometro;
import entities.EstruturaDeDados;
import entities.enums.TipoInsercao;

public class ListaDupla implements EstruturaDeDados {
    private Cronometro cronometro;

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
        cronometro = new Cronometro();
        cronometro.iniciar();
        Nodo aux = Primeiro;

        while (aux != null) {
            comparacoes++;
            if (aux.Item.getChave() == item.getChave()) {
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
        if (Vazia()) System.out.println("Lista Dupla Vazia");
        else { Nodo aux = Primeiro; while (aux != null) { System.out.print(aux.Item.getChave() + " "); aux = aux.Proximo; } System.out.println(); }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = pesquisa(item);

        return "Lista Dupla: " + perfume + " | Quantidade de comparações: " + getComparacoes()
                + " | Tempo de execução: " + getCronometro();
    }

    // ===================== SUPORTE =====================

    private void swap(Nodo one, Nodo two) {
        Perfume temp = one.Item;
        one.Item = two.Item;
        two.Item = temp;
    }

    private int tamanho() {
        int n = 0;
        Nodo aux = Primeiro;
        while (aux != null) { n++; aux = aux.Proximo; }
        return n;
    }

    // ===================== ALGORITMOS NATIVOS (SEM ARRAY) =====================

    public void bubbleSort() {
        int nElems = tamanho();

        for (int out = nElems - 1; out >= 1; out--) {
            Nodo in = Primeiro;
            for (int i = 0; i < out; i++) {
                if (in.Item.getChave() > in.Proximo.Item.getChave())
                    swap(in, in.Proximo);
                in = in.Proximo;
            }
        }
    }

    public void selectionSort() {
        int nElems = tamanho();
        Nodo out = Primeiro;

        for (int o = 0; o < nElems - 1; o++) {
            Nodo min = out;
            Nodo in = out.Proximo;
            for (int i = o + 1; i < nElems - 1; i++) {
                if (in.Item.getChave() < min.Item.getChave())
                    min = in;
                in = in.Proximo;
            }
            swap(out, min);
            out = out.Proximo;
        }
    }

    public void insertionSort() {
        Nodo out = Primeiro;
        while (out != null) {
            Perfume temp = out.Item;
            Nodo in = out;
            while (in.Anterior != null && in.Anterior.Item.getChave() >= temp.getChave()) {
                in.Item = in.Anterior.Item;
                in = in.Anterior;
            }
            in.Item = temp;
            out = out.Proximo;
        }
    }

    public void mergeSort() {
        Primeiro = recMergeSort(Primeiro);

        Primeiro.Anterior = null;
        Nodo aux = Primeiro;
        while (aux.Proximo != null) {
            aux.Proximo.Anterior = aux;
            aux = aux.Proximo;
        }
        Ultimo = aux;
    }

    private Nodo recMergeSort(Nodo inicio) {
        if (inicio == null || inicio.Proximo == null)
            return inicio;

        Nodo meio = encontraMeio(inicio);
        Nodo metadeDir = meio.Proximo;
        meio.Proximo = null;

        Nodo esq = recMergeSort(inicio);
        Nodo dir = recMergeSort(metadeDir);

        return merge(esq, dir);
    }

    private Nodo encontraMeio(Nodo inicio) {
        Nodo lento = inicio, rapido = inicio.Proximo;
        while (rapido != null && rapido.Proximo != null) {
            lento = lento.Proximo;
            rapido = rapido.Proximo.Proximo;
        }
        return lento;
    }

    private Nodo merge(Nodo esq, Nodo dir) {
        Nodo dummy = new Nodo();
        Nodo tail = dummy;

        while (esq != null && dir != null) {
            if (esq.Item.getChave() < dir.Item.getChave()) {
                tail.Proximo = esq; esq = esq.Proximo;
            } else {
                tail.Proximo = dir; dir = dir.Proximo;
            }
            tail = tail.Proximo;
        }
        tail.Proximo = (esq != null) ? esq : dir;

        return dummy.Proximo;
    }

    private int idxPartition;

    public void quickSort() {
        int nElems = tamanho();
        recQuickSort(Primeiro, 0, Ultimo, nElems - 1);
    }

    private void recQuickSort(Nodo left, int leftIdx, Nodo right, int rightIdx) {
        if (right == null || left == null || rightIdx - leftIdx <= 0)
            return;

        long pivot = right.Item.getChave();
        Nodo partitionNode = partitionIt(left, leftIdx, right, rightIdx, pivot);
        int partitionIdx = idxPartition;

        recQuickSort(left, leftIdx, partitionNode.Anterior, partitionIdx - 1);
        recQuickSort(partitionNode.Proximo, partitionIdx + 1, right, rightIdx);
    }

    private Nodo partitionIt(Nodo left, int leftIdx, Nodo right, int rightIdx, long pivot) {
        Nodo leftPtr = left.Anterior;
        int leftPtrIdx = leftIdx - 1;
        Nodo rightPtr = right;
        int rightPtrIdx = rightIdx;

        while (true) {
            do {
                leftPtr = (leftPtr == null) ? left : leftPtr.Proximo;
                leftPtrIdx++;
            } while (leftPtr.Item.getChave() < pivot);

            while (rightPtrIdx > 0 && rightPtr.Anterior.Item.getChave() > pivot) {
                rightPtr = rightPtr.Anterior;
                rightPtrIdx--;
            }

            if (leftPtrIdx >= rightPtrIdx) break;
            else swap(leftPtr, rightPtr);
        }

        swap(leftPtr, right);
        idxPartition = leftPtrIdx;
        return leftPtr;
    }

    private Perfume[] a;
    private int nElems;

    public Perfume[] toArray() {
        nElems = tamanho();
        a = new Perfume[nElems];
        Nodo aux = Primeiro;
        int i = 0;
        while (aux != null) { a[i++] = aux.Item; aux = aux.Proximo; }
        return a;
    }

    public void fromArray() {
        Primeiro = null;
        Ultimo = null;
        for (int i = 0; i < nElems; i++) {
            Nodo novo = new Nodo();
            novo.Item = a[i];
            novo.Proximo = null;
            novo.Anterior = Ultimo;
            if (Primeiro == null) Primeiro = novo; else Ultimo.Proximo = novo;
            Ultimo = novo;
        }
    }

    private void swap(int one, int two) {
        Perfume temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }

    public void shellSort() {
        int inner, outer;
        Perfume temp;

        int h = 1;
        while (h <= nElems/3)
            h = h*3 + 1;

        while (h > 0) {
            for (outer = h; outer < nElems; outer++) {
                temp = a[outer];
                inner = outer;
                while (inner > h-1 && a[inner-h].getChave() >= temp.getChave()) {
                    a[inner] = a[inner-h];
                    inner -= h;
                }
                a[inner] = temp;
            }
            h = (h-1) / 3;
        }
    }

    public void heapSort() {
        for (int i = nElems / 2 - 1; i >= 0; i--)
            heapify(nElems, i);
        for (int i = nElems - 1; i > 0; i--) {
            swap(0, i);
            heapify(i, 0);
        }
    }

    public void heapify(int size, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        if (left < size && a[left].getChave() > a[largest].getChave()) largest = left;
        if (right < size && a[right].getChave() > a[largest].getChave()) largest = right;
        if (largest != root) { swap(root, largest); heapify(size, largest); }
    }
}