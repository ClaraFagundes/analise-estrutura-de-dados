import datastructures.ArvoreAVL.TArvoreAVL;
import datastructures.ArvoreBST.ArvoreBST;
import datastructures.ListaApontador.ListaApontador;
import datastructures.ListaArranjo.ListaArranjo;
import entities.DataStructure;
import entities.Util;
import entities.enums.TipoInsercao;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Programa {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<DataStructure> dataStructures = new ArrayList<>();

        int quantidadeDeDados = 1000;

        TArvoreAVL arvoreAVL = new TArvoreAVL();
        dataStructures.add(arvoreAVL);

        ArvoreBST arvoreBST = new ArvoreBST();
        dataStructures.add(arvoreBST);

        //Há 3 variáveis para listas por conta da forma de inserção
        ListaApontador listaApontadorInicio = new ListaApontador(TipoInsercao.INICIO);
        dataStructures.add(listaApontadorInicio);

        ListaApontador listaApontadorFinal = new ListaApontador(TipoInsercao.FINAL);
        dataStructures.add(listaApontadorFinal);

        ListaApontador listaApontadorOrdenado = new ListaApontador(TipoInsercao.ORDENADO);
        dataStructures.add(listaApontadorOrdenado);

        //Tive que colocar a quantidade de dados no construtor por conta que tem que inicializar o array...
        ListaArranjo listaArranjoInicio = new ListaArranjo(TipoInsercao.INICIO, quantidadeDeDados);
        dataStructures.add(listaArranjoInicio);

        ListaArranjo listaArranjoFinal = new ListaArranjo(TipoInsercao.FINAL, quantidadeDeDados);
        dataStructures.add(listaArranjoFinal);

        String arquivo = "src/common/fragrantica_dataset.csv";

        for (DataStructure ds : dataStructures) {
            Util.armazenar(ds, arquivo, quantidadeDeDados);
        }

        Util.pesquisar(dataStructures);
    }
}
