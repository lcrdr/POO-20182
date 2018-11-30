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
                sb.append("\n\t<Livro>\n\t\t<Código>" + l.getCodigo() + "</Código>\n\t\t<Título>" + l.getTitulo() + "</Título>\n\t\t<Prioridade>" + l.getPrioridadeString() + "</Prioridade>\n\t\t<Ano>" + l.getAno() + "</Ano>\n\t\t<Disponibilidade>" + l.getDisponibilidadeString() + "</Disponibilidade>\n\t\t<Categorias>");
                for(CategoriaLivro c : l.getCategoria()){
                    sb.append("\n\t\t\t<Categoria>\n\t\t\t\t<Código>" + c.getCodigo() + "</Código>\n\t\t\t\t<Nome>" + c.getNome() + "</Nome>\n\t\t\t</Categoria>");
                }
                sb.append("\n\t\t</Categorias>\n\t\t<Autores>");
                for(Autor a : l.getAutor()){
                    sb.append("\n\t\t\t<Autor>\n\t\t\t\t<Código>" + a.getCodigo() + "</Código>\n\t\t\t\t<Nome>" + a.getNome() + "</Nome>\n\t\t\t</Autor>");
                }
                sb.append("\n\t\t</Autores>\n\t</Livro>");
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
                sb.append("\n\t<Usuário>\n\t\t<Código>" + u.getCodigo() + "</Código>\n\t\t<Nome>" + u.getNome() + "</Nome>\n\t\t<Sexo>" + u.getSexoString() + "</Sexo>\n\t\t<Endereço>" + u.getEndereco() + "</Endereço>\n\t\t<Telefone>" + u.getTelefone() + "</Telefone>\n\t\t<Categoria>" + u.getCategoriaUsuario() + "</Categoria>\n\t</Usuário>");
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
                sb.append("\n\t<Empréstimo>\n\t\t<Código>" + e.getCodigo() + "</Código>\n\t\t<Data_Empréstimo>" + e.getDataEmprestimo() + "</Data_Empréstimo>\n\t\t<Data_Devolução>" + e.getDataDevolucao() + "</Data_Devolução>\n\t\t<Situação>" + e.getDevolvidoString() + "</Situação>\n\t\t<Livro>\n\t\t\t<Código>" + e.getLivro().getCodigo() + "</Código>\n\t\t\t<Título>" + e.getLivro().getTitulo() + "</Título>\n\t\t\t<Prioridade>" + e.getLivro().getPrioridadeString() + "</Prioridade>\n\t\t\t<Ano>" + e.getLivro().getAno() + "</Ano>\n\t\t\t<Disponibilidade>" + e.getLivro().getDisponibilidadeString() + "</Disponibilidade>\n\t\t\t<Categorias>");
                for(CategoriaLivro c : e.getLivro().getCategoria()){
                    sb.append("\n\t\t\t\t<Categoria>\n\t\t\t\t\t<Código>" + c.getCodigo() + "</Código>\n\t\t\t\t\t<Nome>" + c.getNome() + "</Nome>\n\t\t\t\t</Categoria>");
                }
                sb.append("\n\t\t\t</Categorias>\n\t\t\t<Autores>");
                for(Autor a : e.getLivro().getAutor()){
                    sb.append("\n\t\t\t\t<Autor>\n\t\t\t\t\t<Código>" + a.getCodigo() + "</Código>\n\t\t\t\t\t<Nome>" + a.getNome() + "</Nome>\n\t\t\t\t</Autor>");
                }
                sb.append("\n\t\t\t</Autores>\n\t\t</Livro>\n\t\t<Usuário>\n\t\t\t<Código>" + e.getUsuario().getCodigo() + "</Código>\n\t\t\t<Nome>" + e.getUsuario().getNome() + "</Nome>\n\t\t\t<Sexo>" + e.getUsuario().getSexoString() + "</Sexo>\n\t\t\t<Endereço>" + e.getUsuario().getEndereco() + "</Endereço>\n\t\t\t<Telefone>" + e.getUsuario().getTelefone() + "</Telefone>\n\t\t\t<Categoria>" + e.getUsuario().getCategoriaUsuario() + "</Categoria>\n\t\t</Usuário>\n\t</Empréstimo>");
            }

            sb.append("\n</Empréstimos>");

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
