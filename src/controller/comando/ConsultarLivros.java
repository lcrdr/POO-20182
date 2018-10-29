package controller.comando;

import dao.LivroDAO;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class ConsultarLivros implements Command {

    @Override
    public void execute(Scanner entrada) {
        LivroDAO dao = new LivroDAO();
        List<Livro> livros = dao.listLivros();
        for(Livro l : livros) {
            System.out.println(l);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
