package controller.comando;

import dao.EmprestimoDAO;
import model.Emprestimo;

import java.util.List;
import java.util.Scanner;

public class ConsultarEmprestimos implements Command {
    @Override
    public void execute(Scanner entrada) {
        EmprestimoDAO dao = new EmprestimoDAO();
        List<Emprestimo> emprestimos = dao.listEmprestimos();
        for(Emprestimo e : emprestimos) {
            System.out.println(e);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
