import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {

    public ArrayList<Perfume> lerArquivo() {

        ArrayList<Perfume> perfumes = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("complete_fragrantica_dataset.csv"));

            br.readLine(); // lê a primeira linha, pois essa é cabeçalho, se eu não pular ela, dá problema pois é tudo texto;

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";", -1);

                Perfume p = new Perfume();

                // chave
                if (dados.length > 0 && !dados[0].trim().isEmpty()) {
                    p.id = Integer.parseInt(dados[0]);
                } else {
                    p.id = 0;
                }

                // nome
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