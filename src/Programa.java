import datastructures.ArvoreAVL.TArvoreAVL;
import datastructures.ArvoreBST.ArvoreBST;
import datastructures.ListaApontador.ListaApontador;
import datastructures.ListaArranjo.ListaArranjo;
import datastructures.ListaDupla.ListaDupla;
import datastructures.PilhaApontador.PilhaApontador;
import datastructures.PilhaArranjo.PilhaArranjo;
import datastructures.FilaApontador.FilaApontador;
import datastructures.FilaArranjo.FilaArranjo;
import entities.EstruturaDeDados;
import entities.Util;
import entities.enums.TipoInsercao;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Programa {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        ArrayList<EstruturaDeDados> estruturaDeDados = new ArrayList<>();

        int quantidadeDeDados = 1000;

        //ÁRVORE AVL
        TArvoreAVL arvoreAVL = new TArvoreAVL();
        estruturaDeDados.add(arvoreAVL);

        //ÁRVORE BST
        ArvoreBST arvoreBST = new ArvoreBST();
        estruturaDeDados.add(arvoreBST);

        //LISTA APONTADOR
        //Há 2 variáveis para listas por conta da forma de inserção
        ListaApontador listaApontadorInicio = new ListaApontador(TipoInsercao.INICIO);
        estruturaDeDados.add(listaApontadorInicio);

        ListaApontador listaApontadorFinal = new ListaApontador(TipoInsercao.FINAL);
        estruturaDeDados.add(listaApontadorFinal);

        //LISTA ARRANJO
        //Tive que colocar a quantidade de dados no construtor por conta que tem que inicializar o array...
        ListaArranjo listaArranjoInicio = new ListaArranjo(TipoInsercao.INICIO, quantidadeDeDados);
        estruturaDeDados.add(listaArranjoInicio);

        ListaArranjo listaArranjoFinal = new ListaArranjo(TipoInsercao.FINAL, quantidadeDeDados);
        estruturaDeDados.add(listaArranjoFinal);

        //LISTA DUPLAMENTE ENCADEADA
        ListaDupla listaDuplaInicio = new ListaDupla(TipoInsercao.INICIO);
        estruturaDeDados.add(listaDuplaInicio);

        ListaDupla listaDuplaFinal = new ListaDupla(TipoInsercao.FINAL);
        estruturaDeDados.add(listaDuplaFinal);

        String arquivo = "src/common/fragrantica_dataset.csv";

        for (EstruturaDeDados ds : estruturaDeDados) {
            Util.armazenar(ds, arquivo, quantidadeDeDados);
        }

        Util.pesquisar(estruturaDeDados);
    }
}
