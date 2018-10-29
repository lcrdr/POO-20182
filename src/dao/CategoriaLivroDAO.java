package dao;

import model.CategoriaLivro;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaLivroDAO {
    public void insert(CategoriaLivro categoriaLivro) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO CategoriaLivro(nome) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoriaLivro.getNome());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<CategoriaLivro> listCategoria() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM CategoriaLivro";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<CategoriaLivro> categorias = new ArrayList<>();
            while (rs.next()) {
                CategoriaLivro c = new CategoriaLivro();
                c.setCodigo(rs.getInt(1));
                c.setNome(rs.getString(2));
                categorias.add(c);
            }

            conn.close();
            return categorias;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    /*public void remove(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Autor SET deletado = true WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }*/


    public void update(CategoriaLivro categoriaLivro) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE CategoriaLivro SET nome = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoriaLivro.getNome());
            ps.setInt(2, categoriaLivro.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public CategoriaLivro getCategoria(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM CategoriaLivro WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                CategoriaLivro c = new CategoriaLivro();
                c.setCodigo(rs.getInt(1));
                c.setNome(rs.getString(2));
                return c;
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
