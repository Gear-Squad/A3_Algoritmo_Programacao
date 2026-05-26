import Classes.EstoqueService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner compartilhado entre os menus do sistema
        Scanner sc = new Scanner(System.in);

        // Objeto de serviço que gerencia a lista de produtos
        EstoqueService stq = new EstoqueService();

        // Variáveis
        int opcao;

        // Loop principal do sistema - encerra quando opcao = 0
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
            System.out.print("Opção escolhida: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    stq.cadastrarProduto();
                    break;

                case 2:
                    // Submenu de movimentação - repete até o usuário voltar

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
                        System.out.print("Opção escolhida: ");
                        movimentacao = sc.nextInt();

                        // Escolha caso dentro de movimentação
                        switch (movimentacao) {
                            case 1:
                                stq.adicionarProduto();
                                break;
                            case 2:
                                stq.registrarSaida();
                                break;
                            case 0:
                                System.out.println("Voltando <-");
                                break;
                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                    } while (movimentacao != 0);
                    break;

                case 3:
                    // Submenu de listagem - repete até o usuário voltar
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
                        System.out.print("Opção escolhida: ");
                        listagem = sc.nextInt();

                        // Escolha caso dentro de listagem
                        switch (listagem) {
                            case 1:
                                stq.listarProduto();
                                break;
                            case 2:
                                stq.buscarProduto();
                                break;
                            case 0:
                                System.out.println("Voltando <-");
                                break;
                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                    } while (listagem != 0);
                    break;

                case 4:
                    // Submenu de edição - repete até o usuário voltar
                    int edicao;
                    do {
                        System.out.println();
                        System.out.println(" -> Gear Squad - 4 Edição de Produtos <- ");
                        System.out.println();
                        System.out.println("Escolha uma opção!");
                        System.out.println(" 1 - Editar produto");
                        System.out.println(" 2 - Excluir produto");
                        System.out.println(" 0 - Voltar");
                        System.out.println();
                        System.out.print("Opção escolhida: ");
                        edicao = sc.nextInt();

                        // Escolha caso dentro de edição
                        switch (edicao) {
                            case 1:
                                stq.editarProduto();
                                break;
                            case 2:
                                stq.excluirProduto();
                                break;
                            case 0:
                                System.out.println("Voltando <-");
                                break;
                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                    } while (edicao != 0);
                    break;

                case 0:
                    System.out.println("Obrigado por acessar o nosso programa! -Gear Squad.");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        // Fecha o Scanner ao encerrar o programa
        sc.close();
    }
}