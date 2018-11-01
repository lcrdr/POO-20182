package controller.comando;

import dao.CategoriaLivroDAO;
import dao.proxy.CategoriaLivroDAOProxy;
import model.CategoriaLivro;

import java.util.Scanner;

public class CadastrarCategoriaLivro implements Command {
    @Override
    public void execute(Scanner entrada) {
        CategoriaLivroDAO dao = CategoriaLivroDAOProxy.getInstance();
        CategoriaLivro categoriaLivro = new CategoriaLivro();
        System.out.println("Entre com o nome da categoria:");
        categoriaLivro.setNome(entrada.nextLine());
        dao.insert(categoriaLivro);
    }
}
