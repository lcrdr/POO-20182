package controller.comando;

import dao.UsuarioDAO;
import dao.proxy.UsuarioDAOProxy;

import java.util.Scanner;

public class RemoverUsuario implements Command {
    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO dao = UsuarioDAOProxy.getInstance();
        System.out.println("Entre com o código do usuário a ser removido:");
        dao.remove(entrada.nextInt());
        entrada.nextLine();
    }
}
