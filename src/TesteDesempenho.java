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

            String arquivo = "src/complete_fragrantica_dataset.csv";

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
                String brand = dados[2];
                String country = dados[3];
                String sexo = dados [4];
                double ratingVAL = Double.parseDouble(dados[5].replace(",", "."));
                int ratingCountry = Integer.parseInt(dados[6]);
                int ano = Integer.parseInt(dados[7]);
                String top = dados[8];
                String midlle = dados[9];
                String base = dados[10];
                String perfurmer = dados[11];
                String mainaccon = dados [12];
                bst.add(
                        new Tinfo(id,nome,brand,country,sexo,ratingVAL,ratingCountry,ano,top,midlle,base,perfurmer,mainaccon)
                );

                if(exibir){
                    System.out.println(
                            id + " - " + nome+brand+sexo+ratingVAL+ratingCountry+ano+top+midlle+base+perfurmer+mainaccon
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
                String brand = dados[2];
                String country = dados[3];
                String sexo = dados [4];
                double ratingVAL = Double.parseDouble(dados[5].replace(",", "."));
                int ratingCountry = Integer.parseInt(dados[6]);
                int ano = Integer.parseInt(dados[7]);
                String top = dados[8];
                String midlle = dados[9];
                String base = dados[10];
                String perfurmer = dados[11];
                String mainaccon = dados [12];

                avl.insere(
                        new TInfo(id,nome,brand,country,sexo,ratingVAL,ratingCountry,ano,top,midlle,base,perfurmer,mainaccon)
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