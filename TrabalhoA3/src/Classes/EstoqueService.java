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

        sc.nextLine(); // Limpa o buffer após nextInt()
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

        System.out.println("-> Lista de Produtos <-");

        // Cabeçalho da tabela
        // %-10s — texto (s) alinhado à esquerda (-) ocupando 10 caracteres
        // %-10d — número inteiro (d) alinhado à esquerda ocupando 10 caracteres
        // O número define a largura da coluna — ajuste conforme precisar
        // %n — quebra de linha

        System.out.printf("%-10s %-20s %-12s %-15s%n", "Código", "Descrição", "Quantidade", "Centro de Custo");
        System.out.println("=".repeat(60));

        // Percorre a lista e exibe cada produto formatado na tabela
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