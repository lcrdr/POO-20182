package controller.comando;

import dao.LivroDAO;

import java.util.Scanner;

public class RemoverLivro implements Command {

    @Override
    public void execute(Scanner entrada) {
        LivroDAO dao = new LivroDAO();
        System.out.println("Entre com o código do livro a ser removido:");
        dao.remove(entrada.nextInt());
    }
}
