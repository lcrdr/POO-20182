package controller.comando;

import java.util.Hashtable;
import java.util.Scanner;

public class ConsultarLivros implements Command {

    @Override
    public void execute(Scanner entrada) {

        Hashtable<Integer, Command> comandos = new Hashtable<Integer, Command>();
        comandos.put(1, new ConsultarLivroTitulo());
        comandos.put(2, new ConsultarLivroAutor());
        comandos.put(3, new ConsultarLivroCategoria());
        comandos.put(4, new ConsultarLivroDisponibilidade());
        comandos.put(5, new ConsultarTodosLivros());

        System.out.println("Você deseja consultar por:");
        System.out.println("1 - Título");
        System.out.println("2 - Autor");
        System.out.println("3 - Categoria");
        System.out.println("4 - Disponibilidade");
        System.out.println("5 - Mostrar todos");
        int comando = entrada.nextInt();
        entrada.nextLine();
        comandos.get(comando).execute(entrada);
    }
}