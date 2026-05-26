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

        // Valida se existe produtos na lista

        if (materiais.isEmpty()) {
            System.out.println("Não existem produtos cadastrados.");
            return;
        }

        // Insira o código

        System.out.println("Digite o código do produto: ");
        int codigo = sc.nextInt();

        // roda a função para verificar se existe esse código
        Produto prod = buscarPorCodigo(codigo);

        // Se não existir o código do produto retorna produto não encontrado.
        if (prod == null) {

            System.out.println("Produto não encontrado.");
            return;
        }

        // Inicio da movimentação
        System.out.println("Digite a quantidade do produto: ");
        int quantidade = sc.nextInt();


        // Se inserir quantidade negativa:
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        prod.setQuantidade(prod.getQuantidade() + quantidade);

        // Sucesso
        System.out.println("Entrada registrada com sucesso.");
        System.out.println("Novo estoque: " + prod.getQuantidade());

    }

    // Função Registrar Saída - Atribuido á: Kaique

    public void registrarSaida() {

        // Valida se existe itens na lista

        if (materiais.isEmpty()) {
            System.out.println("Não existem produtos cadastrados.");
            return;
        }

        // Valida o código

        System.out.print("Digite o código do produto: ");
        int codigo = sc.nextInt();

        // Verifica se existe esse código na lista

        Produto prod = buscarPorCodigo(codigo);

        // Se não existir retorna erro

        if (prod == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        // Inicio da movimentação

        System.out.print("Digite a quantidade de saída: ");
        int quantidade = sc.nextInt();


        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        if (quantidade > prod.getQuantidade()) {
            System.out.println("Erro: quantidade maior que estoque disponível.");
            return;
        }

        prod.setQuantidade(prod.getQuantidade() - quantidade);

        System.out.println("Saída registrada com sucesso.");
        System.out.println("Estoque restante: " + prod.getQuantidade());

    }

    // Função Ordenar - Atribuido á: Deivisson

    public void ordenarProduto() {

        // n = tamanho da lista

        int n = materiais.size();

        //Este é um Bubble Sort (ordenacao por troca),
        // ordenando pelo campo "codigo" (crescente).

        for (int i = 0; i < n - 1; i++) {

            // j percorre até a parte que ainda precisa comparar

            for (int j = 0; j < n - i - 1; j++) {

                // Se o código do item atual for menor que o próximo,
                // eles precisam trocar de lugar para ficar crescente.

                if (materiais.get(j).getCodigo() > materiais.get(j + 1).getCodigo()) {

                    // Troca de posição:
                    // salva o atual em "temp"

                    Produto temp = materiais.get(j);

                    // move o próximo para a posição atual

                    materiais.set(j, materiais.get(j + 1));

                    // coloca o "temp" na posição do próximo

                    materiais.set(j + 1, temp);
                }
            }
        }
        System.out.println("Lista ordenada!");
    }

    // Função Listar Produtos - Atribuido á: Deivisson

    public void listarProduto() {

        // Verifica se há produtos cadastrados antes de exibir
        // Se não houver não exibe nada

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Ordena a lista antes de listar

        ordenarProduto();

        System.out.println("-> Lista de Produtos <-");

        // printf com formatação

        System.out.printf("%-10s %-20s %-12s %-15s%n", "Código", "Descrição", "Quantidade", "Centro de Custo");

        // Linha separadora

        System.out.println("=".repeat(60));

        // Percorre a lista e imprime cada Produto com formatação

        for (Produto lista : materiais) {
            System.out.printf("%-10d %-20s %-12d %-15d%n",
                    lista.getCodigo(),        // Mostra codigo do produto
                    lista.getDescricao(),     // Mostra descrição do produto
                    lista.getQuantidade(),    // Mostra quantidade do produto
                    lista.getCentroCusto());  // Mostra centro de custo do produto
        }
    }

    // Função Buscar por código do produto- Atribuido á: Deivisson

    private Produto buscarPorCodigo(int codigo) {

        // Percorre todos os produtos cadastrados

        for (Produto p : materiais) {

            // Se encontrar um produto com o código informado, retorna ele

            if (p.getCodigo() == codigo) {
                return p;
            }
        }

        // Se não encontrar nenhum produto com o código, retorna null

        return null;
    }

    // Função Buscar Produto - Atribuido á: Deivisson

    public void buscarProduto() {
        System.out.println("-> Buscar Produto <-");

        // Se a lista estiver vazia, não tem o que buscar

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Lê o código que o usuário quer buscar

        System.out.print("Insira o código do produto: ");
        int codigo = sc.nextInt();

        // Usa o metodo auxiliar "buscarPorCodigo" para achar o Produto

        Produto prod = buscarPorCodigo(codigo);

        // Se não achou, prod será null

        if (prod == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        // Se achou, prod mostra as caracteristicas do produto

        System.out.println("=== Produto Encontrado ===");
        System.out.println("Código: " + prod.getCodigo());
        System.out.println("Descrição: " + prod.getDescricao());
        System.out.println("Quantidade: " + prod.getQuantidade());
        System.out.println("Centro de Custo: " + prod.getCentroCusto());
    }

    // Função Editar Produto - Atribuido á: Deivisson

    public void editarProduto() {
        System.out.println("-> Editar Produto <-");

        // Se não houver produtos, não dá para editar

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Lê o código do produto que o usuário quer editar

        System.out.print("Insira o código do produto a editar: ");
        int codigo = sc.nextInt();

        // Usa o metodo auxiliar "buscarPorCodigo" para achar o Produto

        Produto prod = buscarPorCodigo(codigo);

        // Se não encontrou. encerra

        if (prod == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        // Menu de Escolha para definir o que vai ser editado

        System.out.println("Produto atual: " + prod.getDescricao());
        System.out.println("O que deseja editar?");
        System.out.println("1 - Descrição: " + prod.getDescricao());
        System.out.println("2 - Quantidade: " + prod.getQuantidade());
        System.out.println("3 - Centro de Custo: " + prod.getCentroCusto());
        System.out.println("4 - Tudo");
        System.out.println("5 - Código: " + prod.getCodigo());

        System.out.print("Opção: ");

        // Lê opção do menu

        int opcao = sc.nextInt();

        // Limpa buffer(importante por causa da mistura nextInt() e nextLine())

        sc.nextLine();

        // Switch executa a edição conforme a opção escolhida

        switch (opcao) {

            case 1:  // Editar Descrição
                System.out.println("Antiga Descrição: " + prod.getDescricao());
                System.out.print("Nova Descrição: ");
                prod.setDescricao(sc.nextLine());
                break;

            case 2:  // Editar Quantidade
                System.out.println("Antiga Quantidade: " + prod.getQuantidade());
                System.out.print("Nova Quantidade: ");
                prod.setQuantidade(sc.nextInt());
                break;

            case 3:  // Editar Centro de Custo
                System.out.println("Antigo Centro de Custo: " + prod.getCentroCusto());
                System.out.print("Novo Centro de Custo: ");
                prod.setCentroCusto(sc.nextInt());
                break;

            case 4:  // Editar Tudo

                // Descrição
                System.out.println("Antiga Descrição: " + prod.getDescricao());
                System.out.print("Nova Descrição: ");
                prod.setDescricao(sc.nextLine());

                // Quantidade
                System.out.println("Antiga Quantidade: " + prod.getQuantidade());
                System.out.print("Nova Quantidade: ");
                prod.setQuantidade(sc.nextInt());

                // Centro de Custo
                System.out.println("Antigo Centro de Custo: " + prod.getCentroCusto());
                System.out.print("Novo Centro de Custo: ");
                prod.setCentroCusto(sc.nextInt());

                break;

            case 5:  // Editar Código

                // Código
                System.out.println("Antiga Código: " + prod.getCodigo());
                System.out.print("Novo Código: ");
                prod.setCodigo(sc.nextInt());

                break;

            default: // Caso opcao digitada não seja valida
                System.out.println("Opção inválida!");
                return;
        }
        // Feedback ao usuário.
        System.out.println("Produto atualizado com sucesso!");
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
            System.out.println("Produto não encontrado!");
            return;
        }

        // Exibe o produto e pede confirmação antes de excluir

        System.out.println("Produto encontrado: " + prod.getDescricao() + " | Quantidade: " + prod.getQuantidade() + " | Centro de custo: " + prod.getCentroCusto());

        System.out.print("Confirma exclusão? S - Sim | N - Não: ");
        confirmacao = sc.next().toUpperCase();

        if (confirmacao.equals("S")) {
            materiais.remove(prod); // Remove o objeto da lista
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    // Função Limpar Lista de Produtos - Atribuido á : Bruno

    public void limparLista() {

        // Variáveis

        String confirmacao;

        //  Caso esteja vazio

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println(" -> Limpar a Lista <- ");
        System.out.print("Tem certeza que deseja limpar toda a lista: S - Sim | N - Não: ");
        confirmacao = sc.next().toUpperCase();

        if (confirmacao.equals("S")) {
            materiais.clear(); // Limpa toda a lista
            System.out.println("Lista limpa com sucesso!");
        } else {
            System.out.println("Limpeza cancelada.");
        }
    }
}