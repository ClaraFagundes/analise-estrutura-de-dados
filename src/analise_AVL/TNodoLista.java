package analise_AVL;

public class TNodoLista {

    TInfo item;
    TNodoLista proximo;
    TNodo nodo;

    public TNodoLista(TNodo nodo){
        if(nodo != null){
            this.item = new TInfo(nodo.item.chave, nodo.item.nome,nodo.item.brand,nodo.item.country,nodo.item.sexo,
                    nodo.item.ratingVAL,nodo.item.ratingCountry,nodo.item.ano, nodo.item.top,
                    nodo.item.midlle,nodo.item.base,nodo.item.perfurmer,nodo.item.mainaccon
            );
            this.nodo = nodo;
            this.proximo = null;
        }
    }
}
