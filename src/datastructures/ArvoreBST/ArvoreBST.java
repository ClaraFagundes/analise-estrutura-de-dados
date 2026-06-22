package datastructures.ArvoreBST;


import common.Perfume;
import datastructures.ArvoreAVL.TNodo;
import entities.DataStructure;

public class ArvoreBST implements DataStructure {
    protected Nodo T;
    protected long comparacoes = 0;

    public ArvoreBST() {
        T = null;
    }

    public void insere(Perfume Item) {
        T = insere(T, Item, null);
    }

    public Nodo insere(Nodo T, Perfume Item, Nodo pai) {
        if (T == null) {
            T = new Nodo(Item, pai);
        } else {
            pai = T;

            if (Item.getChave() > T.Item.getChave()) {
                T.dir = insere(T.dir, Item, pai);
            } else if (Item.getChave() < T.Item.getChave()) {
                T.esq = insere(T.esq, Item, pai);
            }
        }
        return T;
    }

    public Nodo pesquisa(Perfume item) {
        return pesquisa(T, item);
    }

    public Nodo pesquisa(Nodo T, Perfume item) {
        if (T == null)
            return T;
        else {
            if (item.getChave() == T.Item.getChave()) {
                return T;
            } else if (item.getChave() > T.Item.getChave()) {
                comparacoes++;
                T = pesquisa(T.dir, item);
            } else {
                comparacoes++;
                T = pesquisa(T.esq, item);
            }
            return T;
        }
    }

    public Nodo Remove(Nodo T, Perfume X) {
        if (T == null) return T;

        if (X.getChave() == T.Item.getChave()) {
            Nodo P = T;

            if ((T.esq == null) && (T.dir == null)) {
                if (T.pai == null) {
                    this.T = null;
                    return T;
                } else {
                    if (T.Item.getChave() > T.pai.Item.getChave())
                        T.pai.dir = null;
                    else
                        T.pai.esq = null;
                }
            } else if (T.esq == null) {
                if (T.pai != null) {
                    if (T.dir != null)
                        T.dir.pai = T.pai;

                    if (T.Item.getChave() > T.pai.Item.getChave())
                        T.pai.dir = T.dir;
                    else
                        T.pai.esq = T.dir;
                } else {
                    this.T = T.dir;
                    this.T.pai = null;
                }
            } else if (T.dir == null) {
                if (T.pai != null) {
                    if (T.esq != null)
                        T.esq.pai = T.pai;

                    if (T.Item.getChave() > T.pai.Item.getChave())
                        T.pai.dir = T.esq;
                    else
                        T.pai.esq = T.esq;
                } else {
                    this.T = T.esq;
                    this.T.pai = null;
                }
            } else {
                P = getMax(T.esq);
                T.Item = P.Item;
            }
        } else if (X.getChave() < T.Item.getChave())
            Remove(T.esq, X);
        else
            Remove(T.dir, X);

        return T;
    }

    public Nodo getMax(Nodo T) {
        if (T.dir == null) {
            if (T.esq != null) T.esq.pai = T.pai;

            if (T.Item.getChave() > T.pai.Item.getChave())
                T.pai.dir = T.esq;
            else
                T.pai.esq = T.esq;

            return T;
        } else
            return getMax(T.dir);

    }

    public void emOrdem(Nodo T) {
        if (T != null) {
            emOrdem(T.esq);
            System.out.println(T.Item + " ");
            emOrdem(T.dir);
        }
    }

    public void preOrdem(Nodo T) {
        if (T != null) {
            System.out.println(T.Item + " ");
            preOrdem(T.esq);
            preOrdem(T.dir);
        }
    }

    public void posOrdem(Nodo T) {
        if (T != null) {
            posOrdem(T.esq);
            posOrdem(T.dir);
            System.out.println(T.Item);
        }
    }

    public int altura(Nodo no) {
        if (no == null)
            return 0;

        return 1 + Math.max(
                altura(no.esq),
                altura(no.dir)
        );
    }

    public Nodo raiz() {
        return T;
    }

    public Nodo getRaiz() {
        return T;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public String imprimirPesquisa(Perfume item) {
        Nodo nodo = pesquisa(item);
        return "BST: " + nodo.getItem() + " | Quantidade de comaparações: " + getComparacoes();
    }
}
