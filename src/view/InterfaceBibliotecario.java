package view;

import controller.comando.Command;

import java.util.Hashtable;
import java.util.Scanner;

public class InterfaceBibliotecario {

    public static void menu() {
        System.out.println("Administra��o da Loja");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Alterar Livro");
        System.out.println("3 - Excluir Livro");
        System.out.println("4 - Listar Livros");
        System.out.println("Escolha uma op��o:");

    }

    public static void main(String[] args) {


        int opcao = 0;
        Scanner entrada = new Scanner(System.in);

        Hashtable<Integer, Command> comandos =
                new Hashtable<Integer, Command>();
        /*comandos.put(1, );
        comandos.put(4, );
        comandos.put(0, );*/

        do {
            menu();
            opcao = entrada.nextInt();
            comandos.get(opcao).execute(entrada);

        } while (opcao != 0);

        entrada.close();
    }
}
