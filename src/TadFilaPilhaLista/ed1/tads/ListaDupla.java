package TadFilaPilhaLista.ed1.tads;
import modeloTinfo.TInfo;

public class ListaDupla {

    private class Nodo {
        TInfo Item;
        Nodo Anterior;
        Nodo Proximo;
    }

    private Nodo Primeiro;
    private Nodo Ultimo;

    public ListaDupla() { Primeiro = null; Ultimo = null; }

    public boolean Vazia() { return Primeiro == null; }

    public void InsereInicio(TInfo item) {
        Nodo novo = new Nodo(); novo.Item = item; novo.Anterior = null; novo.Proximo = Primeiro;
        if (Vazia()) Ultimo = novo; else Primeiro.Anterior = novo;
        Primeiro = novo;
    }

    public void InsereFinal(TInfo item) {
        Nodo novo = new Nodo(); novo.Item = item; novo.Proximo = null; novo.Anterior = Ultimo;
        if (Vazia()) Primeiro = novo; else Ultimo.Proximo = novo;
        Ultimo = novo;
    }

    public void RemoveInicio() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else { System.out.println("Removido: " + Primeiro.Item.chave); Primeiro = Primeiro.Proximo; if (Primeiro != null) Primeiro.Anterior = null; else Ultimo = null; }
    }

    public void RemoveFinal() {
        if (Vazia()) System.out.println("Erro: Lista vazia");
        else { System.out.println("Removido: " + Ultimo.Item.chave); Ultimo = Ultimo.Anterior; if (Ultimo != null) Ultimo.Proximo = null; else Primeiro = null; }
    }

    public void Imprime() {
        if (Vazia()) System.out.println("Lista Dupla Vazia");
        else { Nodo aux = Primeiro; while (aux != null) { System.out.print(aux.Item.chave + " "); aux = aux.Proximo; } System.out.println(); }
    }
}
