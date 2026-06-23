import datastructures.ArvoreAVL.TArvoreAVL;
import datastructures.ArvoreBST.ArvoreBST;
import datastructures.FilaApontador.FilaApontador;
import datastructures.FilaArranjo.FilaArranjo;
import datastructures.ListaArranjo.ListaArranjo;
import datastructures.ListaApontador.ListaApontador;
import datastructures.ListaDupla.ListaDupla;
import datastructures.PilhaApontador.PilhaApontador;
import datastructures.PilhaArranjo.PilhaArranjo;
import entities.Cronometro;
import entities.Util;
import common.Perfume;
import entities.EstruturaDeDados;
import entities.enums.TipoInsercao;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TesteDesempenho {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Testar com 1.000 registros");
            System.out.println("2 - Testar com 10.000 registros");
            System.out.println("3 - Testar com 50.000 registros");
            System.out.println("4 - Testar com 91.134 registros");
            System.out.println("5 - Testar com outra quantidade de registros");
            System.out.println("6 - Testar pesquisar um item");
            System.out.println("0 - Sair");

            System.out.print("Opção: ");
            int opcao = sc.nextInt();

            if (opcao == 0)
                break;

            int limite = 0;

            switch (opcao) {
                case 1:
                    limite = 1000;
                    break;
                case 2:
                    limite = 10000;
                    break;
                case 3:
                    limite = 50000;
                    break;
                case 4:
                    limite = 91134;
                    break;
                case 5:
                    System.out.print("Digite a quantidade de registros: ");
                    limite = sc.nextInt();
                    break;
                case 6:
                    System.out.print("\nDigite o limite de registros: \n1- 1.000\n2- 10.000\n3- 50.000\n4- 91.134\n");
                    System.out.print("Opção: ");
                    limite = sc.nextInt();
                    //sc.close();//teste para ver se é a entrada que está sendo consumida
                    executarPesquisas(limite, sc);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    continue;
            }

            executarTeste(limite);
        }

        sc.close();
    }

    public static void executarTeste(int limite) {
        try {
            String arquivo = "src/common/fragrantica_dataset.csv";

            ArvoreBST bst = new ArvoreBST();
            TArvoreAVL avl = new TArvoreAVL();

            Cronometro tempoBST =
                    Util.armazenar(bst, arquivo, limite);

            Cronometro tempoAVL =
                    Util.armazenar(avl, arquivo, limite);

            System.out.println("\n===== RESULTADOS =====");

            System.out.println("\nBST");
            System.out.println("Tempo: " + tempoBST);
//            System.out.println("Altura: " + bst.altura(bst.getRaiz()));
            System.out.println(
                    "Comparações: " +
                            bst.getComparacoes()
            );

            System.out.println("\nAVL");
            System.out.println("Tempo: " + tempoAVL);
            System.out.println("Altura: " + avl.altura(avl.raiz()));
            System.out.println(
                    "Comparações: " +
                            avl.getComparacoes()
            );
            System.out.println(
                    "Rotações: " +
                            avl.getRotacoes()
            );

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void executarPesquisas(int limite, Scanner sc){
        try {
            String arquivo = "src/common/fragrantica_dataset.csv";

            if(limite == 1){limite = 1000;}
            else if(limite == 2){limite = 10000;}
            else if(limite == 3){limite = 50000;}
            else{limite = 91134;}

            ArvoreBST bst = new ArvoreBST();
            TArvoreAVL avl = new TArvoreAVL();
            ListaArranjo LArranjo = new ListaArranjo(TipoInsercao.FINAL, limite);
            ListaApontador LApontador = new ListaApontador(TipoInsercao.FINAL);
            ListaDupla LDupla = new ListaDupla(TipoInsercao.FINAL);
            FilaApontador FApontador = new FilaApontador();
            FilaArranjo FArranjo = new FilaArranjo();
            PilhaApontador PApontador = new PilhaApontador();
            PilhaArranjo PArranjo = new PilhaArranjo();

            Util.armazenar(bst, arquivo, limite);
            Util.armazenar(avl, arquivo, limite);
            Util.armazenar(LArranjo, arquivo, limite);
            Util.armazenar(LApontador, arquivo, limite);
            Util.armazenar(LDupla, arquivo, limite);
            Util.armazenar(FApontador, arquivo, limite);
            Util.armazenar(FArranjo, arquivo, limite);
            Util.armazenar(PApontador, arquivo, limite);
            Util.armazenar(PArranjo, arquivo, limite);

            System.out.println("\n===== RESULTADOS DA PESQUISA=====");

            ArrayList<EstruturaDeDados> estruturas = new ArrayList<>();
            estruturas.add(bst);
            estruturas.add(avl);
            estruturas.add(LArranjo);
            estruturas.add(LApontador);
            estruturas.add(LDupla);
            estruturas.add(FApontador);
            estruturas.add(FArranjo);
            estruturas.add(PApontador);
            estruturas.add(PArranjo);

            Util.pesquisar(estruturas, sc);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}