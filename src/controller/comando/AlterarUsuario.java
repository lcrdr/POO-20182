package controller.comando;

import dao.CategoriaUsuarioDAO;
import dao.UsuarioDAO;
import model.Usuario;

import java.util.Scanner;

public class AlterarUsuario implements Command {
    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO dao = new UsuarioDAO();
        System.out.println("Entre com o código do usuário a ser editado:");
        Usuario usuario = dao.getUsuario(entrada.nextInt());
        entrada.nextLine();
        System.out.println("Entre com o nome do usuário:");
        usuario.setNome(entrada.nextLine());
        System.out.println("Entre com o sexo do usuário (M/F):");
        if (entrada.nextLine().toUpperCase().equals("M"))
            usuario.setSexo(true);
        else
            usuario.setSexo(false);
        System.out.println("Entre com o endereço do usuário:");
        usuario.setEndereco(entrada.nextLine());
        System.out.println("Entre com o telefone do usuário:");
        usuario.setTelefone(entrada.nextLine());
        System.out.println("Entre com o código da categoria do usuário:");
        CategoriaUsuarioDAO cdao = new CategoriaUsuarioDAO();
        usuario.setCategoria(cdao.getCategoria(entrada.nextInt()));
        entrada.nextLine();
        dao.update(usuario);
    }
}
