package dao;

import dao.proxy.EmprestimoDAOProxy;
import model.Multa;
import model.Usuario;
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
            String sql = "INSERT INTO Multa(codigoemprestimo, descricao, valor) VALUES (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, multa.getEmprestimo().getCodigo());
            ps.setString(2, multa.getDescricao());
            ps.setDouble(3, multa.getValor());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Multa> listMulta() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Multa WHERE deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            conn.close();
            List<Multa> multas = new ArrayList<>();
            while (rs.next()) {
                Multa m = new Multa();
                m.setCodigo(rs.getInt(1));
                EmprestimoDAO edao = EmprestimoDAOProxy.getInstance();
                m.setEmprestimo(edao.getEmprestimo(rs.getInt(2)));
                m.setDescricao(rs.getString(3));
                m.setValor(rs.getDouble(4));
                multas.add(m);
            }


            return multas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Multa> listMulta(Usuario usuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT M.* FROM ((Multa M JOIN Emprestimo E ON M.codigoemprestimo = E.codigo) " +
                    "JOIN Usuario U ON E.codigousuario = U.codigo) " +
                    "WHERE U.codigo = ? AND M.deletado = FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getCodigo());
            ResultSet rs = ps.executeQuery();
            conn.close();
            List<Multa> multas = new ArrayList<>();

            while (rs.next()) {
                Multa m = new Multa();
                m.setCodigo(rs.getInt(1));
                EmprestimoDAO edao = EmprestimoDAOProxy.getInstance();
                m.setEmprestimo(edao.getEmprestimo(rs.getInt(2)));
                m.setDescricao(rs.getString(3));
                m.setValor(rs.getDouble(4));
                multas.add(m);
            }

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
            String sql = "UPDATE Multa SET deletado = true WHERE codigo = ?";
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
            String sql = "SELECT * FROM Multa WHERE codigo = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                Multa m = new Multa();
                m.setCodigo(rs.getInt(1));
                EmprestimoDAO edao = EmprestimoDAOProxy.getInstance();
                m.setEmprestimo(edao.getEmprestimo(rs.getInt(2)));
                m.setDescricao(rs.getString(3));
                m.setValor(rs.getDouble(4));

                return m;
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
