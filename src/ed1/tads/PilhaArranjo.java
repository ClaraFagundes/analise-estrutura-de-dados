package ed1.tads;

public class PilhaArranjo {
    private final int Tmax = 50;
    private TInfo[] Item;
    private int Topo;

    public PilhaArranjo() { Item = new TInfo[Tmax]; Topo = -1; }
    public boolean Vazia() { return Topo == -1; }
    public boolean Cheia() { return Topo >= Tmax - 1; }

    public void Empilha(TInfo item) { if (Cheia()) System.out.println("Pilha Cheia"); else Item[++Topo] = item; }
    public TInfo Desempilha() { if (Vazia()) { System.out.println("Pilha Vazia"); return null; } return Item[Topo--]; }

    public void Imprime() {
        if (Vazia()) System.out.println("Pilha Vazia");
        else { System.out.print("Topo-> "); for (int i = Topo; i >= 0; i--) System.out.print(Item[i].Chave + " "); System.out.println("<-Base"); }
    }
}
