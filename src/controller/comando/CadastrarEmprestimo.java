package controller.comando;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.ReservaDAO;
import dao.UsuarioDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.LivroDAOProxy;
import dao.proxy.ReservaDAOProxy;
import dao.proxy.UsuarioDAOProxy;
import model.Emprestimo;
import model.Livro;
import model.Reserva;
import model.Usuario;

import java.time.LocalDate;
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


            ReservaDAO reservaDAO = ReservaDAOProxy.getInstance();

            Reserva reserva = new Reserva();
            int fila;

            do {

                reserva = reservaDAO.verificarFila(livro.getCodigo());
                fila = reserva.getDataReserva().compareTo(LocalDate.now());
                if (fila <= (-1))
                    reservaDAO.concluirReserva(reserva); //conclui reserva que estiver a mais de 1 dia aguardando o usuario.

            } while (fila == -1 || fila == 0);

            if ((usuario.getCodigo() == reserva.getUsuario().getCodigo() || reserva.getId() == 0) && livro.getDisponibilidade() == true) {

                Emprestimo emprestimo = new Emprestimo(usuario, livro);

                dao.insert(emprestimo);

                if (usuario.getCodigo() == reserva.getUsuario().getCodigo())
                    reservaDAO.concluirReserva(reserva); //conclui a reserva se existir alguma no nome do usuario.

                System.out.println("Emprestimo registrado com sucesso.");
            }else if (livro.getDisponibilidade() == false){
                System.out.println("Este livro já esta emprestado. Faça uma reserva.");
            } else {
                Usuario u = udao.getUsuario(reserva.getUsuario().getCodigo());
                System.out.println("Este livro ja esta reservado para o usuario: " + u.getNome());
            }
        }else{
            System.out.println("O usuário não pode realizar empréstimos pois tem multas a pagar.");
        }

        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
