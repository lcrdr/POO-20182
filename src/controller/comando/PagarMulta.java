package controller.comando;

import dao.MultaDAO;
import dao.UsuarioDAO;
import dao.proxy.MultaDAOProxy;
import dao.proxy.UsuarioDAOProxy;
import model.Multa;
import model.Usuario;

import java.util.Scanner;

public class PagarMulta implements Command{

    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO udao = UsuarioDAOProxy.getInstance();
        MultaDAO mdao = MultaDAOProxy.getInstance();

        System.out.println("Entre com o id do usuário:");
        Usuario usuario = udao.getUsuario(entrada.nextInt());
        entrada.nextLine();

        if(!(usuario.getMultas().isEmpty())){
            for(Multa m : usuario.getMultas()){
                System.out.println(m);
            }

            System.out.println("Entre com o id da multa a pagar:");
            mdao.remove(entrada.nextInt());
            entrada.nextLine();

            System.out.println("Multa paga com sucesso!");
        }else {
            System.out.println("Não há multas a pagar!");
        }

        entrada.nextLine();
    }
}