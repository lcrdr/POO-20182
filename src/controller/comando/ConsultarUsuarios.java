package controller.comando;

import dao.UsuarioDAO;
import dao.proxy.UsuarioDAOProxy;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class ConsultarUsuarios implements Command {

    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO dao = UsuarioDAOProxy.getInstance();
        List<Usuario> usuarios = dao.listUsuario();
        for(Usuario u : usuarios) {
            System.out.println(u);
        }
        System.out.println("Pressione enter para continuar...");
        entrada.nextLine();
    }
}
