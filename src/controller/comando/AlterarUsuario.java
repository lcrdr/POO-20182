package controller.comando;

import dao.UsuarioDAO;
import dao.proxy.UsuarioDAOProxy;
import model.Usuario;
import util.ComandosFlyweight;

import java.util.Scanner;

public class AlterarUsuario implements Command {
    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO dao = UsuarioDAOProxy.getInstance();
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

        do {

            System.out.println("Escolha a categoria do usuario:");
            System.out.println("1 - Professor \n2 - Funcionario \n3 - Aluno \n4 - Comunidade");
            usuario.setCategoriaUsuario(ComandosFlyweight.getInstance().getCategoria(entrada.nextInt()));

        } while (usuario.getCategoriaUsuario() == null);

        entrada.nextLine();
        dao.update(usuario);
    }
}
