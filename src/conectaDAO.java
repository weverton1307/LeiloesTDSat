import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectaDAO {
    private Connection conn = null;

    public Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leiloes?useSSL=false", "root", "Wdontf.272.391.646");
            System.out.println("Conexão realizada com sucesso");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
        }
        return conn;
    }

    public void desconectar() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão encerrada com sucesso");
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar: " + e.getMessage());
            }
        }
    }
}
