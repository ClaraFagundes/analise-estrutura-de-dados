package analise_AVL;

import analise_BST.Nodo;
import analise_BST.Tinfo;

public class TNodo {

    TNodo esq;
    TInfo item;
    TNodo dir;
    TNodo pai;

    int bal = 0;
    int hesq = 0;
    int hdir = 0;

    public TNodo(TInfo item, TNodo pai){
            this.item = new TInfo (
                    item.chave, item.nome,item.brand,item.country,item.sexo,
                    item.ratingVAL,item.ratingCountry,item.ano, item.top,
                    item.midlle,item.base,item.perfurmer,item.mainaccon
            );
        this.esq = null;
        this.dir = null;
        this.pai = pai;
    }
}