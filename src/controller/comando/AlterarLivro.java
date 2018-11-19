package controller.comando;

import dao.LivroDAO;
import dao.proxy.LivroDAOProxy;
import model.Livro;

import java.util.Scanner;

public class AlterarLivro implements Command {
    @Override
    public void execute(Scanner entrada) {
        LivroDAO dao = LivroDAOProxy.getInstance();
        System.out.println("Entre com o id do livro a ser editado:");
        Livro livro = dao.getLivro(entrada.nextInt());
        entrada.nextLine();
        System.out.println("Entre com o título do livro:");
        livro.setTitulo(entrada.nextLine());
        System.out.println("Entre com a prioridade do livro:\n1 - baixa\n2 - média\n3 - alta):");
        livro.setPrioridade(entrada.nextInt());
        entrada.nextLine();
        System.out.println("Entre com o ano de lançamento do livro:");
        livro.setAno(entrada.nextInt());
        entrada.nextLine();
        dao.update(livro);
        System.out.println("Livro atualizado com sucesso.");

        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
