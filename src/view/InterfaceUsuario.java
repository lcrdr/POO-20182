package view;

import controller.comando.Command;
import controller.comando.ConsultarLivros;
import controller.comando.Sair;

import java.util.Hashtable;
import java.util.Scanner;

public class InterfaceUsuario {

        public static void menu() {
            System.out.println("Administração da Loja");
            System.out.println("0 - Sair");
            System.out.println("1 - Consultar Livro");
            System.out.println("2 - Reservar Livro");
            System.out.println("Escolha uma opção:");

        }

        public static void main(String[] args) {


            int opcao = 0;
            Scanner entrada = new Scanner(System.in);

            Hashtable<Integer, Command> comandos =
                    new Hashtable<Integer, Command>();
        comandos.put(1, new ConsultarLivros());
        //comandos.put(2, new ReservarLivro());
        comandos.put(0, new Sair());

            do {
                menu();
                opcao = entrada.nextInt();
                comandos.get(opcao).execute(entrada);

            } while (opcao != 0);

            entrada.close();
    }
}
