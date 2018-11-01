package controller.comando;

import dao.LivroDAO;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class ConsultarLivroAutor implements Command {
    @Override
    public void execute(Scanner entrada) {
        System.out.println("Digite o id do autor do livro: ");

        LivroDAO dao = new LivroDAO();
        int id = entrada.nextInt();
        entrada.nextLine();
        List<Livro> livros = dao.listLivro(id);
        for(Livro l : livros) {
            System.out.println(l);
            System.out.println("\n");
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}