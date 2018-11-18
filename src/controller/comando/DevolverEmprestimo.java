package controller.comando;

import dao.EmprestimoDAO;
import dao.UsuarioDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.UsuarioDAOProxy;
import model.Emprestimo;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class DevolverEmprestimo implements Command {
    @Override
    public void execute(Scanner entrada) {
        EmprestimoDAO edao = EmprestimoDAOProxy.getInstance();
        UsuarioDAO udao = UsuarioDAOProxy.getInstance();

        System.out.println("Entre com o id do usuário:");
        Usuario usuario = udao.getUsuario(entrada.nextInt());
        entrada.nextLine();

        List<Emprestimo> emprestimos = edao.listEmprestimo(usuario);

        if (!emprestimos.isEmpty()) {
            for (Emprestimo e : emprestimos) {
                System.out.println(e);
            }

            System.out.println("Entre com o código do empréstimo a ser devolvido:");
            Emprestimo emprestimo = edao.getEmprestimo(entrada.nextInt());
            entrada.nextLine();

            emprestimo.devolver();
        } else {
            System.out.println("Não existem emprestimos para este usuario");
            entrada.nextLine();
        }


    }
}
