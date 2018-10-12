package dao;

import model.Usuario;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public void insert(Usuario usuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO Usuario(codigo, nome, sexo, " +
                    "endereco, telefone, codigocategoria) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getCodigo());
            ps.setString(2, usuario.getNome());
            ps.setBoolean(3, usuario.getSexo());
            ps.setString(4, usuario.getEndereco());
            ps.setString(5, usuario.getTelefone());
            ps.setInt(6, usuario.getCategoria().getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

   public List<Usuario> listUsuarios() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Usuario WHERE deletado IS NULL";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setCodigo(rs.getInt(1));
                u.setNome(rs.getString(2));
                u.setSexo(rs.getBoolean(3));
                u.setEndereco(rs.getString(4));
                u.setTelefone(rs.getString(5));
                //CategoriaUsuarioDAO cDAO = new CategoriaUsuarioDAO();
                //u.setCategoria(cDAO.getCategoria(rs.getInt(6)));
                usuarios.add(u);
            }


            conn.close();
            return usuarios;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public void remove(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Usuario SET deletado = true WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }


    public void update(Usuario usuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Usuario SET nome = ?, sexo = ?, " +
                    "endereco = ?, telefone = ?, codigocategoria = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getCodigo());
            ps.setString(2, usuario.getNome());
            ps.setBoolean(3, usuario.getSexo());
            ps.setString(4, usuario.getEndereco());
            ps.setString(5, usuario.getTelefone());
            ps.setInt(6, usuario.getCategoria().getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Usuario getUsuario(String nome) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Usuario WHERE deletado IS NULL";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
                Usuario u = new Usuario();
                u.setCodigo(rs.getInt(1));
                u.setNome(rs.getString(2));
                u.setSexo(rs.getBoolean(3));
                u.setEndereco(rs.getString(4));
                u.setTelefone(rs.getString(5));
                //CategoriaUsuarioDAO cDAO = new CategoriaUsuarioDAO();
                //u.setCategoria(cDAO.getCategoria(rs.getInt(6)));

            conn.close();
            return u;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }
}
