package dao;

import model.Autor;
import model.Usuario;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    public void insert(Autor autor) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO Autor(nome) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, autor.getNome());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Autor> listAutor() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Autor WHERE deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Autor> autores = new ArrayList<>();
            while (rs.next()) {
                Autor a = new Autor();
                a.setCodigo(rs.getInt(1));
                a.setNome(rs.getString(2));
                autores.add(a);
            }


            conn.close();
            return autores;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public void remove(int id) {
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
    }


    public void update(Autor autor) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Autor SET nome = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, autor.getNome());
            ps.setInt(2, autor.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Autor getAutor(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Autor WHERE codigo = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if (rs.next()) {
                Autor a = new Autor();
                a.setCodigo(rs.getInt(1));
                a.setNome(rs.getString(2));
                return a;
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
