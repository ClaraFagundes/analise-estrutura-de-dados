package datastructures.ArvoreBST;

import common.Perfume;

public class Nodo {
	protected Nodo esq;
	protected Nodo dir;
	protected Perfume Item;
	protected Nodo pai;

	public Nodo () {}

	public Nodo(Perfume Item, Nodo pai) {
		this.Item = Item;
		this.esq = null;
		this.dir = null;
		this.pai = pai;
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

	public Perfume getItem() {
		return Item;
	}

	public void setItem(Perfume item) {
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
		return "Item-> " + Item.getChave() + " "+ Item.getNome();
	}
}
