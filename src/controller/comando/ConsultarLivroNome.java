package controller.comando;

import dao.LivroDAO;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class ConsultarLivroNome implements Command {
    @Override
    public void execute(Scanner entrada) {
        System.out.println("Digite o nome do livro: ");

        LivroDAO dao = new LivroDAO();
        entrada.nextLine();
        String nome = entrada.nextLine();

        List<Livro> livros = dao.listLivros(nome);
        for(Livro l : livros) {
            System.out.println(l);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
