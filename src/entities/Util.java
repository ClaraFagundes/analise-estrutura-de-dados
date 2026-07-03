package entities;

import common.Perfume;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

public class Util {
    private static Scanner sc = new Scanner(System.in);

    //Serve simplesmente para armazenar os items do arquivo em uma estrutura de dados qualquer, ela também retorna o cronômetro com o tempo que demorou para executar esse armazenamento
    public static Cronometro armazenar(EstruturaDeDados estruturaDeDados, String arquivo, int quantidade) throws FileNotFoundException {
        Cronometro cronometro = new Cronometro();
        cronometro.iniciar();

        String linha;
        int contador = 0;
        int linhasPuladas = 0;
        int numeroLinhaArquivo = 1; // 1 = cabeçalho

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); // descarta o cabeçalho

            while (contador < quantidade && (linha = br.readLine()) != null) {
                numeroLinhaArquivo++;

                try {
                    String dados[] = linha.split("\\|");

                    if (dados.length < 13) {
                        throw new ArrayIndexOutOfBoundsException(
                                "Linha com " + dados.length + " campos, esperado >= 13");
                    }

                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String brand = dados[2];
                    String country = dados[3];
                    String gender = dados[4];
                    double ratingValue = Double.parseDouble(dados[5].replace(",", "."));
                    int ratingCount = Integer.parseInt(dados[6]);
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

                    Perfume perfumeObj = new Perfume(
                            id,
                            nome,
                            brand,
                            country,
                            gender,
                            ratingValue,
                            ratingCount,
                            year,
                            top,
                            middle,
                            base,
                            perfumer1,
                            perfumer2,
                            mainaccord1,
                            mainaccord2,
                            mainaccord3,
                            mainaccord4,
                            mainaccord5
                    );
                    estruturaDeDados.insere(perfumeObj);
                    contador++;

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    linhasPuladas++;
                    System.err.printf("[LINHA IGNORADA] arquivo=%s linha=%d motivo=%s%n",
                            arquivo, numeroLinhaArquivo, e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("O arquivo não foi encontrado!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (linhasPuladas > 0) {
            System.out.printf("AVISO: %d linha(s) malformada(s) ignorada(s) em %s%n", linhasPuladas, arquivo);
        }

        cronometro.finalizar();
        return cronometro;
    }

    //Você joga um ArrayList de estruturas de dados armazenadas e ele retorna o resultado da pesquisa de todos
    public static void pesquisar(ArrayList<EstruturaDeDados> estruturaDeDados) {
        System.out.print("Digite a chave a ser pesquisada [Digite 0 para encerrar]: ");
        int chave = sc.nextInt();

        while (chave != 0) {
            Perfume item = new Perfume(chave);

            for (EstruturaDeDados ds : estruturaDeDados) {
                System.out.println(ds.imprimirPesquisa(item) + "\n");
            }

            System.out.print("\nDigite a chave a ser pesquisada [Digite 0 para encerrar]: ");
            chave = sc.nextInt();
        }

    }
}
