import datastructures.ArvoreAVL.TArvoreAVL;
import datastructures.ArvoreBST.ArvoreBST;
import datastructures.ListaApontador.ListaApontador;
import entities.DataStructure;
import entities.Util;
import entities.enums.TipoInsercao;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Programa {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<DataStructure> dataStructures = new ArrayList<>();

        TArvoreAVL arvoreAVL = new TArvoreAVL();
        dataStructures.add(arvoreAVL);

        ArvoreBST arvoreBST = new ArvoreBST();
        dataStructures.add(arvoreBST);

        ListaApontador listaApontadorInicio = new ListaApontador(TipoInsercao.INICIO);
        dataStructures.add(listaApontadorInicio);

        ListaApontador listaApontadorFinal = new ListaApontador(TipoInsercao.FINAL);
        dataStructures.add(listaApontadorFinal);

        ListaApontador listaApontadorOrdenado = new ListaApontador(TipoInsercao.ORDENADO);
        dataStructures.add(listaApontadorOrdenado);


        String arquivo = "src/common/fragrantica_dataset.csv";

        for (DataStructure ds : dataStructures) {
            Util.armazenar(ds, arquivo, 1000);
        }

        Util.pesquisar(dataStructures);
    }
}
