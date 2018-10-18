package controller.comando;

import dao.UsuarioDAO;

import java.util.Scanner;

public class RemoverUsuario implements Command {
    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO dao = new UsuarioDAO();
        System.out.println("Entre com o código do usuário a ser removido:");
        dao.remove(entrada.nextInt());
    }
}
