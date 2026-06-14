package ed1.tads;

public class ListaApontador {

    private class Nodo {
        TInfo Item;
        Nodo Proximo;
    }

    private Nodo Primeiro;
    private Nodo Ultimo;

    public ListaApontador() {
        Primeiro = new Nodo();
        Ultimo = Primeiro;
        Primeiro.Proximo = null;
    }

    public boolean Vazia() { return Primeiro == Ultimo; }

    public void InsereInicio(TInfo item) {
        Nodo p = new Nodo(); p.Item = item;
        p.Proximo = Primeiro.Proximo; Primeiro.Proximo = p;
        if (p.Proximo == null) Ultimo = p;
    }

    public void InserePosicao(TInfo item, int p) {
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

    public void InsereFinal(TInfo item) {
        Nodo p = new Nodo(); p.Item = item; p.Proximo = null;
        Ultimo.Proximo = p; Ultimo = p;
    }

    public void InsereOrdenado(TInfo item) {
        if (Vazia() || Primeiro.Proximo.Item.Chave >= item.Chave) InsereInicio(item);
        else {
            Nodo aux = Primeiro.Proximo;
            while (aux.Proximo != null && aux.Proximo.Item.Chave < item.Chave) aux = aux.Proximo;
            Nodo novo = new Nodo(); novo.Item = item;
            novo.Proximo = aux.Proximo; aux.Proximo = novo;
            if (novo.Proximo == null) Ultimo = novo;
        }
    }

    public void RemoveInicio() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else {
            Nodo p = Primeiro.Proximo; System.out.println("Removido: " + p.Item.Chave);
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

    public void Imprime() {
        if (Vazia()) System.out.println("Lista Vazia");
        else { Nodo aux = Primeiro.Proximo; while (aux != null) { System.out.print(aux.Item.Chave + " "); aux = aux.Proximo; } System.out.println(); }
    }
}
