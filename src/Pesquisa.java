import analise_AVL.*;
import analise_BST.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

public class Pesquisa {
    public static void main (String[] args){
        Scanner sc = new Scanner (System.in);
        String linha;
        int contador = 0;
        String arquivo = "src/complete_fragrantica_dataset.csv";

        TArvoreAVL AVL = new TArvoreAVL();
        Arvore_BST BST = new Arvore_BST();

        try( BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            linha = br.readLine();
            linha = br.readLine();
            while (linha != null && contador < 2000 ){
                    String dados[] = linha.split(";");
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String brand = dados[2];
                    String country = dados[3];
                    String sexo = dados [4];
                    double ratingVAL = Double.parseDouble(dados[5].replace(",", "."));
                    int ratingCountry = Integer.parseInt(dados[6]);
                    double ano = Double.parseDouble(dados[7]);
                    String top = dados[8];
                    String midlle = dados[9];
                    String base = dados[10];
                    String perfurmer = dados[11];
                    String mainaccon = dados [12];
                    BST.add(new Tinfo(id,nome,brand,country,sexo,ratingVAL,ratingCountry,ano,top,midlle,base,perfurmer,mainaccon));
                    AVL.insere(new TInfo(id,nome,brand,country,sexo,ratingVAL,ratingCountry,ano,top,midlle,base,perfurmer,mainaccon));
                    linha = br.readLine();
                    contador++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println ("Digite a chave a ser pesquisada: ");
        int chave = sc.nextInt();
        TInfo item = new TInfo(chave);
        Tinfo item2 = new Tinfo(chave);

        Nodo no = BST.pesquisa(BST.getRaiz(),item2);
        TNodo no2 = AVL.pesquisa(AVL.T,item);

        if (no != null && no2 != null){
            System.out.println("AVL: "+no2.getItem() + " " + "Quantidade de comaparações: " + AVL.getComparacoes()
            + " Rotações: " + AVL.getRotacoes());
            System.out.println("BST: "+no.getItem() + " " + "Quantidade de comaparações: " + BST.getComparacoes());
        }

        else {
            System.out.println("árvore vazia ...");
        }


    }
}
