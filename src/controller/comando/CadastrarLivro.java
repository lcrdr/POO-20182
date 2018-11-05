package controller.comando;

import dao.AutorDAO;
import dao.CategoriaLivroDAO;
import dao.LivroDAO;
import dao.proxy.AutorDAOProxy;
import dao.proxy.CategoriaLivroDAOProxy;
import dao.proxy.LivroDAOProxy;
import model.Autor;
import model.CategoriaLivro;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class CadastrarLivro implements Command {

    @Override
    public void execute(Scanner entrada) {
        LivroDAO dao = LivroDAOProxy.getInstance();
        Livro livro = new Livro();
        System.out.println("Entre com o título do livro:");
        livro.setTitulo(entrada.nextLine());
        System.out.println("Entre com a prioridade do livro:\n1 - baixa\n2 - média\n3 - alta):");
        livro.setPrioridade(entrada.nextInt());
        entrada.nextLine();
        System.out.println("Entre com o ano de lançamento do livro:");
        livro.setAno(entrada.nextInt());
        entrada.nextLine();

        CategoriaLivroDAO cdao = CategoriaLivroDAOProxy.getInstance();
        int id = 0;

        do {
            System.out.println("Digite o id da categoria do livro ou 0 para sair:");
            List<CategoriaLivro> categorias = cdao.listCategoria();
            for (CategoriaLivro c : categorias) {
                System.out.println(c);
            }
            id = entrada.nextInt();
            entrada.nextLine();
            if (id != 0){
                livro.addCategoria(cdao.getCategoria(id));
            }else if (livro.getCategoria().isEmpty()) {
                System.out.println("Você deve cadastrar ao menos uma categoria.");
                id = 1;
            }

        }while (id != 0);

        AutorDAO adao = AutorDAOProxy.getInstance();

        do {
            System.out.println("Entre com o id do autor do livro ou 0 para sair:");
            List<Autor> autores = adao.listAutor();
            for(Autor a : autores){
                System.out.println(a);
            }
            id = entrada.nextInt();
            entrada.nextLine();
            if(id != 0) {
                livro.addAutor(adao.getAutor(id));
            }else if (livro.getAutor().isEmpty()) {
                System.out.println("Você deve cadastrar ao menos um autor.");
                id = 1;
            }
        }while (id != 0);

        dao.insert(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }
}
