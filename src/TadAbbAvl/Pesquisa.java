package TadAbbAvl;
import TadAbbAvl.analise_AVL.*;
import TadAbbAvl.analise_BST.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

import modeloTinfo.TInfo;

public class Pesquisa {
    public static void main (String[] args){
        String linha;
        int contador = 0;
        String arquivo = "complete_fragrantica_dataset.csv";

        TArvoreAVL AVL = new TArvoreAVL();
        Arvore_BST BST = new Arvore_BST();

        try( BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            linha = br.readLine();
            linha = br.readLine();
            while (linha != null && contador < 30){
                    String dados[] = linha.split(";");
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String brand = dados[2];
                    String country = dados[3];
                    String gender = dados[4];
                    double ratingValue = Double.parseDouble(dados[5]);
                    double ratingCount = Double.parseDouble(dados[6]);
                    double year = Double.parseDouble(dados[7]);
                    String top = dados[8];
                    String middle = dados[9];
                    String base = dados[10];
                    String perfumer1 = dados[11];
                    String perfumer2 = dados[12];
                    String mainaccord1 = dados[13];
                    String mainaccord2 = dados[14];
                    String mainaccord3 = dados[15];
                    String mainaccord4 = dados[16];
                    String mainaccord5 = dados[17];
                    BST.add(new TInfo(id, nome, brand, country, gender,
                            ratingValue, ratingCount, year, top, middle, base, perfumer1,
                            perfumer2, mainaccord1, mainaccord2, mainaccord3, mainaccord4, mainaccord5));
                    AVL.insere(new TInfo(id, nome, brand, country, gender,
                            ratingValue, ratingCount, year, top, middle, base, perfumer1,
                            perfumer2, mainaccord1, mainaccord2, mainaccord3, mainaccord4, mainaccord5));
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
