package controller.comando;

import dao.UsuarioDAO;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class ConsultarUsuarios implements Command {

    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.listUsuarios();
        for(Usuario u : usuarios) {
            System.out.println(u);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
