import Classes.EstoqueService;

import java.util.InputMismatchException;
import java.util.Scanner;

// Main é o ponto de entrada do programa.
// Responsabilidade: exibir menus, capturar entradas e chamar os métodos do EstoqueService.
// Não contém lógica de negócio — apenas controla o fluxo da aplicação.

public class Main {

    // Lê um número inteiro do teclado com tratamento de entrada inválida.
    // Se o usuário digitar uma letra ou caractere, o programa não trava —
    // exibe mensagem de erro e pede novamente até receber um número válido.
    private static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números inteiros.");
                sc.next(); // Descarta a entrada inválida do buffer
            }
        }
    }

    public static void main(String[] args) {

        // Scanner lê o que o usuário digita no teclado
        Scanner sc = new Scanner(System.in);

        // Nome mais descritivo que "stq" — deixa claro o que o objeto representa
        EstoqueService estoqueService = new EstoqueService();

        // Variável que guarda a opção digitada pelo usuário no menu principal
        int opcao;

        // =====================
        // MENU PRINCIPAL DO SISTEMA
        // =====================
        // do-while executa ao menos uma vez antes de verificar a condição
        // O menu fica aparecendo até o usuário digitar 0 para sair

        do {
            System.out.println();
            System.out.println(" -> Gear Squad - Controle de Estoque <- ");
            System.out.println("Menu de Escolha");
            System.out.println();
            System.out.println("Escolha uma opção!");
            System.out.println(" 1 - Cadastrar Produtos");
            System.out.println(" 2 - Movimentar Produtos");
            System.out.println(" 3 - Listagem de Produtos");
            System.out.println(" 4 - Editar Produtos");
            System.out.println(" 0 - Saída");
            System.out.println();
            opcao = lerInteiro(sc, "Opção escolhida: ");

            // Switch direciona para o bloco correto conforme a opção digitada
            switch (opcao) {

                case 1:
                    estoqueService.cadastrarProduto();
                    break;

                case 2:
                    // =====================
                    // SUBMENU MOVIMENTAÇÃO
                    // =====================
                    // Variável própria do submenu — não interfere no "opcao" do menu principal
                    int movimentacao;
                    do {
                        System.out.println();
                        System.out.println(" -> Gear Squad - 2 - Movimentação de Produtos <- ");
                        System.out.println();
                        System.out.println("Escolha uma opção!");
                        System.out.println(" 1 - Entrada de Produtos");
                        System.out.println(" 2 - Saída de Produtos");
                        System.out.println(" 0 - Voltar");
                        System.out.println();
                        movimentacao = lerInteiro(sc, "Opção escolhida: ");

                        switch (movimentacao) {
                            case 1:
                                estoqueService.adicionarProduto(); // Registra entrada de estoque
                                break;
                            case 2:
                                estoqueService.registrarSaida(); // Registra saída de estoque
                                break;
                            case 0:
                                System.out.println("Voltando <-");
                                break;
                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                    } while (movimentacao != 0); // Repete o submenu até digitar 0
                    break;

                case 3:
                    // =====================
                    // SUBMENU LISTAGEM
                    // =====================
                    int listagem;
                    do {
                        System.out.println();
                        System.out.println(" -> Gear Squad - 3 - Listagem de Produtos <- ");
                        System.out.println();
                        System.out.println("Escolha uma opção!");
                        System.out.println(" 1 - Listar todos os produtos");
                        System.out.println(" 2 - Buscar um único produto");
                        System.out.println(" 0 - Voltar");
                        System.out.println();
                        listagem = lerInteiro(sc, "Opção escolhida: ");

                        switch (listagem) {
                            case 1:
                                estoqueService.listarProduto(); // Exibe tabela com todos os produtos
                                break;
                            case 2:
                                estoqueService.buscarProduto(); // Busca produto por código
                                break;
                            case 0:
                                System.out.println("Voltando <-");
                                break;
                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                    } while (listagem != 0); // Repete o submenu até digitar 0
                    break;

                case 4:
                    // =====================
                    // SUBMENU EDIÇÃO
                    // =====================
                    int edicao;
                    do {
                        System.out.println();
                        System.out.println(" -> Gear Squad - 4 - Edição de Produtos <- ");
                        System.out.println();
                        System.out.println("Escolha uma opção!");
                        System.out.println(" 1 - Editar produto");
                        System.out.println(" 2 - Excluir produto");
                        System.out.println(" 3 - Limpar toda lista");
                        System.out.println(" 0 - Voltar");
                        System.out.println();
                        edicao = lerInteiro(sc, "Opção escolhida: ");

                        switch (edicao) {
                            case 1:
                                estoqueService.editarProduto(); // Edita atributos do produto
                                break;
                            case 2:
                                estoqueService.excluirProduto(); // Remove produto da lista
                                break;
                            case 3:
                                estoqueService.limparLista(); // Limpa toda a lista
                                break;
                            case 0:
                                System.out.println("Voltando <-");
                                break;
                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                    } while (edicao != 0); // Repete o submenu até digitar 0
                    break;

                case 0:
                    System.out.println("Obrigado por acessar o nosso programa! -Gear Squad.");
                    break;

                default:
                    // Executado quando nenhum case bate com a opção digitada
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0); // Condição do loop principal: encerra quando opcao = 0

        // Fecha o Scanner para liberar o recurso após o encerramento do programa
        sc.close();
    }
}