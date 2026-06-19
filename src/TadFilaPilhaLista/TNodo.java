package TadFilaPilhaLista;
import modeloTinfo.TInfo;

public class TNodo {
    TNodo esq;
    TInfo item;
    TNodo dir;
    TNodo pai;

    public TNodo (TInfo item, TNodo pai){
        this.item = new TInfo(item.chave, item.nome, item.brand, item.country, item.gender,
                item.ratingValue, item.ratingCount, item.year, item.top, item.middle, item.base,
                item.perfumer1, item.perfumer2, item.mainaccord1, item.mainaccord2, item.mainaccord3, item.mainaccord4,
                item.mainaccord5);
        this.esq = null;
        this.dir = null;
        this.pai = pai;
    }

}
