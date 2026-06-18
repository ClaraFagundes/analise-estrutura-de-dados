import analise_AVL.*;
import analise_BST.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

public class Pesquisa {
    public static void main (String[] args){
        String linha;
        int contador = 0;
        String arquivo = "src/fragantica_dataset.csv";

        TArvoreAVL AVL = new TArvoreAVL();
        Arvore_BST BST = new Arvore_BST();

        try( BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            linha = br.readLine();
            linha = br.readLine();
            while (linha != null && contador < 30){
                    String dados[] = linha.split(";");
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    BST.add(new Tinfo(id,nome));
                    AVL.insere(new TInfo(id,nome));
                    linha = br.readLine();
                    contador++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
