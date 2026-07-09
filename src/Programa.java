import common.CriterioOrdenacao;
import datastructures.ListaArranjo.ListaArranjo;
import entities.Cronometro;
import entities.Util;
import entities.enums.TipoInsercao;
import common.Perfume;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {

    private static final int CAPACIDADE = 100000;
    private static final int TOTAL_REGISTROS = 91132;
    private static final String ARQUIVO_CSV = "src/common/fragrantica_dataset.csv";
    private static int proximoId = TOTAL_REGISTROS + 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ListaArranjo lista = new ListaArranjo(CAPACIDADE);
        boolean carregado = false;

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao(sc);

            switch (opcao) {
                case 1 -> {
                    if (carregado) {
                        System.out.println("A base ja foi carregada. Reinicie o programa para recarregar.");
                        break;
                    }
                    carregarBase(lista);
                    if (lista.getTotalRegistros() > 0) carregado = true;
                }
                case 2 -> buscarPerfume(sc, lista, carregado);
                case 3 -> inserirPerfume(sc, lista, carregado);
                case 4 -> removerPerfume(sc, lista, carregado);
                case 5 -> listarPerfumes(lista, carregado);
                case 6 -> exibirEstatisticas(lista, carregado);
                case 7 -> alterarCriterio(sc, lista, carregado);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção invalida.");
            }
        } while (opcao != 0);

        sc.close();
    }

    // ===================== MENU =====================

    private static void exibirMenu() {
        System.out.println("\n===== CATALOGO DE PERFUMES =====");
        System.out.println("1 - Carregar base de dados");
        System.out.println("2 - Buscar perfume");
        System.out.println("3 - Inserir novo perfume");
        System.out.println("4 - Remover perfume por ID");
        System.out.println("5 - Listar todos os perfumes");
        System.out.println("6 - Exibir estatísticas da base");
        System.out.println("7 - Alterar criterio de ordenacao (ID/NOME)");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    private static int lerOpcao(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.next(); // descarta entrada invalida
            return -1;
        }
    }

    // ===================== CARGA (OPCAO 1) =====================

    private static void carregarBase(ListaArranjo lista) {
        System.out.println("Carregando base de dados...");

        Cronometro cronometro = new Cronometro();

        try {
            cronometro.iniciar();
            Cronometro tempoArmazenar = Util.armazenar(lista, ARQUIVO_CSV, TOTAL_REGISTROS);
            System.out.printf("Armazenamento concluído: %.3f ms%n", tempoArmazenar.getTempoMs());

            System.out.println("Ordenando com mergeSort...");
            lista.mergeSort();
            cronometro.finalizar();

            System.out.printf("Carga + ordenação concluídas em %.3f ms%n", cronometro.getTempoMs());

            System.out.println("Base carregada com sucesso! " + TOTAL_REGISTROS + " registros.");

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo nao encontrado em " + ARQUIVO_CSV);
        }
    }

    // ===================== BUSCA (OPCAO 2) =====================

    private static void buscarPerfume(Scanner sc, ListaArranjo lista, boolean carregado) {
        if (!verificarCarregado(carregado)) return;

        Perfume chave;
        if (lista.getCriterio() == CriterioOrdenacao.ID) {
            int id = lerId(sc, "Digite o ID do perfume: ");
            if (id < 0) return;
            chave = new Perfume(id);
        } else {
            sc.nextLine();
            String nome = lerStringObrigatoria(sc, "Digite o NOME do perfume: ");
            chave = new Perfume(nome);
        }

        Cronometro cronometro = new Cronometro();
        cronometro.iniciar();
        Perfume encontrado = lista.PesquisaBinaria(chave);
        cronometro.finalizar();

        if (encontrado != null) {
            System.out.println("Perfume encontrado:");
            System.out.println("  " + encontrado);
            System.out.printf("Tempo: %.3f ms | Comparações: %d%n",
                    cronometro.getTempoMs(), lista.getComparacoes());
        } else {
            System.out.println("Perfume nao encontrado.");
        }
    }

    // ===================== INSERCAO (OPCAO 3) =====================

    private static void inserirPerfume(Scanner sc, ListaArranjo lista, boolean carregado) {
        if (!verificarCarregado(carregado)) return;

        if (lista.Cheia()) {
            System.out.println("Erro: Lista cheia! Capacidade maxima de " + CAPACIDADE + " registros atingida.");
            return;
        }

        System.out.println("--- Inserir novo perfume ---");

        int id = proximoId++;

        sc.nextLine();

        String nome = lerStringObrigatoria(sc, "Nome: ");
        String brand = lerStringObrigatoria(sc, "Marca (brand): ");
        String country = lerStringObrigatoria(sc, "Pais (country): ");
        String gender = lerStringObrigatoria(sc, "Sexo (gender): ");

        double ratingValue = 0.0;
        int ratingCount = 0;

        double year = lerDoubleObrigatorio(sc, "Ano (year): ");

        String top = lerStringObrigatoria(sc, "Notas de topo (top): ");
        String middle = lerStringObrigatoria(sc, "Notas de corpo (middle): ");
        String base = lerStringObrigatoria(sc, "Notas de fundo (base): ");

        String perfumer1 = lerStringOpcional(sc, "Perfumista 1 (opcional, Enter para unknown): ");
        String perfumer2 = lerStringOpcional(sc, "Perfumista 2 (opcional, Enter para unknown): ");
        String mainaccord1 = lerStringOpcional(sc, "Acorde principal 1 (opcional, Enter para unknown): ");
        String mainaccord2 = lerStringOpcional(sc, "Acorde principal 2 (opcional, Enter para unknown): ");
        String mainaccord3 = lerStringOpcional(sc, "Acorde principal 3 (opcional, Enter para unknown): ");
        String mainaccord4 = lerStringOpcional(sc, "Acorde principal 4 (opcional, Enter para unknown): ");
        String mainaccord5 = lerStringOpcional(sc, "Acorde principal 5 (opcional, Enter para unknown): ");

        Perfume novo = new Perfume(
                id, nome, brand, country, gender,
                ratingValue, ratingCount, year,
                top, middle, base,
                perfumer1, perfumer2,
                mainaccord1, mainaccord2, mainaccord3, mainaccord4, mainaccord5
        );


        Cronometro cronometro = new Cronometro();
        cronometro.iniciar();

        lista.InsereFinal(novo);
        lista.insertionSort();

        cronometro.finalizar();

        System.out.println("Perfume ID " + id + " inserido com sucesso no final da lista.");
        System.out.printf("Inserção + ordenação concluídas em %.3f ms%n", cronometro.getTempoMs());

    }

    // ===================== REMOCAO (OPCAO 4) =====================

    private static void removerPerfume(Scanner sc, ListaArranjo lista, boolean carregado) {
        if (!verificarCarregado(carregado)) return;

        if (lista.getCriterio() != CriterioOrdenacao.ID) {
            System.out.println("Remocao por ID requer que a base esteja ordenada por ID. "
                    + "Criterio atual: " + lista.getCriterio()
                    + ". Use a opcao 7 para voltar ao criterio ID antes de remover.");
            return;
        }

        int id = lerId(sc, "Digite o ID do perfume a remover: ");
        if (id < 0) return;

        Perfume encontrado = lista.PesquisaBinaria(new Perfume(id));

        if (encontrado == null) {
            System.out.println("Perfume com ID " + id + " nao encontrado.");
            return;
        }

        System.out.println("Perfume encontrado:");
        System.out.println("  " + encontrado);

        System.out.print("Confirmar remoção? (s/N): ");
        sc.nextLine();
        String confirma = sc.nextLine().trim().toLowerCase();

        if (confirma.equals("s") || confirma.equals("sim")) {
            lista.PesquisaRemove(id);
            System.out.println("Perfume ID " + id + " removido.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    // ===================== LISTAGEM (OPCAO 5) =====================

    private static void listarPerfumes(ListaArranjo lista, boolean carregado) {
        if (!verificarCarregado(carregado)) return;
        lista.Imprime();
    }

    // ===================== ESTATISTICAS (OPCAO 6) =====================

    private static void exibirEstatisticas(ListaArranjo lista, boolean carregado) {
        if (!verificarCarregado(carregado)) return;
        System.out.println("=== Estatísticas da Base ===");
        System.out.println("Total de registros: " + lista.getTotalRegistros());
    }

    // ===================== CRITERIO (OPCAO 7) =====================

    private static void alterarCriterio(Scanner sc, ListaArranjo lista, boolean carregado) {
        System.out.println("Criterio atual: " + lista.getCriterio());
        System.out.println("1 - ID");
        System.out.println("2 - NOME");
        System.out.print("Escolha: ");
        int escolha = lerOpcao(sc);

        CriterioOrdenacao novo;
        switch (escolha) {
            case 1 -> novo = CriterioOrdenacao.ID;
            case 2 -> novo = CriterioOrdenacao.NOME;
            default -> {
                System.out.println("Opcao invalida.");
                return;
            }
        }

        lista.setCriterio(novo);
        System.out.println("Criterio alterado para: " + novo);

        if (carregado) {
            System.out.print("Deseja reordenar a base agora com mergeSort? (s/N): ");
            sc.nextLine();
            String resp = sc.nextLine().trim().toLowerCase();
            if (resp.equals("s") || resp.equals("sim")) {
                Cronometro cron = new Cronometro();
                cron.iniciar();
                lista.mergeSort();
                cron.finalizar();
                System.out.printf("Reordenacao concluida em %.3f ms%n", cron.getTempoMs());
            }
        }
    }

    // ===================== UTILITARIOS DE ENTRADA =====================

    private static boolean verificarCarregado(boolean carregado) {
        if (!carregado) {
            System.out.println("Nenhuma base carregada. Selecione a opção 1 primeiro.");
            return false;
        }
        return true;
    }

    private static int lerId(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = sc.nextInt();
                if (valor < 0) {
                    System.out.println("Erro: ID nao pode ser negativo.");
                    continue;
                }
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um numero inteiro valido.");
                sc.next();
            }
        }
    }

    private static String lerStringObrigatoria(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = sc.nextLine().trim();
            if (!valor.isEmpty()) return valor;
            System.out.println("Erro: Este campo e obrigatorio.");
        }
    }

    private static double lerDoubleObrigatorio(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um numero valido (ex: 2020 ou 2020.5).");
            }
        }
    }

    private static String lerStringOpcional(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        String valor = sc.nextLine().trim();
        return valor.isEmpty() ? "unknown" : valor;
    }
}
