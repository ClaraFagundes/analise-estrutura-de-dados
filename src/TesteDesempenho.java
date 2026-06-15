import analise_AVL.*;
import analise_BST.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class TesteDesempenho {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Testar com 100 registros");
            System.out.println("2 - Testar com 1000 registros");
            System.out.println("3 - Testar com 5000 registros");
            System.out.println("0 - Sair");

            int opcao = sc.nextInt();

            if(opcao == 0)
                break;

            int limite = 0;

            switch(opcao){
                case 1:
                    limite = 100;
                    break;

                case 2:
                    limite = 1000;
                    break;

                case 3:
                    limite = 5000;
                    break;

                default:
                    System.out.println("Opção inválida");
                    continue;
            }

            System.out.println("\nDeseja exibir os registros?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            boolean exibir = sc.nextInt() == 1;

            executarTeste(limite, exibir);
        }

        sc.close();
    }

    public static void executarTeste(int limite, boolean exibir){

        try{

            String arquivo = "src/fragrantica_dataset.csv";

            Arvore_BST bst = new Arvore_BST();

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(arquivo)
                    );

            br.readLine();

            String linha;

            int contador = 0;

            long inicioBST = System.nanoTime();

            while((linha = br.readLine()) != null && contador < limite){

                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);

                String nome = dados[1];

                bst.add(
                        new Tinfo(id,nome)
                );

                if(exibir){
                    System.out.println(
                            id + " - " + nome
                    );
                }

                contador++;
            }

            long fimBST = System.nanoTime();

            br.close();

            TArvoreAVL avl = new TArvoreAVL();

            br = new BufferedReader(
                    new FileReader(arquivo)
            );

            br.readLine();

            contador = 0;

            long inicioAVL = System.nanoTime();

            while((linha = br.readLine()) != null && contador < limite){

                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);

                String nome = dados[1];

                avl.insere(
                        new TInfo(id,nome)
                );

                contador++;
            }

            long fimAVL = System.nanoTime();

            br.close();

            System.out.println("\n===== RESULTADOS =====");

            System.out.println("\nBST");

            System.out.println(
                    "Tempo: "
                            + (fimBST - inicioBST)
                            + " ns"
            );

            System.out.println(
                    "Altura: "
                            + bst.altura(bst.raiz())
            );

            System.out.println(
                    "Comparações: "
                            + bst.getComparacoes()
            );

            System.out.println("\nAVL");

            System.out.println(
                    "Tempo: "
                            + (fimAVL - inicioAVL)
                            + " ns"
            );

            System.out.println(
                    "Altura: "
                            + avl.altura(avl.raiz())
            );

            System.out.println(
                    "Comparações: "
                            + avl.getComparacoes()
            );

            System.out.println(
                    "Rotações: "
                            + avl.getRotacoes()
            );

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}