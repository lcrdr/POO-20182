package controller.comando;

import dao.MultaDAO;
import dao.proxy.MultaDAOProxy;
import model.Multa;

import java.util.List;
import java.util.Scanner;

public class ConsultarMultas implements Command {

    @Override
    public void execute(Scanner entrada) {
        MultaDAO dao = MultaDAOProxy.getInstance();
        List<Multa> multas = dao.listMulta();
        for(Multa m : multas) {
            System.out.println(m);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }

}
