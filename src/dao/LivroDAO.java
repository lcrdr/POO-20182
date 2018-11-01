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
            String sql = "INSERT INTO Livro(titulo, prioridade, ano) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getPrioridade());
            ps.setInt(3, livro.getAno());
            ps.executeUpdate();

            livro.setCodigo(getLast());

            for (CategoriaLivro categoria : livro.getCategoria()) {
                String sql2 = "INSERT INTO LivroCategoria(codigolivro, codigocategoria) VALUES (?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, livro.getCodigo());
                ps2.setInt(2, categoria.getCodigo());
                ps2.executeUpdate();
            }

            for (Autor autor : livro.getAutor()) {
                String sql2 = "INSERT INTO LivroAutor(codigolivro, codigoautor) VALUES (?, ?)";
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
            String sql = "SELECT * FROM Livro WHERE deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            conn.close();
            List<Livro> livros = new ArrayList<>();
            while (rs.next()) {
                Livro l = new Livro();
                l.setCodigo(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setPrioridade(rs.getInt(3));
                l.setAno(rs.getInt(4));
                l.setDisponibilidade(rs.getBoolean(5));
                l.setCategoria(getCategorias(l.getCodigo()));
                l.setAutor(getAutor(l.getCodigo()));
                livros.add(l);
            }

            return livros;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Livro> listLivros(String nome) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Livro WHERE titulo LIKE ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            conn.close();
            List<Livro> livros = new ArrayList<>();
            while (rs.next()) {
                Livro l = new Livro();
                l.setCodigo(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setPrioridade(rs.getInt(3));
                l.setAno(rs.getInt(4));
                l.setDisponibilidade(rs.getBoolean(5));
                l.setCategoria(getCategorias(l.getCodigo()));
                l.setAutor(getAutor(l.getCodigo()));
                livros.add(l);
            }

            return livros;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Livro> listLivros(int autor) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT L.* FROM ((LivroAutor LA JOIN Autor A ON LA.codigoautor = A.codigo) JOIN Livro L ON LA.codigolivro = L.codigo) WHERE A.codigo = ? AND L.deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, autor);
            ResultSet rs = ps.executeQuery();
            conn.close();
            List<Livro> livros = new ArrayList<>();
            while (rs.next()) {
                Livro l = new Livro();
                l.setCodigo(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setPrioridade(rs.getInt(3));
                l.setAno(rs.getInt(4));
                l.setDisponibilidade(rs.getBoolean(5));
                l.setCategoria(getCategorias(l.getCodigo()));
                l.setAutor(getAutor(l.getCodigo()));
                livros.add(l);
            }

            return livros;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Livro> listLivros(boolean disponibilidade) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Livro WHERE disponibilidade = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, disponibilidade);
            ResultSet rs = ps.executeQuery();
            conn.close();
            List<Livro> livros = new ArrayList<>();
            while (rs.next()) {
                Livro l = new Livro();
                l.setCodigo(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setPrioridade(rs.getInt(3));
                l.setAno(rs.getInt(4));
                l.setDisponibilidade(rs.getBoolean(5));
                l.setCategoria(getCategorias(l.getCodigo()));
                l.setAutor(getAutor(l.getCodigo()));
                livros.add(l);
            }

            return livros;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public List<Livro> listLivrosCategoria(int categoria) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT L.* FROM LivroCategoria LC JOIN Livro L ON LC.codigolivro = L.codigo WHERE LC.codigocategoria = ? AND L.deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoria);
            ResultSet rs = ps.executeQuery();
            conn.close();
            List<Livro> livros = new ArrayList<>();
            while (rs.next()) {
                Livro l = new Livro();
                l.setCodigo(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setPrioridade(rs.getInt(3));
                l.setAno(rs.getInt(4));
                l.setDisponibilidade(rs.getBoolean(5));
                l.setCategoria(getCategorias(l.getCodigo()));
                l.setAutor(getAutor(l.getCodigo()));
                livros.add(l);
            }

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
            String sql = "UPDATE Livro SET deletado = TRUE WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    private List<CategoriaLivro> getCategorias(int id){
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT C.codigo, C.nome FROM LivroCategoria LC JOIN CategoriaLivro C ON LC.codigocategoria = C.codigo WHERE LC.codigolivro = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();

            List<CategoriaLivro> lista = new ArrayList<>();

            while(rs.next()) {
                CategoriaLivro l = new CategoriaLivro();
                l.setCodigo(rs.getInt(1));
                l.setNome(rs.getString(2));
                lista.add(l);
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    private List<Autor> getAutor(int id){
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT A.codigo, A.nome FROM LivroAutor LA JOIN Autor A ON LA.codigoautor = A.codigo WHERE LA.codigolivro = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();

            List<Autor> lista = new ArrayList<>();

            while(rs.next()) {
                Autor a = new Autor();
                a.setCodigo(rs.getInt(1));
                a.setNome(rs.getString(2));
                lista.add(a);
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public void update(Livro livro) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE Livro SET titulo = ?, prioridade = ?, ano = ?, disponibilidade = ? WHERE codigo = ?";
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
            String sql = "SELECT * FROM Livro WHERE codigo = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                Livro l = new Livro();
                l.setCodigo(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setPrioridade(rs.getInt(3));
                l.setAno(rs.getInt(4));
                l.setDisponibilidade(rs.getBoolean(5));
                l.setCategoria(getCategorias(l.getCodigo()));
                l.setAutor(getAutor(l.getCodigo()));

                return l;
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    public Livro getLivro(String nome) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM Livro WHERE nome = ? AND deletado=FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "'" + nome + "'");
            ResultSet rs = ps.executeQuery();
            conn.close();

            if(rs.next()) {
                Livro l = new Livro();
                l.setCodigo(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setPrioridade(rs.getInt(3));
                l.setAno(rs.getInt(4));
                l.setDisponibilidade(rs.getBoolean(5));
                l.setCategoria(getCategorias(l.getCodigo()));
                l.setAutor(getAutor(l.getCodigo()));

                return l;
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    private int getLast() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();

        String sql = "SELECT MAX(codigo) from Livro";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        conn.close();

        if(rs.next()) {
            int id = rs.getInt(1);
            return id;
        }

        return 0;
    }
}
