package controller.comando;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.util.Scanner;

public class CadastrarEmprestimo implements Command {

    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO udao = new UsuarioDAO();
        LivroDAO ldao = new LivroDAO();
        EmprestimoDAO dao = new EmprestimoDAO();

        Emprestimo emprestimo = new Emprestimo();
        Usuario usuario = new Usuario();
        Livro livro = new Livro();

        System.out.println("Entre com o id do usuário:");
        usuario = udao.getUsuario(entrada.nextInt());
        entrada.nextLine();

        System.out.println("Entre com o id do usuário:");
        livro = ldao.getLivro(entrada.nextInt());
        entrada.nextLine();

        /*TODO alterar esquema de como funciona CategoriaUsuario para herança e assim usar Strategy (ou algum outro padrão)*/

        dao.insert(emprestimo);
    }
}
