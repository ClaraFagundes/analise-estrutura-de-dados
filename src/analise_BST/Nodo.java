package analise_BST;

public class Nodo {
	protected Nodo esq;
	protected Nodo dir;
	protected Tinfo Item;
	protected Nodo pai;
	
	public Nodo(Tinfo Item,Nodo pai) {
		this.Item = new Tinfo ( Item.Chave, Item.Nome,Item.brand,Item.country, Item.sexo,
		Item.ratingVAL,Item.ratingCountry,Item.ano,
		Item.top,Item.midlle,Item.base,Item.perfurmer,Item.mainaccon);
		this.esq = null;
		this.dir = null;
		this.pai = pai; 
	}
	public Nodo () {
		
	}

	public Nodo getEsq() {
		return esq;
	}

	public void setEsq(Nodo esq) {
		this.esq = esq;
	}

	public Nodo getDir() {
		return dir;
	}

	public void setDir(Nodo dir) {
		this.dir = dir;
	}

	public Tinfo getItem() {
		return Item;
	}

	public void setItem(Tinfo item) {
		Item = item;
	}

	public Nodo getPai() {
		return pai;
	}

	public void setPai(Nodo pai) {
		this.pai = pai;
	}
	@Override
	public String toString() {
		return "Item-> " + Item.Chave + " "+ Item.Nome;
	}
}
