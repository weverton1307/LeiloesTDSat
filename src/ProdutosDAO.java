/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public int cadastrarProduto(ProdutosDTO produto) {
        conectaDAO cDAO = new conectaDAO();
        conn = cDAO.connectDB();

        try {
            ps = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.setString(3, produto.getStatus());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar " + ex.getMessage());
            return ex.getErrorCode();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    cDAO.desconectar();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar recursos: " + ex.getMessage());
            }
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        conectaDAO cDAO = new conectaDAO();
        conn = cDAO.connectDB();
        try {
            ps = conn.prepareStatement("SELECT * FROM produtos");
            resultset = ps.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            return listagem;
        } catch (SQLException e) {
            System.out.println("Erro ao listar " + e.getMessage());
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    cDAO.desconectar();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar recursos: " + ex.getMessage());
            }
        }

    }
    public int venderProduto(int id) {
        conectaDAO cDAO = new conectaDAO();
        conn = cDAO.connectDB();
        try {
            ps = conn.prepareStatement("UPDATE Produtos SET status = ? WHERE id = ?");
            ps.setString(1, "Vendido");
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return e.getErrorCode();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    cDAO.desconectar();
                }
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }
            public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        conectaDAO cDAO = new conectaDAO();
        conn = cDAO.connectDB();
        ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT * FROM produtos WHERE status = ?");
            ps.setString(1, "Vendido");
            resultset = ps.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setStatus(resultset.getString("status"));
                produto.setValor(resultset.getInt("valor"));
                listaVendidos.add(produto);
            }
            return listaVendidos;

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        } finally {
            try {
                 if(resultset != null){
                  resultset.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    cDAO.desconectar();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar recursos: " + ex.getMessage());
            }
        }
    }
}
