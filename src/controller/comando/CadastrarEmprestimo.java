package controller.comando;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.LivroDAOProxy;
import dao.proxy.UsuarioDAOProxy;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.util.Scanner;

public class CadastrarEmprestimo implements Command {

    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO udao = UsuarioDAOProxy.getInstance();
        LivroDAO ldao = LivroDAOProxy.getInstance();
        EmprestimoDAO dao = EmprestimoDAOProxy.getInstance();

        System.out.println("Entre com o id do usuário:");
        Usuario usuario = udao.getUsuario(entrada.nextInt());
        entrada.nextLine();

        if(usuario.getMultas().isEmpty()){
            System.out.println("Entre com o id do livro:");
            Livro livro = ldao.getLivro(entrada.nextInt());
            entrada.nextLine();

            if(livro.getDisponibilidade()){
                Emprestimo emprestimo = new Emprestimo(usuario, livro);

                dao.insert(emprestimo);

                livro.setDisponibilidade(false);
                ldao.update(livro);

                System.out.println("Emprestimo registrado com sucesso.");
            }else {
                System.out.println("O livro já está emprestado! Realizar reserva?");
                //TODO reservar (verificar se o usuário já não tem o livro emprestado/reservado
            }
        }else{
            System.out.println("O usuário não pode realizar empréstimos pois tem multas a pagar.");
        }

        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}