package controller.comando;

import java.util.Hashtable;
import java.util.Scanner;

public class MenuUsuario implements Command {
    @Override
    public void execute(Scanner entrada) {
        int opcao = 0;

        Hashtable<Integer, Command> comandos =
                new Hashtable<Integer, Command>();
        comandos.put(1, new ConsultarLivros());
        comandos.put(2, new ReservarLivro());
        comandos.put(0, new Sair());

        do {
            System.out.println("<--- Biblioteca --->");
            System.out.println("0 - Sair");
            System.out.println("1 - Consultar Livro");
            System.out.println("2 - Reservar Livro");
            System.out.println("Escolha uma opção:");
            opcao = entrada.nextInt();
            entrada.nextLine();
            comandos.get(opcao).execute(entrada);

        } while (opcao != 0);

        entrada.close();
    }
}
