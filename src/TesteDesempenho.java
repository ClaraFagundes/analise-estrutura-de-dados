import datastructures.ArvoreAVL.TArvoreAVL;
import datastructures.ArvoreBST.ArvoreBST;
import entities.Cronometro;
import entities.Util;

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
}