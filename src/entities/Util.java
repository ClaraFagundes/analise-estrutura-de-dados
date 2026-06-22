package entities;

import datastructures.ArvoreAVL.TArvoreAVL;
import common.Perfume;
import datastructures.ArvoreAVL.TNodo;
import datastructures.ArvoreBST.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

public class Util {
    private static Scanner sc = new Scanner(System.in);

    public static Cronometro armazenar(DataStructure estruturaDeDados, String arquivo, int quantidade) throws FileNotFoundException {
        Cronometro cronometro = new Cronometro();
        cronometro.iniciar();

        String linha;
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            linha = br.readLine();

            while (linha != null && contador < quantidade) {
                linha = br.readLine();

                String dados[] = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String brand = dados[2];
                String country = dados[3];
                String sexo = dados[4];
                double ratingVAL = Double.parseDouble(dados[5].replace(",", "."));
                int ratingCountry = Integer.parseInt(dados[6]);
                double ano = Double.parseDouble(dados[7]);
                String top = dados[8];
                String midlle = dados[9];
                String base = dados[10];
                String perfurmer = dados[11];
                String mainaccon = dados[12];

                Perfume perfume = new Perfume(id, nome, brand, country, sexo, ratingVAL, ratingCountry, ano, top, midlle, base, perfurmer, mainaccon);

                estruturaDeDados.insere(perfume);

                contador++;
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("O arquivo não foi encontrado!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        cronometro.finalizar();
        return cronometro;
    }

    public static void pesquisar(TArvoreAVL AVL, ArvoreBST BST) {
        System.out.print("Digite a chave a ser pesquisada [Digite 0 para encerrar]: ");
        int chave = sc.nextInt();

        while (chave != 0) {
            Perfume item = new Perfume(chave);

            TNodo pesquisaAVL = AVL.pesquisa(AVL.T, item);
            Nodo pesquisaBST = BST.pesquisa(BST.getRaiz(), item);

            if (pesquisaBST != null && pesquisaAVL != null) {
                System.out.println("\nAVL: " + pesquisaAVL.getItem() + " | Quantidade de comaparações: " + AVL.getComparacoes()
                        + " | Rotações: " + AVL.getRotacoes());
                System.out.println("BST: " + pesquisaBST.getItem() + " | Quantidade de comaparações: " + BST.getComparacoes());
            } else {
                System.out.println("árvore vazia ...");
            }

            System.out.print("\nDigite a chave a ser pesquisada [Digite 0 para encerrar]: ");
            chave = sc.nextInt();
        }

    }
}
