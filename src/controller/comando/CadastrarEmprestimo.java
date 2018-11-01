package controller.comando;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.LivroDAOProxy;
import dao.proxy.UsuarioDAOProxy;
import model.Emprestimo;

import java.util.Scanner;

public class CadastrarEmprestimo implements Command {

    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO udao = UsuarioDAOProxy.getInstance();
        LivroDAO ldao = LivroDAOProxy.getInstance();
        EmprestimoDAO dao = EmprestimoDAOProxy.getInstance();

        Emprestimo emprestimo = new Emprestimo();

        System.out.println("Entre com o id do usuário:");
        emprestimo.setUsuario(udao.getUsuario(entrada.nextInt()));
        entrada.nextLine();

        System.out.println("Entre com o id do usuário:");
        emprestimo.setLivro(ldao.getLivro(entrada.nextInt()));
        entrada.nextLine();

        /*TODO alterar esquema de como funciona CategoriaUsuario para herança e assim usar Strategy (ou algum outro padrão)*/

        dao.insert(emprestimo);
    }
}
