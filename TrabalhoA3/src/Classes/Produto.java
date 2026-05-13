package Classes;

public class Produto {

    // Atributos

    private int codigo;
    private String descricao;
    private int quantidade;
    private int centroCusto;

    public Produto() {
    }

    // Construtor

    public Produto(int codigo, String descricao, int quantidade, int centroCusto) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.centroCusto = centroCusto;
    }

    // Get and Set

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(int centroCusto) {
        this.centroCusto = centroCusto;
    }

    // Método To String

    @Override
    public String toString() {
        return "Produto{" +
                "centroCusto=" + centroCusto +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", codigo=" + codigo + '}';
    }
}
