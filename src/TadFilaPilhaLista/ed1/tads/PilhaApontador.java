package TadFilaPilhaLista.ed1.tads;
import modeloTinfo.TInfo;

public class PilhaApontador {

    private class Nodo {
        TInfo Item;
        Nodo Proximo;
    }

    private Nodo Topo;

    public PilhaApontador() { Topo = null; }
    public boolean Vazia() { return Topo == null; }

    public void Empilha(TInfo item) { Nodo p = new Nodo(); p.Item = item; p.Proximo = Topo; Topo = p; }
    public TInfo Desempilha() {
        if (Vazia()) { System.out.println("Pilha Vazia"); return null; }
        TInfo item = Topo.Item; Topo = Topo.Proximo; return item;
    }

    public void Imprime() {
        if (Vazia()) System.out.println("Pilha Vazia");
        else { System.out.print("Topo-> "); Nodo aux = Topo; while (aux != null) { System.out.print(aux.Item.chave + " "); aux = aux.Proximo; } System.out.println("<-Base"); }
    }
}
