import datastructures.ArvoreAVL.TArvoreAVL;
import datastructures.ArvoreBST.ArvoreBST;
import entities.DataStructure;
import entities.Util;

import java.io.FileNotFoundException;

public class Programa {

    public static void main(String[] args) throws FileNotFoundException {

        TArvoreAVL arvoreAVL10000 = new TArvoreAVL();
        ArvoreBST arvoreBST10000 = new ArvoreBST();

        String arquivo = "src/common/fragrantica_dataset.csv";

        Util.armazenar((DataStructure) arvoreAVL10000, arquivo, 10000);
        Util.armazenar((DataStructure) arvoreBST10000, arquivo, 10000);

        Util.pesquisar(arvoreAVL10000, arvoreBST10000);
    }
}
