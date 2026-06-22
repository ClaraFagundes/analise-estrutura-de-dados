package entities;

import common.Perfume;

//Interface feita apenas para possibilitar o polimorfismo (cast)
public interface DataStructure {

    public void insere(Perfume item);

    public String imprimirPesquisa(Perfume item);
}
