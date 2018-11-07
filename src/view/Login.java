package view;

import controller.comando.MenuBibliotecario;
import controller.comando.MenuUsuario;
import dao.UsuarioDAO;
import model.Usuario;
import model.categoriausuario.Bibliotecario;
import util.Sessao;

import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        UsuarioDAO uDAO = new UsuarioDAO();

        uDAO.insertAdmin();

        Scanner entrada = new Scanner(System.in);
        Usuario user = new Usuario();
        boolean logado;

        do {
            System.out.println("Login: ");
            user.setLogin(entrada.nextLine());
            System.out.println("Senha: ");
            user.setSenha(entrada.nextLine());
            logado = uDAO.verificaLogin(user.getLogin(), user.getSenha());
            if (!logado)
                System.out.println("Usuario ou senha invalidos.");
        }while (!logado);

            user = uDAO.getUsuario(user.getLogin());
            Sessao.setUsuarioLogado(user);

            if (user.getCategoriaUsuario().getIdCategoria() == 5) {
                new MenuBibliotecario().execute(entrada);
            } else {
                new MenuUsuario().execute(entrada);
            }
    }
}
