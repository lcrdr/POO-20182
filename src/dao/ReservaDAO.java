package dao;

import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.LivroDAOProxy;
import dao.proxy.UsuarioDAOProxy;
import model.Multa;
import model.Reserva;
import model.Usuario;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    public void insert(Reserva reserva) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO Reserva(codigolivro, codigousuario, datareserva) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, reserva.getLivro().getCodigo());
            ps.setInt(2, reserva.getUsuario().getCodigo());
            Date data = Date.valueOf(reserva.getDataReserva());
            ps.setDate(3, data);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Reserva> listReserva() {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Reserva WHERE concluida = false AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Reserva> reservas = new ArrayList<>();
            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt(1));
                LivroDAO livro = LivroDAOProxy.getInstance();
                r.setLivro(livro.getLivro(rs.getInt(2)));
                UsuarioDAO usuario = UsuarioDAOProxy.getInstance();
                r.setUsuario(usuario.getUsuario(rs.getInt(3)));
                reservas.add(r);
            }

            conn.close();
            return reservas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public void remove(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Reserva SET deletado = true WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }


    public void update(Reserva reserva) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Reserva SET livro = ?, usuario = ?, datareserva = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reserva.getLivro().getCodigo());
            ps.setInt(2, reserva.getUsuario().getCodigo());
            Date data = Date.valueOf(reserva.getDataReserva());
            ps.setDate(3, data);
            ps.setInt(4, reserva.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public void concluirReserva(Reserva reserva) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Reserva SET concluida = true WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reserva.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Reserva getReserva(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Reserva WHERE codigo = ? AND concluida = false AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt(1));
                LivroDAO livro = LivroDAOProxy.getInstance();
                r.setLivro(livro.getLivro(rs.getInt(2)));
                UsuarioDAO usuario = UsuarioDAOProxy.getInstance();
                r.setUsuario(usuario.getUsuario(rs.getInt(3)));

                return r;
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Reserva getReserva(int idLivro, int idUsuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Reserva WHERE codigolivro = ? AND codigousuario = ? AND concluida = false AND deletado = FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idLivro);
            ps.setInt(2, idUsuario);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt(1));
                LivroDAO livro = LivroDAOProxy.getInstance();
                r.setLivro(livro.getLivro(rs.getInt(2)));
                UsuarioDAO usuario = UsuarioDAOProxy.getInstance();
                r.setUsuario(usuario.getUsuario(rs.getInt(3)));

                return r;
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Reserva verificarFila(int idLivro) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Reserva WHERE codigolivro = ? AND concluida = false AND deletado = FALSE ORDER BY datareserva DESC LIMIT 1;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idLivro);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt(1));
                LivroDAO livro = LivroDAOProxy.getInstance();
                r.setLivro(livro.getLivro(rs.getInt(2)));
                UsuarioDAO usuario = UsuarioDAOProxy.getInstance();
                r.setUsuario(usuario.getUsuario(rs.getInt(3)));

                return r;
            }else{
                Reserva r = new Reserva();
                r.setId(0);
                return r;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public boolean verificarReserva(int idLivro, int idUsuario) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Reserva WHERE codigolivro = ? AND codigousuario = ? AND concluida = false AND deletado = FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idLivro);
            ps.setInt(2, idUsuario);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
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
