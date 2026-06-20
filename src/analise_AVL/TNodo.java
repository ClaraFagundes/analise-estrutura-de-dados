package analise_AVL;

import analise_BST.Nodo;
import analise_BST.Tinfo;

public class TNodo {

    public TNodo esq;
    public TInfo item;
    public TNodo dir;
    public TNodo pai;

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
    public TNodo () {

    }

    public TNodo getEsq() {
        return esq;
    }

    public void setEsq(TNodo esq) {
        this.esq = esq;
    }

    public TNodo getDir() {
        return dir;
    }

    public void setDir(TNodo dir) {
        this.dir = dir;
    }

    public TInfo getItem() {
        return item;
    }

    public void setItem(Tinfo item) {
        item = item;
    }

    public TNodo getPai() {
        return pai;
    }
}