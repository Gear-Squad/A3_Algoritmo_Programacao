package Classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// EstoqueService é a classe de serviço do sistema.
// Toda a lógica fica aqui: validações, busca, cadastro, edição, exclusão.
// A Main apenas chama os métodos desta classe.

public class EstoqueService {

    // =====================
    // ATRIBUTOS DA CLASSE
    // =====================

    // Scanner privado para leitura de dados do teclado.
    // "private" impede que outras classes acessem o Scanner diretamente.
    private Scanner sc = new Scanner(System.in);

    // ArrayList privado que armazena todos os produtos cadastrados no sistema.
    // Toda interação com a lista passa pelos métodos desta classe.
    private ArrayList<Produto> materiais = new ArrayList<>();

    // =====================
    // MÉTODOS AUXILIARES PRIVADOS
    // =====================

    // Verifica se a lista está vazia e exibe mensagem caso esteja.
    // Retorna true se vazia, false se houver produtos.
    // Evita repetir o mesmo bloco de verificação em todos os métodos.
    private boolean listaVazia() {
        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return true;
        }
        return false;
    }

    // Verifica se um código já existe na lista.
    // Retorna "true" se encontrar, "false" se não encontrar.
    private boolean codigoExiste(int codigo) {
        for (Produto material : materiais) {
            if (material.getCodigo() == codigo) {
                return true; // Código encontrado
            }
        }
        return false; // Código não encontrado
    }

    // Percorre a lista e retorna o objeto Produto com o código informado.
    // Se não encontrar, retorna null.
    private Produto buscarPorCodigo(int codigo) {
        for (Produto material : materiais) {
            if (material.getCodigo() == codigo) {
                return material;
            }
        }
        return null;
    }

    // Valida se a quantidade para movimentação é positiva (maior que zero).
    // 0 não é permitido na movimentação — por isso <= 0.
    // Retorna true se inválida, false se válida.
    private boolean quantidadeInvalida(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida. Informe um valor maior que zero.");
            return true;
        }
        return false;
    }

    // Lê um número inteiro do teclado com tratamento de entrada inválida.
    // Se o usuário digitar uma letra ou caractere, o programa não trava —
    // exibe mensagem de erro e pede novamente até receber um número válido.
    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                // InputMismatchException ocorre quando o tipo lido não bate com o esperado
                System.out.println("Entrada inválida! Digite apenas números inteiros.");
                sc.next(); // Descarta a entrada inválida do buffer
            }
        }
    }


    // =====================
    // CADASTRAR PRODUTO
    // =====================
    // Responsável por: ler os dados do teclado, validar e adicionar na lista.
    // Usa do-while no lugar de recursão para evitar crescimento da pilha de chamadas.

    public void cadastrarProduto() {

        System.out.println("-> Cadastro de Produtos <-");

        // Loop de validação do código:
        // Repete até o usuário informar um código válido (positivo e não duplicado)
        int codigo;
        do {
            codigo = lerInteiro("Insira o código do produto: ");

            if (codigo < 0) {
                System.out.println("Código inválido! Insira um número inteiro positivo.");
            } else if (codigoExiste(codigo)) {
                System.out.println("Código já existente! Utilize outro código ou faça a movimentação.");
                System.out.print("Deseja tentar outro código? S - Sim | N - Não (voltar ao menu): ");
                String resposta = sc.next().toUpperCase();
                if (!resposta.equals("S")) {
                    return; // Usuário optou por voltar ao menu
                }
                codigo = -1; // Força o loop a continuar
            }
        } while (codigo < 0 || codigoExiste(codigo));

        // Código válido — cria o produto e preenche os atributos
        Produto prod = new Produto();
        prod.setCodigo(codigo);

        // Limpa o "\n" que ficou no buffer após o lerInteiro (que usa nextInt internamente)
        sc.nextLine();

        System.out.print("Insira a descrição do produto: ");
        prod.setDescricao(sc.nextLine()); // nextLine lê texto com espaços

        // Loop de validação da quantidade:
        // 0 é permitido no cadastro — por isso < 0 e não <= 0
        int quantidade;
        do {
            quantidade = lerInteiro("Insira a quantidade do produto: ");
            if (quantidade < 0) {
                System.out.println("Quantidade inválida! Insira um número inteiro positivo.");
            }
        } while (quantidade < 0);

        prod.setQuantidade(quantidade);

        prod.setCentroCusto(lerInteiro("Insira o Centro de Custo do produto: "));

        // Adiciona o produto preenchido na lista
        materiais.add(prod);

        System.out.println("Cadastro realizado com sucesso!");
        System.out.println();

        // Ordena a lista após cada novo cadastro para manter a ordem por código
        ordenarProduto();
    }


    // =====================
    // ADICIONAR PRODUTO (ENTRADA DE ESTOQUE)
    // =====================
    // Aumenta a quantidade de um produto já cadastrado.

    public void adicionarProduto() {

        // Método auxiliar verifica e exibe mensagem se lista estiver vazia
        if (listaVazia()) return;

        int codigo = lerInteiro("Digite o código do produto: ");

        Produto prod = buscarPorCodigo(codigo);
        if (prod == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        // Exibe o estoque atual antes de pedir a quantidade — ajuda o usuário
        System.out.println("Estoque atual: " + prod.getQuantidade());

        int quantidade = lerInteiro("Digite a quantidade a adicionar: ");

        // Método auxiliar valida e exibe mensagem se quantidade for inválida
        // 0 não é permitido na movimentação
        if (quantidadeInvalida(quantidade)) return;

        // Soma a quantidade atual com a nova entrada
        prod.setQuantidade(prod.getQuantidade() + quantidade);

        System.out.println("Entrada registrada com sucesso!");
        System.out.println("Novo estoque: " + prod.getQuantidade());
    }


    // =====================
    // REGISTRAR SAÍDA (SAÍDA DE ESTOQUE)
    // =====================
    // Diminui a quantidade de um produto já cadastrado.

    public void registrarSaida() {

        if (listaVazia()) return;

        int codigo = lerInteiro("Digite o código do produto: ");

        Produto prod = buscarPorCodigo(codigo);
        if (prod == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        // Exibe o estoque atual antes de pedir a quantidade — ajuda o usuário
        System.out.println("Estoque atual: " + prod.getQuantidade());

        int quantidade = lerInteiro("Digite a quantidade de saída: ");

        // 0 não é permitido na movimentação
        if (quantidadeInvalida(quantidade)) return;

        // Valida que não está tentando retirar mais do que existe em estoque
        if (quantidade > prod.getQuantidade()) {
            System.out.println("Erro: quantidade solicitada maior que o estoque disponível.");
            System.out.println("Estoque atual: " + prod.getQuantidade());
            return;
        }

        // Subtrai a quantidade retirada do estoque atual
        prod.setQuantidade(prod.getQuantidade() - quantidade);

        System.out.println("Saída registrada com sucesso!");
        System.out.println("Estoque restante: " + prod.getQuantidade());
    }


    // =====================
    // ORDENAR PRODUTO (BUBBLE SORT)
    // =====================
    // Ordena a lista de produtos pelo código, em ordem crescente.
    // Algoritmo: Bubble Sort — compara pares adjacentes e troca se necessário.

    public void ordenarProduto() {

        // n = número total de produtos na lista
        int n = materiais.size();

        // Loop externo: controla quantas passagens serão feitas
        // A cada passagem, o maior elemento "sobe" para o final
        for (int i = 0; i < n - 1; i++) {

            // Loop interno: percorre os pares ainda não ordenados
            for (int j = 0; j < n - i - 1; j++) {

                // Se o código do elemento atual for maior que o próximo, troca
                if (materiais.get(j).getCodigo() > materiais.get(j + 1).getCodigo()) {

                    // Troca usando variável temporária para não perder o valor
                    Produto temp = materiais.get(j);
                    materiais.set(j, materiais.get(j + 1));
                    materiais.set(j + 1, temp);
                }
            }
        }
    }


    // =====================
    // LISTAR PRODUTOS
    // =====================
    // Exibe todos os produtos cadastrados em formato de tabela.

    public void listarProduto() {

        if (listaVazia()) return;

        // Ordena antes de exibir para garantir a ordem por código
        ordenarProduto();

        System.out.println("-> Lista de Produtos <-");

        // printf formata a saída em colunas:
        // %-10s = texto alinhado à esquerda ocupando 10 caracteres
        // %-10d = número inteiro alinhado à esquerda ocupando 10 caracteres
        // %n = quebra de linha compatível com todos os sistemas operacionais
        System.out.printf("%-10s %-20s %-12s %-15s%n", "Código", "Descrição", "Quantidade", "Centro de Custo");

        // Linha separadora visual entre cabeçalho e dados
        System.out.println("=".repeat(60));

        // Percorre cada produto da lista e imprime os dados formatados
        // Leitura: "para cada material em materiais"
        for (Produto material : materiais) {
            System.out.printf("%-10d %-20s %-12d %-15d%n",
                    material.getCodigo(),
                    material.getDescricao(),
                    material.getQuantidade(),
                    material.getCentroCusto());
        }

        // Exibe o total de produtos cadastrados ao final da tabela
        System.out.println("=".repeat(60));
        System.out.println("Total de produtos cadastrados: " + materiais.size());
    }


    // =====================
    // BUSCAR PRODUTO
    // =====================
    // Localiza e exibe os dados de um produto pelo código.

    public void buscarProduto() {
        System.out.println("-> Buscar Produto <-");

        if (listaVazia()) return;

        int codigo = lerInteiro("Insira o código do produto: ");

        Produto prod = buscarPorCodigo(codigo);

        if (prod == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.println("=== Produto Encontrado ===");
        System.out.println("Código: " + prod.getCodigo());
        System.out.println("Descrição: " + prod.getDescricao());
        System.out.println("Quantidade: " + prod.getQuantidade());
        System.out.println("Centro de Custo: " + prod.getCentroCusto());
    }


    // =====================
    // EDITAR PRODUTO
    // =====================
    // Permite alterar um ou todos os atributos de um produto já cadastrado.

    public void editarProduto() {
        System.out.println("-> Editar Produto <-");

        if (listaVazia()) return;

        int codigo = lerInteiro("Insira o código do produto a editar: ");

        Produto prod = buscarPorCodigo(codigo);
        if (prod == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.println("Produto atual: " + prod.getDescricao());
        System.out.println("O que deseja editar?");
        System.out.println("1 - Código: " + prod.getCodigo());
        System.out.println("2 - Descrição: " + prod.getDescricao());
        System.out.println("3 - Quantidade: " + prod.getQuantidade());
        System.out.println("4 - Centro de Custo: " + prod.getCentroCusto());
        System.out.println("5 - Tudo");

        int opcao = lerInteiro("Opção: ");

        // Limpa o buffer antes de qualquer nextLine() dentro do switch
        sc.nextLine();

        switch (opcao) {

            case 1: // Editar apenas o Código
                System.out.println("Antigo Código: " + prod.getCodigo());
                int novoCodigo = lerInteiro("Novo Código: ");

                // Verifica se o novo código já existe na lista (exceto o próprio produto)
                if (codigoExiste(novoCodigo) && novoCodigo != prod.getCodigo()) {
                    System.out.println("Código já existente! Edição cancelada.");
                    return;
                }
                prod.setCodigo(novoCodigo);
                break;

            case 2: // Editar apenas a Descrição
                System.out.println("Antiga Descrição: " + prod.getDescricao());
                System.out.print("Nova Descrição: ");
                prod.setDescricao(sc.nextLine());
                break;

            case 3: // Editar apenas a Quantidade
                System.out.println("Antiga Quantidade: " + prod.getQuantidade());
                prod.setQuantidade(lerInteiro("Nova Quantidade: "));
                break;

            case 4: // Editar apenas o Centro de Custo
                System.out.println("Antigo Centro de Custo: " + prod.getCentroCusto());
                prod.setCentroCusto(lerInteiro("Novo Centro de Custo: "));
                break;

            case 5: // Editar todos os campos
                System.out.println("Antigo Código: " + prod.getCodigo());
                int novoCodigoTudo = lerInteiro("Novo Código: ");

                // Verifica duplicidade do novo código
                if (codigoExiste(novoCodigoTudo) && novoCodigoTudo != prod.getCodigo()) {
                    System.out.println("Código já existente! Edição cancelada.");
                    return;
                }
                prod.setCodigo(novoCodigoTudo);

                sc.nextLine(); // Limpa buffer antes do nextLine()
                System.out.println("Antiga Descrição: " + prod.getDescricao());
                System.out.print("Nova Descrição: ");
                prod.setDescricao(sc.nextLine());

                System.out.println("Antiga Quantidade: " + prod.getQuantidade());
                prod.setQuantidade(lerInteiro("Nova Quantidade: "));

                System.out.println("Antigo Centro de Custo: " + prod.getCentroCusto());
                prod.setCentroCusto(lerInteiro("Novo Centro de Custo: "));
                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

        System.out.println("Produto atualizado com sucesso!");
    }


    // =====================
    // EXCLUIR PRODUTO
    // =====================
    // Remove um produto da lista após confirmação do usuário.

    public void excluirProduto() {
        System.out.println("-> Excluir Produto <-");

        if (listaVazia()) return;

        int codigo = lerInteiro("Insira o código do produto a excluir: ");

        Produto prod = buscarPorCodigo(codigo);
        if (prod == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.println("Produto encontrado: " + prod.getDescricao()
                + " | Quantidade: " + prod.getQuantidade()
                + " | Centro de Custo: " + prod.getCentroCusto());

        System.out.print("Confirma exclusão? S - Sim | N - Não: ");
        String confirmacao = sc.next().toUpperCase();

        if (confirmacao.equals("S")) {
            // remove() recebe o objeto e o remove da lista
            // Como "prod" é a referência do objeto que está na lista,
            // o ArrayList sabe exatamente qual elemento remover
            materiais.remove(prod);
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }


    // =====================
    // LIMPAR LISTA
    // =====================
    // Remove todos os produtos da lista após confirmação do usuário.

    public void limparLista() {

        if (listaVazia()) return;

        System.out.println("-> Limpar a Lista <-");
        System.out.print("Tem certeza que deseja limpar toda a lista? S - Sim | N - Não: ");
        String confirmacao = sc.next().toUpperCase();

        if (confirmacao.equals("S")) {
            // clear() remove todos os elementos do ArrayList de uma vez
            materiais.clear();
            System.out.println("Lista limpa com sucesso!");
        } else {
            System.out.println("Limpeza cancelada.");
        }
    }
}