package controller.comando;

import dao.UsuarioDAO;
import dao.proxy.UsuarioDAOProxy;
import model.Usuario;
import model.categoriausuario.*;
import util.ComandosFlyweight;

import java.util.Hashtable;
import java.util.Scanner;

public class CadastrarUsuario implements Command {

    @Override
    public void execute(Scanner entrada) {
        UsuarioDAO dao = UsuarioDAOProxy.getInstance();
        Usuario usuario = new Usuario();
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
            System.out.println("1 - Professor \n2 - Funcionario \n3 - Aluno \n4 - Comunidade \n5 - Bibliotecario");
            usuario.setCategoriaUsuario(ComandosFlyweight.getInstance().getCategoria(entrada.nextInt()));
            entrada.nextLine();

        } while (usuario.getCategoriaUsuario() == null);



        String login;

        do {
            System.out.println("Digite o login:");
            login = entrada.nextLine();
            if(dao.verificaLogin(login))
                System.out.println("Este usuario já esta em uso, digite outro.");
        } while (dao.verificaLogin(login));

        usuario.setLogin(login);
        String senha;
        do {
            System.out.println("Digite a senha:");
            usuario.setSenha(entrada.nextLine());

            System.out.println("Confirme sua senha:");
            senha = entrada.nextLine();

            if (!(senha.equals(usuario.getSenha())))
                System.out.println("As senhas não conferem, digite novamente.");
        } while (!(senha.equals(usuario.getSenha())));
        dao.insert(usuario);
        System.out.println("Usuario cadastrado com sucesso.");
    }
}