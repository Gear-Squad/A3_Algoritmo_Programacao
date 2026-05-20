package Classes;

import java.util.ArrayList;
import java.util.Scanner;

public class EstoqueService {

    // Scanner compartilhado entre os métodos da classe

    Scanner sc = new Scanner(System.in);

    // Lista privada de produtos cadastrados no sistema

    private ArrayList<Produto> materiais = new ArrayList<>();

    // Método auxiliar - Verifica se um código já existe na lista

    private boolean codigoExiste(int codigo) {

        for (Produto lista : materiais) {

            if (lista.getCodigo() == codigo) {
                return true; // Código encontrado
            }
        }
        return false; // Código não encontrado
    }

    // Função Cadastrar Produto - Atribuido á: Bruno

    public void cadastrarProduto() {

        int codigo;
        String validador;

        System.out.println("-> Cadastro de Produtos <-");

        // Lê o código e verifica se já existe na lista

        System.out.print("Insira o código do produto: ");
        codigo = sc.nextInt();

        // Se esse IF for True ou seja se existir esse código

        if (codigoExiste(codigo)) {

            // Código duplicado - solicita ação ao usuário

            System.out.println("Código já existente! Utilize outro código ou faça a movimentação.");
            System.out.print("Qual ação deseja? C - Outro código | M - Voltar ao menu: ");
            validador = sc.next().toUpperCase(); // toUpperCase aceita 'c' e 'C'

            if (validador.equals("C")) {
                cadastrarProduto(); // Reinicia o cadastro com novo código
            }
            return; // Encerra essa execução em caso de M
        }

        // Código válido - cria um novo produto e preenche os atributos

        Produto prod = new Produto();
        prod.setCodigo(codigo);

        sc.nextLine(); // Limpa o buffer
        System.out.print("Insira a descrição do produto: ");
        prod.setDescricao(sc.nextLine());

        System.out.print("Insira a quantidade do produto: ");
        prod.setQuantidade(sc.nextInt());

        System.out.print("Insira o Centro de Custo do produto: ");
        prod.setCentroCusto(sc.nextInt());

        // Adiciona o produto na lista e confirma o cadastro

        materiais.add(prod);
        System.out.println("Cadastro realizado com sucesso!");
        System.out.println();
        ordenarProduto();
    }

    // Função Adicionar Produtos - Atribuido á: Kaique

    public void adicionarProduto() {
    }

    // Função Registrar Saída - Atribuido á: Kaique

    public void registrarSaida() {
    }

    // Função Listar Produtos - Atribuido á: Deivisson

    public void listarProduto() {

        // Verifica se há produtos cadastrados antes de exibir

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Ordena a lista antes de listar

        ordenarProduto();

        System.out.println("-> Lista de Produtos <-");

        System.out.printf("%-10s %-20s %-12s %-15s%n", "Código", "Descrição", "Quantidade", "Centro de Custo");
        System.out.println("=".repeat(60));

        for (Produto lista : materiais) {
            System.out.printf("%-10d %-20s %-12d %-15d%n",
                    lista.getCodigo(),
                    lista.getDescricao(),
                    lista.getQuantidade(),
                    lista.getCentroCusto());
        }
    }

    // Função Ordenar - Atribuido á: Deivisson

    public void ordenarProduto() {
        int n = materiais.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                // Ordena por código (crescente)

                if (materiais.get(j).getCodigo() > materiais.get(j + 1).getCodigo()) {

                    // Troca de posição

                    Produto temp = materiais.get(j);
                    materiais.set(j, materiais.get(j + 1));
                    materiais.set(j + 1, temp);
                }
            }
        }
    }

    // Função Buscar Produto por código - Atribuido á: Deivisson

    private Produto buscarPorCodigo(int codigo) {

        for (Produto p : materiais) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    // Função Buscar Produto - Atribuido á: Deivisson

    public void buscarProduto() {
        System.out.println("-> Buscar Produto <-");

        // Se a lista estiver vazia

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Caso possua itens na lista

        System.out.print("Insira o código do produto: ");
        int codigo = sc.nextInt();

        Produto prod = buscarPorCodigo(codigo);

        // Caso Não encontre o Produto

        if (prod == null) {
            System.out.println("✗ Produto não encontrado!");
            return;
        }

        // Caso Encontre Produto

        System.out.println("\n=== Produto Encontrado ===");
        System.out.println("Código: " + prod.getCodigo());
        System.out.println("Descrição: " + prod.getDescricao());
        System.out.println("Quantidade: " + prod.getQuantidade());
        System.out.println("Centro de Custo: " + prod.getCentroCusto());
    }

    // Função Editar Produto - Atribuido á: Deivisson

    public void editarProduto() {
        System.out.println("-> Editar Produto <-");

        // Caso a lista esteje vazia

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Caso possua itens na lista

        System.out.print("Insira o código do produto a editar: ");
        int codigo = sc.nextInt();

        Produto prod = buscarPorCodigo(codigo);

        // Caso não encontre o código

        if (prod == null) {
            System.out.println("✗ Produto não encontrado!");
            return;
        }

        // Menu de Escolha para definir o que vai ser editado

        System.out.println("Produto atual: " + prod.getDescricao());
        System.out.println("O que deseja editar?");
        System.out.println("1 - Descrição: " + prod.getDescricao());
        System.out.println("2 - Quantidade: " + prod.getQuantidade());
        System.out.println("3 - Centro de Custo: " + prod.getCentroCusto());
        System.out.println("4 - Tudo");
        System.out.print("Opção: ");
        int opcao = sc.nextInt();
        sc.nextLine(); // Limpa buffer

        switch (opcao) {

            case 1:  // Descrição
                System.out.print("Nova descrição: " + prod.getDescricao());
                prod.setDescricao(sc.nextLine());
                break;

            case 2:  // Quantidade
                System.out.print("Nova quantidade: " + prod.getQuantidade());
                prod.setQuantidade(sc.nextInt());
                break;

            case 3:  // Centro de Custo
                System.out.print("Novo Centro de Custo: " + prod.getCentroCusto());
                prod.setCentroCusto(sc.nextInt());
                break;

            case 4:  // Tudo

                // Descrição
                System.out.println("Antiga descrição: " + prod.getDescricao());
                System.out.print("Nova descrição: ");
                prod.setDescricao(sc.nextLine());

                // Quantidade
                System.out.println("Antiga quantidade: " + prod.getQuantidade());
                System.out.print("Nova quantidade: ");
                prod.setQuantidade(sc.nextInt());

                // Centro de Custo
                System.out.println("Antigo Centro de Custo: " + prod.getCentroCusto());
                System.out.print("Novo Centro de Custo: ");
                prod.setCentroCusto(sc.nextInt());

                break;

            default: // Nenhuma opção
                System.out.println("✗ Opção inválida!");
                return;
        }
        // Feedback ao usuário.
        System.out.println("✓ Produto atualizado com sucesso!");
    }

    // Função Excluir Produto - Atribuido á: Bruno

    public void excluirProduto() {

        // Variável Confirmação

        String confirmacao;

        System.out.println("-> Excluir Produto <-");

        // Verifica se há produtos cadastrados

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Busca o produto pelo código

        System.out.print("Insira o código do produto a excluir: ");
        int codigo = sc.nextInt();

        Produto prod = buscarPorCodigo(codigo);

        // Caso não encontre
        if (prod == null) {
            System.out.println("✗ Produto não encontrado!");
            return;
        }

        // Exibe o produto e pede confirmação antes de excluir

        System.out.println("Produto encontrado: " + prod.getDescricao() + " | Quantidade: " + prod.getQuantidade() + " | Centro de custo: " + prod.getCentroCusto());

        System.out.print("Confirma exclusão? S - Sim | N - Não: ");
        confirmacao = sc.next().toUpperCase();

        if (confirmacao.equals("S")) {
            materiais.remove(prod); // Remove o objeto da lista
            System.out.println("✓ Produto excluído com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    // Função Limpar Lista de Produtos - Atribuido á : Bruno

    public void limparLista() {
        materiais.clear();
    }
}