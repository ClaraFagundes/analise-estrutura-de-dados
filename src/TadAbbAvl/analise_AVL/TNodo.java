package TadAbbAvl.analise_AVL;
import modeloTinfo.TInfo;

public class TNodo {

    TNodo esq;
    TInfo item;
    TNodo dir;
    TNodo pai;

    int bal = 0;
    int hesq = 0;
    int hdir = 0;

    public TNodo(TInfo item, TNodo pai){
        this.item = new TInfo(item.chave, item.nome, item.brand, item.country, item.gender,
                item.ratingValue, item.ratingCount, item.year, item.top, item.middle,
                item.base, item.perfumer1, item.perfumer2, item.mainaccord1, item.mainaccord2,
                item.mainaccord3, item.mainaccord4, item.mainaccord5);
        this.esq = null;
        this.dir = null;
        this.pai = pai;
    }
}