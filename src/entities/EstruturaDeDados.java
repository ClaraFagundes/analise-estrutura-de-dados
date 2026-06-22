package entities;

import common.Perfume;

//Interface feita apenas para possibilitar o polimorfismo (cast)
public interface EstruturaDeDados {

    void insere(Perfume item);

    String imprimirPesquisa(Perfume item);
}
