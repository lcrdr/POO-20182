package util.relatorios;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.LivroDAOProxy;
import dao.proxy.UsuarioDAOProxy;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class GeradorRelatorioXML implements GeradorRelatorio {

    private static GeradorRelatorioXML instance;

    private GeradorRelatorioXML(){}

    public static synchronized GeradorRelatorioXML getInstance() {
        if(instance == null)
            instance = new GeradorRelatorioXML();
        return instance;
    }

    @Override
    public void gerarRelatorioLivros() {
        LivroDAO dao = LivroDAOProxy.getInstance();
        List<Livro> livros = dao.listLivro();

        try {
            PrintWriter pw = new PrintWriter(new File("relatorioLivros.xml"));
            StringBuilder sb = new StringBuilder();

            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Livros>");

            for(Livro l : livros){
                sb.append("\n<Livro>\n<Código>" + l.getCodigo() + "</Código>\n<Título>" + l.getTitulo() + "</Título>\n<Prioridade>" + l.getPrioridadeString() + "</Prioridade>\n<Ano>" + l.getAno() + "</Ano>\n<Disponibilidade>" + l.getDisponibilidadeString() + "</Disponibilidade>\n<Categorias>");
                for(CategoriaLivro c : l.getCategoria()){
                    sb.append("\n<Categoria>\n<Código>" + c.getCodigo() + "</Código>\n<Nome>" + c.getNome() + "</Nome>\n</Categoria>");
                }
                sb.append("</Categorias>\n<Autores>");
                for(Autor a : l.getAutor()){
                    sb.append("\n<Autor>\n<Código>" + a.getCodigo() + "</Código>\n<Nome>" + a.getNome() + "</Nome>\n</Autor>");
                }
                sb.append("</Autores>\n</Livro>");
            }

            sb.append("\n</Livros>");

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gerarRelatorioUsuarios() {
        UsuarioDAO dao = UsuarioDAOProxy.getInstance();
        List<Usuario> usuarios = dao.listUsuario();

        try {
            PrintWriter pw = new PrintWriter(new File("relatorioUsuarios.xml"));
            StringBuilder sb = new StringBuilder();

            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Usuários>");

            for(Usuario u : usuarios){
                sb.append("\n<Usuário>\n<Código>" + u.getCodigo() + "</Código>\n<Nome>" + u.getNome() + "</Nome>\n<Sexo>" + u.getSexoString() + "</Sexo>\n<Endereço>" + u.getEndereco() + "</Endereço>\n<Telefone>" + u.getTelefone() + "</Telefone>\n<Categoria>" + u.getCategoriaUsuario() + "</Categoria>\n</Usuário>");
            }

            sb.append("\n</Usuários>");

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gerarRelatorioEmprestimos() {
        EmprestimoDAO dao = EmprestimoDAOProxy.getInstance();
        List<Emprestimo> emprestimos = dao.listEmprestimo();

        try {
            PrintWriter pw = new PrintWriter(new File("relatorioEmprestimos.xml"));
            StringBuilder sb = new StringBuilder();

            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Empréstimos>");

            for(Emprestimo e : emprestimos){
                sb.append("\n<Empréstimo>\n<Código>" + e.getCodigo() + "</Código>\n<Data_Empréstimo>" + e.getDataEmprestimo() + "</Data_Empréstimo>\n<Data_Devolução>" + e.getDataDevolucao() + "</Data_Devolução>\n<Situação>" + e.getDevolvidoString() + "</Situação>\n<Livro>\n<Código>" + e.getLivro().getCodigo() + "</Código>\n<Título>" + e.getLivro().getTitulo() + "</Título>\n<Prioridade>" + e.getLivro().getPrioridadeString() + "</Prioridade>\n<Ano>" + e.getLivro().getAno() + "</Ano>\n<Disponibilidade>" + e.getLivro().getDisponibilidadeString() + "</Disponibilidade>\n<Categorias>");
                for(CategoriaLivro c : e.getLivro().getCategoria()){
                    sb.append("\n<Categoria>\n<Código>" + c.getCodigo() + "</Código>\n<Nome>" + c.getNome() + "</Nome>\n</Categoria>");
                }
                sb.append("</Categorias>\n<Autores>");
                for(Autor a : e.getLivro().getAutor()){
                    sb.append("\n<Autor>\n<Código>" + a.getCodigo() + "</Código>\n<Nome>" + a.getNome() + "</Nome>\n</Autor>");
                }
                sb.append("</Autores>\n</Livro>\n<Usuário>\n<Código>" + e.getUsuario().getCodigo() + "</Código>\n<Nome>" + e.getUsuario().getNome() + "</Nome>\n<Sexo>" + e.getUsuario().getSexoString() + "</Sexo>\n<Endereço>" + e.getUsuario().getEndereco() + "</Endereço>\n<Telefone>" + e.getUsuario().getTelefone() + "</Telefone>\n<Categoria>" + e.getUsuario().getCategoriaUsuario() + "</Categoria>\n</Usuário>\n</Empréstimo>");
            }

            sb.append("\n</Empréstimos>");

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
