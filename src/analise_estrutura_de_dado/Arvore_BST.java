package analise_estrutura_de_dado;


public class Arvore_BST {
protected Nodo T;
	
	public Arvore_BST() {
		T = null;
	}
	public void add (Tinfo Item) {
		T = add(T,Item,null);
	}
	
	public Nodo add(Nodo T,Tinfo Item,Nodo pai) {
		if (T == null) {
			T = new Nodo (Item,pai);
		} else {
			pai = T;
			 if (Item.Chave > T.Item.Chave) {
				T.dir = add(T.dir,Item,pai);
			 }
			 else if (Item.Chave < T.Item.Chave) {
				 T.esq = add(T.esq,Item,pai);
			 }
		}
		 return T;
	}
	public Nodo pesquisa (Tinfo item) {
		return pesquisa (T, item);
	}
	public Nodo pesquisa (Nodo T, Tinfo item) {
		if (T == null)
			return T;
		else {
			if (item.Chave == T.Item.Chave)
				return T;
			else 
				if (item.Chave > T.Item.Chave)
					T = pesquisa (T.dir,item);
				else 
					T = pesquisa (T.esq,item);
			return T;
		}
	}
	public Nodo Remove(Nodo T, Tinfo X) {
	    if (T == null) return T;

	    if (X.	Chave == T.Item.Chave) {
	        Nodo P = T;

	        if ((T.esq == null) && (T.dir == null)) {
	            if (T.pai == null) {
	                this.T = null;
	                return T;
	            } else {
	                if (T.Item.Chave > T.pai.Item.Chave)
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

	                    if (T.Item.Chave > T.pai.Item.Chave)
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

	                        if (T.Item.Chave > T.pai.Item.Chave)
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
	        if (X.Chave < T.Item.Chave)
	            Remove(T.esq, X);
	        else
	            Remove(T.dir, X);

	    return T;
	}
	public Nodo getMax(Nodo T) {
		if (T.dir == null)
		{
			if (T.esq != null ) T.esq.pai = T.pai;
			
			if (T.Item.Chave>T.pai.Item.Chave)
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
}
