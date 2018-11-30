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

public final class GeradorRelatorioJSON implements GeradorRelatorio {

    private static GeradorRelatorioJSON instance;

    private GeradorRelatorioJSON(){

    }

    public static synchronized GeradorRelatorioJSON getInstance() {
        if(instance == null)
            instance = new GeradorRelatorioJSON();
        return instance;
    }

    @Override
    public void gerarRelatorioLivros() {
        LivroDAO dao = LivroDAOProxy.getInstance();
        List<Livro> livros = dao.listLivro();

        try {
            PrintWriter pw = new PrintWriter(new File("relatorioLivros.json"));
            StringBuilder sb = new StringBuilder();

            sb.append("{\n\t\"Livros\" : [\n");

            for(Livro l : livros){
                sb.append("\t\t{\n\t\t\t\"Código\": " + l.getCodigo() + ",\n\t\t\t\"Título\": \"" + l.getTitulo() + "\",\n\t\t\t\"Prioridade\": \"" + l.getPrioridadeString() + "\",\n\t\t\t\"Ano\": " + l.getAno() + ",\n\t\t\t\"Disponibilidade\": \"" + l.getDisponibilidadeString() + "\",\n\t\t\t\"Categoria(s)\": [\n");
                for(CategoriaLivro c : l.getCategoria()){
                    sb.append("\t\t\t\t{\n\t\t\t\t\t\"Código\": " + c.getCodigo() + ",\n\t\t\t\t\t\"Nome\": \"" + c.getNome() + "\"\n\t\t\t\t},\n");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("\t\t\t],\n\t\t\t\"Autor(es)\": [\n");
                for(Autor a : l.getAutor()){
                    sb.append("\t\t\t\t{\n\t\t\t\t\t\"Código\": " + a.getCodigo() + ",\n\t\t\t\t\t\"Nome\": \"" + a.getNome() + "\"\n\t\t\t\t},\n");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("\t\t\t]\n\t\t},\n");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("\t]\n}");

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
            PrintWriter pw = new PrintWriter(new File("relatorioUsuarios.json"));
            StringBuilder sb = new StringBuilder();

            sb.append("{\n\t\"Usuários\" : [\n");

            for(Usuario u : usuarios){
                sb.append("\t\t{\n\t\t\t\"Código\": " + u.getCodigo() + ",\n\t\t\t\"Nome\": \"" + u.getNome() + "\",\n\t\t\t\"Sexo\": \"" + u.getSexoString() + "\",\n\t\t\t\"Endereço\": \"" + u.getEndereco() + "\",\n\t\t\t\"Telefone\": \"" + u.getTelefone() + "\",\n\t\t\t\"Categoria\": \"" + u.getCategoriaUsuario() + "\"\n\t\t},\n");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("\t]\n}");

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
            PrintWriter pw = new PrintWriter(new File("relatorioEmprestimos.json"));
            StringBuilder sb = new StringBuilder();

            sb.append("{\n\t\"Empréstimos\" : [\n");

            for(Emprestimo e : emprestimos){
                sb.append("\t\t{\n\t\t\t\"Código\": " + e.getCodigo() + ",\n\t\t\t\"Data Empréstimo\": \"" + e.getDataEmprestimo() + "\",\n\t\t\t\"Data Devolução\": \"" + e.getDataDevolucao() + "\",\n\t\t\t\"Situação\": \"" + e.getDevolvidoString() + "\",\n\t\t\t\"Livro\": [\n\t\t\t\t{\n\t\t\t\t\t\"Código\": " + e.getLivro().getCodigo() + ",\n\t\t\t\t\t\"Título\": \"" + e.getLivro().getTitulo() + "\",\n\t\t\t\t\t\"Prioridade\": \"" + e.getLivro().getPrioridadeString() + "\",\n\t\t\t\t\t\"Ano\": " + e.getLivro().getAno() + ",\n\t\t\t\t\t\"Disponibilidade\": \"" + e.getLivro().getDisponibilidadeString() + "\",\n\t\t\t\t\t\"Categoria(s)\": [\n");
                for(CategoriaLivro c : e.getLivro().getCategoria()){
                    sb.append("\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\"Código\": " + c.getCodigo() + ",\n\t\t\t\t\t\t\t\"Nome\": \"" + c.getNome() + "\"\n\t\t\t\t\t\t},\n");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("\t\t\t\t\t],\n\t\t\t\t\t\"Autor(es)\": [\n");
                for(Autor a : e.getLivro().getAutor()){
                    sb.append("\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\"Código\": " + a.getCodigo() + ",\n\t\t\t\t\t\t\t\"Nome\": \"" + a.getNome() + "\"\n\t\t\t\t\t\t},\n");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("\t\t\t\t\t]\n\t\t\t\t}\n\t\t\t],\n\t\t\t\"Usuário\": [\n\t\t\t\t{\n\t\t\t\t\t\"Código\": " + e.getUsuario().getCodigo() + ",\n\t\t\t\t\t\"Nome\": \"" + e.getUsuario().getNome() + "\",\n\t\t\t\t\t\"Sexo\": \"" + e.getUsuario().getSexoString() + "\",\n\t\t\t\t\t\"Endereço\": \"" + e.getUsuario().getEndereco() + "\",\n\t\t\t\t\t\"Telefone\": \"" + e.getUsuario().getTelefone() + "\",\n\t\t\t\t\t\"Categoria\": \"" + e.getUsuario().getCategoriaUsuario() + "\"\n\t\t\t\t}\n\t\t\t]\n\t\t},\n");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("\t]\n}");

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
