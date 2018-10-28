package view;

import controller.comando.*;

import java.util.Hashtable;
import java.util.Scanner;

public class InterfaceBibliotecario {

    public static void menu() {
        System.out.println("0 - Sair\n");

        System.out.println("<-- Gerenciar Livros -->\n");
        System.out.println("1 - Cadastrar Livro\n");
        System.out.println("2 - Alterar Livro\n");
        System.out.println("3 - Excluir Livro\n");
        System.out.println("4 - Consultar Livro\n");
        System.out.println("\n");
        System.out.println("<-- Gerenciar Usuarios -->\n");
        System.out.println("5 - Cadastrar Usuario\n");
        System.out.println("6 - Alterar Usuario\n");
        System.out.println("7 - Excluir Usuario\n");
        System.out.println("8 - Consultar Usuario\n");
        System.out.println("\n");
        System.out.println("<-- Gerenciar Emprestimos -->\n");
        System.out.println("9 - Cadastrar Emprestimo\n");
        System.out.println("10 - Consultar Emprestimo\n");
        System.out.println("\n");
        System.out.println("<-- Gerenciar Multas -->\n");
        System.out.println("11 - Cadastrar Multa\n");
        System.out.println("\n");
        System.out.println("Escolha uma opção:");

    }

    public static void main(String[] args) {


        int opcao = 0;
        Scanner entrada = new Scanner(System.in);

        Hashtable<Integer, Command> comandos =
                new Hashtable<Integer, Command>();
        comandos.put(1, new CadastrarLivro());
        comandos.put(2, new AlterarLivro());
        comandos.put(3, new RemoverLivro());
        comandos.put(4, new ConsultarLivros());

        comandos.put(5, new CadastrarUsuario());
        comandos.put(6, new AlterarUsuario());
        comandos.put(7, new RemoverUsuario());
        comandos.put(8, new ConsultarUsuarios());

        comandos.put(9, new CadastrarEmprestimo());
        comandos.put(10, new ConsultarEmprestimos());

        //comandos.put(11, new CadastrarMulta());

        comandos.put(0, new Sair());

        do {
            menu();
            opcao = entrada.nextInt();
            comandos.get(opcao).execute(entrada);

        } while (opcao != 0);

        entrada.close();
    }
}
