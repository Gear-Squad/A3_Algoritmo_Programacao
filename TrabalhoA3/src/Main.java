import Classes.EstoqueService;

import java.util.Scanner;

// Main é o ponto de entrada do programa.
// Responsabilidade: exibir menus, capturar entradas e chamar os métodos do EstoqueService.
// Não contém lógica de negócio — apenas controla o fluxo da aplicação.

public class Main {
    public static void main(String[] args) {

        // Scanner lê o que o usuário digita no teclado
        // System.in representa a entrada padrão (teclado)
        Scanner sc = new Scanner(System.in);

        // EstoqueService é o objeto que contém toda a lógica do sistema
        // Todos os métodos/funções são chamados a partir daqui
        EstoqueService stq = new EstoqueService();

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
            System.out.print("Opção escolhida: ");
            opcao = sc.nextInt(); // Lê a opção do menu principal

            // Switch direciona para o bloco correto conforme a opção digitada
            switch (opcao) {

                case 1:
                    // Chama o metodo de cadastro no EstoqueService (stq)
                    stq.cadastrarProduto();
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
                        System.out.print("Opção escolhida: ");
                        movimentacao = sc.nextInt();

                        switch (movimentacao) {
                            case 1:
                                stq.adicionarProduto(); // Registra entrada de estoque
                                break;
                            case 2:
                                stq.registrarSaida(); // Registra saída de estoque
                                break;
                            case 0:
                                System.out.println("Voltando <-");
                                break;
                            default: // Caso escolha uma opção diferente das que foram mapeadas
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                    } while (movimentacao != 0); // Repete o submenu até digitar 0
                    break; // Sai do case 2 e volta ao menu principal

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
                        System.out.print("Opção escolhida: ");
                        listagem = sc.nextInt();

                        switch (listagem) {
                            case 1:
                                stq.listarProduto(); // Exibe tabela com todos os produtos
                                break;
                            case 2:
                                stq.buscarProduto(); // Busca produto por código
                                break;
                            case 0:
                                System.out.println("Voltando <-");
                                break;
                            default: // Caso escolha uma opção diferente das que foram mapeadas
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
                        System.out.print("Opção escolhida: ");
                        edicao = sc.nextInt();

                        switch (edicao) {
                            case 1:
                                stq.editarProduto(); // Edita atributos do produto
                                break;
                            case 2:
                                stq.excluirProduto(); // Remove produto da lista
                                break;
                            case 3:
                                stq.limparLista(); // Limpa toda a lista
                                break;
                            case 0:
                                System.out.println("Voltando <-");
                                break;
                            default: // Caso escolha uma opção diferente das que foram mapeadas
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                    } while (edicao != 0); // Repete o submenu até digitar 0
                    break;

                case 0:
                    // Mensagem de encerramento antes de sair do loop
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