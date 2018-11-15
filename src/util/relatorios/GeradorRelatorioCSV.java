package util.relatorios;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.LivroDAOProxy;
import dao.proxy.UsuarioDAOProxy;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class GeradorRelatorioCSV implements GeradorRelatorio {

    private static GeradorRelatorioCSV instance;

    private GeradorRelatorioCSV(){

    }

    public static synchronized GeradorRelatorioCSV getInstance() {
        if(instance == null)
            instance = new GeradorRelatorioCSV();
        return instance;
    }

    @Override
    public void gerarRelatorioLivros() {
        LivroDAO dao = LivroDAOProxy.getInstance();
        List<Livro> livros = dao.listLivro();

        try {
            PrintWriter pw = new PrintWriter(new File("relatorioLivros.csv"));
            StringBuilder sb = new StringBuilder();

            sb.append("Código,Título,Prioridade,Categoria(s),Ano,Disponibilidade,Autor(es)\n");

            for(Livro l : livros){
                sb.append("\"" + l.getCodigo() + "\",\"" + l.getTitulo() + "\",\"" + l.getPrioridadeString() + "\",\"" + l.getCategoria() + "\",\"" + l.getAno() + "\",\"" + l.getDisponibilidadeString() + "\",\"" + l.getAutor() + "\"\n");
            }

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
            PrintWriter pw = new PrintWriter(new File("relatorioUsuarios.csv"));
            StringBuilder sb = new StringBuilder();

            sb.append("Código,Nome,Sexo,Endereço,Telefone,Categoria\n");

            for(Usuario u : usuarios){
                sb.append("\"" + u.getCodigo() + "\",\"" + u.getNome() + "\",\"" + u.getSexoString() + "\",\"" + u.getEndereco() + "\",\"" + u.getTelefone() + "\",\"" + u.getCategoriaUsuario() + "\"\n");
            }

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
            PrintWriter pw = new PrintWriter(new File("relatorioEmprestimos.csv"));
            StringBuilder sb = new StringBuilder();

            sb.append("Código,Data Empréstimo,Data Devolução,Livro,Usuário\n");

            for(Emprestimo e : emprestimos){
                sb.append("\"" + e.getCodigo() + "\",\"" + e.getDataEmprestimo() + "\",\"" + e.getDataDevolucao() + "\",\"" + e.getLivro().getTitulo() + "\",\"" + e.getUsuario().getNome() + "\"\n");
            }

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
