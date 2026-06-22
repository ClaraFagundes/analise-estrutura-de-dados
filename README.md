# Análise de Estruturas de Dados

Projeto Java que carrega um dataset real de perfumes e compara o desempenho de diferentes estruturas de dados na inserção e pesquisa, medindo comparações, rotações e tempo de execução.

---

## Estrutura do projeto

```
src/
├── Programa.java
├── TesteDesempenho.java
├── common/
│   └── Perfume.java
├── datastructures/
│   ├── ArvoreAVL/
│   │   ├── TArvoreAVL.java
│   │   ├── TNodo.java
│   │   ├── TPilhaPonteiro.java
│   │   ├── TCelulaCabeca.java
│   │   └── TNodoLista.java
│   ├── ArvoreBST/
│   │   ├── ArvoreBST.java
│   │   └── Nodo.java
│   ├── ListaApontador/
│   │   └── ListaApontador.java
│   ├── ListaArranjo/
│   │   └── ListaArranjo.java
│   └── ListaDupla/
│       └── ListaDupla.java
└── entities/
    ├── EstruturaDeDados.java
    ├── Cronometro.java
    ├── Util.java
    └── enums/
        └── TipoInsercao.java
```

---

## Pacotes

### `common`
Contém a classe `Perfume`, que é o item armazenado em todas as estruturas. O atributo `chave` (o `id` do perfume no dataset) é usado como critério de comparação em todas as operações de inserção e pesquisa.

### `datastructures`
Implementações das estruturas de dados. Todas implementam a interface `EstruturaDeDados`, o que permite tratá-las de forma polimórfica no `Programa.java`.

As listas (`ListaApontador`, `ListaArranjo`, `ListaDupla`) recebem um `TipoInsercao` no construtor (`INICIO` ou `FINAL`), então cada uma é instanciada duas vezes no programa — uma para cada modo — para que ambos sejam comparados.

A `ListaArranjo` também exige que o tamanho máximo seja passado no construtor, pois precisa inicializar o array internamente.

### `entities`
Classes de suporte ao funcionamento geral do programa.

`EstruturaDeDados` é a interface que todas as estruturas implementam. Ela define dois métodos: `insere(Perfume)` e `imprimirPesquisa(Perfume)`. O objetivo é permitir que o `Programa.java` itere sobre um `ArrayList<EstruturaDeDados>` e chame os mesmos métodos em qualquer estrutura sem precisar saber qual é.

`Cronometro` encapsula `System.nanoTime()` e expõe o tempo decorrido em milissegundos via `toString()`, facilitando a exibição dos resultados.

`Util` centraliza duas operações usadas pelo programa:
- `armazenar(estrutura, arquivo, quantidade)` — lê o CSV, monta os objetos `Perfume` e os insere na estrutura, retornando o `Cronometro` com o tempo gasto.
- `pesquisar(lista de estruturas)` — recebe uma chave pelo terminal e chama `imprimirPesquisa()` em todas as estruturas do `ArrayList`, exibindo o resultado de cada uma.

`TipoInsercao` é um enum com os valores `INICIO` e `FINAL`, usado pelas listas para decidir o modo de inserção.

---

## Como executar

### `Programa.java`
Insere 1.000 registros do dataset em todas as estruturas (AVL, BST, e as três listas em modo início e final) e abre um loop no terminal para pesquisar uma chave em todas simultaneamente. Digite `0` para encerrar.

### `TesteDesempenho.java`
Focado apenas nas árvores BST e AVL. Apresenta um menu para escolher a quantidade de registros (1.000, 10.000, 50.000, 91.134 ou um valor customizado) e exibe ao final o tempo de inserção, número de comparações e — para a AVL — a altura e o número de rotações.

---

## Dataset

O arquivo `fragrantica_dataset.csv` fica em `src/common/` e contém dados de perfumes do site Fragrantica. Cada linha representa um perfume com `id`, nome, marca, país, gênero, avaliação, contagem de avaliações, ano e notas olfativas. O `id` é usado como chave nas estruturas.