import datastructures.ListaApontador.ListaApontador;
import datastructures.ListaArranjo.ListaArranjo;
import datastructures.ListaDupla.ListaDupla;
import entities.Cronometro;
import entities.Util;
import entities.enums.TipoInsercao;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TesteOrdenacao {

    private static final int REPETICOES = 5;

    private static final int[] TAMANHOS = {1000, 10000, 50000, 91134};
    private static final String[] CASOS = {"caso_ordem_crescente", "caso_qualquer", "caso_ordem_decrescente"};

    private static final int LIMITE_BUBBLE_SELECTION = 10000;

    private static final String CSV_CRESC   = "src/common/other/fragrantica_asc.csv";
    private static final String CSV_QUALQUER = "src/common/other/fragrantica_random.csv";
    private static final String CSV_DESCRESC     = "src/common/other/fragrantica_desc.csv";

    private static final String[] NATIVOS_APONTADOR = {"bubbleSort", "selectionSort", "insertionSort", "mergeSort"};
    private static final String[] NATIVOS_DUPLA      = {"bubbleSort", "selectionSort", "insertionSort", "mergeSort", "quickSort"};
    private static final String[] TODOS_ARRANJO      = {"bubbleSort", "selectionSort", "insertionSort", "shellSort", "mergeSort", "quickSort", "heapSort"};

    private static final String[] INDEX_BOUND_APONTADOR = {"shellSort", "heapSort", "quickSort"};
    private static final String[] INDEX_BOUND_DUPLA     = {"shellSort", "heapSort"};

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("resultados_ordenacao.csv"));
        out.println("estrutura;caso;algoritmo;tamanho;tempo_ms_medio");

        for (String caso : CASOS) {
            String arquivo = arquivoDoCaso(caso);

            for (int tamanho : TAMANHOS) {
                System.out.println("=== " + caso + " | n=" + tamanho + " ===");

                testeArranjo(out, caso, arquivo, tamanho);
                testeApontador(out, caso, arquivo, tamanho);
                testeDupla(out, caso, arquivo, tamanho);
            }
        }

        out.close();
        System.out.println("Concluído -> resultados_ordenacao.csv");
    }

    private static String arquivoDoCaso(String caso) {
        switch (caso) {
            case "caso_ordem_crescente": return CSV_CRESC;
            case "caso_ordem_decrescente":   return CSV_DESCRESC;
            default:            return CSV_QUALQUER;
        }
    }

    private static boolean deveExecutar(String algoritmo, int tamanho) {
        boolean isBubbleOuSelection = algoritmo.equals("bubbleSort") || algoritmo.equals("selectionSort");
        if (isBubbleOuSelection && tamanho > LIMITE_BUBBLE_SELECTION) {
            System.out.printf("[PULADO] %s | n=%d (acima do limite de %d)%n", algoritmo, tamanho, LIMITE_BUBBLE_SELECTION);
            return false;
        }
        return true;
    }

    // ===================== LISTA COM ARRANJO =====================

    private static void testeArranjo(PrintWriter out, String caso, String arquivo, int tamanho) throws FileNotFoundException {
        for (String algoritmo : TODOS_ARRANJO) {
            if (!deveExecutar(algoritmo, tamanho)) continue;

            medirEregistrar(out, "ListaArranjo", caso, algoritmo, tamanho, () -> {
                ListaArranjo lista = new ListaArranjo(TipoInsercao.FINAL, tamanho);
                try {
                    Util.armazenar(lista, arquivo, tamanho);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                executarArranjo(lista, algoritmo);
            });
        }
    }

    // ===================== LISTA SIMPLESMENTE ENCADEADA =====================

    private static void testeApontador(PrintWriter out, String caso, String arquivo, int tamanho) {
        for (String algoritmo : NATIVOS_APONTADOR) {
            if (!deveExecutar(algoritmo, tamanho)) continue;

            medirEregistrar(out, "ListaApontador", caso, algoritmo, tamanho, () -> {
                ListaApontador lista = new ListaApontador(TipoInsercao.FINAL);
                try {
                    Util.armazenar(lista, arquivo, tamanho);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                executarApontador(lista, algoritmo);
            });
        }

        for (String algoritmo : INDEX_BOUND_APONTADOR) {
            if (!deveExecutar(algoritmo, tamanho)) continue;

            medirEregistrar(out, "ListaApontador", caso, algoritmo, tamanho, () -> {
                ListaApontador lista = new ListaApontador(TipoInsercao.FINAL);
                try {
                    Util.armazenar(lista, arquivo, tamanho);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                lista.toArray();
                executarApontador(lista, algoritmo);
                lista.fromArray();
            });
        }
    }

    // ===================== LISTA DUPLAMENTE ENCADEADA =====================

    private static void testeDupla(PrintWriter out, String caso, String arquivo, int tamanho) {
        for (String algoritmo : NATIVOS_DUPLA) {
            if (!deveExecutar(algoritmo, tamanho)) continue;

            medirEregistrar(out, "ListaDupla", caso, algoritmo, tamanho, () -> {
                ListaDupla lista = new ListaDupla(TipoInsercao.FINAL);
                try {
                    Util.armazenar(lista, arquivo, tamanho);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                executarDupla(lista, algoritmo);
            });
        }

        for (String algoritmo : INDEX_BOUND_DUPLA) {
            if (!deveExecutar(algoritmo, tamanho)) continue;

            medirEregistrar(out, "ListaDupla", caso, algoritmo, tamanho, () -> {
                ListaDupla lista = new ListaDupla(TipoInsercao.FINAL);
                try {
                    Util.armazenar(lista, arquivo, tamanho);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                lista.toArray();
                executarDupla(lista, algoritmo);
                lista.fromArray();
            });
        }
    }

    private static void executarArranjo(ListaArranjo l, String algoritmo) {
        switch (algoritmo) {
            case "bubbleSort": l.bubbleSort(); break;
            case "selectionSort": l.selectionSort(); break;
            case "insertionSort": l.insertionSort(); break;
            case "shellSort": l.shellSort(); break;
            case "mergeSort": l.mergeSort(); break;
            case "quickSort": l.quickSort(); break;
            case "heapSort": l.heapSort(); break;
        }
    }

    private static void executarApontador(ListaApontador l, String algoritmo) {
        switch (algoritmo) {
            case "bubbleSort": l.bubbleSort(); break;
            case "selectionSort": l.selectionSort(); break;
            case "insertionSort": l.insertionSort(); break;
            case "mergeSort": l.mergeSort(); break;
            case "shellSort": l.shellSort(); break;
            case "quickSort": l.quickSort(); break;
            case "heapSort": l.heapSort(); break;
        }
    }

    private static void executarDupla(ListaDupla l, String algoritmo) {
        switch (algoritmo) {
            case "bubbleSort": l.bubbleSort(); break;
            case "selectionSort": l.selectionSort(); break;
            case "insertionSort": l.insertionSort(); break;
            case "mergeSort": l.mergeSort(); break;
            case "quickSort": l.quickSort(); break;
            case "shellSort": l.shellSort(); break;
            case "heapSort": l.heapSort(); break;
        }
    }

    // ===================== MEDIÇÃO COM PROTEÇÃO CONTRA STACKOVERFLOW =====================

    private static void medirEregistrar(PrintWriter out, String estrutura, String caso,
                                        String algoritmo, int tamanho, Runnable execucao) {
        double somaMs = 0;
        boolean falhou = false;

        for (int r = 0; r < REPETICOES; r++) {
            try {
                Cronometro c = new Cronometro();
                c.iniciar();
                execucao.run();
                c.finalizar();
                somaMs += c.getTempoMs();
            } catch (StackOverflowError e) {
                falhou = true;
                System.out.printf("[STACK OVERFLOW] %s | %s | %s | n=%d%n",
                        estrutura, caso, algoritmo, tamanho);
                break;
            }
        }

        if (falhou) {
            out.println(estrutura + ";" + caso + ";" + algoritmo + ";" + tamanho + ";STACKOVERFLOW");
            out.flush();
        } else {
            registrar(out, estrutura, caso, algoritmo, tamanho, somaMs / REPETICOES);
        }
    }

    private static void registrar(PrintWriter out, String estrutura, String caso, String algoritmo, int tamanho, double tempoMsMedio) {
        out.println(estrutura + ";" + caso + ";" + algoritmo + ";" + tamanho + ";" +
                String.format("%.4f", tempoMsMedio).replace(".", ","));
        out.flush();
        System.out.printf("%-16s %-14s %-16s n=%-7d %.4f ms%n", estrutura, caso, algoritmo, tamanho, tempoMsMedio);
    }
}