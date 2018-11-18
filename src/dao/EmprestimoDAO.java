package dao;

import dao.proxy.LivroDAOProxy;
import dao.proxy.UsuarioDAOProxy;
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

            String sql2 = "UPDATE Livro SET disponibilidade = false WHERE codigo = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, emprestimo.getLivro().getCodigo());
            ps2.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Emprestimo> listEmprestimo() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Emprestimo WHERE devolvido = false AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Emprestimo> emprestimos = new ArrayList<>();
            conn.close();
            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setCodigo(rs.getInt(1));
                e.setDataEmprestimo(rs.getDate(2).toLocalDate());
                e.setDataDevolucao(rs.getDate(3).toLocalDate());
                e.setDevolvido(rs.getBoolean(4));
                LivroDAO lDAO = new LivroDAO();
                e.setLivro(lDAO.getLivro(rs.getInt(5)));
                UsuarioDAO uDAO = new UsuarioDAO();
                e.setUsuario(uDAO.getUsuario(rs.getInt(6)));
                emprestimos.add(e);
            }


            return emprestimos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Emprestimo> listEmprestimoPendente(Usuario usuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Emprestimo WHERE codigousuario = ? AND devolvido = false AND deletado = false";
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
                e.setDevolvido(rs.getBoolean(4));
                LivroDAO lDAO = new LivroDAO();
                e.setLivro(lDAO.getLivro(rs.getInt(5)));
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

    public List<Emprestimo> listEmprestimo(Usuario usuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Emprestimo WHERE codigousuario = ? AND deletado = false";
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
                e.setDevolvido(rs.getBoolean(4));
                LivroDAO lDAO = new LivroDAO();
                e.setLivro(lDAO.getLivro(rs.getInt(5)));
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
            String sql = "UPDATE Emprestimo SET dataemprestimo = ?, datadevolucao = ?, devolvido = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            Date dataEmprestimo = Date.valueOf(emprestimo.getDataEmprestimo());
            ps.setDate(1, dataEmprestimo);
            Date dataDevolucao = Date.valueOf(emprestimo.getDataDevolucao());
            ps.setDate(2, dataDevolucao);
            ps.setBoolean(3, emprestimo.getDevolvido());
            ps.setInt(4, emprestimo.getCodigo());
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
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setCodigo(rs.getInt(1));
                e.setDataEmprestimo(rs.getDate(2).toLocalDate());
                e.setDataDevolucao(rs.getDate(3).toLocalDate());
                e.setDevolvido(rs.getBoolean(4));
                LivroDAO lDAO = LivroDAOProxy.getInstance();
                e.setLivro(lDAO.getLivro(rs.getInt(5)));
                UsuarioDAO uDAO = UsuarioDAOProxy.getInstance();
                e.setUsuario(uDAO.getUsuario(rs.getInt(6)));

                return e;
            }else{
                return new Emprestimo();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Emprestimo getEmprestimoByLivro(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Emprestimo WHERE codigolivro = ? AND devolvido = false AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setCodigo(rs.getInt(1));
                e.setDataEmprestimo(rs.getDate(2).toLocalDate());
                e.setDataDevolucao(rs.getDate(3).toLocalDate());
                e.setDevolvido(rs.getBoolean(4));
                LivroDAO lDAO = LivroDAOProxy.getInstance();
                e.setLivro(lDAO.getLivro(rs.getInt(5)));
                UsuarioDAO uDAO = UsuarioDAOProxy.getInstance();
                e.setUsuario(uDAO.getUsuario(rs.getInt(6)));

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
