import Classes.EstoqueService;
import Classes.Produto;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Imports

        Scanner sc = new Scanner(System.in);
        Produto prod = new Produto();
        EstoqueService stq = new EstoqueService();

        // Váriaveis

        int opcao;

        // Do - While

        do {

            System.out.println(" -> Gear Squad - Controle de Estoque <- ");
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

            // Switch Opções

            switch (opcao) {

                case 1: // Executa função Cadastro
                case 2: // Executa função Movimentação
                case 3: // Executa função relatórios
                case 0:
                    System.out.println("Obrigado por acessar o nosso programa! -Gear Squad.");
            }
        }
        while (opcao != 0);
    } // Fim programa
}