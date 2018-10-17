package dao;

import model.CategoriaUsuario;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaUsuarioDAO {
    public void insert(CategoriaUsuario categoriaUsuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO CategoriaUsuario(nome,devolucao) "
                    + "VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoriaUsuario.getNome());
            ps.setInt(2, categoriaUsuario.getDevolucao());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<CategoriaUsuario> listCategoria() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM CategoriaUsuario";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<CategoriaUsuario> categorias = new ArrayList<>();
            while (rs.next()) {
                CategoriaUsuario c = new CategoriaUsuario();
                c.setCodigo(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDevolucao(rs.getInt(3));
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

    /*
    public void remove(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE CategoriaUsuario SET deletado = true WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }*/

    public void update(CategoriaUsuario categoriaUsuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE CategoriaUsuario SET nome = ?, devolucao = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoriaUsuario.getNome());
            ps.setInt(2, categoriaUsuario.getDevolucao());
            ps.setInt(3, categoriaUsuario.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public CategoriaUsuario getCategoria(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM CategoriaUsuario WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            CategoriaUsuario c = new CategoriaUsuario();
            c.setCodigo(rs.getInt(1));
            c.setNome(rs.getString(2));
            c.setDevolucao(rs.getInt(3));

            conn.close();
            return c;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }
}
