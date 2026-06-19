package TadFilaPilhaLista.ed1.tads;
import modeloTinfo.TInfo;

public class FilaArranjo {
    private final int Tmax = 50;
    private TInfo[] Item;
    private int Frente, Tras;

    public FilaArranjo() { Item = new TInfo[Tmax]; Frente = 0; Tras = 0; }
    public boolean Vazia() { return Frente == Tras; }
    public boolean Cheia() { return ((Tras + 1) % Tmax) == Frente; }

    public void Enfileira(TInfo item) { if (Cheia()) System.out.println("Fila Cheia"); else { Item[Tras] = item; Tras = (Tras + 1) % Tmax; } }
    public TInfo Desenfileira() {
        if (Vazia()) { System.out.println("Fila Vazia"); return null; }
        TInfo item = Item[Frente]; Item[Frente] = null; Frente = (Frente + 1) % Tmax; return item;
    }

    public void Imprime() {
        if (Vazia()) System.out.println("Fila Vazia");
        else { System.out.print("Frente-> "); int i = Frente; while (i != Tras) { System.out.print(Item[i].chave + " "); i = (i + 1) % Tmax; } System.out.println("<-Tras"); }
    }
}
