package datastructures.ArvoreAVL;

import common.Perfume;

public class TNodo {

    public TNodo esq;
    public Perfume item;
    public TNodo dir;
    public TNodo pai;

    int bal = 0;
    int hesq = 0;
    int hdir = 0;

    public TNodo () {

    }

    public TNodo(Perfume item, TNodo pai){
        this.item = item;
        this.esq = null;
        this.dir = null;
        this.pai = pai;
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

    public Perfume getItem() {
        return item;
    }

    public void setItem(Perfume item) {
        item = item;
    }

    public TNodo getPai() {
        return pai;
    }
}