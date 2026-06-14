package analise_AVL;

public class TNodoLista {

    TInfo item;
    TNodoLista proximo;
    TNodo nodo;

    public TNodoLista(TNodo nodo){
        if(nodo != null){
            this.item = new TInfo(nodo.item.chave, nodo.item.nome);
            this.nodo = nodo;
            this.proximo = null;
        }
    }
}
