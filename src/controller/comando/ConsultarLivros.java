package controller.comando;

import dao.LivroDAO;
import model.Livro;

import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class ConsultarLivros implements Command {

    @Override
    public void execute(Scanner entrada) {

        Hashtable<Integer, Command> comandos = new Hashtable<Integer, Command>();
        comandos.put(1, new ConsultarLivroNome());
        comandos.put(2, new ConsultarLivroId());

            System.out.println("Você deseja consultar por nome (1) ou por id (2)? ");
            comandos.get(entrada.nextInt()).execute(entrada);
    }
}
