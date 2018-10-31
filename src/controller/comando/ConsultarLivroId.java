package controller.comando;

import dao.LivroDAO;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class ConsultarLivroId implements Command {
    @Override
    public void execute(Scanner entrada) {
        System.out.println("Digite o id do livro: ");

        LivroDAO dao = new LivroDAO();
        int id = entrada.nextInt();
        List<Livro> livros = dao.listLivros(id);
        for(Livro l : livros) {
            System.out.println(l);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
        entrada.nextLine();
    }
}