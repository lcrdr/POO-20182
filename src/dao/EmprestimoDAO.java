package dao;

import model.Emprestimo;
import model.Usuario;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    public void insert(Emprestimo emprestimo) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO Emprestimo(dataemprestimo, datadevolucao, codigolivro, codigousuario) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            Date dataEmprestimo = Date.valueOf(emprestimo.getDataEmprestimo());
            ps.setDate(1, dataEmprestimo);
            Date dataDevolucao = Date.valueOf(emprestimo.getDataDevolucao());
            ps.setDate(2, dataDevolucao);
            ps.setInt(3, emprestimo.getLivro().getCodigo());
            ps.setInt(4, emprestimo.getUsuario().getCodigo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Emprestimo> listEmprestimo() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Emprestimo WHERE deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Emprestimo> emprestimos = new ArrayList<>();
            conn.close();
            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setCodigo(rs.getInt(1));
                e.setDataEmprestimo(rs.getDate(2).toLocalDate());
                e.setDataDevolucao(rs.getDate(3).toLocalDate());
                LivroDAO lDAO = new LivroDAO();
                e.setLivro(lDAO.getLivro(rs.getInt(4)));
                UsuarioDAO uDAO = new UsuarioDAO();
                e.setUsuario(uDAO.getUsuario(rs.getInt(5)));
                emprestimos.add(e);
            }


            return emprestimos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Emprestimo> listEmprestimo(Usuario usuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Emprestimo WHERE codigousuario = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,usuario.getCodigo());

            ResultSet rs = ps.executeQuery();
            List<Emprestimo> emprestimos = new ArrayList<>();
            conn.close();

            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setCodigo(rs.getInt(1));
                e.setDataEmprestimo(rs.getDate(2).toLocalDate());
                e.setDataDevolucao(rs.getDate(3).toLocalDate());
                LivroDAO lDAO = new LivroDAO();
                e.setLivro(lDAO.getLivro(rs.getInt(4)));
                e.setUsuario(usuario);
                emprestimos.add(e);
            }

            return emprestimos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public void remove(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Emprestimo SET deletado = true WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }


    public void update(Emprestimo emprestimo) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Emprestimo SET dataemprestimo = ?, datadevolucao = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            Date dataEmprestimo = Date.valueOf(emprestimo.getDataEmprestimo());
            ps.setDate(1, dataEmprestimo);
            Date dataDevolucao = Date.valueOf(emprestimo.getDataDevolucao());
            ps.setDate(2, dataDevolucao);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Emprestimo getEmprestimo(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Emprestimo WHERE codigo = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setCodigo(rs.getInt(1));
                e.setDataEmprestimo(rs.getDate(2).toLocalDate());
                e.setDataDevolucao(rs.getDate(3).toLocalDate());
                /*LivroDAO lDAO = new LivroDAO();
                e.setLivro(lDAO.getLivro(rs.getInt(4)));*/
                /*UsuarioDAO uDAO = new UsuarioDAO();
                e.setUsuario(uDAO.getUsuario(rs.getInt(5)));*/

                return e;
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }
}
