package controller.comando;

import dao.AutorDAO;
import dao.CategoriaLivroDAO;
import dao.LivroDAO;
import model.Autor;
import model.CategoriaLivro;
import model.Livro;

import java.util.Scanner;

public class CadastrarLivro implements Command {

    @Override
    public void execute(Scanner entrada) {
        LivroDAO dao = new LivroDAO();
        Livro livro = new Livro();
        System.out.println("Entre com o título do livro:");
        livro.setTitulo(entrada.next());
        System.out.println("Entre com a prioridade do livro:\n1 - baixa\n2 - média\n3 - alta):");
        livro.setPrioridade(entrada.nextInt());
        entrada.nextLine();
        System.out.println("Entre com o ano de lançamento do livro:");
        livro.setAno(entrada.nextInt());
        entrada.nextLine();

        CategoriaLivroDAO cdao = new CategoriaLivroDAO();
        int id = 0;

        do {
            System.out.println("Digite o id da categoria do livro ou 0 para sair:");
            id = entrada.nextInt();
            if(id != 0)
                livro.addCategoria(cdao.getCategoria(id));
        }while (id != 0);

        AutorDAO adao = new AutorDAO();

        do {
            System.out.println("Entre com o id do autor do livro ou 0 para sair:");
            id = entrada.nextInt();

            if(id != 0) {
                Autor a = adao.getAutor(id);
                System.out.println(a.getCodigo() + a.getNome());
                livro.addAutor(a);}
        }while (id != 0);

        dao.insert(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }
}
