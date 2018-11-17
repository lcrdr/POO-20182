package controller.comando;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.ReservaDAO;
import dao.proxy.EmprestimoDAOProxy;
import dao.proxy.LivroDAOProxy;
import dao.proxy.ReservaDAOProxy;
import model.Emprestimo;
import model.Livro;
import model.Reserva;
import util.Sessao;

import java.util.Scanner;

public class ReservarLivro implements Command {
    @Override
    public void execute(Scanner entrada) {
        if(!Sessao.getUsuarioLogado().getMultas().isEmpty()) {

            System.out.println("Você não pode reservar um livro pois existem multas pendentes em seu nome.");

        } else {

            System.out.println("Digite o id do livro que deseja reservar: ");

            LivroDAO livroDAO = LivroDAOProxy.getInstance();
            Livro livro = livroDAO.getLivro(entrada.nextInt());
            entrada.nextLine();

            if (!livro.getDisponibilidade()) {

                EmprestimoDAO eDAO = EmprestimoDAOProxy.getInstance();
                Emprestimo emprestimo = eDAO.getEmprestimoByLivro(livro.getCodigo());


                Reserva reserva = new Reserva(livro, Sessao.getUsuarioLogado(), emprestimo.getDataDevolucao());
                ReservaDAO reservaDAO = ReservaDAOProxy.getInstance();
                reservaDAO.insert(reserva);

                System.out.println("Livro reservado com sucesso!");
                System.out.println("Fila de reservas para este livro: ");
                reservaDAO.listReserva();

            } else {
                System.out.println("Este livro está disponível para empréstimo.");
            }
        }
    }
}
