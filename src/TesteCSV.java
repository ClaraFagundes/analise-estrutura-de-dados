import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TesteCSV {

    public static void main(String[] args) throws IOException {

        String arquivo = "src/common/other/fragrantica_asc.csv";

        BufferedReader br = new BufferedReader(new FileReader(arquivo));

        String linha;

        // pula o cabeçalho
        br.readLine();

        int numeroLinha = 1;

        while ((linha = br.readLine()) != null) {

            numeroLinha++;

            String[] dados = linha.split("\\|", -1);

            // Se a quantidade de colunas for diferente de 18, imprime tudo
            if (dados.length != 18) {

                System.out.println("====================================");
                System.out.println("Linha: " + numeroLinha);
                System.out.println("Quantidade de campos: " + dados.length);
                System.out.println();
                System.out.println(linha);
                System.out.println();

                for (int i = 0; i < dados.length; i++) {
                    System.out.println(i + " -> [" + dados[i] + "]");
                }

                System.out.println("====================================");
                break;
            }

            // Testa exatamente os parses do Util
            try {

                Integer.parseInt(dados[0]);
                Double.parseDouble(dados[5].replace(",", "."));
                Integer.parseInt(dados[6]);
                Double.parseDouble(dados[7]);

            } catch (Exception e) {

                System.out.println("====================================");
                System.out.println("Erro na linha: " + numeroLinha);
                System.out.println(e);
                System.out.println();

                System.out.println(linha);
                System.out.println();

                for (int i = 0; i < dados.length; i++) {
                    System.out.println(i + " -> [" + dados[i] + "]");
                }

                System.out.println("====================================");

                break;
            }
        }

        br.close();

        System.out.println("Fim do teste.");
    }
}