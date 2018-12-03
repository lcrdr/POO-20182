package controller.comando;

import dao.LivroDAO;
import dao.proxy.LivroDAOProxy;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class ConsultarTodosLivros implements Command {
    @Override
    public void execute(Scanner entrada) {
        LivroDAO dao = LivroDAOProxy.getInstance();
        List<Livro> livros = dao.listLivro();
        for(Livro l : livros) {
            System.out.println(l);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
