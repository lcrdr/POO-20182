package view;

import controller.comando.Command;

import java.util.Hashtable;
import java.util.Scanner;

public class InterfaceUsuario {

        public static void menu() {
            System.out.println("Administração da Loja");
            System.out.println("0 - ");
            System.out.println("1 - ");
            System.out.println("2 - ");
            System.out.println("3 - ");
            System.out.println("4 - ");
            System.out.println("Escolha uma opção:");

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
