package ed1.tads;

public class FilaApontador {

    private class Nodo {
        TInfo Item;
        Nodo Proximo;
    }

    private Nodo Frente;
    private Nodo Tras;

    public FilaApontador() { Frente = new Nodo(); Tras = Frente; Frente.Proximo = null; }
    public boolean Vazia() { return Frente == Tras; }

    public void Enfileira(TInfo item) { Nodo p = new Nodo(); p.Item = item; p.Proximo = null; Tras.Proximo = p; Tras = p; }
    public TInfo Desenfileira() {
        if (Vazia()) { System.out.println("Fila Vazia"); return null; }
        Nodo p = Frente.Proximo; TInfo item = p.Item; Frente.Proximo = p.Proximo;
        if (Frente.Proximo == null) Tras = Frente; return item;
    }

    public void Imprime() {
        if (Vazia()) System.out.println("Fila Vazia");
        else { System.out.print("Frente-> "); Nodo aux = Frente.Proximo; while (aux != null) { System.out.print(aux.Item.Chave + " "); aux = aux.Proximo; } System.out.println("<-Tras"); }
    }
}
