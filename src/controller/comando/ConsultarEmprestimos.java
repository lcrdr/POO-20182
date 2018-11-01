package controller.comando;

import dao.EmprestimoDAO;
import dao.proxy.EmprestimoDAOProxy;
import model.Emprestimo;

import java.util.List;
import java.util.Scanner;

public class ConsultarEmprestimos implements Command {
    @Override
    public void execute(Scanner entrada) {
        EmprestimoDAO dao = EmprestimoDAOProxy.getInstance();
        List<Emprestimo> emprestimos = dao.listEmprestimo();
        for(Emprestimo e : emprestimos) {
            System.out.println(e);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
