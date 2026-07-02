package datastructures.ListaApontador;

import common.Perfume;
import entities.Cronometro;
import entities.EstruturaDeDados;
import entities.enums.TipoInsercao;
import entities.Util;
import entities.Cronometro;

public class ListaApontador implements EstruturaDeDados {
    private Cronometro cronometro;

    private class Nodo {
        Perfume Item;
        Nodo Proximo;
    }

    private Nodo Primeiro;
    private Nodo Ultimo;
    private int comparacoes;
    private TipoInsercao tipoInsercao;

    public ListaApontador() {
        Primeiro = new Nodo();
        Ultimo = Primeiro;
        Primeiro.Proximo = null;
    }

    public ListaApontador(TipoInsercao tipoInsercao) {
        Primeiro = new Nodo();
        Ultimo = Primeiro;
        Primeiro.Proximo = null;
        this.tipoInsercao = tipoInsercao;
    }

    public boolean Vazia() { return Primeiro == Ultimo; }

    //Você pode escolher o tipo de inserção ao criar a lista, por exemplo: new ListaAPontador(TipoInsercao.INICIO);
    public void insere(Perfume item) {
        if (tipoInsercao == TipoInsercao.INICIO) InsereInicio(item);
        else InsereFinal(item);
    }

    public void InsereInicio(Perfume item) {
        Nodo p = new Nodo(); p.Item = item;
        p.Proximo = Primeiro.Proximo; Primeiro.Proximo = p;
        if (p.Proximo == null) Ultimo = p;
    }

    public void InserePosicao(Perfume item, int p) {
        if (p == 1) InsereInicio(item);
        else {
            int i = 1; Nodo aux = Primeiro.Proximo;
            while ((i < p - 1) && (aux != null)) { i++; aux = aux.Proximo; }
            if (aux == null) System.out.println("Posição não existe");
            else {
                Nodo ptr = new Nodo(); ptr.Item = item;
                ptr.Proximo = aux.Proximo; aux.Proximo = ptr;
                if (ptr.Proximo == null) Ultimo = ptr;
            }
        }
    }

    public void InsereFinal(Perfume item) {
        Nodo p = new Nodo(); p.Item = item; p.Proximo = null;
        Ultimo.Proximo = p; Ultimo = p;
    }

    public void InsereOrdenado(Perfume item) {
        if (Vazia() || Primeiro.Proximo.Item.getChave() >= item.getChave()) InsereInicio(item);
        else {
            Nodo aux = Primeiro.Proximo;
            while (aux.Proximo != null && aux.Proximo.Item.getChave() < item.getChave()) aux = aux.Proximo;
            Nodo novo = new Nodo(); novo.Item = item;
            novo.Proximo = aux.Proximo; aux.Proximo = novo;
            if (novo.Proximo == null) Ultimo = novo;
        }
    }

    public void RemoveInicio() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            Nodo p = Primeiro.Proximo; System.out.println("Removido: " + p.Item.getChave());
            Primeiro.Proximo = p.Proximo; if (Primeiro.Proximo == null) Ultimo = Primeiro;
        }
    }

    public void RemoveFinal() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            Nodo p = Primeiro.Proximo;
            if (p.Proximo == null) { Primeiro.Proximo = null; Ultimo = Primeiro; }
            else { Nodo aux = null; while (p.Proximo != null) { aux = p; p = p.Proximo; } aux.Proximo = null; Ultimo = aux; }
        }
    }

    public Perfume pesquisa(Perfume item) {

        comparacoes = 0;
        cronometro = new Cronometro();
        cronometro.iniciar();
        Nodo aux = Primeiro.Proximo;

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
        if (Vazia()) System.out.println("Lista Vazia");
        else { Nodo aux = Primeiro.Proximo; while (aux != null) { System.out.print(aux.Item.getChave() + " "); aux = aux.Proximo; } System.out.println(); }
    }

    public String imprimirPesquisa(Perfume item) {
        Perfume perfume = pesquisa(item);

        return "Lista Apontador: "+ perfume + " | Quantidade de comparações: "
                + getComparacoes() + " | Tempo de execução: " + getCronometro();
    }

    // ===================== SUPORTE =====================

    private void swap(Nodo one, Nodo two) {
        Perfume temp = one.Item;
        one.Item = two.Item;
        two.Item = temp;
    }

    private int tamanho() {
        int n = 0;
        Nodo aux = Primeiro.Proximo;
        while (aux != null) { n++; aux = aux.Proximo; }
        return n;
    }

    // ===================== ALGORITMOS NATIVOS (SEM ARRAY) =====================

    public void bubbleSort() {
        int nElems = tamanho();

        for (int out = nElems - 1; out >= 1; out--) {
            Nodo in = Primeiro.Proximo;
            for (int i = 0; i < out; i++) {
                if (in.Item.getChave() > in.Proximo.Item.getChave())
                    swap(in, in.Proximo);
                in = in.Proximo;
            }
        }
    }

    public void selectionSort() {
        int nElems = tamanho();
        Nodo out = Primeiro.Proximo;

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

    // Adaptação necessária: lista simples não tem ponteiro Anterior, então a
    // varredura "para trás" do insertion sort de array vira uma busca "para frente"
    // a partir do início da sub-lista já ordenada. A lógica de essência (construir
    // um prefixo ordenado incrementalmente) é preservada.
    public void insertionSort() {
        if (Primeiro.Proximo == null || Primeiro.Proximo.Proximo == null) return;

        Nodo fimOrdenado = Primeiro.Proximo;
        Nodo out = fimOrdenado.Proximo;

        while (out != null) {
            Nodo proximoOut = out.Proximo;

            if (out.Item.getChave() >= fimOrdenado.Item.getChave()) {
                fimOrdenado = out;
            } else {
                fimOrdenado.Proximo = proximoOut;

                Nodo in = Primeiro;
                while (in.Proximo.Item.getChave() <= out.Item.getChave())
                    in = in.Proximo;

                out.Proximo = in.Proximo;
                in.Proximo = out;
            }
            out = proximoOut;
        }

        Nodo aux = Primeiro.Proximo;
        while (aux.Proximo != null) aux = aux.Proximo;
        Ultimo = aux;
    }

    public void mergeSort() {
        Primeiro.Proximo = recMergeSort(Primeiro.Proximo);

        Nodo aux = Primeiro.Proximo;
        if (aux == null) { Ultimo = Primeiro; return; }
        while (aux.Proximo != null) aux = aux.Proximo;
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
                tail.Proximo = esq;
                esq = esq.Proximo;
            } else {
                tail.Proximo = dir;
                dir = dir.Proximo;
            }
            tail = tail.Proximo;
        }
        tail.Proximo = (esq != null) ? esq : dir;

        return dummy.Proximo;
    }

    // ===================== INDEX-BOUND: exigem array por natureza estrutural =====================
    // Shell Sort (saltos de h), Quick Sort com partição de Hoare (rightPtr precisa
    // andar para trás — impossível em O(1) sem ponteiro Anterior), e Heap Sort
    // (aritmética 2i+1/2i+2). Nesses três casos, toArray()/fromArray() DEVEM ser
    // contados na medição de tempo, pois são parte inerente do custo do algoritmo
    // nesta estrutura, não um artefato evitável de implementação.

    private Perfume[] a;
    private int nElems;

    public Perfume[] toArray() {
        nElems = tamanho();
        a = new Perfume[nElems];
        Nodo aux = Primeiro.Proximo;
        int i = 0;
        while (aux != null) { a[i++] = aux.Item; aux = aux.Proximo; }
        return a;
    }

    public void fromArray() {
        Primeiro.Proximo = null;
        Ultimo = Primeiro;
        for (int i = 0; i < nElems; i++) {
            Nodo p = new Nodo();
            p.Item = a[i];
            p.Proximo = null;
            Ultimo.Proximo = p;
            Ultimo = p;
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

    public void quickSort() {
        recQuickSort(0, nElems - 1);
    }

    public void recQuickSort(int left, int right) {
        if (right - left <= 0) return;
        long pivot = a[right].getChave();
        int partition = partitionIt(left, right, pivot);
        recQuickSort(left, partition - 1);
        recQuickSort(partition + 1, right);
    }

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;

        while (true) {
            while (a[++leftPtr].getChave() < pivot);
            while (rightPtr > 0 && a[--rightPtr].getChave() > pivot);
            if (leftPtr >= rightPtr) break;
            else swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right);
        return leftPtr;
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