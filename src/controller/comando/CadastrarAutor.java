package controller.comando;

import dao.AutorDAO;
import dao.proxy.AutorDAOProxy;
import model.Autor;

import java.util.Scanner;

public class CadastrarAutor implements Command {

    @Override
    public void execute(Scanner entrada) {
        AutorDAO dao = AutorDAOProxy.getInstance();
        Autor autor = new Autor();
        System.out.println("Entre com o nome do autor:");
        autor.setNome(entrada.nextLine());
        dao.insert(autor);
        System.out.println("Auto cadastrado com sucesso.");

        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }

}
