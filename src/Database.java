import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {


    private static Connection connection = null;

    public static Connection connectDatabase() {

        try {

            Class.forName("org.postgresql.Driver");


            String url = "jdbc:postgresql://localhost:5432/postgres"; //Set your database


            String username = ""; //Set your username
            String password = ""; //Set your password


            connection = DriverManager.getConnection(url, username, password);


            System.out.println("Conexão estabelecida.");
        } catch (ClassNotFoundException e) {
            System.out.println("Não foi encontrado o driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar.");
            e.printStackTrace();
        }

        return connection;
    }

    public static void insert(List<Produto> produtos) throws SQLException {

        String sql = "INSERT INTO produto (id_produto, nome, quantidade, categoria, preco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Produto produto : produtos) {
                preparedStatement.setInt(1, produto.getIdProduto());
                preparedStatement.setString(2, produto.getNome());
                preparedStatement.setInt(3, produto.getQuantidade());
                preparedStatement.setString(4, produto.getCategoria());
                preparedStatement.setFloat(5, produto.getPreco());
                preparedStatement.executeUpdate();
            }
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro na inserção de dados.");
            e.printStackTrace();
        }finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
