import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        List<Produto> produtos = new ArrayList<>();
        String csvSeparator = ";";

        Connection connection = Database.connectDatabase();
        try(BufferedReader br = new BufferedReader(new FileReader("./resources/estoque.csv"))){
            produtos = br.lines()
                    .skip(1)
                    .map(line -> {
                        String[] campos = line.split(csvSeparator);
                        int idProduto = Integer.parseInt(campos[0]);
                        String nome = campos[1];
                        int quantidade = Integer.parseInt(campos[2]);
                        String categoria = campos[3];
                        float preco = Float.parseFloat(campos[4]);
                        return new Produto(idProduto, nome, quantidade, categoria, preco);
                    })
                    .toList();
        }catch (IOException e){
            e.printStackTrace();
        }

        for(Produto produto: produtos){
            System.out.println(produto);
        }

        quantidadeCategorias(produtos);
        quantidadeProdutoPorCategoria(produtos);
        valorMedioProdutos(produtos);
        estoqueBaixo(produtos);

        Database.insert(produtos);


    }

    private static void estoqueBaixo(List<Produto> produtos) {
        List<Produto> produtosBaixo = new ArrayList<>();

        for(Produto produto: produtos){
            if(produto.getQuantidade()<3){
                produtosBaixo.add(produto);
            }
        }

        System.out.println("Produtos estoque baixo: "+produtosBaixo);
    }

    private static void valorMedioProdutos(List<Produto> produtos) {
        float media=0;
        int quantidade=0;

        for(Produto produto: produtos){
            media = media+produto.getPreco();
            quantidade++;
        }

        System.out.println("A média dos preços de cada produto é: "+(media/quantidade));
    }

    private static void quantidadeProdutoPorCategoria(List<Produto> produtos) {
        Set<String> categorias = new HashSet<>();
        HashMap<String, String> produtosCategoria = new HashMap<>();
        int quantiddade=0;

        for(Produto produto: produtos){
            categorias.add(produto.getCategoria());
        }

        for(String categoria: categorias){
            quantiddade=0;
            for(Produto produto: produtos){
                if(Objects.equals(produto.getCategoria(), categoria)){
                    quantiddade++;
                }
            }
            produtosCategoria.put(categoria, String.valueOf(quantiddade));
        }

        System.out.println("A quantidade de produtos de cada categoria tem em estoque");
        System.out.println(produtosCategoria);



    }

    private static void quantidadeCategorias(List<Produto> produtos) {

        Set<String> categorias = new HashSet<>();

        for(Produto produto: produtos){
            categorias.add(produto.getCategoria());
        }

        System.out.println("A quantidade de categorias é: "+categorias.size());
        System.out.println("São elas: "+categorias);


    }
}