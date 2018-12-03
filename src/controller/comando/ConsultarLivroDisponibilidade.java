package controller.comando;

import dao.LivroDAO;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class ConsultarLivroDisponibilidade implements Command {
    @Override
    public void execute(Scanner entrada) {
        System.out.println("Digite (1) para disponiveis e (2) para não disponiveis:  ");

        LivroDAO dao = new LivroDAO();
        boolean disponibilidade = (entrada.nextInt() == 1);
        entrada.nextLine();
        List<Livro> livros = dao.listLivro(disponibilidade);
        for(Livro l : livros) {
            System.out.println(l);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}