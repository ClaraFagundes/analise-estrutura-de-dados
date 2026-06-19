package TadAbbAvl.analise_AVL;
import modeloTinfo.TInfo;

public class TNodoLista {

    TInfo item;
    TNodoLista proximo;
    TNodo nodo;

    public TNodoLista(TNodo nodo){
        if(nodo != null){
            this.item = new TInfo(nodo.item.chave, nodo.item.nome, nodo.item.brand, nodo.item.country,
                    nodo.item.gender, nodo.item.ratingValue, nodo.item.ratingCount, nodo.item.year, nodo.item.top,
                    nodo.item.middle, nodo.item.base, nodo.item.perfumer1, nodo.item.perfumer2, nodo.item.mainaccord1,
                    nodo.item.mainaccord2, nodo.item.mainaccord3, nodo.item.mainaccord4, nodo.item.mainaccord5);
            this.nodo = nodo;
            this.proximo = null;
        }
    }
}
