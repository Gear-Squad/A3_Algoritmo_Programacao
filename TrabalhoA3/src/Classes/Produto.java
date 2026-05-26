package Classes;

// A classe Produto representa um produto do sistema.
// Ela é responsável apenas por guardar os dados.

public class Produto {

    // =====================
    // ATRIBUTOS
    // =====================
    // Os atributos são "private" — isso é encapsulamento.
    // Nenhuma outra classe acessa esses valores diretamente.
    // O acesso é feito apenas pelos métodos getters e setters abaixo.

    private int codigo;         // Identificador único do produto
    private String descricao;   // Nome ou descrição do produto
    private int quantidade;     // Quantidade disponível em estoque
    private int centroCusto;    // Código do centro de custo responsável

    // =====================
    // CONSTRUTOR VAZIO
    // =====================
    // Permite criar um objeto Produto sem informar nada.
    // Usado quando os dados serão preenchidos depois via setters.
    // Exemplo: Produto p = new Produto();

    public Produto() {
    }

    // =====================
    // CONSTRUTOR COMPLETO
    // =====================
    // Em vez de setar os atributos linha por linha com os setters,
    // o construtor completo permite passar todos os valores de uma vez, separados por vírgula.
    // O "this" é responsável por pegar cada valor recebido e guardar no atributo correto do objeto.
    // Exemplo: Produto p = new Produto(1, "Parafuso", 50, 101);

    public Produto(int codigo, String descricao, int quantidade, int centroCusto) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.centroCusto = centroCusto;
    }

    // =====================
    // GETTERS E SETTERS
    // =====================
    // Getters: retornam o valor do atributo (leitura)
    // Setters: definem o valor do atributo (escrita)
    // Como os atributos são private (privados), esse é o único caminho para acessá-los.

    // Retorna o código do produto
    public int getCodigo() {
        return codigo;
    }

    // Define o código do produto
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Retorna a descrição do produto
    public String getDescricao() {
        return descricao;
    }

    // Define a descrição do produto
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Retorna a quantidade em estoque
    public int getQuantidade() {
        return quantidade;
    }

    // Define a quantidade em estoque
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Retorna o centro de custo
    public int getCentroCusto() {
        return centroCusto;
    }

    // Define o centro de custo
    public void setCentroCusto(int centroCusto) {
        this.centroCusto = centroCusto;
    }

    // =====================
    // METODO TOSTRING
    // =====================
    // O @Override indica que estamos substituindo o toString() padrão do Java.
    // O toString() padrão mostraria algo como "Classes.Produto@1b6d3586" (endereço de memória).
    // Com esse override, System.out.println(produto) exibe os dados do produto de forma legível.

    @Override
    public String toString() {
        return "Produto -> " +
                "Código: " + codigo +
                " | Descrição: " + descricao +
                " | Quantidade: " + quantidade +
                " | Centro de Custo: " + centroCusto;
    }
}