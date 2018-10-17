package dao;

import model.Multa;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MultaDAO {
    public void insert(Multa multa) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO Multa(codigousuario, descricao, valor) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, multa.getUsuario().getCodigo());
            ps.setString(2, multa.getDescricao());
            ps.setDouble(3, multa.getValor());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Multa> listMultas() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Multa WHERE deletado IS NULL";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Multa> multas = new ArrayList<>();
            while (rs.next()) {
                Multa m = new Multa();
                m.setCodigo(rs.getInt(1));
                /*UsuarioDAO uDAO = new UsuarioDAO();
                m.setUsuario(uDAO.getUsuario(rs.getInt(2)));*/
                m.setDescricao(rs.getString(3));
                m.setValor(rs.getDouble(4));
                multas.add(m);
            }

            conn.close();
            return multas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public void remove(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Multa SET deletado = true WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }


    public void update(Multa multa) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Multa SET descricao = ?, valor = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, multa.getDescricao());
            ps.setDouble(2, multa.getValor());
            ps.setInt(3, multa.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Multa getMulta(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Multa WHERE codigo = ? AND deletado IS NULL";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Multa m = new Multa();
            m.setCodigo(rs.getInt(1));
            /*UsuarioDAO uDAO = new UsuarioDAO();
            m.setUsuario(uDAO.getUsuario(rs.getInt(2)));*/
            m.setDescricao(rs.getString(3));
            m.setValor(rs.getDouble(4));

            conn.close();
            return m;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }
}
