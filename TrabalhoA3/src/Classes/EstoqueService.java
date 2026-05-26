package Classes;

import java.util.ArrayList;
import java.util.Scanner;

// EstoqueService é a classe de serviço do sistema.
// Toda a lógica fica aqui: validações, busca, cadastro, edição, exclusão.
// A Main apenas chama os métodos desta classe.

public class EstoqueService {

    // =====================
    // ATRIBUTOS DA CLASSE
    // =====================

    // Scanner para leitura de dados do teclado.
    // É compartilhado entre todos os métodos da classe.
    Scanner sc = new Scanner(System.in);

    // ArrayList que armazena todos os produtos cadastrados no sistema.
    // É "private" para que nenhuma outra classe acesse ou modifique a lista diretamente.
    // Toda interação com a lista passa pelos métodos desta classe.
    private ArrayList<Produto> materiais = new ArrayList<>();

    // =====================
    // MÉTODO AUXILIAR PRIVADO — codigoExiste
    // =====================
    // Verifica se já existe um produto com o código informado na lista.
    // Retorna "true" se encontrar, "false" se não encontrar.
    // É "private" porque só é usado internamente por esta classe.

    private boolean codigoExiste(int codigo) {

        // Percorre cada produto da lista usando o apelido "material"
        // Leitura: "para cada material em materiais"
        for (Produto material : materiais) {

            // Compara o código de cada produto com o código recebido
            // Para int, usamos == (comparação de valor)
            if (material.getCodigo() == codigo) {
                return true; // Código encontrado — interrompe e retorna true
            }
        }
        return false; // Percorreu tudo e não encontrou — retorna false
    }

    // =====================
    // MÉTODO AUXILIAR PRIVADO — buscarPorCodigo
    // =====================
    // Percorre a lista e retorna o objeto Produto com o código informado.
    // Se não encontrar, retorna null.
    // É "private" porque é um método de suporte usado internamente.

    private Produto buscarPorCodigo(int codigo) {

        // Percorre cada produto da lista
        for (Produto material : materiais) {

            // Se o código bater, retorna o próprio objeto Produto
            if (material.getCodigo() == codigo) {
                return material;
            }
        }

        // Se percorreu toda a lista sem encontrar, retorna null
        return null;
    }

    // =====================
    // CADASTRAR PRODUTO
    // =====================
    // Responsável por: ler os dados do teclado, validar duplicidade e adicionar na lista.

    public void cadastrarProduto() {

        int codigo;
        String validador;

        System.out.println("-> Cadastro de Produtos <-");

        // Lê o código digitado pelo usuário
        System.out.print("Insira o código do produto: ");
        codigo = sc.nextInt();

        // Valida que o código não seja negativo — if separado e independente
        if (codigo < 0) {
            System.out.println("Código inválido! Insira um número inteiro positivo.");
            return; // Encerra — não faz sentido continuar com código inválido
        }

        // Chama o método auxiliar para verificar se esse código já existe na lista
        // Se retornar true, o código está duplicado
        if (codigoExiste(codigo)) {

            System.out.println("Código já existente! Utilize outro código ou faça a movimentação.");
            System.out.print("Qual ação deseja? C - Outro código | M - Voltar ao menu: ");

            // toUpperCase() converte a entrada para maiúscula
            // assim "c" e "C" funcionam igualmente
            validador = sc.next().toUpperCase();

            if (validador.equals("C")) {
                // Chama o próprio método de novo (recursão)
                // Isso reinicia o cadastro do zero com um novo código
                cadastrarProduto();
            }

            // "return" encerra essa execução do método
            // tanto para o caso "M" quanto após a recursão de "C"
            return;
        }

        // Chegou aqui: código é válido e único
        // Cria um novo objeto Produto vazio para preencher
        Produto prod = new Produto();

        // Define o código já lido
        prod.setCodigo(codigo);

        // Limpa o "\n" que ficou no buffer após o sc.nextInt()
        // Sem isso, o sc.nextLine() abaixo capturaria o Enter vazio
        sc.nextLine();

        System.out.print("Insira a descrição do produto: ");
        prod.setDescricao(sc.nextLine()); // nextLine lê texto com espaços

        System.out.print("Insira a quantidade do produto: ");
        int quantidade = sc.nextInt();

        // Valida que a quantidade não seja negativa
        // 0 é permitido no cadastro — por isso < 0 e não <= 0
        if (quantidade < 0) {
            System.out.println("Quantidade inválida! Insira um número inteiro positivo.");
            return; // Encerra sem cadastrar — sem return o produto seria adicionado mesmo assim
        }

        prod.setQuantidade(quantidade);

        System.out.print("Insira o Centro de Custo do produto: ");
        prod.setCentroCusto(sc.nextInt());

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

        // Verifica se há produtos antes de tentar movimentar
        if (materiais.isEmpty()) {
            System.out.println("Não existem produtos cadastrados.");
            return;
        }

        System.out.print("Digite o código do produto: ");
        int codigo = sc.nextInt();

        // Busca o produto pelo código — retorna null se não encontrar
        Produto prod = buscarPorCodigo(codigo);

        if (prod == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite a quantidade a adicionar: ");
        int quantidade = sc.nextInt();

        // Valida que a quantidade informada seja positiva
        // 0 não é permitido na movimentação — por isso <= 0
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida. Informe um valor maior que zero.");
            return;
        }

        // Soma a quantidade atual com a nova entrada
        // getQuantidade() retorna o valor atual, + quantidade é o que entra
        prod.setQuantidade(prod.getQuantidade() + quantidade);

        System.out.println("Entrada registrada com sucesso!");
        System.out.println("Novo estoque: " + prod.getQuantidade());
    }

    // =====================
    // REGISTRAR SAÍDA (SAÍDA DE ESTOQUE)
    // =====================
    // Diminui a quantidade de um produto já cadastrado.

    public void registrarSaida() {

        // Verifica se há produtos antes de tentar movimentar
        if (materiais.isEmpty()) {
            System.out.println("Não existem produtos cadastrados.");
            return;
        }

        System.out.print("Digite o código do produto: ");
        int codigo = sc.nextInt();

        // Busca o produto na lista
        Produto prod = buscarPorCodigo(codigo);

        if (prod == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite a quantidade de saída: ");
        int quantidade = sc.nextInt();

        // Valida que a quantidade seja positiva
        // 0 não é permitido na movimentação — por isso <= 0
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida. Informe um valor maior que zero.");
            return;
        }

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
    // Algoritmo usado: Bubble Sort — compara pares adjacentes e troca se necessário.

    public void ordenarProduto() {

        // n = número total de produtos na lista
        int n = materiais.size();

        // Loop externo: controla quantas passagens serão feitas
        // A cada passagem, o maior elemento "sobe" para o final
        for (int i = 0; i < n - 1; i++) {

            // Loop interno: percorre os pares ainda não ordenados
            // A cada iteração de i, o último elemento já está no lugar certo
            for (int j = 0; j < n - i - 1; j++) {

                // Compara o código do elemento atual com o próximo
                // Se o atual for maior, eles estão fora de ordem — precisa trocar
                if (materiais.get(j).getCodigo() > materiais.get(j + 1).getCodigo()) {

                    // Troca de posição usando variável temporária "temp"
                    // temp guarda o elemento atual para não perdê-lo durante a troca
                    Produto temp = materiais.get(j);
                    materiais.set(j, materiais.get(j + 1)); // atual recebe o próximo
                    materiais.set(j + 1, temp);             // próximo recebe o temp (antigo atual)
                }
            }
        }
    }


    // =====================
    // LISTAR PRODUTOS
    // =====================
    // Exibe todos os produtos cadastrados em formato de tabela.

    public void listarProduto() {

        // Se não houver produtos, não há nada para listar
        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Ordena antes de exibir para garantir a ordem por código
        ordenarProduto();

        System.out.println("-> Lista de Produtos <-");

        // printf formata a saída em colunas:
        // %-10s = texto alinhado à esquerda ocupando 10 caracteres
        // %-10d = número inteiro alinhado à esquerda ocupando 10 caracteres
        // %n = quebra de linha (compatível com todos os sistemas operacionais)
        System.out.printf("%-10s %-20s %-12s %-15s%n", "Código", "Descrição", "Quantidade", "Centro de Custo");

        // Linha separadora visual entre o cabeçalho e os dados
        System.out.println("=".repeat(60));

        // Percorre cada produto da lista e imprime os dados formatados
        // Leitura: "para cada material em materiais"
        for (Produto material : materiais) {
            System.out.printf("%-10d %-20s %-12d %-15d%n",
                    material.getCodigo(),       // %d = inteiro
                    material.getDescricao(),    // %s = texto
                    material.getQuantidade(),   // %d = inteiro
                    material.getCentroCusto()); // %d = inteiro
        }
    }


    // =====================
    // BUSCAR PRODUTO
    // =====================
    // Localiza e exibe os dados de um produto pelo código.

    public void buscarProduto() {
        System.out.println("-> Buscar Produto <-");

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.print("Insira o código do produto: ");
        int codigo = sc.nextInt();

        // Usa o método auxiliar para encontrar o produto
        Produto prod = buscarPorCodigo(codigo);

        // Se retornou null, o produto não existe na lista
        if (prod == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        // Produto encontrado — exibe os dados
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

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.print("Insira o código do produto a editar: ");
        int codigo = sc.nextInt();

        // Busca o produto — se retornar null, encerra
        Produto prod = buscarPorCodigo(codigo);

        if (prod == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        // Exibe o estado atual e o menu de opções de edição
        System.out.println("Produto atual: " + prod.getDescricao());
        System.out.println("O que deseja editar?");
        System.out.println("1 - Código: " + prod.getCodigo());
        System.out.println("2 - Descrição: " + prod.getDescricao());
        System.out.println("3 - Quantidade: " + prod.getQuantidade());
        System.out.println("4 - Centro de Custo: " + prod.getCentroCusto());
        System.out.println("5 - Tudo");
        System.out.print("Opção: ");

        int opcao = sc.nextInt();

        // Limpa o buffer — necessário porque nextInt() deixa o \n para trás
        // O nextLine() dentro do switch precisaria disso
        sc.nextLine();

        switch (opcao) {

            case 1: // Editar apenas o Código
                System.out.println("Antigo Código: " + prod.getCodigo());
                System.out.print("Novo Código: ");
                prod.setCodigo(sc.nextInt());
                break;

            case 2: // Editar apenas a Descrição
                System.out.println("Antiga Descrição: " + prod.getDescricao());
                System.out.print("Nova Descrição: ");
                prod.setDescricao(sc.nextLine());
                break;

            case 3: // Editar apenas a Quantidade
                System.out.println("Antiga Quantidade: " + prod.getQuantidade());
                System.out.print("Nova Quantidade: ");
                prod.setQuantidade(sc.nextInt());
                break;

            case 4: // Editar apenas o Centro de Custo
                System.out.println("Antigo Centro de Custo: " + prod.getCentroCusto());
                System.out.print("Novo Centro de Custo: ");
                prod.setCentroCusto(sc.nextInt());
                break;

            case 5: // Editar todos os campos
                System.out.println("Antigo Código: " + prod.getCodigo());
                System.out.print("Novo Código: ");
                prod.setCodigo(sc.nextInt());
                sc.nextLine(); // Limpa buffer antes do nextLine()

                System.out.println("Antiga Descrição: " + prod.getDescricao());
                System.out.print("Nova Descrição: ");
                prod.setDescricao(sc.nextLine());

                System.out.println("Antiga Quantidade: " + prod.getQuantidade());
                System.out.print("Nova Quantidade: ");
                prod.setQuantidade(sc.nextInt());

                System.out.println("Antigo Centro de Custo: " + prod.getCentroCusto());
                System.out.print("Novo Centro de Custo: ");
                prod.setCentroCusto(sc.nextInt());
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

        String confirmacao;

        System.out.println("-> Excluir Produto <-");

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.print("Insira o código do produto a excluir: ");
        int codigo = sc.nextInt();

        // Busca o produto na lista pelo código
        Produto prod = buscarPorCodigo(codigo);

        if (prod == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        // Exibe os dados encontrados e pede confirmação antes de excluir
        System.out.println("Produto encontrado: " + prod.getDescricao()
                + " | Quantidade: " + prod.getQuantidade()
                + " | Centro de Custo: " + prod.getCentroCusto());

        System.out.print("Confirma exclusão? S - Sim | N - Não: ");
        confirmacao = sc.next().toUpperCase();

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

        String confirmacao;

        if (materiais.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("-> Limpar a Lista <-");
        System.out.print("Tem certeza que deseja limpar toda a lista? S - Sim | N - Não: ");
        confirmacao = sc.next().toUpperCase();

        if (confirmacao.equals("S")) {
            // clear() remove todos os elementos do ArrayList de uma vez
            materiais.clear();
            System.out.println("Lista limpa com sucesso!");
        } else {
            System.out.println("Limpeza cancelada.");
        }
    }
}