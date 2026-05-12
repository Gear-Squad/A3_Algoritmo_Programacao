// Metódo Main

import java.util.Scanner;

public class Main {
    static void main(String[] args) {

        // Imports

        Scanner sc = new Scanner(System.in);

        // Váriaveis

        int opcao;

        // Do - While

        do {

            System.out.println(" -> Gear Squad - Controle de Estoque <- ");
            System.out.println();
            System.out.println("Menu de Escolha");
            System.out.println();
            System.out.println("Escolha uma opção!");
            System.out.println(" 1 - Cadastrar Produtos");
            System.out.println(" 2 - Movimentar Produtos");
            System.out.println(" 3 - Relatórios");
            System.out.println(" 0 - Saída");
            System.out.println();
            System.out.print("Opção escolhida: ");
            opcao = sc.nextInt();

            // Validação das Opções

            if (opcao == 1) {

                // Executa função Cadastro

            }
            if (opcao == 2) {

                // Executa função Movimentação

            }
            if (opcao == 3) {

                // Executa função relatórios

            }
            if (opcao == 0) {

                System.out.println("Obrigado por acessar o nosso programa! -Gear Squad.");
                System.exit(0);

            }
        } while (opcao != 0);

        // Fim programa
    }
}
