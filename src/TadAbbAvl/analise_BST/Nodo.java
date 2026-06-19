package TadAbbAvl.analise_BST;
import modeloTinfo.TInfo;

public class Nodo {
	protected Nodo esq;
	protected Nodo dir;
	protected TInfo Item;
	protected Nodo pai;
	
	public Nodo(TInfo Item,Nodo pai) {
		this.Item = new TInfo (Item.chave, Item.nome);
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

	public TInfo getItem() {
		return Item;
	}

	public void setItem(TInfo item) {
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
		return "Item-> " + Item.chave + " "+ Item.nome;
	}
}
