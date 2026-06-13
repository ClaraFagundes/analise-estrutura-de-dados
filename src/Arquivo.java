import java.io.FileReader; // esse pacote ler apenas um caractere por vez, para usar o BufferedREader é necessário
//adicionar esse, pois ele complementa o outro;

import java.io.BufferedReader;//pacote que especificamnete lê arquivos em java. A diferença dele para File.io.FileReader
// é que esse tem um ação que ajuda a ler uam linha completa, é próprio para isso;

import java.io.IOException;//tratamento de erros, é necesssário para tratar erros como se o programa não conseguir abrir o arquivo
//ou se ele não existe; try-catch

import java.util.ArrayList;

public class Arquivo {

    public ArrayList<Perfume> lerArquivo() {

        ArrayList<Perfume> perfumes = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("fragrantica_dataset.csv"));

            br.readLine(); // lê a primeira linha, pois essa é cabeçalho, se eu não pular ela, dá problema pois é tudo texto;

            String linha;

            while ((linha = br.readLine()) != null) {

                //String[] dados = linha.split(";");
                String[] dados = linha.split(";", -1);

                Perfume p = new Perfume();

                // id
                if (dados.length > 0 && !dados[0].trim().isEmpty()) {
                    p.id = Integer.parseInt(dados[0]);
                } else {
                    p.id = 0;
                }

                // perfume
                if (dados.length > 1) {
                    p.perfume = dados[1];
                } else {
                    p.perfume = "";
                }

                // brand
                if (dados.length > 2) {
                    p.brand = dados[2];
                } else {
                    p.brand = "";
                }

                // country
                if (dados.length > 3) {
                    p.country = dados[3];
                } else {
                    p.country = "";
                }

                // gender
                if (dados.length > 4) {
                    p.gender = dados[4];
                } else {
                    p.gender = "";
                }

                // ratingValue
                try {
                    p.ratingValue = Double.parseDouble(dados[5].replace(",", "."));
                } catch (NumberFormatException e) {
                    p.ratingValue = 0; // valor padrão
                }

                // ratingCount
                if (dados.length > 6 && !dados[6].trim().isEmpty()) {
                    p.ratingCount = Integer.parseInt(dados[6].replace(",", ""));
                } else {
                    p.ratingCount = 0;
                }

                // year
                if (dados.length > 7 && !dados[7].trim().isEmpty()) {
                    p.year = Double.parseDouble(dados[7].replace(",", ""));
                } else {
                    p.year = 0;
                }

                perfumes.add(p);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }

        return perfumes;
    }
}