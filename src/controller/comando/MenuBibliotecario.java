package controller.comando;

import java.util.Hashtable;
import java.util.Scanner;

public class MenuBibliotecario implements Command{
    @Override
    public void execute(Scanner entrada) {
        int opcao = 0;

        Hashtable<Integer, Command> comandos = new Hashtable<Integer, Command>();
        comandos.put(0, new Sair());

        comandos.put(1, new CadastrarLivro());
        comandos.put(2, new AlterarLivro());
        comandos.put(3, new RemoverLivro());
        comandos.put(4, new ConsultarLivros());
        comandos.put(5, new CadastrarAutor());
        comandos.put(6, new CadastrarCategoriaLivro());

        comandos.put(7, new CadastrarUsuario());
        comandos.put(8, new AlterarUsuario());
        comandos.put(9, new RemoverUsuario());
        comandos.put(10, new ConsultarUsuarios());

        comandos.put(11, new CadastrarEmprestimo());
        comandos.put(12, new ConsultarEmprestimos());

        comandos.put(13, new DevolverEmprestimo());
        comandos.put(14, new PagarMulta());

        comandos.put(15, new GerarRelatorio());

        do {

            System.out.println("0 - Sair\n");

            System.out.println("<-- Gerenciar Livros -->");
            System.out.println("1 - Cadastrar Livro"); //ok
            System.out.println("2 - Alterar Livro"); //ok
            System.out.println("3 - Excluir Livro"); //ok
            System.out.println("4 - Consultar Livros"); //ok
            System.out.println("5 - Cadastrar Autor"); //ok
            System.out.println("6 - Cadastrar Categoria de Livro"); //ok
            System.out.println("\n<-- Gerenciar Usuários -->");
            System.out.println("7 - Cadastrar Usuário");
            System.out.println("8 - Alterar Usuário");
            System.out.println("9 - Excluir Usuário");
            System.out.println("10 - Consultar Usuários");
            System.out.println("\n<-- Gerenciar Empréstimos -->");
            System.out.println("11 - Cadastrar Empréstimo");
            System.out.println("12 - Consultar Empréstimo");
            System.out.println("13 - Devolver Empréstimo");
            System.out.println("14 - Pagar Multa");
            System.out.println("\n<-- Relatórios -->");
            System.out.println("15 - Gerar relatórios");
            System.out.println("\nEscolha uma opção:");

            opcao = entrada.nextInt();
            entrada.nextLine();
            comandos.get(opcao).execute(entrada);

        } while (opcao != 0);

        entrada.close();
    }
}
