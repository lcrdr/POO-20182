package dao;

import model.Autor;
import model.CategoriaLivro;
import model.Livro;
import model.Usuario;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    public void insert(Livro livro) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO Livro(titulo, prioridade, " +
                    "ano) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getPrioridade());
            ps.setInt(3, livro.getAno());
            ps.executeUpdate();

            for (CategoriaLivro categoria : livro.getCategoria()) {
                String sql2 = "INSERT INTO LivroCategoria(codigolivro, codigocategoria) "
                        + "VALUES (?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, livro.getCodigo());
                ps2.setInt(2, categoria.getCodigo());
                ps2.executeUpdate();
            }

            for (Autor autor : livro.getAutor()) {
                String sql2 = "INSERT INTO LivroAutor(codigolivro, codigoautor) "
                        + "VALUES (?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, livro.getCodigo());
                ps2.setInt(2, autor.getCodigo());
                ps2.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Livro> listLivros() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Livro WHERE deletado IS NULL";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Livro> livros = new ArrayList<>();
            while (rs.next()) {
                Livro l = new Livro();
                l.setCodigo(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setPrioridade(rs.getInt(3));
                l.setAno(rs.getInt(4));
                l.setDisponibilidade(rs.getBoolean(5));
                //CategoriaLivro cDAO = new CategoriaLivroDAO();
                //l.setCategoria(cDAO.getCategoria(rs.getInt(6)));
                livros.add(l);
            }


            conn.close();
            return livros;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public void remove(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Livro SET deletado = true WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }


    public void update(Livro livro) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Livro SET titulo = ?, prioridade = ?, " +
                    "ano = ?, disponibilidade = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getPrioridade());
            ps.setInt(3, livro.getAno());
            ps.setBoolean(4, livro.getDisponibilidade());
            ps.setInt(5, livro.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Livro getLivro(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Livro WHERE codigo = ? AND deletado IS NULL";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Livro l = new Livro();
            l.setCodigo(rs.getInt(1));
            l.setTitulo(rs.getString(2));
            l.setPrioridade(rs.getInt(3));
            l.setAno(rs.getInt(4));
            l.setDisponibilidade(rs.getBoolean(5));
            //CategoriaLivro cDAO = new CategoriaLivroDAO();
            //l.setCategoria(cDAO.getCategoria(rs.getInt(6)));

            conn.close();
            return l;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }
}
