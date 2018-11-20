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

            sb.append("{\n\"Livros\" : [\n");

            for(Livro l : livros){
                sb.append("{\n\"Código\": " + l.getCodigo() + ",\n\"Título\": \"" + l.getTitulo() + "\",\n\"Prioridade\": \"" + l.getPrioridadeString() + "\",\n\"Ano\": " + l.getAno() + ",\n\"Disponibilidade\": \"" + l.getDisponibilidadeString() + "\",\n\"Categoria(s)\": [\n");
                for(CategoriaLivro c : l.getCategoria()){
                    sb.append("{\n\"Código\": " + c.getCodigo() + ",\n\"Nome\": \"" + c.getNome() + "\"\n},\n");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("],\n\"Autor(es)\": [\n");
                for(Autor a : l.getAutor()){
                    sb.append("{\n\"Código\": " + a.getCodigo() + ",\n\"Nome\": \"" + a.getNome() + "\"\n},\n");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("]\n},");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]\n}");

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

            sb.append("{\n\"Usuários\" : [\n");

            for(Usuario u : usuarios){
                sb.append("{\n\"Código\": " + u.getCodigo() + ",\n\"Nome\": \"" + u.getNome() + "\",\n\"Sexo\": \"" + u.getSexoString() + "\",\n\"Endereço\": \"" + u.getEndereco() + "\",\n\"Telefone\": \"" + u.getTelefone() + "\",\n\"Categoria\": \"" + u.getCategoriaUsuario() + "\"\n},\n");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("]\n}");

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

            sb.append("{\n\"Empréstimos\" : [\n");

            for(Emprestimo e : emprestimos){
                sb.append("{\n\"Código\": " + e.getCodigo() + ",\n\"Data Empréstimo\": \"" + e.getDataEmprestimo() + "\",\n\"Data Devolução\": \"" + e.getDataDevolucao() + "\",\n\"Situação\": \"" + e.getDevolvidoString() + "\",\n\"Livro\": [\n{\n\"Código\": " + e.getLivro().getCodigo() + ",\n\"Título\": \"" + e.getLivro().getTitulo() + "\",\n\"Prioridade\": \"" + e.getLivro().getPrioridadeString() + "\",\n\"Ano\": " + e.getLivro().getAno() + ",\n\"Disponibilidade\": \"" + e.getLivro().getDisponibilidadeString() + "\",\n\"Categoria(s)\": [\n");
                for(CategoriaLivro c : e.getLivro().getCategoria()){
                    sb.append("{\n\"Código\": " + c.getCodigo() + ",\n\"Nome\": \"" + c.getNome() + "\"\n},\n");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("],\n\"Autor(es)\": [\n");
                for(Autor a : e.getLivro().getAutor()){
                    sb.append("{\n\"Código\": " + a.getCodigo() + ",\n\"Nome\": \"" + a.getNome() + "\"\n},\n");
                }
                sb.deleteCharAt(sb.length() - 2);
                sb.append("]\n}\n],\n\"Usuário\": [\n{\n\"Código\": " + e.getUsuario().getCodigo() + ",\n\"Nome\": \"" + e.getUsuario().getNome() + "\",\n\"Sexo\": \"" + e.getUsuario().getSexoString() + "\",\n\"Endereço\": \"" + e.getUsuario().getEndereco() + "\",\n\"Telefone\": \"" + e.getUsuario().getTelefone() + "\",\n\"Categoria\": \"" + e.getUsuario().getCategoriaUsuario() + "\"\n}\n]\n},\n");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("]\n}");

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
