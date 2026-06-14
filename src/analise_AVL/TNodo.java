package analise_AVL;

public class TNodo {

    TNodo esq;
    TInfo item;
    TNodo dir;
    TNodo pai;

    int bal = 0;
    int hesq = 0;
    int hdir = 0;

    public TNodo(TInfo item, TNodo pai){
        this.item = new TInfo(item.chave, item.nome);
        this.esq = null;
        this.dir = null;
        this.pai = pai;
    }
}