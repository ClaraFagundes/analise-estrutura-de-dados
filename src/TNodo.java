public class TNodo {
    TNodo esq;
    TInfo item;
    TNodo dir;
    TNodo pai;

    public TNodo (TInfo item, TNodo pai){
        this.item = new TInfo(item.chave, item.nome);
        this.esq = null;
        this.dir = null;
        this.pai = pai;
    }

}
