package Classes;

import java.util.ArrayList;
import java.util.Scanner;

public class EstoqueService {

    // Imports

    Produto prod = new Produto();
    Scanner sc = new Scanner(System.in);

    // Lista de Produtos nome de materiais

    private ArrayList<Produto> materiais = new ArrayList<>();

    // Função Cadastrar Produto  - Atribuido á: Bruno

    public void cadastrarProduto() {

        System.out.println("-> Cadastro de Produtos <-");

        System.out.print("Insira o código do produto: ");
        prod.setCodigo(sc.nextInt());

        sc.nextLine(); // Limpa o buffer
        System.out.print("Insira o descrição do produto: ");
        prod.setDescricao(sc.nextLine());

        System.out.print("Insira o Quantidade do produto: ");
        prod.setQuantidade(sc.nextInt());

        System.out.print("Insira o Centro de Custo do produto: ");
        prod.setCentroCusto(sc.nextInt());

        System.out.println("Cadastro realizado com sucesso! ");
        System.out.println();

        materiais.add(prod);
    }

    // Função Adicionar Produtos - Atribuido á: Kaique

    public void adicionarProduto() {
    }

    // Função Registrar Saída - Atribuido á: Kaique

    public void registrarSaida() {
    }

    // Função Listar Produtos - Atribuido á: Deivisson

    public void listarProduto() {
        System.out.println("-> Lista de Produtos <-");
        System.out.printf("%-10s %-20s %-12s %-15s%n", "Código", "Descrição", "Quantidade", "Centro de Custo");
        System.out.println("=".repeat(60));

        for (Produto p : materiais) {
            System.out.printf("%-10d %-20s %-12d %-15d%n",
                    p.getCodigo(),
                    p.getDescricao(),
                    p.getQuantidade(),
                    p.getCentroCusto());
        }
    }

    // Função Ordenar - Atribuido á Deivisson

    public void ordenarProduto() {
    }

    // Função Buscar Produto - Atribuido á: Deivisson

    public void buscarProduto() {
    }

    // Função Editar Produto - Atribuido á: Deivisson

    public void editarProduto() {
    }

    // Função Excluir Produto - Atribuido á: Bruno

    public void excluirProduto() {
    }

}
