package TadAbbAvl.analise_BST;
import modeloTinfo.TInfo;


public class Arvore_BST {
	protected Nodo T;
	protected long comparacoes = 0;
	
	public Arvore_BST() {
		T = null;
	}
	public void add (TInfo Item) {
		T = add(T,Item,null);
	}

	public long getComparacoes(){
		return comparacoes;
	}
	
	public Nodo add(Nodo T,TInfo Item,Nodo pai) {
		if (T == null) {
			T = new Nodo (Item,pai);
		} else {
			pai = T;

			 if (Item.chave > T.Item.chave) {
				 comparacoes++;
				T.dir = add(T.dir,Item,pai);
			 }

			 else if (Item.chave < T.Item.chave) {
				 comparacoes++;
				 T.esq = add(T.esq,Item,pai);
			 }
		}
		 return T;
	}
	public Nodo pesquisa (TInfo item) {
		return pesquisa (T, item);
	}
	public Nodo pesquisa (Nodo T, TInfo item) {
		if (T == null)
			return T;
		else {
			if (item.chave == T.Item.chave)
				return T;
			else 
				if (item.chave > T.Item.chave)
					T = pesquisa (T.dir,item);
				else 
					T = pesquisa (T.esq,item);
			return T;
		}
	}
	public Nodo Remove(Nodo T, TInfo X) {
	    if (T == null) return T;

	    if (X.	chave == T.Item.chave) {
	        Nodo P = T;

	        if ((T.esq == null) && (T.dir == null)) {
	            if (T.pai == null) {
	                this.T = null;
	                return T;
	            } else {
	                if (T.Item.chave > T.pai.Item.chave)
	                    T.pai.dir = null;
	                else
	                    T.pai.esq = null;
	            }
	        }
	        else
	            if (T.esq == null) {
	                if (T.pai != null) {
	                    if (T.dir != null)
	                        T.dir.pai = T.pai;

	                    if (T.Item.chave > T.pai.Item.chave)
	                        T.pai.dir = T.dir;
	                    else
	                        T.pai.esq = T.dir;
	                } else {
	                    this.T = T.dir;
	                    this.T.pai = null;
	                }
	            }
	            else
	                if (T.dir == null) {
	                    if (T.pai != null) {
	                        if (T.esq != null)
	                            T.esq.pai = T.pai;

	                        if (T.Item.chave > T.pai.Item.chave)
	                            T.pai.dir = T.esq;
	                        else
	                            T.pai.esq = T.esq;
	                    } else {
	                        this.T = T.esq;
	                        this.T.pai = null;
	                    }
	                }
	                else {
	                    P = getMax(T.esq);
	                    T.Item = P.Item;
	                }
	    }
	    else
	        if (X.chave < T.Item.chave)
	            Remove(T.esq, X);
	        else
	            Remove(T.dir, X);

	    return T;
	}
	public Nodo getMax(Nodo T) {
		if (T.dir == null)
		{
			if (T.esq != null ) T.esq.pai = T.pai;
			
			if (T.Item.chave>T.pai.Item.chave)
				T.pai.dir = T.esq;
			else 
				T.pai.esq=T.esq;
			
			return T;
		}
		else 
			return getMax (T.dir);
		
	}
	public void emOrdem (Nodo T) {
		if (T != null)
		{
			emOrdem (T.esq);
			System.out.println(T.Item+" ");
			emOrdem(T.dir);
		}
	}
	public void preOrdem(Nodo T) {
		if (T != null) 
		{
			System.out.println(T.Item+" ");
			preOrdem (T.esq);
			preOrdem (T.dir);
		}
	}
	public void posOrdem (Nodo T) {
		if (T != null)
		{
			posOrdem (T.esq);
			posOrdem (T.dir);
			System.out.println(T.Item);
		}
	}
	public int altura(Nodo no){
		if(no == null)
			return 0;

		return 1 + Math.max(
				altura(no.esq),
				altura(no.dir)
		);
	}
	public Nodo raiz() {
		return T;
	}
}
