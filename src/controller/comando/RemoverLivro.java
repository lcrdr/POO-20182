package controller.comando;

import dao.LivroDAO;
import dao.proxy.LivroDAOProxy;
import model.Livro;

import java.util.Scanner;

public class RemoverLivro implements Command {

    @Override
    public void execute(Scanner entrada) {
        LivroDAO dao = LivroDAOProxy.getInstance();
        System.out.println("Entre com o código do livro a ser removido:");
        Livro livro = dao.getLivro(entrada.nextInt());
        entrada.nextLine();

        if(livro.getDisponibilidade()){
            dao.remove(livro.getCodigo());
            System.out.println("Livro removido com sucesso.");
        }else{
            System.out.println("O livro está emprestado e não pode ser devolvido no momento.");
        }

        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
