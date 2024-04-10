public class Produto {

    private int idProduto;
    private String nome;
    private int quantidade;
    private String categoria;
    private float preco;

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", categoria='" + categoria + '\'' +
                ", preco=" + preco +
                '}';
    }

    public Produto(int idProduto, String nome, int quantidade, String categoria, float preco) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.preco = preco;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
