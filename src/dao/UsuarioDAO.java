package dao;

import model.Usuario;
import util.ComandosFlyweight;
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
            String sql = "INSERT INTO Usuario(nome, sexo, endereco, telefone, categoriausuario, login, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setBoolean(2, usuario.getSexo());
            ps.setString(3, usuario.getEndereco());
            ps.setString(4, usuario.getTelefone());
            ps.setInt(5, usuario.getCategoriaUsuario().getIdCategoria());
            ps.setString(6,usuario.getLogin());
            ps.setString(7, usuario.getSenha());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

   public List<Usuario> listUsuario() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Usuario WHERE deletado=FALSE";
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
                u.setLogin(rs.getString(6));
                u.setSenha(rs.getString(7));
                u.setCategoriaUsuario(ComandosFlyweight.getInstance().getCategoria(rs.getInt(6)));
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
            String sql = "UPDATE Usuario SET deletado = true WHERE codigo = ?";
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
            String sql = "UPDATE Usuario SET nome = ?, sexo = ?, endereco = ?, telefone = ?, categoriausuario = ?, senha = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setBoolean(2, usuario.getSexo());
            ps.setString(3, usuario.getEndereco());
            ps.setString(4, usuario.getTelefone());
            ps.setInt(5, usuario.getCategoriaUsuario().getIdCategoria());
            ps.setString(6, usuario.getSenha());
            ps.setInt(7, usuario.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Usuario getUsuario(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Usuario WHERE codigo = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()){
                Usuario u = new Usuario();
                u.setCodigo(rs.getInt(1));
                u.setNome(rs.getString(2));
                u.setSexo(rs.getBoolean(3));
                u.setEndereco(rs.getString(4));
                u.setTelefone(rs.getString(5));
                u.setCategoriaUsuario(ComandosFlyweight.getInstance().getCategoria(rs.getInt(6)));
                u.setLogin(rs.getString(7));
                u.setSenha(rs.getString(8));

                return u;
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Usuario getUsuario(String login) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Usuario WHERE login = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()){
                Usuario u = new Usuario();
                u.setCodigo(rs.getInt(1));
                u.setNome(rs.getString(2));
                u.setSexo(rs.getBoolean(3));
                u.setEndereco(rs.getString(4));
                u.setTelefone(rs.getString(5));
                u.setCategoriaUsuario(ComandosFlyweight.getInstance().getCategoria(rs.getInt(6)));
                u.setLogin(rs.getString(7));
                u.setSenha(rs.getString(8));

                return u;
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public boolean verificaLogin(String login) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Usuario WHERE login = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "'" + login + "'");
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public boolean verificaLogin(String login, String senha) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Usuario WHERE login = ? AND senha = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2,   senha);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }
}
